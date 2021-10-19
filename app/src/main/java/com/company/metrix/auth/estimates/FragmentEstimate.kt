package com.company.metrix.auth.estimates

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.company.metrix.R
import com.company.metrix.auth.AuthHandler
import com.company.metrix.databinding.FragmentEstimateBinding
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.ui.servicesEmployee.CharacteristicAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

enum class Skills(val skillName: String) {
    POLITE("Вежливость "), MOBILE("Мобильность"), PROF("Профессионализм"),
    SPEED("Скорость"), FRIENDLY("Дружелюбность")
}

@AndroidEntryPoint
class FragmentEstimate() : Fragment() {

    private val viewModel: EstimateViewModel by viewModels()

    private lateinit var characteristicsDatabase: DatabaseReference
    private lateinit var usersDatabase: DatabaseReference

    private var userRating: Double = 5.0
    private var userComment: String = ""
    private val characteristicsList = mutableListOf<String>()

    companion object {
        fun newInstance(authHandler: AuthHandler): Fragment {
            return FragmentEstimate(authHandler)
        }
    }

    private constructor(authHandler: AuthHandler) : this() {
        this.authHandler = authHandler
    }

    private var authHandler: AuthHandler? = null
    private lateinit var binding: FragmentEstimateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEstimateBinding.inflate(inflater)

        val adapter = CharacteristicAdapter(mutableListOf()) { id, isSelected ->
            if (isSelected) {

                if (id in characteristicsList) characteristicsList.remove(id)
            } else {
                Log.d("test_test", "onCreateView: ${id}")
                characteristicsList.add(Skills.values()[id.toInt()].skillName)
            }
        }
        binding.strengthsView.adapter = adapter

        usersDatabase = Firebase.database.reference.child("users")
        characteristicsDatabase = Firebase.database.reference.child("characteristics")

        val characteristicsValueListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: MutableList<CharacteristicInfo> = ArrayList()
                for (ds in dataSnapshot.children) {
                    val characteristic: CharacteristicInfo? =
                        ds.getValue(CharacteristicInfo::class.java)
                    if (characteristic != null) {
                        list.add(characteristic)
                    }
                }
                binding.loadingBar.visibility = View.INVISIBLE
                adapter.setData(list)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.database_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        characteristicsDatabase.addListenerForSingleValueEvent(characteristicsValueListener)

        binding.buttonConfirmEstimate.setOnClickListener { onSubmit() }

        binding.ratingBar.onRatingBarChangeListener =
            OnRatingBarChangeListener { _, rating, _ ->
                if (rating <= 0.5) {
                    binding.ratingBar.rating = 1.0f
                }
            }

        binding.sentButton.setOnClickListener {
            binding.sentButton.visibility = View.INVISIBLE
            binding.sentEmoji.visibility = View.INVISIBLE
            binding.sentTitle.visibility = View.INVISIBLE
            binding.loadingBackground.visibility = View.INVISIBLE
            binding.buttonConfirmEstimate.visibility = View.VISIBLE
        }

        return binding.root
    }

    private fun onSubmit() {
        showLoading()
        hideError()

        val userId = binding.employeeIdField.text.toString().trim()
        userRating = binding.ratingBar.rating.toDouble()
        userComment = binding.employeeCommentField.text.toString().trim()
        usersDatabase.orderByChild("id").equalTo(userId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.children.iterator().hasNext()) {
                    sendFeedback(userId)
                    onSent()
                } else {
                    setError()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                setError()
            }
        })
    }

    private fun onSent() {
        binding.sentButton.visibility = View.VISIBLE
        binding.sentEmoji.visibility = View.VISIBLE
        binding.sentTitle.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
    }

    private fun hideError() {
        binding.employeeIdBlock.error = ""
    }

    private fun setError() {
        binding.employeeIdBlock.error = getString(R.string.id_not_found)
        hideLoading()
    }

    private fun sendFeedback(userId: String) {
        viewModel.viewModelScope.launch {
            viewModel.sendFeedback(userId, characteristicsList, userComment, userRating)
        }
    }

    private fun showLoading() {
        binding.loadingBackground.visibility = View.VISIBLE
        binding.loadingBar.visibility = View.VISIBLE
        binding.buttonConfirmEstimate.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        binding.loadingBackground.visibility = View.INVISIBLE
        binding.loadingBar.visibility = View.INVISIBLE
        binding.buttonConfirmEstimate.visibility = View.VISIBLE
    }

//    private fun onSubmit() {
//        val userId = binding.employeeIdField.text.toString().trim()
//        userRating = binding.ratingBar.rating.toDouble()
//        userComment = binding.employeeCommentField.text.toString().trim()
//        usersDatabase.orderByChild("id").equalTo(userId).addListenerForSingleValueEvent(object :
//            ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.children.iterator().hasNext()) {
//                    sendFeedback(snapshot.children.iterator().next())
//                } else {
//                    setError()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                setError()
//            }
//        })
//    }
//
//    private fun setError() {
//        binding.employeeIdBlock.error = getString(R.string.id_not_found)
//    }
//
//    private fun sendFeedback(userSnapshot: DataSnapshot) {
//        binding.loadingBackground.visibility = View.VISIBLE
//        binding.buttonConfirmEstimate.visibility = View.INVISIBLE
//
//        val userDatabase = usersDatabase.child(userSnapshot.key!!)
//        val ratings: MutableList<Double> =
//            userSnapshot.child("ratings").getValue<MutableList<Double>>() ?: mutableListOf()
//        val strongSkills: MutableList<String> =
//            userSnapshot.child("strongSkills").getValue<MutableList<String>>() ?: mutableListOf()
//        val comments: MutableList<String> =
//            userSnapshot.child("comments").getValue<MutableList<String>>() ?: mutableListOf()
//        ratings.add(userRating)
//        if (characteristicsList.size > 0) {
//            strongSkills.addAll(characteristicsList)
//            userDatabase.child("strongSkills").setValue(strongSkills)
//        }
//        if (userComment != "") {
//            comments.add(userComment)
//            userDatabase.child("comments").setValue(comments)
//        }
//        userDatabase.child("ratings").setValue(ratings)
//
//        binding.sentButton.visibility = View.VISIBLE
//        binding.sentImage.visibility = View.VISIBLE
//        binding.sentTitle.visibility = View.VISIBLE
//    }

}
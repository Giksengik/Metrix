package com.company.metrix.ui.servicesEmployee.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.metrix.BackButtonHandler
import com.company.metrix.R
import com.company.metrix.data.model.AwardInfo
import com.company.metrix.data.model.AwardNomination
import com.company.metrix.data.model.AwardType
import com.company.metrix.databinding.FragmentAwardsBinding
import com.company.metrix.ui.support.setupNavigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAwards : Fragment(), BackButtonHandler {

    private var _binding: FragmentAwardsBinding? = null
    private val binding get() = _binding!!

    private var awardsAdapter: AwardsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().setupNavigation(this, FragmentAwardsDirections.actionFragmentAwardsToServiceFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwardsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupOnBackButtonPressed()
        setupAwards()
        loadData()
    }

    override fun setupOnBackButtonPressed() {
        binding.backButton.setOnClickListener {
            val action = FragmentAwardsDirections.actionFragmentAwardsToServiceFragment()
            findNavController().navigate(action)
        }
    }

    private fun loadData() {
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val awards: MutableMap<String, List<String>> =
                            snapshot.children.iterator().next().child("awards")
                                .getValue<MutableMap<String, List<String>>>() ?: mutableMapOf()
                        updateAwardsView(awards)
                    } else {
                        showErrorToast()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    showErrorToast()
                }
            })
    }

    private fun updateAwardsView(awards: MutableMap<String, List<String>>) {
        val list = mutableListOf(
            AwardInfo(
                "???????????? ?? ??????????????",
                nominations = listOf(
                    AwardNomination(
                        type = AwardType.CUP,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.FIRST_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.SECOND_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.THIRD_PLACE,
                        isReceived = false
                    )
                )
            ),
            AwardInfo(
                "?????????? ??????????????",
                nominations = listOf(
                    AwardNomination(
                        type = AwardType.CUP,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.FIRST_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.SECOND_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.THIRD_PLACE,
                        isReceived = false
                    )
                )
            ),
            AwardInfo(
                "???????? ????????????????????",
                nominations = listOf(
                    AwardNomination(
                        type = AwardType.CUP,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.FIRST_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.SECOND_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.THIRD_PLACE,
                        isReceived = false
                    )
                )
            ),
            AwardInfo(
                "???????????????? ????????????????????????",
                nominations = listOf(
                    AwardNomination(
                        type = AwardType.CUP,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.FIRST_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.SECOND_PLACE,
                        isReceived = false
                    ),
                    AwardNomination(
                        type = AwardType.THIRD_PLACE,
                        isReceived = false
                    )
                )
            )
        )
        awards.forEach { entry ->
            val awardInfoIndex = list.indexOfFirst { it.name == entry.key }
            val nominations = list[awardInfoIndex].nominations
            entry.value.forEach { value ->
                val awardNominationIndex = nominations.indexOfFirst { it.type.title == value }
                nominations[awardNominationIndex].isReceived = true
            }
        }
        awardsAdapter?.submitList(list)
        binding.loadingBar.visibility = View.INVISIBLE
        binding.awardsContent.visibility = View.VISIBLE
    }

    private fun showErrorToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.database_error),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupAwards() {
        awardsAdapter = AwardsListAdapter()
        binding.awardsList.apply {
            adapter = awardsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        setupDummyAwards()
    }

    private fun setupDummyAwards() {

    }
}
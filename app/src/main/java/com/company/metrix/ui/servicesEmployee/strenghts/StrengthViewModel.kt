package com.company.metrix.ui.servicesEmployee.strenghts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.LoadingState
import com.company.metrix.data.repository.EstimationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StrengthViewModel @Inject constructor(val estimationRepo: EstimationRepository) :
    ViewModel() {
    private val _estimations = MutableLiveData<List<Estimation>>()
    val estimations: LiveData<List<Estimation>> = _estimations
    private val strengthsList: MutableList<CharacteristicInfo> = mutableListOf()
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    suspend fun getNegativeFeedback() {
        //estimations.value = estimationRepo.getEstimationsByUserId(id).filter { it.rate >= 4.0 }
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val comments: MutableList<String> =
                            snapshot.children.iterator().next().child("comments")
                                .getValue<MutableList<String>>() ?: mutableListOf()
                        val list = comments.map {
                            Estimation(
                                id = (comments.size + 1).toLong(),
                                comment = it,
                                user_id = user.email!!,
                                reviewer_id = 0,
                                rate = 5.0,
                                skillName = "Рекомендация")
                        }
                        _estimations.postValue(list)
                    } else {
                        _estimations.postValue(listOf())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _estimations.postValue(listOf())
                }
            })

    }

    suspend fun getPositiveFeedback() {
        //estimations.value = estimationRepo.getEstimationsByUserId(id).filter { it.rate < 4.0 }
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val strengths: MutableList<String> =
                            snapshot.children.iterator().next().child("strongSkills")
                                .getValue<MutableList<String>>() ?: mutableListOf()
                        val topStrengths = mutableListOf<CharacteristicInfo>()
                        val sortedStrengths = strengths.sortedByDescending { it }.distinct().reversed()
                        var i = 0
                        while (topStrengths.size < 3 && i < sortedStrengths.size) {
                            val characteristic = strengthsList.find { it.textShort == sortedStrengths[i] }
                            if (characteristic != null) topStrengths.add(characteristic)
                            i += 1
                        }
                        val list = topStrengths.map {
                            Estimation(
                                id = (topStrengths.size + 1).toLong(),
                                comment = it.text,
                                user_id = user.email!!,
                                reviewer_id = 0,
                                rate = 5.0,
                                skillName = it.textShort)
                        }
                        _estimations.postValue(list)
                    } else {
                        _estimations.postValue(listOf())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _estimations.postValue(listOf())
                }
            })
    }

    fun loadSkills() {
        val  characteristicsDatabase = Firebase.database.reference.child("characteristics")
        val characteristicsValueListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list: MutableList<CharacteristicInfo> = ArrayList()
                for (ds in dataSnapshot.children) {
                    val characteristic: CharacteristicInfo? =
                        ds.getValue(CharacteristicInfo::class.java)
                    if (characteristic != null) list.add(characteristic)
                }
                strengthsList.addAll(list)
                _loadingState.postValue(LoadingState.RECEIVING_SUCCESS)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                _loadingState.postValue(LoadingState.RECEIVING_ERROR)
            }
        }
        characteristicsDatabase.addListenerForSingleValueEvent(characteristicsValueListener)
    }

}
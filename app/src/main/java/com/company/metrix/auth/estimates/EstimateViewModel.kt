package com.company.metrix.auth.estimates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.CharacteristicInfo
import com.company.metrix.data.model.LoadingState
import com.company.metrix.data.repository.EstimationRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EstimateViewModel @Inject constructor(val estimateRepository: EstimationRepository) :
    ViewModel() {
    private val _allData = MutableLiveData<List<CharacteristicInfo>>()
    val allData: LiveData<List<CharacteristicInfo>> = _allData
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    private val usersDatabase = Firebase.database.reference.child("users")
    private val characteristicsDatabase = Firebase.database.reference.child("characteristics")

    private var stubId: Any = Any();

    fun loadData() {
        _loadingState.postValue(LoadingState.LOADING)
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
                _allData.postValue(list)
                _loadingState.postValue(LoadingState.RECEIVING_SUCCESS)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                _loadingState.postValue(LoadingState.RECEIVING_ERROR)
            }
        }
        characteristicsDatabase.addListenerForSingleValueEvent(characteristicsValueListener)
    }

    fun sendFeedback(id: String, list: MutableList<String>, comment: String, rate: Double) {
        // stubId = Any();
        // estimateRepository.addEstimation(Estimation(stubId.hashCode().toLong(), comment, id,2, rate, est))
        _loadingState.postValue(LoadingState.LOADING)
        usersDatabase.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.children.iterator().hasNext()) {
                    for (est in list) {
                            val userSnapshot = snapshot.children.iterator().next()
                        val userDatabase = usersDatabase.child(userSnapshot.key!!)
                        val ratings: MutableList<Double> =
                            userSnapshot.child("ratings").getValue<MutableList<Double>>() ?: mutableListOf()
                        val strongSkills: MutableList<String> =
                            userSnapshot.child("strongSkills").getValue<MutableList<String>>()
                                ?: mutableListOf()
                        val comments: MutableList<String> =
                            userSnapshot.child("comments").getValue<MutableList<String>>() ?: mutableListOf()
                        ratings.add(rate)
                        if (list.size > 0) {
                            strongSkills.addAll(list)
                            userDatabase.child("strongSkills").setValue(strongSkills)
                        }
                        if (comment != "") {
                            comments.add(comment)
                            userDatabase.child("comments").setValue(comments)
                        }
                        userDatabase.child("ratings").setValue(ratings)
                    }
                    _loadingState.postValue(LoadingState.SENDING_SUCCESS)
                } else {
                    _loadingState.postValue(LoadingState.SENDING_ERROR)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                _loadingState.postValue(LoadingState.SENDING_ERROR)
            }
        })

    }

}
package com.company.metrix.ui.servicesEmployee.rating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
class RatingViewModel @Inject constructor(val repository: EstimationRepository) : ViewModel() {

    private val _allData = MutableLiveData<List<Double>>()
    val allData: LiveData<List<Double>> = _allData

    val rating: MutableLiveData<List<Double>> = MutableLiveData<List<Double>>()
    
    fun loadData() {
        val user = Firebase.auth.currentUser!!
        Firebase.database.reference.child("users").orderByChild("id").equalTo(user.email)
            .addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.children.iterator().hasNext()) {
                        val ratings: MutableList<Double> =
                            snapshot.children.iterator().next().child("ratings")
                                .getValue<MutableList<Double>>() ?: mutableListOf()
                        _allData.postValue(ratings)
                    } else {
                        _allData.postValue(listOf())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    _allData.postValue(listOf())
                }
            })
    }

    suspend fun calculateRating() {
        val list = repository.getAllEstimations()
        val rate: MutableList<Double> = arrayListOf()

        for (est in list)
            rate.add(est.rate)

        rating.value = rate
    }
}
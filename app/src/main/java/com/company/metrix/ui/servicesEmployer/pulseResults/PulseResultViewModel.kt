package com.company.metrix.ui.servicesEmployer.pulseResults

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.metrix.data.repository.PulseRepository
import com.company.metrix.data.repository.UserRepository
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
class PulseResultViewModel @Inject constructor(
    private val pulseRepo: PulseRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    val percent1Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent2Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent3Q1: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent4Q1: MutableLiveData<Int> = MutableLiveData<Int>()

    val percent1Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent2Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent3Q2: MutableLiveData<Int> = MutableLiveData<Int>()
    val percent4Q2: MutableLiveData<Int> = MutableLiveData<Int>()

    suspend fun getTeamPulseResults(teamId: Long) {
        val user = userRepo.getUserByEmail(Firebase.auth.currentUser?.email!!)
        //val results1 = pulseRepo.getPulseByCompanyAndIdQuestion(user.companyName, 1, teamId)
        //val results2 = pulseRepo.getPulseByCompanyAndIdQuestion(user.companyName, 1, teamId)


        Firebase.database.reference.child("pulse").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val iterator = snapshot.children.iterator()
                if (iterator.hasNext()) {
                    val results1 = iterator.next().getValue<MutableList<Int>>() ?: mutableListOf()
                    val results2 = iterator.next().getValue<MutableList<Int>>() ?: mutableListOf()

                    val votesOneQ1 = results1.count { it == 1 }
                    val votesTwoQ1 = results1.count { it == 2 }
                    val votesThreeQ1 = results1.count { it == 3 }
                    val votesFourQ1 = results1.count { it == 4 }

                    val votesOneQ2 = results2.count { it == 1 }
                    val votesTwoQ2 = results2.count { it == 2 }
                    val votesThreeQ2 = results2.count { it == 3 }
                    val votesFourQ2 = results2.count { it == 4 }

                    val totalRes1 = votesOneQ1 + votesTwoQ1 + votesThreeQ1 + votesFourQ1
                    val totalRes2 = votesOneQ2 + votesTwoQ2 + votesThreeQ2 + votesFourQ2

                    if (totalRes1 != 0) {
                        results1.also {
                            var one = votesOneQ1 * 100 / totalRes1
                            var two = votesTwoQ1 * 100 / totalRes1
                            var three = votesThreeQ1 * 100 / totalRes1
                            var four = votesFourQ1 * 100 / totalRes1

                            if (one == 100) one -= 10
                            if (two == 100) two -= 10
                            if (three == 100) three -= 10
                            if (four == 100) four -= 10

                            percent1Q1.value = if (one != 0) one else 10
                            percent2Q1.value = if (two != 0) two else 10
                            percent3Q1.value = if (three != 0) three else 10
                            percent4Q1.value = if (four != 0) four else 10
                        }
                    }

                    if (totalRes2 != 0) {
                        results2.also {
                            var one = votesOneQ2 * 100 / totalRes2
                            var two = votesTwoQ2 * 100 / totalRes2
                            var three = votesThreeQ2 * 100 / totalRes2
                            var four = votesFourQ2 * 100 / totalRes2

                            if (one == 100) one -= 10
                            if (two == 100) two -= 10
                            if (three == 100) three -= 10
                            if (four == 100) four -= 10

                            percent1Q2.value = if (one != 0) one else 10
                            percent2Q2.value = if (two != 0) two else 10
                            percent3Q2.value = if (three != 0) three else 10
                            percent4Q2.value = if (four != 0) four else 10
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        /*val totalRes1 =
            results1.votesOne + results1.votesTwo + results1.votesThree + results1.votesFour
        val totalRes2 =
            results2.votesOne + results2.votesTwo + results2.votesThree + results2.votesFour

        if (totalRes1 != 0) {
            results1.also {
                percent1Q1.value = it.votesOne * 100 / totalRes1
                percent2Q1.value = it.votesTwo * 100 / totalRes1
                percent3Q1.value = it.votesThree * 100 / totalRes1
                percent4Q1.value = it.votesFour * 100 / totalRes1
            }
        }

        if (totalRes2 != 0) {
            results2.also {
                percent1Q2.value = it.votesOne * 100 / totalRes2
                percent2Q2.value = it.votesTwo * 100 / totalRes2
                percent3Q2.value = it.votesThree * 100 / totalRes2
                percent4Q2.value = it.votesFour * 100 / totalRes2
            }
        }*/
    }
}
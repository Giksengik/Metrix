package com.company.metrix.ui.pulse

import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Pulse
import com.company.metrix.data.repository.PulseRepository
import com.company.metrix.data.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PulseViewModel @Inject constructor(
    private val pulseRepository: PulseRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    suspend fun updateVotes(number: Long, positionIn: Int) {
        val user = userRepository.getUserByEmail(Firebase.auth.currentUser?.email!!)
        when (positionIn) {
            1 -> {
                //TODO спиннер с выбором команды из существующих
                val currentPulse = pulseRepository.getPulseByCompanyAndIdQuestion(user.companyName, number, 1)
                val newPulse = Pulse(
                    id = currentPulse.id,
                    question_id = number,
                    team_id = currentPulse.team_id,
                    companyName = currentPulse.companyName,
                    votesOne = (currentPulse.votesOne + 1),
                    votesTwo = currentPulse.votesTwo,
                    votesThree = currentPulse.votesThree,
                    votesFour = currentPulse.votesFour
                )

                val pulss = newPulse
                pulseRepository.updatePulse(
                    newPulse
                )
            }

            2 -> {
                val currentPulse = pulseRepository.getPulseByCompanyAndIdQuestion(user.companyName, number, 1)
                pulseRepository.updatePulse(
                    Pulse(
                        id = currentPulse.id,
                        question_id = number,
                        team_id = currentPulse.team_id,
                        companyName = currentPulse.companyName,
                        votesOne = currentPulse.votesOne,
                        votesTwo = (currentPulse.votesTwo + 1),
                        votesThree = currentPulse.votesThree,
                        votesFour = currentPulse.votesFour
                    )
                )
            }

            3 -> {
                val currentPulse = pulseRepository.getPulseByCompanyAndIdQuestion(user.companyName, number, 1)
                pulseRepository.updatePulse(
                    Pulse(
                        id = currentPulse.id,
                        question_id = number,
                        team_id = currentPulse.team_id,
                        companyName = currentPulse.companyName,
                        votesOne = currentPulse.votesOne,
                        votesTwo = currentPulse.votesTwo,
                        votesThree = (currentPulse.votesThree + 1),
                        votesFour = currentPulse.votesFour
                    )
                )
            }

            4 -> {
                val currentPulse = pulseRepository.getPulseByCompanyAndIdQuestion(user.companyName, number, 1)
                pulseRepository.updatePulse(
                    Pulse(
                        id = currentPulse.id,
                        question_id = number,
                        team_id = currentPulse.team_id,
                        companyName = currentPulse.companyName,
                        votesOne = currentPulse.votesOne,
                        votesTwo = currentPulse.votesTwo,
                        votesThree = currentPulse.votesThree,
                        votesFour = (currentPulse.votesFour + 1)
                    )
                )
            }

        }
    }

    //TODO to initial stub
    suspend fun addPulseStatistics(pulse: Pulse) {
        pulseRepository.insertPulse(
            pulse
        )
    }

}
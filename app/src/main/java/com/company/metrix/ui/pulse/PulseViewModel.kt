package com.company.metrix.ui.pulse

import androidx.lifecycle.ViewModel
import com.company.metrix.data.model.Pulse
import com.company.metrix.data.repository.PulseRepository
import com.company.metrix.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PulseViewModel @Inject constructor(private val pulseRepository: PulseRepository) : ViewModel() {

    suspend fun updateVotes(companyName: String, number: Long) {
        when (number) {
            1L -> {
                val currentPulse = pulseRepository.getPulseByCompany(companyName)
                pulseRepository.updatePulse(
                    Pulse(
                        question_id = number,
                        team_id = currentPulse.team_id,
                        companyName = currentPulse.companyName,
                        votesOne = (currentPulse.votesOne + 1),
                        votesTwo = currentPulse.votesTwo,
                        votesThree = currentPulse.votesThree,
                        votesFour = currentPulse.votesFour
                    )
                )
            }

            2L -> {
                val currentPulse = pulseRepository.getPulseByCompany(companyName)
                pulseRepository.updatePulse(
                    Pulse(
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

            3L -> {
                val currentPulse = pulseRepository.getPulseByCompany(companyName)
                pulseRepository.updatePulse(
                    Pulse(
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

            4L -> {
                val currentPulse = pulseRepository.getPulseByCompany(companyName)
                pulseRepository.updatePulse(
                    Pulse(
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
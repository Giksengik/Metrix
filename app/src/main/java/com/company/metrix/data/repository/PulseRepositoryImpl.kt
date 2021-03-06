package com.company.metrix.data.repository

import com.company.metrix.data.model.Pulse
import com.company.metrix.dataProvider.LocalPulseDataProvider
import javax.inject.Inject

class PulseRepositoryImpl @Inject constructor(
    private val localPulseProvider: LocalPulseDataProvider
) : PulseRepository {

    override suspend fun insertPulse(item: Pulse) = localPulseProvider.insertPulse(item)

    override suspend fun getAllPulse(): List<Pulse> = localPulseProvider.getAllPulse()

    override suspend fun getPulseByCompany(companyName: String): Pulse =
        localPulseProvider.getPulseByCompany(companyName) // localPulseProvider.getPulseByTeamId(team_id)

    override suspend fun deletePulse(item: Pulse) = localPulseProvider.deletePulse(item)

    override suspend fun updatePulse(item: Pulse) = localPulseProvider.updatePulse(item)
    override suspend fun getPulseByCompanyAndIdQuestion(companyName: String, question_id: Long, team_id : Long): Pulse =
        localPulseProvider.getPulseByCompanyAndIdQuestion(companyName, question_id, team_id )
}
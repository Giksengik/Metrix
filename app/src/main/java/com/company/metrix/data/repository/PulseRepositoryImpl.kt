package com.company.metrix.data.repository

import com.company.metrix.data.model.Pulse
import com.company.metrix.dataProvider.LocalEstimationDataProvider
import com.company.metrix.dataProvider.LocalPulseDataProvider
import javax.inject.Inject

class PulseRepositoryImpl @Inject constructor(
    private val localPulseProvider: LocalPulseDataProvider
) : PulseRepository {

    override suspend fun insertPulse(item: Pulse) = localPulseProvider.insertPulse(item)

    override suspend fun getAllPulse(): List<Pulse> = localPulseProvider.getAllPulse()

    override suspend fun getPulseByTeamId(team_id: Long): List<Pulse> = localPulseProvider.getPulseByTeamId(team_id)

    override suspend fun deletePulse(item: Pulse) = localPulseProvider.deletePulse(item)

    override suspend fun updatePulse(item: Pulse) = localPulseProvider.updatePulse(item)
}
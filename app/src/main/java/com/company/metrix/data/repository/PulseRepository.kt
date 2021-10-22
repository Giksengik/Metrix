package com.company.metrix.data.repository

import com.company.metrix.data.model.Pulse

interface PulseRepository {
    suspend fun insertPulse(item: Pulse)
    suspend fun getAllPulse(): List<Pulse>
    suspend fun getPulseByCompany(companyName: String): Pulse
    suspend fun deletePulse(item: Pulse)
    suspend fun updatePulse(item: Pulse)
}
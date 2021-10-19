package com.company.metrix.dataProvider

import com.company.metrix.data.model.Pulse
import com.company.metrix.db.entity.PulseEntity

interface LocalPulseDataProvider {
    suspend fun insertPulse(item: Pulse)
    suspend fun getAllPulse(): List<Pulse>
    suspend fun getPulseByCompany(companyName : String): Pulse
    suspend fun deletePulse(item: Pulse)
    suspend fun updatePulse(item: Pulse)
}
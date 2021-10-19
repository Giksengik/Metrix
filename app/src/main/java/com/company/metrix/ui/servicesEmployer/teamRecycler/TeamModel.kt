package com.company.metrix.ui.servicesEmployer.teamRecycler

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamModel(
    val teamName: String,
    val peopleCount: String,
    val teamId: Long,
    val companyName : String
) : Parcelable
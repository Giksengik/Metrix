package com.company.metrix.ui.servicesEmployer.teams.recyclerEmployee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeModel(
    val userName : String,
    val role : String
) : Parcelable
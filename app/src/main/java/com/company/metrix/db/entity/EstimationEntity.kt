package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estimation")
data class EstimationEntity(
    @PrimaryKey(autoGenerate = true)
    val estimation_id : Long,
    val user_id : Long,
    val reviewer_id : Long,
    val polite : Double,
    val mobile : Double,
    val professional : Double,
    val speed : Double,
    val friendly : Double,
    val comment: String
)
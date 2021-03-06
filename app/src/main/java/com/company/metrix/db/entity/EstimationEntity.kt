package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estimation")
data class EstimationEntity(
    @PrimaryKey(autoGenerate = true)
    val estimate_id : Long,
    val comment: String,
    val user_id : String,
    val reviewer_id : Long,
    val rate : Double,
    val skillName : String
 )
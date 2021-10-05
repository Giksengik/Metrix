package com.company.metrix.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estimation")
data class EstimationEntity(
    @PrimaryKey(autoGenerate = true)
    val estimate_id : Long,
    val comment: String,
    val user_id : Long,
    val reviewer_id : Long,
    val rate : Double,
    val skillName : String
//    val mobile : Double,
//    val professional : Double,
//    val speed : Double,
//    val friendly : Double,
 )
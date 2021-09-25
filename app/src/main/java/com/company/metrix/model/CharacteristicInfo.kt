package com.company.metrix.model

data class CharacteristicInfo(
    val emoji : String,
    val text : String,
    // CONTRACT
    // > 0 - STRENGTH
    // < 0 - WEAKNESS
    // 0 - NORM
    val strengthStatus : Int
)
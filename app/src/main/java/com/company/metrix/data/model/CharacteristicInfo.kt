package com.company.metrix.data.model

data class CharacteristicInfo(
    val id : String = "",
    val emoji : String = "",
    val text : String = "",
    val textShort: String = "",
    // CONTRACT
    // > 0 - STRENGTH
    // < 0 - WEAKNESS
    // 0 - NORM
    val strengthStatus : Int = 0
)
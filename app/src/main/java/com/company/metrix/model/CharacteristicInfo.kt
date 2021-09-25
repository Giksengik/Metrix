package com.company.metrix.model

data class CharacteristicInfo(
    val iconId : Int,
    val textId : Int,
    // CONTRACT
    // > 0 - STRENGTH
    // < 0 - WEAKNESS
    // 0 - NORM
    val strengthStatus : Int
)
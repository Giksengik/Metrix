package com.company.metrix.ui.services.strenghts

import com.company.metrix.data.model.CharacteristicInfo

interface CharacteristicFactory {
    fun produceAllStrengths() : List<CharacteristicInfo>
    fun produceAllWeakness() : List<CharacteristicInfo>
}
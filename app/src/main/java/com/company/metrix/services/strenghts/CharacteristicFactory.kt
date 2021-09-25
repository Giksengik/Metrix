package com.company.metrix.services.strenghts

import com.company.metrix.model.CharacteristicInfo

interface CharacteristicFactory {
    fun produceAllStrengths() : List<CharacteristicInfo>
    fun produceAllWeakness() : List<CharacteristicInfo>
}
package com.company.metrix.ui.servicesEmployee.strenghts

import com.company.metrix.data.model.CharacteristicInfo

interface CharacteristicFactory {
    fun produceAllStrengths() : List<CharacteristicInfo>
    fun produceAllWeakness() : List<CharacteristicInfo>
}
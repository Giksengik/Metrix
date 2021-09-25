package com.company.metrix.services.strenghts

import com.company.metrix.R
import com.company.metrix.model.CharacteristicInfo

class CharacteristicFactoryImpl : CharacteristicFactory {
    override fun produceAllStrengths(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                iconId = R.drawable.ic_politeness,
                textId = R.string.politness_strength_description,
                strengthStatus = 1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_mobilnost,
                textId = R.string.mobilnost_strength_description,
                strengthStatus = 1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_profi,
                textId = R.string.profi_strength_description,
                strengthStatus = 1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_speed,
                textId = R.string.speed_strength_description,
                strengthStatus = 1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_friendliness,
                textId = R.string.friendliness_strength_description,
                strengthStatus = 1
            )
        )

    override fun produceAllWeakness(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                iconId = R.drawable.ic_politeness,
                textId = R.string.politness_weakness_description,
                strengthStatus = -1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_mobilnost,
                textId = R.string.mobilnost_weakness_description,
                strengthStatus = -1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_profi,
                textId = R.string.profi_weakness_description,
                strengthStatus = -1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_speed,
                textId = R.string.speed_weakness_description,
                strengthStatus = -1
            ),
            CharacteristicInfo(
                iconId = R.drawable.ic_friendliness,
                textId = R.string.friendliness_weakness_description,
                strengthStatus = -1
            )
        )
}
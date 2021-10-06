package com.company.metrix.ui.servicesEmployee.strenghts

import com.company.metrix.data.model.CharacteristicInfo

class CharacteristicFactoryImpl : CharacteristicFactory {
    override fun produceAllStrengths(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                id = "0",
                emoji = "😁",
                text = "Вежливость. Вы внимательны к своим клиентам.",
                textShort = "Вежливость",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                id = "1",
                emoji = "📱",
                text = "Мобильность. Здорово, что вы всегда на связи!",
                textShort = "Мобильность",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                id = "2",
                emoji = "👍",
                text = "Профессионализм. Подходите к работе с трепетом, мы это ценим!",
                textShort = "Профессионализм",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                id = "3",
                emoji = "⌚",
                text = "Скорость. Вы выполняете свои задачи быстро.",
                textShort = "Скорость",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                id = "4",
                emoji = "🤗",
                text = "Дружелюбность. Клиенты отмечают вас, как дружелюбного человека.",
                textShort = "Дружелюбность",
                strengthStatus = 1
            )
        )

    override fun produceAllWeakness(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                id = "0",
                emoji = "😁",
                text = "Вежливость. Вы внимательны к своим клиентам.",
                textShort = "Вежливость",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                id = "1",
                emoji = "📱",
                text = "Мобильность. Здорово, что вы всегда на связи!",
                textShort = "Мобильность",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                id = "2",
                emoji = "👍",
                text = "Профессионализм. Подходите к работе с трепетом, мы это ценим!",
                textShort = "Профессионализм",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                id = "3",
                emoji = "⌚",
                text = "Скорость. Вы выполняете свои задачи быстро.",
                textShort = "Скорость",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                id = "4",
                emoji = "🤗",
                text = "Дружелюбность. Клиенты отмечают вас, как дружелюбного человека.",
                textShort = "Дружелюбность",
                strengthStatus = -1
            )
        )
}
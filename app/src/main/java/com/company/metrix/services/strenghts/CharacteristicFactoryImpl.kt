package com.company.metrix.services.strenghts

import com.company.metrix.R
import com.company.metrix.model.CharacteristicInfo

class CharacteristicFactoryImpl : CharacteristicFactory {
    override fun produceAllStrengths(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                emoji = "😁",
                text = "Вежливость. Вы внимательны к своим клиентам.",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                emoji = "📱",
                text = "Мобильность. Здорово, что вы всегда на связи!",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                emoji = "👍",
                text = "Профессионализм. Подходите к работе с трепетом, мы это ценим!",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                emoji = "⌚",
                text = "Скорость. Вы выполняете свои задачи быстро.",
                strengthStatus = 1
            ),
            CharacteristicInfo(
                emoji = "🤗",
                text = "Дружелюбность. Клиенты отмечают вас, как дружелюбного человека.",
                strengthStatus = 1
            )
        )

    override fun produceAllWeakness(): List<CharacteristicInfo> =
        listOf(
            CharacteristicInfo(
                emoji = "😁",
                text = "Вежливость. Некоторые клиенты недовольны вашими манерами.",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                emoji = "📱",
                text = "Мобильность. Клиенты отмечают, что иногда с вами сложно связаться.",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                emoji = "👍",
                text = "Профессионализм. Вам стоит поработать над своей квалификацией.",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                emoji = "⌚",
                text = "Скорость. Некоторые задачи можно выполнять быстрее.",
                strengthStatus = -1
            ),
            CharacteristicInfo(
                emoji = "🤗",
                text = "Дружелюбность. К сожалению, не все клиенты считают вас дружелюбным.",
                strengthStatus = -1
            )
        )
}
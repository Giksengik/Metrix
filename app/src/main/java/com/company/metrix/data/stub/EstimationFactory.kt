package com.company.metrix.data.stub

import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User

class EstimationFactory {
    fun getAllEstimations(): List<Estimation> =
        listOf(
            Estimation(
                id = 121,
                user_id = "feds.msc@gmail.com",
                reviewer_id = 2,
                comment = "Грубиян!",
                rate = 2.0,
                skillName = "Вежливость"
            ),
            Estimation(
                id = 22,
                user_id = "feds.msc@gmail.com",
                reviewer_id = 2,
                comment = "So slooow",
                rate = 2.0,
                skillName = "Скорость"
            ),
            Estimation(
                id = 1211,
                user_id = "feds.msc@gmail.com",
                reviewer_id = 3,
                rate = 3.0,
                skillName = "Профессионализм",
                comment = "Не особо профи"
            ),
            Estimation(
                id = 234,
                user_id = "feds.msc@gmail.com",
                reviewer_id = 1,
                rate = 1.0,
                skillName = "Мобильность",
                comment = "УЖАС"
            ),
            Estimation(
                id = 231234,
                user_id = "feds.msc@gmail.com",
                reviewer_id = 1,
                rate = 1.0,
                skillName = "Мобильность",
                comment = "УЖАС"
            )
        )
}
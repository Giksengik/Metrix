package com.company.metrix.data.stub

import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User

class EstimationFactory {
    fun getAllEstimations(): List<Estimation> =
        listOf(
            Estimation(
                user_id = 1,
                reviewer_id = 2,
                comment = "Грубиян!",
                rate = 4.0,
                skillName = "Вежливость"
            ),
            Estimation(
                user_id = 1,
                reviewer_id = 2,
                comment = "So slooow",
                rate = 2.0,
                skillName = "Скорость"
            ),
            Estimation(
                user_id = 1,
                reviewer_id = 3,
                rate = 3.0,
                skillName = "Профессионализм",
                comment = "Не особо профи"
            ),
            Estimation(
                user_id = 3,
                reviewer_id = 1,
                rate = 1.0,
                skillName = "Мобильность",
                comment = "УЖАС"
            )
        )
}
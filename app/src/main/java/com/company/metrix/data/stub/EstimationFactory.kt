package com.company.metrix.data.stub

import com.company.metrix.data.model.Estimation
import com.company.metrix.data.model.User

class EstimationFactory {
    fun getAllEstimations(): List<Estimation> =
        listOf(
            Estimation(
                user_id = 1,
                reviewer_id = 2,
                polite = 2.0,
                mobile = 3.0,
                professional = 4.0,
                speed = 5.0,
                friendly = 2.0,
                comment = "Так себе"
            ),
            Estimation(
                user_id = 3,
                reviewer_id = 2,
                polite = 5.0,
                mobile = 5.0,
                professional = 4.0,
                speed = 5.0,
                friendly = 4.0,
                comment = "Отличный сотрудник"
            ),
            Estimation(
                user_id = 2,
                reviewer_id = 3,
                polite = 4.0,
                mobile = 4.0,
                professional = 4.0,
                speed = 4.0,
                friendly = 4.0,
                comment = "Норм"
            ),
            Estimation(
                user_id = 3,
                reviewer_id = 1,
                polite = 2.0,
                mobile = 2.0,
                professional = 3.0,
                speed = 2.0,
                friendly = 3.0,
                comment = "УЖАС"
            )
        )
}
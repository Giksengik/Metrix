package com.company.metrix.data.model

import com.company.metrix.R

enum class AwardType(val iconId : Int, val title : String) {
    CUP(R.drawable.ic_award_cup_active, "cup"),
    FIRST_PLACE(R.drawable.ic_award_first_inactive, "first_place"),
    SECOND_PLACE(R.drawable.ic_award_first_inactive, "second_place"),
    THIRD_PLACE(R.drawable.ic_award_first_inactive, "third_place")
}
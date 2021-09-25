package com.company.metrix.model

import com.company.metrix.R

enum class AwardType(val iconId : Int, val title : String) {
    CUP(R.drawable.ic_cup_award, "cup"),
    FIRST_PLACE(R.drawable.ic_first_place_award, "first_place"),
    SECOND_PLACE(R.drawable.ic_second_place_award, "second_place"),
    THIRD_PLACE(R.drawable.ic_third_place_award, "third_place")
}
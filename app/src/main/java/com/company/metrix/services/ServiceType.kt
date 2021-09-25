package com.company.metrix.services

import com.company.metrix.R

enum class ServiceType(val iconId : Int, val textId : Int) {
    DIAGNOSTIC(R.drawable.diagnostics, R.string.diagnostic_text),
    RATING(R.drawable.rating, R.string.rating_text),
    STRENGTHS(R.drawable.strengths, R.string.strengths_text),
    RECOMENDATIONS(R.drawable.recommendations, R.string.recomendations_text),
    AWARDS(R.drawable.awards, R.string.awards_text),
    TEAM(R.drawable.team, R.string.team_text)
}
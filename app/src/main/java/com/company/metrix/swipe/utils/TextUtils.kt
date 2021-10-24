package com.company.metrix.swipe.utils

object TextUtils {

    fun formatQuestionDatabase(question: String): String {
        return question.replace(".", "_")
            .replace(".", "_")
            .replace("#", "_")
            .replace("$", "_")
            .replace("[", "_")
            .replace("]", "_")
    }

}
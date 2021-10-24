package com.company.metrix.auth

import com.company.metrix.data.model.AuthType

interface AuthHandler {
    fun handleSuccessAuth(authType: AuthType)
}
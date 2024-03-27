package com.baben.apps.appformation3.domain.models

data class LoginResponse(
    val token: String,
    val user: User
    // Ajoutez d'autres champs de réponse si nécessaire
)
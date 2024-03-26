package com.baben.apps.appformation3.data.remote.retrofit

import com.baben.apps.appformation3.domain.models.User

data class LoginResponseDto(
    val token: String, // Exemple de champ de réponse
    val user: User // Exemple de champ de réponse contenant les informations de l'utilisateur
)

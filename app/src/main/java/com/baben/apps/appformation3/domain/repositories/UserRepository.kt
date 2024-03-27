package com.baben.apps.appformation3.domain.repositories

interface UserRepository {
    fun saveUserCredentials(username: String, password: String)
}
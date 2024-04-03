package com.baben.apps.appformation3

import android.content.Context
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.presentation.screens.login.LoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    lateinit var loginRepository: LoginRepository

    @Mock
    lateinit var context: Context

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun `test login with valid username and password`() {
        // Given
        val username = "username"
        val password = "password"
        val token = "token"
        Mockito.`when`(loginRepository.login(Login(username, password)))
            .thenReturn(LoginRepository.LoginResult.SUCCESS)


        // When
        loginViewModel.login(username, password)

        // Then
        Mockito.verify(loginRepository).login(Login(username, password))
       // Mockito.verify(loginRepository).saveToken(token)
        Assert.assertEquals(LoginViewModel.LoginViewState.Success, loginViewModel.loginState.value)
    }

    // Add more test cases for other scenarios (invalid username/password, no internet connection, etc.)
}

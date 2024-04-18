package com.baben.apps.appformation3

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import com.baben.apps.appformation3.core.networking.NetworkErrorException
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.presentation.screens.login.LoginViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private lateinit var loginRepository: LoginRepository
    private lateinit var authLocalStorage: AuthLocalStorage
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        authLocalStorage = AuthLocalStorage(context)
      //  loginRepository = LoginRepository(authLocalStorage)
        loginViewModel = LoginViewModel(loginRepository)
    }
    @Test
    fun `test login with valid username and password`() {
        // Given
        val username = "username"
        val password = "password"
       // val token = "token"
        Mockito.`when`(loginRepository.login(Login(username, password)))
            .thenReturn(LoginRepository.LoginResult.SUCCESS)

        // When
        loginViewModel.login(username, password)

        // Then
        Mockito.verify(loginRepository).login(Login(username, password))
        Assert.assertEquals(LoginViewModel.LoginViewState.Success, loginViewModel.loginState.value)
    }

    @Test
    fun `test login with invalid username and password`() {
        // Given
        val username = ""
        val password = ""
       // val token = "token"
        Mockito.`when`(loginRepository.login(Login(username, password)))
            .thenReturn(LoginRepository.LoginResult.AUTH_ERROR)

        // When
        loginViewModel.login(username, password)

        // Then
        Mockito.verify(loginRepository).login(Login(username, password))
        Assert.assertEquals(LoginViewModel.LoginViewState.Error("Invalid username or password"), loginViewModel.loginState.value)
    }
    @Test
    fun `test login avec nom_utilisateur vide`() {
        // Given
        val username = ""
        val password = "password"

        // When
        loginViewModel.login(username, password)

        // Then
        Assert.assertEquals(LoginViewModel.LoginViewState.Error("Invalid username or password,username empty"), loginViewModel.loginState.value)
    }

    @Test
    fun `test login avec pwd vide`() {
        // Given
        val username = "username"
        val password = ""

        // When
        loginViewModel.login(username, password)

        // Then
        Assert.assertEquals(LoginViewModel.LoginViewState.Error("Invalid username or password,password is empty"), loginViewModel.loginState.value)
    }

    @Test
    fun `test login with no internet connection`() {
        // Given
        val username = "username"
        val password = "password"
        Mockito.`when`(loginRepository.login(Login(username, password)))
            .thenThrow(NetworkErrorException())

        // When
        loginViewModel.login(username, password)

        // Then
        Assert.assertEquals(LoginViewModel.LoginViewState.Error("No internet connection"), loginViewModel.loginState.value)
    }
    @Test
    fun `test LoginViewState Loading`() {
        val loginViewState = LoginViewModel.LoginViewState.Loading
        assertEquals(LoginViewModel.LoginViewState.Loading, loginViewState)
    }

    @Test
    fun `test LoginViewState Success`() {
        val loginViewState = LoginViewModel.LoginViewState.Success
        assertEquals(LoginViewModel.LoginViewState.Success, loginViewState)
    }

    @Test
    fun `test LoginViewState Error`() {
        val errorMessage="Erreur"
        val loginViewState = LoginViewModel.LoginViewState.Error(errorMessage)
        assertEquals(LoginViewModel.LoginViewState.Error(errorMessage), loginViewState)
        assertEquals(errorMessage, loginViewState.message)
    }

}

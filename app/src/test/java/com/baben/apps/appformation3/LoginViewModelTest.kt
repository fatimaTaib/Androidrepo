package com.baben.apps.appformation3

import com.baben.apps.appformation3.core.app.AuthLocalStorage
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.domain.viewModels.LoginViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginViewModelTest {
    @Mock
    private lateinit var loginViewModel: LoginViewModel
    @Mock
    private lateinit var loginRepository: LoginRepository
    @Mock
    private lateinit var authLocalStorage: AuthLocalStorage

    @Before
    fun setUp()
    {
        MockitoAnnotations.initMocks(this)
        loginViewModel= LoginViewModel(loginRepository,authLocalStorage)
    }
    @Test
    fun isNotBlank()
    {

    }
}
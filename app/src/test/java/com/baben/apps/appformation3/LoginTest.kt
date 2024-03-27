package com.baben.apps.appformation3

import android.service.autofill.UserData
import androidx.lifecycle.Observer
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import com.baben.apps.appformation3.data.remote.repositories.ApiLoginRepository
import com.baben.apps.appformation3.domain.models.Login
import com.baben.apps.appformation3.domain.repositories.LoginRepository
import com.baben.apps.appformation3.domain.viewModels.LoginViewModel
import junit.framework.TestCase.assertEquals
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginTest {
    @Mock
    private lateinit var apiLoginRepository : ApiLoginRepository
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var authLocalStorage : AuthLocalStorage
    @Test
    fun valideCredential()
    {

    }

}
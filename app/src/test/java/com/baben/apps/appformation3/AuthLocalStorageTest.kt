package com.baben.apps.appformation3


import android.content.SharedPreferences
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AuthLocalStorageTest {

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    lateinit var authLocalStorage: AuthLocalStorage
    @Mock
    lateinit var editor: SharedPreferences.Editor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        authLocalStorage = AuthLocalStorage(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
    }

    @Test
    fun testSaveToken() {
        val token = "test_token"
        authLocalStorage.saveToken(token)
        verify(editor).putString("token", token)
        verify(editor).apply()
    }


    @Test
    fun testGetToken() {
        val token = "test_token"
        `when`(sharedPreferences.getString("token", null)).thenReturn(token)
        assertEquals(token, authLocalStorage.getToken())
    }

    @Test
    fun testIsLoggedIn() {
        `when`(sharedPreferences.contains("token")).thenReturn(true)
        assertEquals(true, authLocalStorage.isLoggedIn())
    }
}

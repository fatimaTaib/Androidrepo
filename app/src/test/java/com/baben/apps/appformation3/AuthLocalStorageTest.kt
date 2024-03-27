package com.baben.apps.appformation3

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class AuthLocalStorageTest {
    @Mock
    private lateinit var sharedPreferences: SharedPreferences
    @Mock

    private lateinit var editor: SharedPreferences.Editor
    @Mock

    private lateinit var context: Context

    private lateinit var authLocalStorage: AuthLocalStorage


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)
        `when`(editor.apply()).then { null }
        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        authLocalStorage = AuthLocalStorage(context)
    }


    @Test
    fun saveAccessToken() {
        val accessToken = "test_access_token"
        `when`(editor.putString(authLocalStorage.token, accessToken)).thenReturn(editor)
        val result =authLocalStorage.saveAccessToken(accessToken)
        assertEquals(true,result)
    }


    @Test
    fun getAccessToken() {
        val accessToken = "test_access_token"
        `when`(sharedPreferences.getString(authLocalStorage.token, null)).thenReturn(accessToken)
        val result = authLocalStorage.getAccessToken()
        assertEquals(accessToken, result)
    }
    @Test
    fun isLoggedIn()
    {
        `when`(sharedPreferences.contains(authLocalStorage.token)).thenReturn(true)
        val result =authLocalStorage.isLoggedIn()
        assertEquals(true,result)
    }
}
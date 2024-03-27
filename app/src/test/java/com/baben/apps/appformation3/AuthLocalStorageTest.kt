package com.baben.apps.appformation3

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import com.baben.apps.appformation3.core.app.AuthLocalStorage
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class AuthLocalStorageTest {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var context: Context
    private lateinit var authLocalStorage: AuthLocalStorage


    @Before
    fun setUp() {
        context = mock(Context::class.java)
        sharedPreferences = mock(SharedPreferences::class.java)
        editor = mock(SharedPreferences.Editor::class.java)

        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)
        `when`(editor.apply()).then { null } // Stubbing apply() method to return null

        `when`(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)

        authLocalStorage = AuthLocalStorage(context)
    }



    @Test
    fun saveAccessToken() {
        val accessToken = "test_access_token"
        authLocalStorage.saveAccessToken(accessToken)
        verify(editor).putString("access_token", accessToken)
        verify(editor).apply()
    }
    @Test
    fun getAccessToken() {
        val accessToken = "test_access_token"
        `when`(sharedPreferences.getString("access_token", null)).thenReturn(accessToken)
        val result = authLocalStorage.getAccessToken()
        assertEquals(accessToken, result)
    }
    @Test
    fun isLoggedIn()
    {
        `when`(sharedPreferences.contains("access_token")).thenReturn(true)
        val result =authLocalStorage.isLoggedIn()
        assertEquals(true,result)
    }
}
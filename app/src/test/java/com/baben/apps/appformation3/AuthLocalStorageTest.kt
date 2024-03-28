package com.baben.apps.appformation3

import com.baben.apps.appformation3.core.app.AuthLocalStorage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import android.content.SharedPreferences
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import android.content.Context
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class AuthLocalStorageTest {

    private lateinit var authLocalStorage: AuthLocalStorage

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val context = mock(Context::class.java)
        `when`(context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)

        authLocalStorage = AuthLocalStorage(context)
    }

    @Test
    fun testSaveToken() {
        authLocalStorage.saveToken("test_token")
        verify(editor).putString("token", "test_token")
        verify(editor).apply()
    }

    @Test
    fun testGetToken() {
        `when`(sharedPreferences.getString("token", null)).thenReturn("test_token")
        assertEquals("test_token", authLocalStorage.getToken())
    }

    @Test
    fun testIsLoggedIn() {
        `when`(sharedPreferences.contains("token")).thenReturn(true)
        assertTrue(authLocalStorage.isLoggedIn())

        `when`(sharedPreferences.contains("token")).thenReturn(false)
        assertFalse(authLocalStorage.isLoggedIn())
    }
}

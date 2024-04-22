package com.baben.apps.appformation3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.baben.apps.appformation3.presentation.screens.signup.SignupActivity
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

class SignupActivityTest {
    @RunWith(MockitoJUnitRunner::class)
    class SignupActivityTest {

        @Mock
        lateinit var mockContext: Context

        @Mock
        lateinit var mockIntent: Intent

        lateinit var activity: SignupActivity

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            activity = spy(SignupActivity())
            doReturn(mockContext).`when`(activity)
            doReturn(mockIntent).`when`(activity).getIntent()
            activity.onCreate(Bundle())
        }

        @Test
        fun testActivityCreation() {
            assertNotNull(activity)
            assertNotNull(activity.binding)
            assertNotNull(activity.binding.uiBackButton)
            assertNotNull(activity.binding.uiSaveButton)
        }

        @Test
        fun testSetupActions() {
            assertNotNull(activity.binding.uiBackButton)
            assertNotNull(activity.binding.uiSaveButton)
            activity.binding.uiBackButton.performClick()
            verify(activity).onBackButtonClicked(any())
            activity.binding.uiSaveButton.performClick()
            verify(activity).onSaveButtonClicked(any())
        }

        @Test
        fun testOnSaveButtonClicked() {
            activity.onSaveButtonClicked(null)
            verify(mockContext).startActivity(mockIntent)
            verify(activity).finish()
        }

        @Test
        fun testOnBackButtonClicked() {
            activity.onBackButtonClicked(null)
            verify(activity).finish()
        }
    }

}
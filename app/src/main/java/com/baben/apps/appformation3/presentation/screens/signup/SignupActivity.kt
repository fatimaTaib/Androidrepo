package com.baben.apps.appformation3.presentation.screens.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.baben.apps.appformation3.R
import com.baben.apps.appformation3.core.bases.BaseActivities
import com.baben.apps.appformation3.databinding.ActivitySignupBinding
import com.baben.apps.appformation3.presentation.screens.login.LoginActivity

class SignupActivity : BaseActivities() {

    lateinit var binding: ActivitySignupBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActions()
    }

    private fun setupActions() {
        binding.uiBackButton.setOnClickListener(::onBackButtonClicked)
        binding.uiSaveButton.setOnClickListener(::onSaveButtonClicked)
    }

    fun onSaveButtonClicked(view: View?) {

        startActivity(Intent(context, LoginActivity::class.java))
        finish()
    }

    fun onBackButtonClicked(view: View?) {
        finish()
    }
}
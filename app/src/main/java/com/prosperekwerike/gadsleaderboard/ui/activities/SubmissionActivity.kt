package com.prosperekwerike.gadsleaderboard.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.ActivitySubmissionBinding

class SubmissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmissionBinding
    private lateinit var firstNameTextInputLayout : TextInputLayout
    private lateinit var lastNameTextInputLayout : TextInputLayout
    private lateinit var emailAddressTextInputLayout: TextInputLayout
    private lateinit var projectGitHubLinkTextInputLayout: TextInputLayout
    private lateinit var submitButton : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submission)
        initializeViews()

        submitButton.setOnClickListener {
            var firstName = firstNameTextInputLayout.editText!!.text.toString()
            var lastName = lastNameTextInputLayout.editText!!.text.toString()
            var emailAddress = emailAddressTextInputLayout.editText!!.text.toString()
            var projectGitHubLink = projectGitHubLinkTextInputLayout.editText!!.text.toString()


        }
    }

    private fun initializeViews(){
        firstNameTextInputLayout = binding.firstNameTextInputLayout
        lastNameTextInputLayout = binding.lastNameTextInputLayout
        emailAddressTextInputLayout = binding.emailAddressTextInputLayout
        projectGitHubLinkTextInputLayout = binding.projectGithubLinkTextInputLayout
        submitButton = binding.submitProjectButton
    }
}
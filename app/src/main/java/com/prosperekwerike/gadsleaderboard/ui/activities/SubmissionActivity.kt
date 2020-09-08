package com.prosperekwerike.gadsleaderboard.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.prosperekwerike.gadsleaderboard.R
import com.prosperekwerike.gadsleaderboard.databinding.ActivitySubmissionBinding
import com.prosperekwerike.gadsleaderboard.databinding.SubmitProjectCustomAlertDialogLayoutBinding
import com.prosperekwerike.gadsleaderboard.viewmodels.SubmissionActivityViewModel

class SubmissionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmissionBinding
    private lateinit var firstNameTextInputLayout: TextInputLayout
    private lateinit var lastNameTextInputLayout: TextInputLayout
    private lateinit var emailAddressTextInputLayout: TextInputLayout
    private lateinit var projectGitHubLinkTextInputLayout: TextInputLayout
    private lateinit var submitButton: MaterialButton
    private lateinit var submitProjectCustomToolbar: Toolbar

    private val submissionActivityViewModel: SubmissionActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submission)
        initializeViews()
        setOnClickListenersOnViews()
        observeLiveDataFromViewModel()
    }

    private fun observeLiveDataFromViewModel() {
        submissionActivityViewModel.networkErrorWhileSubmittingProject.observe(this,
            Observer {
                it?.let {
                    //show project submission failed alert dialog to user
                }
            })

        submissionActivityViewModel.submissionSuccessfullySignal.observe(this,
        Observer {
            it?.let{
                //show project submission was successful alert dialog to user
            }
        })
    }

    private fun setOnClickListenersOnViews() {
        submitButton.setOnClickListener {
            var firstName = firstNameTextInputLayout.editText!!.text.toString()
            var lastName = lastNameTextInputLayout.editText!!.text.toString()
            var emailAddress = emailAddressTextInputLayout.editText!!.text.toString()
            var projectGitHubLink = projectGitHubLinkTextInputLayout.editText!!.text.toString()

            val alertDialog = AlertDialog.Builder(this).create()
            val customSubmitProjectAlertDialogLayout =
                DataBindingUtil.inflate<SubmitProjectCustomAlertDialogLayoutBinding>(
                    LayoutInflater.from(this),
                    R.layout.submit_project_custom_alert_dialog_layout,
                    null,
                    false
                )

            //attach a click listener to the submit button in the customSubmitProjectAlertDialogLayout
            // to submit the project
            customSubmitProjectAlertDialogLayout.confirmProjectSubmissionButton.setOnClickListener {
                submissionActivityViewModel.submitProject(
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    projectGithubLink = projectGitHubLink
                )
                Toast.makeText(this, "Submitting project", Toast.LENGTH_SHORT).show()
            }

            //attach a click listener to the close project submission button
            customSubmitProjectAlertDialogLayout.closeProjectSubmissionAlertDialog.setOnClickListener {
                alertDialog.cancel()
            }

            alertDialog.setView(customSubmitProjectAlertDialogLayout.root)
            alertDialog
                .window!!
                .setBackgroundDrawable(resources.getDrawable(R.drawable.alert_dialog_window_background))
            alertDialog.show()
        }


        submitProjectCustomToolbar.setNavigationOnClickListener {
            supportFinishAfterTransition()
        }
    }

    private fun initializeViews() {
        firstNameTextInputLayout = binding.firstNameTextInputLayout
        lastNameTextInputLayout = binding.lastNameTextInputLayout
        emailAddressTextInputLayout = binding.emailAddressTextInputLayout
        projectGitHubLinkTextInputLayout = binding.projectGithubLinkTextInputLayout
        submitButton = binding.submitProjectButton
        submitProjectCustomToolbar = binding.submitProjectActivityCustomToolbar as Toolbar
    }
}
package com.prosperekwerike.gadsleaderboard.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prosperekwerike.gadsleaderboard.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeSplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_splash_screen)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1200)
            Intent(this@WelcomeSplashScreenActivity, HomepageActivity::class.java).apply {
                startActivity(this)
            }
            supportFinishAfterTransition()
        }
    }
}
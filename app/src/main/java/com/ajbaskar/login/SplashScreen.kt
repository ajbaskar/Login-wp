package com.ajbaskar.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    // creating constant keys for shared preferences.
    val SHARED_PREFS = "shared_prefs"

    // key for storing email.
    val EMAIL_KEY = "email_key"

    // key for storing password.
    val PASSWORD_KEY = "password_key"

    // variable for shared preferences.
    var sharedpreferences: SharedPreferences? = null

    var username:String?=null
    var password:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // in shared prefs inside het string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
        username = sharedpreferences!!.getString(EMAIL_KEY, null);
        password = sharedpreferences!!.getString(PASSWORD_KEY, null);

        Handler(Looper.getMainLooper()).postDelayed({
            if (username != null && password != null) {
                val i = Intent(this@SplashScreen, HomeScreen::class.java)
                startActivity(i)
                finish()
            }else{
                val i1 = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(i1)
                finish()
            }
        }, 3000)
    }
}
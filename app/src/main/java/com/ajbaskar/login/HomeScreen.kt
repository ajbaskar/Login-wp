package com.ajbaskar.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class HomeScreen : AppCompatActivity() {

    // creating constant keys for shared preferences.
    val SHARED_PREFS = "shared_prefs"

    // key for storing email.
    val EMAIL_KEY = "email_key"

    // key for storing password.
    val PASSWORD_KEY = "password_key"

    // variable for shared preferences.
    var sharedpreferences: SharedPreferences? = null
    var email: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences!!.getString(EMAIL_KEY, null);

        val logoutButton=findViewById<Button>(R.id.buttonLogout)
        logoutButton.setOnClickListener {

            // calling method to edit values in shared prefs.
            val editor = sharedpreferences!!.edit()

            editor.clear()

            editor.apply()


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout Successfully", Toast.LENGTH_LONG).show()
        }
    }
}
package com.ajbaskar.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

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
    val url="https://kanifoods.xyz/wp-json/jwt-auth/v1/token"

    var progressDialog:ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton=findViewById<Button>(R.id.buttonLogin)
        val txtUsername=findViewById<EditText>(R.id.txtUsername)
        val txtPassword=findViewById<EditText>(R.id.txtPassword)

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);


       loginButton.setOnClickListener(View.OnClickListener {

           progressDialog= ProgressDialog(this)
           progressDialog?.show()
           progressDialog?.setContentView(R.layout.custom_dialog)

           username=txtUsername.text.toString()
           password=txtPassword.text.toString()
           loginUser(username!!, password!!)
       })

    }

    private fun loginUser(username: String,password: String) {

        val stringRequest: StringRequest = object : StringRequest( Method.POST, url,
            Response.Listener { response ->

                try {
                    val jsonObject = JSONObject(response)
                    var success=jsonObject.get("success").toString()

                    if(success=="true"){
                        val editor = sharedpreferences!!.edit()

                        // below two lines will put values for
                        // email and password in shared preferences.

                        // below two lines will put values for
                        // email and password in shared preferences.
                        editor.putString(EMAIL_KEY, username)
                        editor.putString(PASSWORD_KEY, password)

                        // to save our data with key and value.

                        // to save our data with key and value.
                        editor.apply()
                        //Parse your api responce here
                        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                        finish()
                        progressDialog?.hide()
                    }

                    else {
                        Toast.makeText(
                            this,
                            jsonObject.get("message").toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        progressDialog?.hide()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                //Change with your post params
                params["username"] = username
                params["password"] = password
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

}
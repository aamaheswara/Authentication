package com.android.authentication

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.android.authentication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    public var email = ""
    public var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setMessage("Sedang mengalihkan kamu...")
        progressDialog.setCanceledOnTouchOutside(false)

        auth = FirebaseAuth.getInstance()
        checkUser()

        loginBinding.btnSubmit.setOnClickListener {
            validateData()
        }
        loginBinding.btnDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData() {
        email = loginBinding.etEmail.text.toString().trim()
        password = loginBinding.etPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginBinding.etEmail.error = "Email tidak terdaftar"
        } else if(TextUtils.isEmpty(password)) {
            loginBinding.etPassword.error = "Mohon masukkan kata sandi"
        } else {
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val auth = auth.currentUser
                val email = auth!!.email
                Toast.makeText(this, "Anda masuk sebagai $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
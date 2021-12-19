package com.android.authentication

import android.app.ActionBar
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.authentication.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setMessage("Sedang mendaftarkan akunmu...")
        progressDialog.setCanceledOnTouchOutside(false)

        auth = FirebaseAuth.getInstance()
        registerBinding.btnSubmit.setOnClickListener {
            validateData()
        }
        registerBinding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateData() {
        email = registerBinding.etEmail.text.toString().trim()
        password = registerBinding.etPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerBinding.etEmail.error = "Email tidak terdaftar"
        } else if(TextUtils.isEmpty(password)) {
            registerBinding.etPassword.error = "Mohon masukkan kata sandi"
        } else if(password.length < 6) {
            registerBinding.etPassword.error = "Kata sandi paling sedikit 6 huruf"
        } else {
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
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
                Toast.makeText(this, "Regitser failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
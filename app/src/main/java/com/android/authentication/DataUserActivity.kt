package com.android.authentication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.authentication.databinding.ActivityDataUserBinding
import com.google.firebase.auth.FirebaseAuth

class DataUserActivity : AppCompatActivity() {
    private lateinit var dataUserBinding: ActivityDataUserBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var auth: FirebaseAuth
    private val db = RealtimeDatabase.instance()
    private val firebaseUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataUserBinding = ActivityDataUserBinding.inflate(layoutInflater)
        setContentView(dataUserBinding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setMessage("Data kamu sedang dimasukkan...")
        progressDialog.setCanceledOnTouchOutside(false)

        val userId = firebaseUser!!.uid
        val email = firebaseUser!!.email

        dataUserBinding.btnCompleteData.setOnClickListener {
            setDataToDatabase(
                User(
                    id = userId.toString().trim(),
                    name = dataUserBinding.etName.text.toString().trim(),
                    email = email.toString().trim(),
                    phone = dataUserBinding.etPhone.text.toString().trim(),
                )
            )
        }
    }

    private fun setDataToDatabase(user: User) {
        if(firebaseUser != null) {
            if (user.name.isNotEmpty() && user.phone.isNotEmpty()) {
                db.getReference("users").child(user.id).setValue(user).addOnSuccessListener {
                    dataUserBinding.etName.setText("")
                    dataUserBinding.etPhone.setText("")
                    Toast.makeText(this, "Data berhasil disimpan",

                        Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Data gagal disimpan",

                        Toast.LENGTH_SHORT).show()
                }
            }
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(dataUserBinding.etName.windowToken, 0)
            imm.hideSoftInputFromWindow(dataUserBinding.etPhone.windowToken, 0)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
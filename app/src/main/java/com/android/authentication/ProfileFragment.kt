package com.android.authentication

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.authentication.databinding.ActivityHomeBinding
import com.android.authentication.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.*

class ProfileFragment : Fragment() {
    private lateinit var profileHomeBinding: ActivityHomeBinding
    private lateinit var profileBinder: FragmentProfileBinding
    private lateinit var actionBar: ActionBar
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        actionBar = supportActionBar!!
//        actionBar.title = "ProfileFragment"
        profileBinder = FragmentProfileBinding.inflate(layoutInflater)
        activity?.setContentView(profileBinder.root)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        profileBinder.btnLogout.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }

        return inflater.inflate(R.layout.fragment_profile, container, false)
//        profileHomeBinding.btnLogout.setOnClickListener {
//            Firebase.auth.signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    private fun checkUser() {
        // to check is user logged in or not????
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email: String = firebaseUser.email.toString()
            profileBinder.etEmail.setText(email)
        } else {
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }

}
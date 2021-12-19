package com.android.authentication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBar
import com.android.authentication.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var actionBar: ActionBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        auth = FirebaseAuth.getInstance()
        checkUser()

//        homeBinding.btnLogout.setOnClickListener {
//            Firebase.auth.signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        bottom_navigation.setOnNavigationItemSelectedListener(onBottomNav)

        var fr = supportFragmentManager.beginTransaction()
        fr.add(R.id.fl_fragment, HomeFragment())
        fr.commit()
    }

    private fun checkUser() {
        val firebaseUser = auth.currentUser
        if(firebaseUser != null) {
            val email = firebaseUser.email
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private val onBottomNav = BottomNavigationView.OnNavigationItemSelectedListener {i ->
        var selectedFragment: Fragment = HomeFragment()

        when(i.itemId){
            R.id.item_home -> {
                selectedFragment = HomeFragment()
            }

            R.id.item_post -> {
                selectedFragment = NewPostFragment()
            }

            R.id.item_search -> {
                selectedFragment = SearchFragment()
            }

            /*
            R.id.item_stars -> {
                selectedFragment = //mohon diisi fragment gamification-nya, lalu un-comment
            }*/

            R.id.item_account -> {
                selectedFragment = ProfileFragment()
            }

        }

        var fr = supportFragmentManager.beginTransaction()
        fr.replace(R.id.fl_fragment, selectedFragment)
        fr.commit()

        true
    }





}
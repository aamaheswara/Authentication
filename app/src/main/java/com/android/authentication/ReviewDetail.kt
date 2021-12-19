package com.android.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
Activity ini tidak dipakai. Untuk Single Post view,
tolong lihat GameDetail.kt dan activity_game_detail.xml
 */


class ReviewDetail : AppCompatActivity() {

    // inisiasi Bundle
    var b: Bundle? = null

    // list foto
    val itemImg = intArrayOf(
        R.drawable.game1,
        R.drawable.game2,
        R.drawable.game3,
        R.drawable.game4,
        R.drawable.game5,
        R.drawable.game6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)

        //initView()
    }

//    fun initView(){
//        b = intent.extras
//
//        var idParsing = b?.getString("idGambar").toString()
//        var idFix: Int = idParsing.toInt()
//
//        iv_game.setImageResource(itemImg[idFix])
//        tv_name.text = b?.getString("name")
//        tv_title.text = b?.getString("title")
//        tv_description.text = b?.getString("description")
//    }

}
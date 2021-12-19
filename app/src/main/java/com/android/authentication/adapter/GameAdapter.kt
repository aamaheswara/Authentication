package com.android.authentication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.authentication.GameDetail
import com.android.authentication.R
import com.android.authentication.model.listGame

class GameAdapter(val context: Context): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val games: MutableList<listGame> = mutableListOf()

    // list foto
    val itemImg = intArrayOf(
        R.drawable.game1,
        R.drawable.game2,
        R.drawable.game3,
        R.drawable.game4,
        R.drawable.game5,
        R.drawable.game6
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return  GameViewHolder(LayoutInflater.from(context).inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: GameAdapter.GameViewHolder, position: Int) {
        holder.bindModel(games[position])
        holder.gameImage.setImageResource(itemImg[position])
        holder.setPosition(position)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setGame(data: List<listGame>){
        games.clear()
        games.addAll(data)
        notifyDataSetChanged()
    }

    inner  class GameViewHolder(item: View): RecyclerView.ViewHolder(item){
        var pos: Int = 0
        val txtName: TextView = item.findViewById(R.id.tv_account)
        val txtTitle: TextView = item.findViewById(R.id.tv_title)
        val txtDescription: TextView = item.findViewById(R.id.tv_description)
        val gameImage: ImageView =  item.findViewById(R.id.iv_img)

        // card view
        val cardViewGame: CardView = item.findViewById(R.id.cv_game)

        fun bindModel(lg: listGame){


            txtName.text = lg.getName()
            txtTitle.text = lg.getTitle()
            txtDescription.text = lg.getDescription()


            // set click untuk card view
            cardViewGame.setOnClickListener{
                var i = Intent(context, GameDetail::class.java)

                var a: String = lg.getId()
                i.putExtra("idGambar", a)
                i.putExtra("name", lg.getName())
                i.putExtra("title", lg.getTitle())
                i.putExtra("description", lg.getDescription())


                context.startActivity(i)
            }
        }

        fun setPosition(position: Int){
            this.pos = position
        }
    }
}
package fr.tuto.dofusapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.tuto.dofusapi.dataClass.Monster

class RecyclerAdapterMonster(val context: Context) : RecyclerView.Adapter<RecyclerAdapterMonster.ViewHolderMonster>() {


    private val images = R.drawable.oeuf
    var monsterType : List<Monster> = listOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterMonster.ViewHolderMonster {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_monster,parent,false)
        return ViewHolderMonster(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterMonster.ViewHolderMonster, position: Int) {
        holder.item_monsterName.text = monsterType.get(position).name
        holder.itemImage.setImageResource(images)
    }

    fun setMonsters(monsterType: List<Monster>){
        this.monsterType = monsterType
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return monsterType.size
    }

    inner class ViewHolderMonster(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var item_monsterName: TextView
        init {

            itemImage = itemView.findViewById(R.id.item_monsterImage)
            item_monsterName = itemView.findViewById(R.id.item_monsterName)


        }
    }
}
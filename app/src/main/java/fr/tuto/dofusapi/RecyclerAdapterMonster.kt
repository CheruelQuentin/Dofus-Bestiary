package fr.tuto.dofusapi

import android.annotation.SuppressLint
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
import com.bumptech.glide.Glide
import fr.tuto.dofusapi.dataClass.Monster

class RecyclerAdapterMonster(val context: Context) : RecyclerView.Adapter<RecyclerAdapterMonster.ViewHolderMonster>() {


    private val images = R.drawable.oeuf
    var monsterType : List<Monster> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterMonster.ViewHolderMonster {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_monster,parent,false)
        return ViewHolderMonster(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerAdapterMonster.ViewHolderMonster, position: Int) {
        holder.item_monsterName.text = monsterType[position].name
        holder.item_PV.text = "PV : ${monsterType[position].pvMin}  - ${monsterType[position].pvMax} "
        holder.item_PA.text = "PA : ${monsterType[position].paMin}  - ${monsterType[position].paMax} "
        holder.item_PM.text = "PM : ${monsterType[position].pmMin}  - ${monsterType[position].pmMax} "


        val image = monsterType[position].imgUrl
        val holderTest = holder.itemImage
        Glide.with(context)
            .load(image)
            .into(holderTest)
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
        var item_PV :TextView
        var item_PA :TextView
        var item_PM :TextView
        init {

            itemImage = itemView.findViewById(R.id.item_monsterImage)
            item_monsterName = itemView.findViewById(R.id.item_monsterName)
            item_PV = itemView.findViewById(R.id.item_monsterPV)
            item_PA = itemView.findViewById(R.id.item_monsterPA)
            item_PM = itemView.findViewById(R.id.item_monsterPM)

        }
    }
}
package fr.tuto.dofusapi.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.tuto.dofusapi.dataClass.Monster
import android.widget.*
import fr.tuto.dofusapi.R
import fr.tuto.dofusapi.activity.MonsterActivity


class RecyclerAdapter(val context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val images = R.drawable.oeuf
    var monsterType : List<Monster> = listOf()



    fun setTypeMonsters(monsterType: List<Monster>){
        this.monsterType = monsterType
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemImage.setImageResource(images)
        holder.itemTitle.text = monsterType.get(position).type


    }

    override fun getItemCount(): Int {
        return monsterType.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView


        init {
                itemImage = itemView.findViewById(R.id.item_image)
                itemTitle = itemView.findViewById(R.id.item_title)

                val intent : Intent = Intent(context, MonsterActivity::class.java)
                itemTitle.setOnClickListener {
                    intent.putExtra("type", monsterType.get(position).type)
                    context.startActivity(intent) }
                itemImage.setOnClickListener {
                intent.putExtra("type", monsterType.get(position).type)
                context.startActivity(intent) }
        }
    }
}
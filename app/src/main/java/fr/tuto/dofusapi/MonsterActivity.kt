package fr.tuto.dofusapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.tuto.dofusapi.API.ApiInterface
import fr.tuto.dofusapi.dataClass.Monster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var typeSelect : String? = null

class MonsterActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapterMonster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monster)

        val monster : ArrayList<Monster> = ArrayList()
        var minHP : Double = 0.0
        var maxHP : Double = 0.0
        var minPA : Double = 0.0
        var maxPA : Double = 0.0
        var minPM : Double = 0.0
        var maxPM : Double = 0.0

        val extras = intent.extras
        if (extras != null) {
             typeSelect = extras.getString("type")
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMonster)

        recyclerAdapter= RecyclerAdapterMonster(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getListMonsters()
        apiInterface.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.body() != null) {
                    val allMonster = response.body()
                    if (allMonster != null) {
                        for (monstreType in allMonster){
                            if(typeSelect == monstreType.type){
                                val stats = monstreType.stat
                                for (stat in stats){
                                    val pv = stat.pv
                                    val pa = stat.pa
                                    val pm = stat.pm
                                        if(pv !=null){
                                             minHP = pv.min
                                             maxHP = pv.max
                                        }
                                        if(pa !=null){
                                            minPA = pa.min
                                            maxPA = pa.max
                                        }
                                        if(pm !=null){
                                            minPM = pm.min
                                            maxPM = pm.max
                                        }
                                    }

                                monster.add(Monster("",monstreType.name,monstreType.imgUrl,
                                    monstreType.stat, minHP, maxHP,minPA, maxPA,minPM,maxPM))
                            }
                        }
                    }
                    recyclerAdapter.setMonsters(monster)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}
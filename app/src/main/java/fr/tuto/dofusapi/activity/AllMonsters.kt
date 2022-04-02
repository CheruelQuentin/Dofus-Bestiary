package fr.tuto.dofusapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.tuto.dofusapi.API.ApiInterface
import fr.tuto.dofusapi.R
import fr.tuto.dofusapi.dataClass.Monster
import fr.tuto.dofusapi.recycler.RecyclerAdapterMonster
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllMonsters : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapterMonster
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_monsters)
        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)

        configureBottomView()

        val monster : ArrayList<Monster> = ArrayList()
        var minHP : Double = 0.0
        var maxHP : Double = 0.0
        var minPA : Double = 0.0
        var maxPA : Double = 0.0
        var minPM : Double = 0.0
        var maxPM : Double = 0.0

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
                            monster.add(
                                Monster("",monstreType.name,monstreType.imgUrl, monstreType.stat, minHP, maxHP,minPA, maxPA,minPM,maxPM)
                            )
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



    private fun configureBottomView() {
        bottomNavigationView.setOnItemSelectedListener { item -> updateFragmentBottom(item.itemId) }
    }

    fun updateFragmentBottom(integer: Int): Boolean {
        when (integer) {
            R.id.allMonster -> {
                val intent = Intent(this, AllMonsters::class.java)
                startActivity(intent)
                finish()
            }
            R.id.typeMonster -> {
                val intent = Intent(this, TypeMonsterActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.monsterPV ->{
                val intent = Intent(this, MonsterOrderByPV::class.java)
                startActivity(intent)
                finish()
            }
            R.id.monsterPM ->{
                val intent = Intent(this, MonsterOrderByPM::class.java)
                startActivity(intent)
                finish()
            }
            R.id.monsterPA ->{
                val intent = Intent(this, MonsterOrderByPA::class.java)
                startActivity(intent)
                this.finish()
            }
        }
        return true
    }

}
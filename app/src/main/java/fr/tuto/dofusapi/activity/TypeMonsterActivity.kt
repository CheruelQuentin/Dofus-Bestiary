package fr.tuto.dofusapi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.tuto.dofusapi.API.ApiInterface
import fr.tuto.dofusapi.R
import fr.tuto.dofusapi.dataClass.Monster
import fr.tuto.dofusapi.recycler.RecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeMonsterActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_monster)

        bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerAdapter= RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        val apiInterface = ApiInterface.create().getListMonsters()
        apiInterface.enqueue(object : Callback<List<Monster>> {
            override fun onResponse(call: Call<List<Monster>>, response: Response<List<Monster>>) {
                if (response.body() != null) {
                    Log.e("response Json", "${response.body()}")
                    val jack = response.body()!!.distinctBy { it.type }
                    recyclerAdapter.setTypeMonsters(jack)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                Log.e("Yo","FailureTypeMonsterActivity")
            }
        })
    this.configureBottomView()
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
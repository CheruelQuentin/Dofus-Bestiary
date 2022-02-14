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

        var test : ArrayList<Monster> = ArrayList()

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
                                test.add(Monster("",monstreType.name,monstreType.imgUrl))
                            }
                        }
                    }
                    Log.e("voici mon nom", "$test")

                    typeSelect?.let { Log.e("je suis le type Select", it) }

                    recyclerAdapter.setMonsters(test)
                    //recyclerAdapter.setTypeMonsters(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}
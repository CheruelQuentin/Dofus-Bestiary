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

class TypeMonsterActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_monster)
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
                    //recyclerAdapter.setTypeMonsters(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<Monster>>, t: Throwable) {
                Log.e("Yo","FailureTypeMonsterActivity")
            }
        })

    }
}
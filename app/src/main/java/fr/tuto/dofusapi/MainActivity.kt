package fr.tuto.dofusapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import fr.tuto.dofusapi.DAO.UserDao
import fr.tuto.dofusapi.Database.UserRoomDatabase
import fr.tuto.dofusapi.dataClass.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var username: AppCompatEditText
    private lateinit var accessDatabase: UserRoomDatabase
    private lateinit var userDao: UserDao

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_start)
        username = findViewById(R.id.et_name)

        val intent : Intent = Intent(this, TypeMonsterActivity::class.java)
        button.setOnClickListener {
            val user = User(username.text.toString())
            launch {
                accessDatabase()
                userDao.insert(user)
                Log.e("tesf","success")
            }

            startActivity(intent)
        }
    }
    fun accessDatabase(){
       accessDatabase =  UserRoomDatabase.getDatabase(this)
        userDao = accessDatabase.userDao()
    }
}



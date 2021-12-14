package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var nameTxt: EditText
    private lateinit var locationTxt: EditText
    private lateinit var addBtnPress: Button
    private lateinit var updateDeletePress: Button
    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameTxt = findViewById(R.id.etName)
        locationTxt = findViewById(R.id.etLocation)
        addBtnPress = findViewById(R.id.btAdd)
        updateDeletePress =  findViewById(R.id.btUpdateDelete)
        users = Users()
        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(users)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)
        val builder = AlertDialog.Builder(this)
        val apiInterface = RetrofitBuilder().getRetrofitBuilder()?.create(ServiceAPI::class.java)

        apiInterface?.getAPIUsers()?.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                users = response.body()!!
                rvAdapter.update(users)
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("MAIN", "Unable to get data")
            }
        })
updateDeletePress.setOnClickListener {
    val intent = Intent(this,UpdateDeleteActivity::class.java)
    startActivity(intent)
}
        addBtnPress.setOnClickListener {
            apiInterface!!.addUser(
                UsersItem(
                    locationTxt.text.toString(),
                    nameTxt.text.toString(),
                    0
                )
            ).enqueue(object : Callback<UsersItem> {
                override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                    locationTxt.text = null
                    nameTxt.text = null
                    recreate()
                }

                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Log.d("MAIN", "Something went wrong!")
                }

            })

        }
    }
}
package com.example.postrequestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class UpdateDeleteActivity : AppCompatActivity() {
    private var userPk: Int = 0
    private var username: String = ""
    private var userlocation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)
        val deletbtn = findViewById<Button>(R.id.btDelete)
        val updatebtn = findViewById<Button>(R.id.btUpdate)
        val editTextName = findViewById<EditText>(R.id.txtVName)
        val editTextPk = findViewById<EditText>(R.id.tvID)
        val editTextLocation = findViewById<EditText>(R.id.txtVlocation)
        val apiInterface = RetrofitBuilder().getRetrofitBuilder()?.create(ServiceAPI::class.java)
        deletbtn.setOnClickListener {
             userPk = editTextPk.text.toString().toInt()
             username = editTextName.text.toString()
             userlocation = editTextLocation.text.toString()
            apiInterface!!.deleteUser(userPk).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(
                        applicationContext,
                        "The User Has Been Deleted Successfully!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    moveToMainActivity()
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Sorry,The User Has Not Been Deleted Successfully!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
        updatebtn.setOnClickListener {
            userPk = editTextPk.text.toString().toInt()
            username = editTextName.text.toString()
            userlocation = editTextLocation.text.toString()
            apiInterface!!.updateUser(userPk, UsersItem(userlocation,username,userPk))
                .enqueue(object :
                    Callback<UsersItem> {
                    override fun onResponse(
                        call: Call<UsersItem>,
                        response: Response<UsersItem>
                    ) {
                        Toast.makeText(
                            applicationContext,
                            "The User Has Been Updated Successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveToMainActivity()}
                    override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                        Toast.makeText(
                            applicationContext,
                            "Sorry,The User Has Not Been Updated Successfully!!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
    fun moveToMainActivity(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
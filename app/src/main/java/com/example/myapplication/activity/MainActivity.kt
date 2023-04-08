package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.ListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Question
import com.example.myapplication.model.QuestionList
import com.example.myapplication.rest.APIService
import com.example.myapplication.rest.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var mAPIService : APIService? = null
    private var mAdapter : ListAdapter? = null
    private var mQuestions : MutableList<Question> = ArrayList()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // intialize the interface
        mAPIService = RestClient.client.create(APIService::class.java)
        //associating our recyclerview to a layoutmanager
        binding.listRecyclerView!!.layoutManager = LinearLayoutManager(this)

        mAdapter = ListAdapter(this, mQuestions, 1)
        //associate recyclerview to adapter
        binding.listRecyclerView.adapter = mAdapter
        //method to make the fetch using retrofit
        fetchQuestions()
    }

    private fun fetchQuestions() {
        val call = mAPIService!!.fetchQuestions("android");
        call.enqueue(object : Callback<QuestionList> {
            override fun onResponse(call: Call<QuestionList>, response: Response<QuestionList>) {
                Log.d("response", "Total Questions: " + response.body()!!.items!!.size)
                val questions = response.body()
                if (questions != null){
                    mQuestions.addAll(questions.items!!)
                    mAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<QuestionList>, t: Throwable) {
                Toast.makeText(applicationContext,"Something went wrong , check internet connection " ,Toast.LENGTH_LONG).show()
                Log.d("response", "Error occurred")

            }

        })
    }
}
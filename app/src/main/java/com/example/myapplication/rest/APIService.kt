package com.example.myapplication.rest

import com.example.myapplication.model.QuestionList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    // GET annotation to indicate the HTTP request mode
    // Query annotation used to add query parameters to the parameters
    // Call is an object type parameter indicating what data its expecting from the result
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    fun fetchQuestions(@Query("tagged") tags: String) : Call<QuestionList>
}
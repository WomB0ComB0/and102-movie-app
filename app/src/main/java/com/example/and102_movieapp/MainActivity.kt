package com.example.and102_movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.Headers


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.Rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        updateAdapter(recyclerView)
    }

    private fun updateAdapter(recyclerView: RecyclerView) {
        var client = AsyncHttpClient()
        var params = RequestParams()
        params["api_key"] = API_KEY

        client["https://api.themoviedb.org/3/movie/now_playing", params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON?) {
                json?.let {
                    try {
                        val moviesRawJSON: String = json.jsonObject.get("results").toString()
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<TrendingMovies>>() {}.type
                        val movies: List<TrendingMovies> = gson.fromJson(moviesRawJSON, arrayMovieType)
                        recyclerView.adapter = TrendingMoviesAdapter(movies)
                    } catch (e: JsonSyntaxException) {
                        Log.e("MainActivity", "Error parsing JSON: ${e.message}")
                    }
                } ?: Log.d("MainActivity", "Empty JSON response")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("MainActivity", response ?: "No response")
            }
        }]
    }
}
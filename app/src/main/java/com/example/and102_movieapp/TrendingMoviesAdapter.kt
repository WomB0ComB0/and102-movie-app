package com.example.and102_movieapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TrendingMoviesAdapter(
    private val movies: List<TrendingMovies>,
): RecyclerView.Adapter<TrendingMoviesAdapter.ViewHolder>()
{
    inner class ViewHolder(moviesView: View): RecyclerView.ViewHolder(moviesView) {
        val movieTitle: TextView = moviesView.findViewById(R.id.title)
        val movieOverview: TextView = moviesView.findViewById(R.id.description)
        val moviePoster: ImageView = moviesView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.movies, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        val baseUrl = "https://image.tmdb.org/t/p/w500/"

        var imageUrl = baseUrl + movie.movieImage

        holder.movieTitle.text = movie.movieTtile
        holder.movieOverview.text = movie.movieDescription

        Glide.with(holder.itemView)
            .load(imageUrl)
            .centerInside()
            .into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
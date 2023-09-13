package com.smallworldfs.movieapp.ui.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.smallworldfs.movieapp.R
import com.smallworldfs.movieapp.model.Movie

class MovieListAdapter (private var movies: ArrayList<Movie>, var clickListener: (position: Int, data: Movie) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]

        // sets the image to the imageview from our itemHolder class
       // holder.imageView.setImageResource(movie.poster_path)

        // sets the text to the textview from our itemHolder class
        holder.tvTitle.text = movie.title
        holder.tvOverview.text = movie.overview
        holder.tvReleaseDate.text = movie.release_date
        holder.cvMovie.setOnClickListener {
            clickListener(position,movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvOverview: TextView = itemView.findViewById(R.id.tvOverview)
        val tvReleaseDate: TextView = itemView.findViewById(R.id.tvReleaseDate)
        val cvMovie: CardView = itemView.findViewById(R.id.cvMovie)
    }

    fun addItems(list:ArrayList<Movie>){
        this.movies.addAll(list)
        notifyDataSetChanged()
    }

    // method for filtering our recyclerview items.
    fun filterList(filterlist: ArrayList<Movie>) {
        // below line is to add our filtered
        // list in our course array list.
        movies = filterlist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }
}
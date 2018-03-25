package com.samsung.interview.moviedb.demo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.interview.moviedb.demo.R;
import com.samsung.interview.moviedb.demo.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter used to show a list of Movie
 * Created by licha on 3/24/2018.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private List<Movie> data;
    private Context context;
    private IClickListener         listener;

    /**
     * Constructs a new MovieRecyclerAdapter with a context
     *
     * @param ctx
     */
    public MovieRecyclerAdapter(Context ctx) {
        this.context = ctx;
    }

    /**
     * Replace data in the adapter
     *
     * @param data
     */
    public void setData(List<Movie> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * Sets the click listener
     * @param listener
     */
    public void setListener(IClickListener listener) {
        this.listener = listener;
    }

    /**
     * Clear all data from adapter
     */
    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movies_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = data.get(position);

        holder.title.setText(movie.getTitle());
        holder.genres.setText(movie.getGenresString());
        holder.popularity.setText(context.getString(R.string.row_popularity, movie.getPopularity()));
        holder.releaseYear.setText(context.getString(R.string.row_released, movie.getReleaseDate()));
        Picasso.with(context).load(movie.getPoster()).placeholder(R.drawable.placeholder2)
                .into(holder.thumb_image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Interface for click listeners for this adapter
     */
    public interface IClickListener {
        void onItemClick(Movie movie);
    }

    /**
     * List row members
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView genres;
        public TextView popularity;
        public TextView  releaseYear;
        public ImageView thumb_image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.movie_title_details);
            genres = (TextView) itemView.findViewById(R.id.genres);
            popularity = (TextView) itemView.findViewById(R.id.popularity);
            releaseYear = (TextView) itemView.findViewById(R.id.release_year);
            thumb_image = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}

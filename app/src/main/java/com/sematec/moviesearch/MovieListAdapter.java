package com.sematec.moviesearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    List<String> moviesList;
    String name;

    public MovieListAdapter(List<String> list) {

        moviesList = list;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_movie_list_adapter, viewGroup , false);
        MyViewHolder myViewHolder = new MyViewHolder(v);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i ) {
        String name = moviesList.get(i);
        myViewHolder.txtMovie.setText(name);
    }



    @Override
    public int getItemCount()
    {
        return moviesList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView txtMovie;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovie = itemView.findViewById(R.id.txtMovie);

        }
    }
}

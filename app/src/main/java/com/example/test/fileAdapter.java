package com.example.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class fileAdapter extends RecyclerView.Adapter<fileAdapter.ViewHolder> {


private final Context context;
    private ArrayList<files> filelist;
    private dbhelper mdb;

    public fileAdapter(Context context, ArrayList<files> filelist) {
        this.context = context;
        this.filelist = filelist;
        mdb = new dbhelper(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(
                                R.layout.row_file,
                                viewGroup,
                                false
                        )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull fileAdapter.ViewHolder viewHolder, int i) {
        final files file=filelist.get(i);
        viewHolder.name.setText(file.getFname());
        viewHolder.time.setText(file.getTime());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + file.getFname(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filelist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;
        CardView cardView;

        private ViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.cardView);
            name = v.findViewById(R.id.name);
            time = v.findViewById(R.id.time);
        }
    }
}

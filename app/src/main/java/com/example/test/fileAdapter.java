package com.example.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class fileAdapter extends RecyclerView.Adapter<fileAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<files> filelist;
    private dbhelper mdb;

//    private ArrayList<files> marraylist;
    public fileAdapter(Context context, ArrayList<files> filelist){
        this.context=context;
        this.filelist=filelist;
//        this.marraylist=marraylist;
        mdb=new dbhelper(context);

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_file, viewGroup, false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(fileAdapter.ViewHolder viewHolder, int i) {
        final files file=filelist.get(i);
        viewHolder.name.setText(file.getFname());
        viewHolder.time.setText(file.getTime());

        ViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+files.getFname(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filelist.size();
    }


//    public Filter getFilter() {
//
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//
//                String charString = charSequence.toString();
//
//                if (charString.isEmpty()) {
//
//                    filelist = marraylist;
//                } else {
//
//                    ArrayList<files> filteredList = new ArrayList<>();
//
//                    for (files files : marraylist) {
//
//                        if (files.getFname().toLowerCase().contains(charString)) {
//
//                            filteredList.add(files);
//                        }
//                    }
//
//                    filelist = filteredList;
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = filelist;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                filelist = (ArrayList<files>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,time;
        public static RelativeLayout relativeLayout;
        public ViewHolder(View v) {
            super(v);
            name=(TextView)v.findViewById(R.id.name);
            time=(TextView)v.findViewById(R.id.time);
            relativeLayout=(RelativeLayout)v.findViewById(R.id.listraw);
        }
    }
}

package com.example.mahdi.testapp.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahdi.testapp.Model.definition;
import com.example.mahdi.testapp.R;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView word, meaning;

    public SearchViewHolder(View itemView)
    {
        super(itemView);
        word = (TextView)itemView.findViewById(R.id.word);
        meaning = (TextView)itemView.findViewById(R.id.meaning);

    }
}


public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context  context;
    private List<definition> definitions;


    public SearchAdapter(Context context, List<definition> definitions)
    {
        this.context = context;
        this.definitions = definitions;
    }


    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layoutsearch,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( SearchViewHolder holder, int position) {
            holder.word.setText(definitions.get(position).getWord());
            holder.meaning.setText(definitions.get(position).getMeaning());
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }
}

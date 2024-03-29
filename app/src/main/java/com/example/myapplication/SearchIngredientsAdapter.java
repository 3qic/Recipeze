package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// Code für Adaptererstellung: https://www.youtube.com/watch?v=PmqYd-AdmC0


public class SearchIngredientsAdapter extends RecyclerView.Adapter<SearchIngredientsAdapter.MyViewHolder> {
    public Context c;
    public ArrayList<Recipe> arrayList;


    public SearchIngredientsAdapter(Context c, ArrayList<Recipe> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_ingredientsearchresult_layout, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(arrayList.get(i).getName());
        myViewHolder.desc.setText(arrayList.get(i).getKurzbeschreibung());
        myViewHolder.ingredts.setText(arrayList.get(i).getZutaten());
        myViewHolder.cookingTime.setText(arrayList.get(i).getArbeitszeit());
        myViewHolder.instruction.setText(arrayList.get(i).getAnleitung());
        myViewHolder.id.setText(arrayList.get(i).getRezeptid());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc, ingredts, instruction, cookingTime, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            desc = itemView.findViewById(R.id.Kurzbeschreibung);
            instruction = itemView.findViewById(R.id.Anleitung);
            ingredts = itemView.findViewById(R.id.Zutaten);
            cookingTime = itemView.findViewById(R.id.Kochzeit);
            id = itemView.findViewById(R.id.Rezeptid);

        }
    }

    public void filterList(ArrayList<Recipe> filteredList){
        arrayList = filteredList;
        notifyDataSetChanged();
    }


}



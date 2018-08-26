package com.desafiolatam.adventure.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiolatam.adventure.R;
import com.desafiolatam.adventure.data.CurrentUser;
import com.desafiolatam.adventure.data.EmailSanitized;
import com.desafiolatam.adventure.data.Nodes;
import com.desafiolatam.adventure.models.Adventure;
import com.desafiolatam.adventure.models.AdventureListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdventureAdapter extends FirebaseRecyclerAdapter<Adventure,AdventureAdapter.AdventureHolder> {

    private AdventureListener adventureListener;

    private boolean first = true;


    public AdventureAdapter(LifecycleOwner lifecycleOwner,AdventureListener adventureListener) {
        super(new FirebaseRecyclerOptions.Builder<Adventure>()
                .setQuery(new Nodes().adventure(new EmailSanitized().emailSanitized(new CurrentUser().email())),Adventure.class)
                .setLifecycleOwner(lifecycleOwner)
                .build()
        );
        this.adventureListener = adventureListener;
    }

    @NonNull
    @Override
    public AdventureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adventure,parent,false);
        return new AdventureHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final AdventureHolder holder, int position, @NonNull Adventure model) {

        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adventure auxAdventure = getItem(holder.getAdapterPosition());
                adventureListener.clicked(auxAdventure);
            }
        });

        holder.deleteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adventure auxAdventure = getItem(holder.getAdapterPosition());
                adventureListener.clickedtwo(auxAdventure);

            }
        });

    }

    //cambio en la data se llama se va a usar para el efecto de cargar data

    @Override
    public void onDataChanged() {
        if (first){
            first = false;
            adventureListener.dataChanged();

        }

    }

    public static class AdventureHolder extends RecyclerView.ViewHolder{

        private TextView name,date;
        private ImageView deleteName;


        public AdventureHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.adventureTv);
            date = itemView.findViewById(R.id.dateTv);
            deleteName = itemView.findViewById(R.id.deleteImgBtn);

        }
    }
}

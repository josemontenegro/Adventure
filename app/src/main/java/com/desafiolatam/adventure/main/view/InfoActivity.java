package com.desafiolatam.adventure.main.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desafiolatam.adventure.R;
import com.desafiolatam.adventure.models.Adventure;

public class InfoActivity extends AppCompatActivity {

    private Adventure adventure;
    private String nameItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //llamar desde el infoActivity al listado de item

        LinearLayout linearLayout = findViewById(R.id.infoActivity);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        //para mostrar un item de la lista

        Adventure adventure = (Adventure) getIntent().getSerializableExtra(MainActivityFragment.ADVENTURE);
        //getActionBar().setTitle((CharSequence) adventure.getName());


        TextView nameItem = findViewById(R.id.nameItem);
        nameItem.setText(adventure.getName());

        TextView infoCategory = findViewById(R.id.infoCategory);
        infoCategory.setText(adventure.getCategory());

        TextView infoDescription = findViewById(R.id.infoDescription);
        infoDescription.setText(adventure.getDescription());

        TextView infoDate = findViewById(R.id.infoDate);
        infoDate.setText(adventure.getDate());




    }


}

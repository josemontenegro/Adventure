package com.desafiolatam.adventure.main;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.desafiolatam.adventure.R;
import com.desafiolatam.adventure.data.CurrentUser;
import com.desafiolatam.adventure.data.EmailSanitized;
import com.desafiolatam.adventure.data.Nodes;
import com.desafiolatam.adventure.login.LoginActivity;
import com.desafiolatam.adventure.models.Adventure;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Adventure adventure = new Adventure();
    CurrentUser user = new CurrentUser();
    String emailSanitized = new EmailSanitized().emailSanitized(user.email());
    private String category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //DatePickerDialog

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        final String today = sdf.format(date);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.save_data);

                Button saveButton = dialog.findViewById(R.id.save);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText saveName = dialog.findViewById(R.id.saveName);
                        String name = saveName.getText().toString();

                        EditText savedescription = dialog.findViewById(R.id.saveDescription);
                        String description = savedescription.getText().toString();



                      final   RadioGroup radioGroup = dialog.findViewById(R.id.saveCategoryRg);
                       int id = radioGroup.getCheckedRadioButtonId();
                       if (id != -1){
                           RadioButton radioButton= radioGroup.findViewById(id);
                            category = radioButton.getText().toString();
                       }else {

                       }

                       //Date

                        // final TextView savedate = dialog.findViewById(R.id.saveDate);
                        String date =today;


//-------------------------------------------------------------------------------------------------------------------------------


                        String key = new Nodes().adventure(emailSanitized).push().getKey();

                        adventure = new Adventure(name, description,category,date, key, emailSanitized);

                        if (name.trim().length() > 0) {
                            //se guarda un nuevo Place con el Name
                            new Nodes().adventure(emailSanitized).child(key).setValue(adventure);

                        }



                        Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                dialog.show();



            }
        });

        TextView textView = findViewById(R.id.logoutTv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(intent);
                                MainActivity.this.finish();
                            }
                        });
            }
        });

        TextView userEmail = findViewById(R.id.activityTV);
        userEmail.setText(new CurrentUser().email());







    }

}

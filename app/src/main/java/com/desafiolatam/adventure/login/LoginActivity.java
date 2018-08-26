package com.desafiolatam.adventure.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.desafiolatam.adventure.main.MainActivity;
import com.desafiolatam.adventure.R;
import com.desafiolatam.adventure.data.CurrentUser;
import com.desafiolatam.adventure.data.EmailSanitized;
import com.desafiolatam.adventure.data.Nodes;
import com.desafiolatam.adventure.models.User;
import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements Callback {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (new CurrentUser().getCurrentUser() != null) {
            logged();
        } else {
            signUp();
        }

        //llamar al login

        RelativeLayout relativeLayout = findViewById(R.id.homeAdventure);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode && RESULT_OK == resultCode){
            logged();
        }
    }

    @Override
    public void signUp() {

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(

                                new AuthUI.IdpConfig.EmailBuilder().build()//,
                                //new AuthUI.IdpConfig.GoogleBuilder().build()

                        ))
                        .setTheme(R.style.LoginTheme)



                        .build(),
                RC_SIGN_IN);

    }

    @Override
    public void logged() {

        User user = new User();

        CurrentUser currentUser = new CurrentUser();
        if (currentUser.getCurrentUser() == null) {
            String key = new EmailSanitized().emailSanitized(currentUser.email());
            user.setEmail(currentUser.email());
            user.setName(currentUser.getCurrentUser().getDisplayName());
            user.setUid(currentUser.uid());
            new Nodes().user(key).setValue(user);

        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();


    }


}


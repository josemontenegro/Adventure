package com.desafiolatam.adventure.main.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.desafiolatam.adventure.R;
import com.desafiolatam.adventure.adapters.AdventureAdapter;
import com.desafiolatam.adventure.data.CurrentUser;
import com.desafiolatam.adventure.data.EmailSanitized;
import com.desafiolatam.adventure.data.Nodes;
import com.desafiolatam.adventure.main.view.InfoActivity;
import com.desafiolatam.adventure.models.Adventure;
import com.desafiolatam.adventure.models.AdventureListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdventureListener{

    public static final String ADVENTURE = "com.desafiolatam.adventure.KEY.ADVENTURE";
    private AdventureAdapter adapter;
    CurrentUser user = new CurrentUser();
    String emailSanitized = new EmailSanitized().emailSanitized(user.email());

    //para que aparesca un dialog antes de cargar la data
    private ProgressDialog progressDialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.show();

        RecyclerView recyclerView = view.findViewById(R.id.adventureRv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //para dividir
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new AdventureAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);

        getActivity().setTitle(new CurrentUser().email());


    }



    @Override
    public void clicked(Adventure adventure) {

        Toast.makeText(getContext(),adventure.getName(), Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getActivity(), InfoActivity.class);
        intent.putExtra(ADVENTURE, adventure);
        startActivity(intent);

        Log.d("hola", adventure.getUid());

    }

    @Override
    public void clickedtwo(Adventure adventure) {

        new Nodes().adventure(emailSanitized).child(adventure.getUid()).removeValue();

    }

    @Override
    public void dataChanged() {
        progressDialog.dismiss();

    }
}

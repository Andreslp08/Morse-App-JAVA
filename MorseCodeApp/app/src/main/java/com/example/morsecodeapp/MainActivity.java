package com.example.morsecodeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragmento fragmento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmento = new Fragmento();
        fragmento.agregarFragmento(this, R.id.contenedorBarraP, new FragmentoBarraP() );
        fragmento.agregarFragmento(this, R.id.contenedorFrame, new FragmentoMenu() );
    }

    public void agregarFragmento( int container, Fragment f){
        fragment = f;
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container, f );
        fragmentTransaction.commit();
    }
}

package com.example.morsecodeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

public class Fragmento extends AppCompatActivity  {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    AppCompatActivity appCompatActivity;

    public void agregarFragmento( AppCompatActivity appCompatActivity, int container, Fragment f){
        this.appCompatActivity = appCompatActivity;
        fragment = f;
        fragmentManager = appCompatActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(container, f );
        fragmentTransaction.commit();
    }

    public void replace( AppCompatActivity appCompatActivity, int container, Fragment f){
        this.appCompatActivity = appCompatActivity;
        fragment = f;
        fragmentManager = appCompatActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(container, f );
        fragmentTransaction.commit();
    }
    public void replaceFtoF( Fragment fragmentPadre, int container, Fragment f){
        fragment = f;
        fragmentManager = fragmentPadre.getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(container, f );
        fragmentTransaction.commit();
    }
}

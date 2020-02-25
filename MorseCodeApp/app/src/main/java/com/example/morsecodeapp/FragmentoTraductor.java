package com.example.morsecodeapp;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class FragmentoTraductor extends Fragment {


    View view;
    private EditText caja1, caja2;
    private Button botonCambiar, botonTeclado, botonLimpiar;
    private TextView textoSentido;
    private String[] sentidoTraduccion = {  "De español a morse", "De morse a español" };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragmento_traductor, container, false);
        caja1 = view.findViewById(R.id.cajaTexto1);
        caja2 = view.findViewById(R.id.cajaTexto2);
        textoSentido = view.findViewById(R.id.textoSentido);
        textoSentido.setText(sentidoTraduccion[0]);
        botonCambiar = view.findViewById(R.id.botonCambiar);
        botonLimpiar = view.findViewById(R.id.botonLimpiar);
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if( textoSentido.getText().equals( sentidoTraduccion[0]) ){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        textoSentido.setText(sentidoTraduccion[1]);
                        botonCambiar.setForeground(getResources().getDrawable(R.drawable.icono_flechas2));
                        caja1.setText("");
                        caja2.setText("");
                        return;
                    }
                }
                else if( textoSentido.getText().equals( sentidoTraduccion[1]) ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        textoSentido.setText(sentidoTraduccion[0]);
                        botonCambiar.setForeground(getResources().getDrawable(R.drawable.icono_flechas1));
                        caja1.setText("");
                        caja2.setText("");
                        return;
                    }
                }
            }
        });
        botonTeclado = view.findViewById(R.id.botonTeclado);
        botonTeclado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( textoSentido.getText().equals(sentidoTraduccion[0])){
                    Teclado teclado = new Teclado( 0);
                    teclado.show(getFragmentManager(), "MyFragment");

                    return;
                }
                else if( textoSentido.getText().equals(sentidoTraduccion[1])){
                        Teclado teclado = new Teclado( 1);
                        teclado.show(getFragmentManager(), "MyFragment");
                        return;
                }
            }
        });
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               caja1.setText("");
               caja2.setText("");
            }
        });
        return view;
    }



}

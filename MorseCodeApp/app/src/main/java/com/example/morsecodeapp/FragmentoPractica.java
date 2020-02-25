package com.example.morsecodeapp;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class FragmentoPractica extends Fragment {


    View view;
    private Logica logica;
    private TextView textViews[];
    private int cantidadTextViews = 0;
    private GridLayout gridLayout;
    private LinearLayout layoutBotones;
    private String textoBotones[] = {".", "-" };
    private Button botones[];
    private EditText cajaTexto;
    private Typeface corbel;
    private Button botonInsertar, botonBorrar;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragmento_practica, container, false);
        corbel = ResourcesCompat.getFont( getActivity(),R.font.impact);
        logica = new Logica();
        cajaTexto = view.findViewById(R.id.cajaTexto);
        gridLayout = view.findViewById(R.id.layoutAbecedario);
        layoutBotones = view.findViewById(R.id.layoutBotones);
        cantidadTextViews = logica.getAlfaNum().length;
        textViews = new TextView[cantidadTextViews];
        crearTextViews();
        botones = new Button[textoBotones.length];
        crearBotones();
        botonInsertar = view.findViewById(R.id.botonInsertar);
        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarCodigo(v);
            }
        });
        botonBorrar = view.findViewById(R.id.botonBorrar);
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarTodo(v);
            }
        });
        return view;
    }
    public void crearTextViews(){
        for (int i = 0; i < cantidadTextViews; i ++){
            textViews[i] = new TextView(getActivity());
            textViews[i].setText( "   " + logica.getAlfaNum()[i] + " = " + logica.getCodigoMorse()[i] + "   ");
            textViews[i].setTextSize(30);
            textViews[i].setTextColor(Color.parseColor("#252525"));
            textViews[i].setTypeface(corbel);
            textViews[i].setGravity(Gravity.CENTER);
            gridLayout.addView(textViews[i]);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void crearBotones(){

        for (int i = 0; i < botones.length; i ++){
            botones[i] = new Button(getActivity());
            botones[i].setText(textoBotones[i]);
            LinearLayout.LayoutParams lpBoton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lpBoton.setMargins( 10, 10, 10, 10);
            botones[i].setLayoutParams(lpBoton);
            botones[i].setTextSize(60);
            botones[i].setTextColor(Color.WHITE);
            botones[i].setTypeface(corbel);
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.ripple_boton);
            botones[i].setBackground( drawable);
            botones[i].setGravity(Gravity.CENTER);
            layoutBotones.addView(botones[i]);
            botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button boton = ( Button ) v;
                    switch (boton.getText().toString()){
                        case ".":
                            logica.agregarCaracter(".");
                            break;
                        case "-":
                            logica.agregarCaracter("-");
                            break;
                    }
                }
            });
        }
    }

    public  void insertarCodigo(View view){
        if( logica.verificarCodMorse( logica.getCadenaPalabra()) == true ){
            cajaTexto.setText( logica.getTexto());
        }
        else{
            Toast toast;
            toast = Toast.makeText(getActivity(), "No se reconoce el codigo, intente de nuevo.", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void borrarTodo(View view ){
        logica.borrarTodo();
        cajaTexto.setText("");
    }

    public void regresar(View view ){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}

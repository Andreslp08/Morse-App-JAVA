package com.example.morsecodeapp;

import android.content.Context;
import android.graphics.Color;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Teclado extends DialogFragment {

    private String[] tecladoNormal = {
            "1", "2", "3", "4", "5","6", "7", "8", "9", "0",
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l", "ñ",
            "z", "x", "c", "v", "b", "n", "m" };
    private String[] tecladoMorse = {
            ".-","-...","-.-.","-..",".",
            "..-.","--.","....","..",".---",
            "-.-",".-..", "--","-.","--.--","---",
            ".--.","--.-",".-.","...","-",
            "..-","...-",".--","-..-", "-.--", "--..",
            ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----" };

    private StringBuilder textoATraducir;
    private ArrayList<String> arrayAtraducir;
    private Button[] botones;
    private int cantidadBotones = 0;
    private GridLayout gridLayout;
    private int tipoTeclado;
    private String[] tecladoAUsar;
    private EditText caja1, caja2;
    private Button botonEspacio, botonBorrar, botonTraducir;
    private Logica logica;

    public Teclado(int tipoTeclado ){
        this.tipoTeclado = tipoTeclado;
        switch (tipoTeclado){
            case 0:
             tecladoAUsar = tecladoNormal;
             break;
            case 1:
                tecladoAUsar = tecladoMorse;
                break;
        }
    }
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.teclado, container, false);
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        logica = new Logica();
        textoATraducir = new StringBuilder();
        arrayAtraducir = new ArrayList<String>();
        gridLayout = view.findViewById(R.id.gridLayoutTecladoNormal);
        botonEspacio = view.findViewById(R.id.botonEspacioT);
        botonBorrar = view.findViewById(R.id.botonBorrarT);
        botonTraducir = view.findViewById(R.id.botonTraducir);
        caja1 = getActivity().findViewById(R.id.cajaTexto1);
        caja2 = getActivity().findViewById(R.id.cajaTexto2);
        cantidadBotones = tecladoNormal.length;
        botones = new Button[cantidadBotones];
        crearBotones();
        botonEspacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch( tipoTeclado){
                    case 0:
                        textoATraducir.append( " ");
                        caja1.setText( textoATraducir );
                        break;
                    case 1:
                        arrayAtraducir.add( " ");
                        textoATraducir.append( " ");
                        caja1.setText( textoATraducir);
                        break;
                }
            }
        });
        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    switch (tipoTeclado){
                        case 0:
                            textoATraducir.deleteCharAt(obtenerUltimoCaracter());
                            caja1.setText(textoATraducir);
                        case 1:
                            arrayAtraducir.remove(obtenerUltimoCaracter(arrayAtraducir));
                            textoATraducir.deleteCharAt(obtenerUltimoCaracter());
                            caja1.setText(textoATraducir);
                            break;
                    }
                }catch (Exception ex){

                }

            }
        });
        botonTraducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (tipoTeclado){
                    case 0:
                        logica.traducirAmorse(textoATraducir);
                        caja2.setText(logica.getTextoTraducido());
                        textoATraducir = new StringBuilder();
                        getDialog().dismiss();
                        break;
                    case 1:
                        logica.traducirAespaniol(arrayAtraducir);
                        caja2.setText(logica.getTextoTraducido());
                        textoATraducir = new StringBuilder();
                        arrayAtraducir = new ArrayList<String>();
                        getDialog().dismiss();
                }

            }
        });
        return view;
    }


    public void crearBotones(){
        LinearLayout.LayoutParams lpBoton = null;
        for( int i = 0; i < cantidadBotones; i++ ){
            botones[i] = new Button(getActivity());
            botones[i].setText(tecladoAUsar[i]);
            botones[i].setTextColor(Color.BLACK);
            botones[i].setGravity(Gravity.CENTER);
            if (tipoTeclado == 0){
                gridLayout.setColumnCount(10);
                gridLayout.setRowCount(4);
                botones[i].setTextSize(11.0f);
                lpBoton = new LinearLayout.LayoutParams(dp_px( getActivity(),32.0f),dp_px( getActivity(),32.0f));
            }
            if (tipoTeclado == 1){
                gridLayout.setColumnCount(8);
                gridLayout.setRowCount(6);
                botones[i].setTextSize(20.0f);
                lpBoton = new LinearLayout.LayoutParams(dp_px( getActivity(),41.0f),dp_px( getActivity(),41.0f));
            }
            lpBoton.setMargins(dp_px(getActivity(),1.0f), dp_px(getActivity(),1.0f), dp_px(getActivity(),dp_px(getActivity(),1.0f)), dp_px(getActivity(),dp_px(getActivity(),1.0f)));
            botones[i].setLayoutParams(lpBoton);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    botones[i].setBackground(getResources().getDrawable(R.drawable.ripple_boton));
                }
            gridLayout.addView(botones[i]);
                botones[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button boton = (Button) v;
                        switch (tipoTeclado){
                            case  0:
                                textoATraducir.append( boton.getText() );
                                caja1.setText(textoATraducir );
                                break;
                            case 1:
                                arrayAtraducir.add( boton.getText().toString());
                                textoATraducir.append( boton.getText() );
                                caja1.setText(textoATraducir );

                                break;
                        }
                    }
                });
        }
    }
    public int obtenerUltimoCaracter(){
        int ultimoCaracter = 0;
            for (int i = 0; i < textoATraducir.length(); i++) {
                if (i == textoATraducir.length()-1) {
                    ultimoCaracter = i;
                    break;
                }
        }
        return ultimoCaracter;
    }
    public int obtenerUltimoCaracter( ArrayList arrayList){
        int ultimoCaracter = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == arrayList.size()-1) {
                ultimoCaracter = i;
                break;
            }
        }
        return ultimoCaracter;
    }

    public int dp_px(Context ctx, float dps) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }
}

package com.example.morsecodeapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class FragmentoMenu extends Fragment {
    private Button[] botones;
    private final String[] textoBotones = { "Practica basica", "Traductor", "Juega y aprende" };
    private Typeface gothic;
    private LinearLayout linearLayout;
    View view;
    Fragmento fragmento;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_menu, container, false );
        fragmento = new Fragmento();
        gothic = ResourcesCompat.getFont(getActivity(), R.font.gothicb);
        botones = new Button[textoBotones.length];
        crearBotones();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void crearBotones() {
        for (int i = 0; i < textoBotones.length; i++) {

            botones[i] = new Button(getActivity());
            botones[i].setBackgroundColor(Color.argb(180, 255, 255, 255));
            botones[i].setText(textoBotones[i]);
            botones[i].setTextColor(Color.WHITE);
            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.ripple_boton2);
            botones[i].setBackground( drawable);
            LinearLayout.LayoutParams lpBotones = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lpBotones.setMargins(50, 30 , 50, 30);
            botones[i].setLayoutParams( lpBotones);
            botones[i].setTypeface(gothic);
            botones[i].setAllCaps(false);
            LinearLayout linearLayout = view.findViewById(R.id.layoutBotones);
            linearLayout.addView(botones[i]);
            botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button boton = (Button) v;
                    if (boton.getText().equals(textoBotones[0])) {
                        fragmento.replaceFtoF(getFragment(), R.id.contenedorFrame, new FragmentoPractica());
                    }
                    else if (boton.getText().equals(textoBotones[1])) {
                        fragmento.replaceFtoF(getFragment(), R.id.contenedorFrame, new FragmentoTraductor());
                    }
                    else if (boton.getText().equals(textoBotones[2])) {
                        fragmento.replaceFtoF(getFragment(), R.id.contenedorFrame, new FragmentoJuego());
                    }
                }
            });
        }
    }

    public Fragment getFragment(){
        return this;
    }

}

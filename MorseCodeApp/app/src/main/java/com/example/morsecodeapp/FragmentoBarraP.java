package com.example.morsecodeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoBarraP extends Fragment {

    View view;
    private Button botonRegresar;
    private Fragmento fragmento;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmento_barrap, container, false );
        fragmento = new Fragmento();
        botonRegresar = view.findViewById(R.id.atras);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmento.replaceFtoF(getFragment(), R.id.contenedorFrame, new FragmentoMenu() );
            }
        });
        return view;
    }

    public Fragment getFragment(){
        return this;
    }
}


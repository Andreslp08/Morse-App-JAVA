package com.example.morsecodeapp;

import android.widget.Toast;

import java.util.ArrayList;

public class Logica {

    private String letraTransformada;
    private String letraEnMorse;
    private boolean codMorseReconocido;
    private String texto;
    private StringBuilder cadenaPalabra;
    private StringBuilder cadenaTexto;
    private StringBuilder textoTraducido;



    private String[] alfaNum = {
            "a","b","c","d","e",
            "f","g","h","i","j",
            "k","l","m","n", "ñ","o",
            "p","q","r","s", "t",
            "u", "v","w","x","y","z",
            "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "0"};

    private String[] codigoMorse = {
            ".-","-...","-.-.","-..",".",
            "..-.","--.","....","..",".---",
            "-.-",".-..", "--","-.","--.--","---",
            ".--.","--.-",".-.","...","-",
            "..-","...-",".--","-..-", "-.--", "--..",
            ".----", "..---", "...--", "....-", ".....",
            "-....", "--...", "---..", "----.", "-----"};

    public String[] getAlfaNum(){
        return alfaNum;
    }

    public String[] getCodigoMorse(){
        return codigoMorse;
    }

    public Logica(){
    codMorseReconocido = false;
    cadenaPalabra = new StringBuilder();
    cadenaTexto = new StringBuilder();
    }


    public void agregarCaracter( String caracter ){
        cadenaPalabra.append( caracter);
    }


    public String getCadenaPalabra(){
        return cadenaPalabra.toString();
    }

    public boolean verificarCodMorse( String codigo ){
        for (int i = 0; i < codigoMorse.length; i ++ ){
            if( codigo.equals(codigoMorse[i])) {
                letraEnMorse = codigoMorse[i];
                cadenaTexto.append( letraEnMorse );
                texto =  cadenaTexto.toString();
                cadenaPalabra = new StringBuilder();
                return true;
            }
        }
        cadenaPalabra = new StringBuilder();
        return false;
    }

    public boolean codMorseReconocido(){
        return codMorseReconocido;
    }

    public String getTexto(){
        return texto;
    }

    public void borrarTodo(){
        cadenaPalabra = new StringBuilder();
        cadenaTexto = new StringBuilder();
        texto = "";
    }

    // traduce correctamente un texto a morse
    public void traducirAmorse( StringBuilder stringBuilder){
        textoTraducido = new StringBuilder();
        for (int i = 0; i < stringBuilder.length(); i ++){
            for( int j = 0; j < codigoMorse.length; j ++ ){
                if (Character.toString( stringBuilder.charAt(i)).equals(alfaNum[j]) ){
                    textoTraducido.append(codigoMorse[j]);
                }
            }
            if (Character.toString( stringBuilder.charAt(i)).equals(" ")){
                textoTraducido.append( " ");
            }
        }
    }

    public void traducirAespaniol(ArrayList arrayList){
        textoTraducido = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i ++){
            for( int j = 0; j < alfaNum.length; j ++ ){
                if (arrayList.get(i).toString().equals(codigoMorse[j]) ){
                    textoTraducido.append(alfaNum[j]);
                }
            }
            if (arrayList.get(i).toString().equals(" ")){
                textoTraducido.append( " ");
            }
        }
    }

    // obtiene el texto traducido no importa el sentido
    public String getTextoTraducido(){
        return textoTraducido.toString();
    }
}


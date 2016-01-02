package com.rzodkiewicz.michal;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 15/10/2015.
 */
public class InputSaver {

    ArrayList<Double> voltageTab;
    ArrayList<Double> currentTab;
    double[] tablicaTemp = new double[10];
    double[] tablica = new double[1000];
    byte[] index = new byte[1];
    byte[] suma = new byte[1];

    public InputSaver(boolean voltage, boolean current){
        if(voltage){
            voltageTab = new ArrayList<>();
        }

        if(current){
            currentTab = new ArrayList<>();
        }

    }

    public double toDouble(byte[] bytes){
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public void saveVoltage(byte[] bytes){
        int i = 0;
        while(i < tablicaTemp.length){
            if(bytes.length != 0){
                if(false/*warunek zlapania indexu*/){
                    index = bytes;
                }else if(false /* warunek zlapania sumy kontrolnej*/){
                    suma = bytes;
                }else {
                    tablicaTemp[i] = this.toDouble(bytes);
                    i++;
                    //this.voltageTab.add(this.toDouble(bytes));
                }


            }else{
                System.out.println("Error during saving voltage byte: byte is null");
            }

        }

        if(this.validate()){
            /* dodaj zawartosc tablicyTemp do glownej tablicy,
                wyczysc tablice temp
                gotowy do odebrania nowej ramki
             */
        }



    }

    public void saveCurrent(byte[] bytes){
        if(bytes.length != 0){
            this.currentTab.add(this.toDouble(bytes));
        }else{
            System.out.println("Error during saving current byte: byte is null");
        }


    }

    private boolean validate(){
        return true;
    }
}

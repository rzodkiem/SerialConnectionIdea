package com.rzodkiewicz.michal;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by michal on 16/10/2015.
 */


public class SerialReader {
    InputStream inputStream;
    byte[] holder = new byte[2000];
    public SerialReader(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public static String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }


    public void run() {
        /*
     * Narazie metoda run pisze, to co odbierze z portu na konsole, wstawi sie tam implementacje jakiejs logiki
     */
        //Scanner scanner = new Scanner();
        byte[] buffer = new byte[1];
        int i = 0;
        try {
            while (i < 1000/*(this.inputStream.read(buffer)) > -1*/) {
                if(this.inputStream.available() == 1){
                    this.inputStream.read(buffer);
                    holder[i] = buffer[0];
                    String result2 = SerialReader.toBinary(buffer);
                    System.out.println("[INFO]: read byte:  " + result2);
                    if(i % 10 == 0){
                        System.out.println("-----------");
                        System.out.println(i);
                        System.out.println("-----------");
                    }
                    i++;

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

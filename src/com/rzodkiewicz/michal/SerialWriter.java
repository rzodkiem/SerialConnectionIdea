package com.rzodkiewicz.michal;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by michal on 16/10/2015.
 */
public class SerialWriter {
    final static byte[] startMeasurement = {0x32};
    final static byte[] startTransmission = {0x33};
    final static byte[] sumaKontrolnaOk = {0x20};
    final static byte[] getSumaKontrolnaError = {0x21};


    OutputStream outputStream;

    public SerialWriter(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }


    public void run(byte[] data) {

        try{
            /*int c = 0;
            while((c = System.in.read()) > -1){
                this.outputStream.write(c);
            }*/
            System.out.println("[INFO]: Writing data");
            System.out.println(toBinary(data));
            this.outputStream.write(data);
            this.outputStream.close();//TODO: Check whether is this correct!
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("[INFO]: Data has been sent correctly");
    }
}

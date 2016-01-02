package com.rzodkiewicz.michal;

/**
 * Created by michal on 20/10/2015.
 */
public class Protocol {
    TwoWaySerialComm twoWaySerialComm = new TwoWaySerialComm();
    SerialWriter serialWriter;
    Measurement measurement = new Measurement();
    SerialReader serialReader;


    public void startTransmission(){
        try{
            serialWriter = new SerialWriter(twoWaySerialComm.connectOut("COM8"));
            serialWriter.run(SerialWriter.startTransmission);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void startMeasurement(){
        try{
            serialWriter = new SerialWriter(twoWaySerialComm.connectOut("COM8"));
            serialWriter.run(SerialWriter.startMeasurement);
            serialReader = measurement.startMeasurement("COM8");
        }catch(Exception e){

        }
    }
}

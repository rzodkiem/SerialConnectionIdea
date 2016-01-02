package com.rzodkiewicz.michal;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by michal on 16/10/2015.
 */
public class Measurement {
    private final static byte[] START_MEASUREMENT_COMMAND = {0x61};
    public SerialReader serialReader;
    public SerialWriter serialWriter;
    public SerialReader startMeasurement(String portName) {
        TwoWaySerialComm twoWaySerialComm = new TwoWaySerialComm();
        InputSaver inputSaver = new InputSaver(true, true);
        try {

            /**
             * serialWriter sends command to start transmission as 0x61
             */
            OutputStream startCommandStream = twoWaySerialComm.connectOut(portName);
            serialWriter = new SerialWriter(startCommandStream);
            serialWriter.run(START_MEASUREMENT_COMMAND);

            /**
             * serialReader receives data that should be sent after start command
             */
            InputStream inputStream = twoWaySerialComm.connectIn(portName);
            serialReader = new SerialReader(inputStream);
            serialReader.run();
            System.out.println("[INFO]: Reading from serial port finished.");

            /**
             * inputSaver object saves received data into desired structure
             * TODO: tutaj jest zapisywanie do tablic, z ktorych bedzie korzystala logika intefrejsu graficznego
             */
            inputSaver.saveCurrent(serialReader.holder);

            //new Thread(new SerialWriter(outputStream)).start();



        }catch(Exception e){
            System.out.println("Error during connection:");
        }

        return serialReader;
    }


}

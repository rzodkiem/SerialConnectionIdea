package com.rzodkiewicz.michal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import gnu.io.*;


/*
 *  portName - nazwa portu, do ktorego podlaczona jest przejsciowka
 *  w metodzie connect ustawiamy boundrate, liczbe bitow danych, liczbe bitow stopu, parzystosc
 *  
 */
public class TwoWaySerialComm {

    public InputStream connectIn(String portName) throws Exception {

        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");

            } else {
                int timeout = 2000;
                CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);
                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);
                    InputStream inputStream= serialPort.getInputStream();
                    return inputStream;


                } else {
                    System.out.println("Error: Port is not a serial port - probably problem with przejsciowka");
                    return null;
                }
            }

        } catch (NoSuchPortException e) {
            System.out.println("Port " + portName + " not recognized. There is a problem with driver or device is not connected properly.");

        }

        return null;


    }

    public OutputStream connectOut(String portName) throws Exception {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");

            } else {
                int timeout = 2000;
                CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);
                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);
                    OutputStream outputStream = serialPort.getOutputStream();
                    return outputStream;


                } else {
                    System.out.println("Error: Port is not a serial port - probably problem with przejsciowka");
                    return null;

                }
            }

        } catch (NoSuchPortException e) {
            System.out.println("Port " + portName + " not recognized. There is a problem with driver or device is not connected properly.");

        }
        return null;

    }












}

package com.lowlevelcommunication.pkg;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by asebak on 9/22/2014.
 */
/*
To fix exception getting serial ports do the following fix
* put rxtx.dll and rxtxserial.dll into c:/program files/java/jdk1.5.0_02/jre/bin
put rxtx.jar into c:/program files/java/jdk1.5.0_02/jre/lib/ext
This is for the HW abstraction on windows.
*/
public class HardwareAbstractionLayer implements SerialPortEventListener {
    private SerialPort serialPort;
    private InputStream input;
    private OutputStream output;
    private static final int TIME_OUT = 2000;
    private static final int DATA_RATE = 9600;
    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    public HardwareAbstractionLayer(){
        System.out.println("Starting Serial Communication");
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            String portName = "";
            switch (this.getCurrentOperatingSystem()) {
                case Windows:
                    portName = "COM15";
                    break;
                case MacOS:
                    break;
                case Linux:
                    break;
                case Other:
                    break;
            }
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
        }

        if (portId == null) {
            System.out.println("MCU Cannot be found");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

            System.out.println(">>>>>>>>>>>> initialised");


        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private OSType getCurrentOperatingSystem(){
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            return OSType.MacOS;
        } else if (OS.indexOf("win") >= 0) {
            return OSType.Windows;
        } else if (OS.indexOf("nux") >= 0) {
            return OSType.Linux;
        } else {
            return OSType.Other;
        }
    }


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                int available = input.available();
                byte chunk[] = new byte[available];
                input.read(chunk, 0, available);

                // Displayed results are codepage dependent
                System.out.print(new String(chunk));
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void send(char c) throws IOException {
        output.write(c);
    }
}

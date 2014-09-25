package com.lowlevelcommunication.pkg;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

/**
 * Created by asebak on 9/22/2014.
 */
/*
To fix exception getting serial ports do the following fix
* put rxtx.dll and rxtxserial.dll into c:/program files/java/jdk1.5.0_02/jre/bin
put rxtx.jar into c:/program files/java/jdk1.5.0_02/jre/lib/ext
This is for the HW abstraction on windows.
*/
public class EmbeddedDevice {
    public EmbeddedDevice(){
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // iterate through, looking for the port
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        }
    }
}

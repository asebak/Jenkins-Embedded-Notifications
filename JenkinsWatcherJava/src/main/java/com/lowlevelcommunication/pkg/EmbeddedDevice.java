package com.lowlevelcommunication.pkg;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

/**
 * Created by asebak on 9/22/2014.
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

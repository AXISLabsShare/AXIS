/**
*  Copyright 2017 AXIS Labs
*
*  @Author: Nabeel Ahmed, Firmware Engineer
*/

metadata {
    definition (name: "AXIS Gear", namespace: "axis", author: "AXIS Labs") {  
        capability "Actuator"
        capability "Switch"
        capability "Switch Level"
        //capability "Sensor"        
        //capability "Battery"
        //capability "Temperature Measurement"
        capability "Window Shade"


        fingerprint profileId: "0201", inClusters: "0000, 0004, 0005, 0006, 0008, 0100", manufacturer: "AXIS", model: "GR-ZB01-W", deviceJoinName: "AXIS Gear"
        //fingerprint profileId: "0104", inClusters: "0000, 0003, 0004, 0005, 0006, 0008, FF00", outClusters: "0019", manufacturer: "MRVL", model: "MZ100", deviceJoinName: "Wemo Bulb"
    }

	tiles(scale: 2) {
    	standardTile(name:'shade', "shade", "device.switch", width: 4, height: 4, canChangeIcon: true) {
            state "off", label: "Open", action: "switch.on", //label: '${currentValue}'
                  icon: "st.Home.home9", backgroundColor: "#79b821" //icon: "st.Home.home9"
            state "on", label: "Closed", action: "switch.off", //label: '${currentValue}'
                  icon: "st.Home.home9", backgroundColor: "#6021b8" //icon: "st.Home.home9"
        }

		controlTile("levelSliderControl", "device.level", "slider",
     	  	     height: 2, width: 6) {
    		state "level", action:"switch level.setLevel" //
		}
        
        
        main "shade" // or maybe "Shade Control"
        //details('shade', 'levelSliderControl')
        //details('levelSliderControl', 'otherTile', 'anotherTile') //adjustment and order of tiles
	}
    /*
        tiles(scale: 2) {
        multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
            tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
                attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#79b821", nextState:"turningOff"
                attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
                attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.light.on", backgroundColor:"#79b821", nextState:"turningOff"
                attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.light.off", backgroundColor:"#ffffff", nextState:"turningOn"
            }
            tileAttribute ("device.level", key: "SLIDER_CONTROL") {
                attributeState "level", action:"switch level.setLevel"
            }
        }
        standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
            state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
        }
        main "switch"
        details(["switch", "refresh"])
    }
    */
}

def on() {
    zigbee.on()
}

def off() {
    zigbee.off()
}

def setLevel(value) {
    zigbee.setLevel(value)
}

def open() {
   zigbee.off()
}

def close() {
    zigbee.on()
}
/*

def refresh() {
    return zigbee.readAttribute(0x0006, 0x0000) +
        zigbee.readAttribute(0x0008, 0x0000) +
        zigbee.configureReporting(0x0006, 0x0000, 0x10, 0, 600, null) +
        zigbee.configureReporting(0x0008, 0x0000, 0x20, 1, 3600, 0x01)
}

def configure() {
    log.debug "Configuring Reporting and Bindings."

    return zigbee.configureReporting(0x0006, 0x0000, 0x10, 0, 600, null) +
        zigbee.configureReporting(0x0008, 0x0000, 0x20, 1, 3600, 0x01) +
        zigbee.readAttribute(0x0006, 0x0000) +
        zigbee.readAttribute(0x0008, 0x0000)
}

*/
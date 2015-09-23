package poc.logitech.com.logitechpoc.bean;

import java.util.ArrayList;

/**
 * Created by rajendhiran on 9/23/2015.
 */

public class DevicesBean
{
    ArrayList<Devices> devices;

    public DevicesBean()
    {
        devices = new ArrayList<Devices>();
    }

    public ArrayList<Devices> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devices> devices) {
        this.devices = devices;
    }

    public class Devices {
        private String deviceType;
        private String model;
        private String name;

        public Devices() {
            deviceType = "";
            model = "";
            name = "";
        }

        public String getDeviceType() {
            return deviceType;
        }


        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }


        public String getModel() {
            return model;
        }


        public void setModel(String model) {
            this.model = model;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}




public class Truck {
    private String truckNo;
    private String vehicleName;
    private int Capacity;
    private String driverName;

    public Truck(){

    }
    public Truck(String truckNo, String vehicleName, int capacity, String driverName) {
        this.truckNo = truckNo;
        this.vehicleName = vehicleName;
        this.Capacity = capacity;
        this.driverName = driverName;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "truckNo='" + truckNo + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", Capacity=" + Capacity +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}

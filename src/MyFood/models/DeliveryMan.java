package MyFood.models;

public class DeliveryMan extends User {
    private String vehicle;
    private String licensePlate;

    public DeliveryMan() {

    }

    public DeliveryMan(String name, String email, String password, String address, String vehicle, String licensePlate) {
        super(name, email, password, address);
        this.vehicle = vehicle;
        this.licensePlate = licensePlate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getAtribute(String nomeAtributo) {
        if (nomeAtributo.equals("veiculo")) {
            return getVehicle();
        }
        else if(nomeAtributo.equals("placa")) {
            return getLicensePlate();
        }
        else {
            return super.getAtribute(nomeAtributo);
        }
    }
}

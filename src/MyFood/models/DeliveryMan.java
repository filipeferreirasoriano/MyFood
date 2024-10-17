package MyFood.models;

public class DeliveryMan extends User {
    private String vehicle;
    private String licensePlate;
    private Boolean inDelivery;

    public DeliveryMan() {
        super();
    }

    public DeliveryMan(String name, String email, String password, String address, String vehicle, String licensePlate) {
        super(name, email, password, address);
        this.vehicle = vehicle;
        this.licensePlate = licensePlate;
        inDelivery = false;
        this.setType("DeliveryMan");
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

    public Boolean getInDelivery() {
        return inDelivery;
    }

    public void setInDelivery(Boolean inDelivery) {
        this.inDelivery = inDelivery;
    }

    @Override
    public String getAttribute(String nomeAtributo) {
        if (nomeAtributo.equals("veiculo")) {
            return getVehicle();
        }
        else if(nomeAtributo.equals("placa")) {
            return getLicensePlate();
        }
        else {
            return super.getAttribute(nomeAtributo);
        }
    }
}

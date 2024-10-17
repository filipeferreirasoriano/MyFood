package MyFood.models;

import java.util.UUID;

public class Delivery {
    private int id;
    private ShoppingCart shoppingCart;
    private DeliveryMan deliveryMan;
    private String address;

    public Delivery() {
    }

    public Delivery(ShoppingCart shoppingCart, DeliveryMan deliveryMan, String address) {
        id = UUID.randomUUID().hashCode();
        this.shoppingCart = shoppingCart;
        this.deliveryMan = deliveryMan;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

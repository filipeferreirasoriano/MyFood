package MyFood.Order;

import MyFood.Product.Product;

import java.util.ArrayList;
import java.util.UUID;

public class ShoppingCart {
    private int clientId;
    private int enterpriseId;
    private int orderId;
    private boolean openOrder;
    private ArrayList<Product> products;

    public ShoppingCart() {

    }

    public ShoppingCart(int clientId, int enterpriseId) {
        this.clientId = clientId;
        this.enterpriseId = enterpriseId;
        this.orderId = UUID.randomUUID().hashCode();
        openOrder = true;
        this.products = new ArrayList<>();
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public boolean isOpenOrder() {
        return openOrder;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOpenOrder(boolean openOrder) {
        this.openOrder = openOrder;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getValue();
        }
        return totalPrice;
    }
}

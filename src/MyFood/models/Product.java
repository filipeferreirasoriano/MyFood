package MyFood.models;

import java.util.UUID;

public class Product {
    private int id;
    private String name;
    private Double value;
    private String category;

    public Product(String name, Double value, String category) {
        this.id = UUID.randomUUID().hashCode();
        this.name = name;
        this.value = value;
        this.category = category;
    }
    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

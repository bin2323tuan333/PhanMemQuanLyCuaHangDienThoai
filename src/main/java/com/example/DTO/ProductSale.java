package com.example.DTO;

public class ProductSale {
    private int id;
    private String name;
    private String category;
    private int sold;

    public ProductSale(int id, String name, String category, int sold) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.sold = sold;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getSold() { return sold; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setSold(int sold) { this.sold = sold; }
}

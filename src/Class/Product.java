/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author Admin
 */
public class Product extends ProductType{
    private String ID;
    private String ProductName;
    private int Price;

    public Product(String ID, String ProductName, int Price, String IDType, String TypeName) {
        super(IDType, TypeName);
        this.ID = ID;
        this.ProductName = ProductName;
        this.Price = Price;
    }

    public Product() {
    }

    public Product(String ID, String ProductName, int Price) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.Price = Price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    
}

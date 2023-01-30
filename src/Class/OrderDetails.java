/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author Admin
 */
public class OrderDetails {
    String IDDetails,IdOder, IDproduct;
    int amount;

    public OrderDetails(String IDDetails, String IdOder, String IDproduct, int amount) {
        this.IDDetails = IDDetails;
        this.IdOder = IdOder;
        this.IDproduct = IDproduct;
        this.amount = amount;
    }

    public String getIDDetails() {
        return IDDetails;
    }

    public void setIDDetails(String IDDetails) {
        this.IDDetails = IDDetails;
    }

    public String getIdOder() {
        return IdOder;
    }

    public void setIdOder(String IdOder) {
        this.IdOder = IdOder;
    }

    public String getIDproduct() {
        return IDproduct;
    }

    public void setIDproduct(String IDproduct) {
        this.IDproduct = IDproduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

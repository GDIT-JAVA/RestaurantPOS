/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Aspire2 Student
 */
public class Payment extends BaseModel {
    
    
    private Order order; 
    private double totalPaid;
    
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
     public String getCreatedAt(){
        return createdAt;
    } 
    
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }
    
    public double getTotalPaid(){
        return totalPaid;
    }
    
    public void setTotalPaid(double totalPaid){
        this.totalPaid = totalPaid;
    }
    
    public boolean getIsActive(){
        return isActive;
    }
    
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }
    
}

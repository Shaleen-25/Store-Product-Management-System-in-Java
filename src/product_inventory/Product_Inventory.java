
package product_inventory;

public class Product_Inventory {

    
    private int id;
    private String name;
    private String add_Date;
    private float price;
    private byte[] picture;
    
    public Product_Inventory(int pid, String pname, String pdate, float pprice, byte[] ppicture){
        this.id=pid;
        this.name=pname;
        this.add_Date=pdate;
        this.price=pprice;
        this.picture=ppicture;
        
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd_Date() {
        return add_Date;
    }

    public void setAdd_Date(String add_Date) {
        this.add_Date = add_Date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
  
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

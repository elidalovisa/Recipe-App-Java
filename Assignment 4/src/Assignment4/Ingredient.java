import java.io.Serializable;

public class Ingredient implements Serializable  {
  String name;
  String unit;
  Double value;
  Double price;
  Double baseCost;

  // Constructor
  public Ingredient(String name, String unit, double value, double price) {
    this.name = name;
    this.unit = unit;
    this.value = value;
    this.price = price;
    this.baseCost = this.price / this.value;
  }
  
  public String toString() {
    return String.format("%s; %.2f %s; Price: %.2f", name, value, unit, price);
  }
  

}

package Assignment;
import java.io.Serializable;

public class Ingredient implements Serializable  {

  private static final long serialVersionUID = 1234568L;

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
    this.baseCost = /*this.price / this.value; */ calculateBaseCost();
  }
  
  public String toString() {
    return String.format("%s; %.2f %s; Price: %.2f", name, value, unit, price);
  }
  
  public Double calculateBaseCost() {
    if(this.unit.equals("Pieces")) {
    this.value = Math.ceil(this.value/100);
    System.out.println("basecost value: " + this.value);
    }
    this.baseCost = this.price / this.value;
    return this.baseCost;
  }
  

}

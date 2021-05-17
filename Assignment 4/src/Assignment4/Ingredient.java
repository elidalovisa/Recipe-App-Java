public class Ingredient {
  String name;
  String unit;
  Double value;
  Double price;

  // Constructor
  public Ingredient(String name, String unit, double value, double price) {
    this.name = name;
    this.unit = unit;
    this.value = value;
    this.price = price;
  }
  
/*
  public String toString() {
    return String.format("%s; %d  %d; : %s; Price:", name, value, unit, price);
  }
  */
}

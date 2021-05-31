//package Assignment4;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Recipe implements Serializable {
  String name;
  int portions;
  ListIngredients ingredients = new ListIngredients();
  ArrayList<Double> amounts = new ArrayList<Double>();
  String comment;
  String instruction;
  int totalCost;

  // Constructor for name
  public Recipe(String name) {
    this.name = name;
  }

  // Constructor
  public Recipe(String name, int portions, List<Ingredient> ingredients, List<Double> amounts, String instructions) {
  }

  public void setPortions(int portions) {
    this.portions = portions;
  }

  public void addIngredient(Ingredient ingredient) {
    ingredients.addIngredient(ingredient);
  }

  public void addComment(String comment) {
    this.comment = comment;
  }

  public void addInstructions(String instruction) {
    this.instruction = instruction;
  }

  public String toString() {
    return String.format("%s; Portions: %d; Ingredients: %s; Comment: %s; Instructions: %s; totalCost: %d", name, portions, ingredients.toString(), comment, instruction, ingredients.getTotalCost());
  }

  public void calculateIngredients(int wantedPortions) {
    ingredients.calculateIng(wantedPortions);
  }
}

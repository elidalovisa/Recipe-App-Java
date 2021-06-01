import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.io.Serializable;

import java.io.*;

public class ListIngredients implements Serializable {

  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

  // Constructor
  public ListIngredients() {
  }

  // Add ingredient and check so name is unique.
  public int addIngredient(Ingredient ingredient) {
    if (checkIfNameExist(ingredient.name)) {
      return -1;
    }
    ingredients.add(ingredient);
    saveIngredientsToDisk();
    return 0;
  }

  public boolean checkIfNameExist(String name) {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    for (int i = 0; i < savedIngredients.size(); i++) {
      System.out.println(name);
      if (savedIngredients.get(i).name.equals(name)) {
        return true;
      }
    }
    return false;
  }

  // Iterate and return all names.
  public ArrayList<String> getAllNames() {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    return savedIngredients.stream().map(s -> s.name).collect(Collectors.toCollection(ArrayList<String>::new));
  }

  // Iterate and return selected ingredient.
  public void getIngredient(String name) {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    for (int i = 0; i < savedIngredients.size(); i++) {
      if (savedIngredients.get(i).name.equals(name)) {
        System.out.println(savedIngredients.get(i).name + " " + savedIngredients.get(i).unit + " " + savedIngredients.get(i).value
            + " " + savedIngredients.get(i).price);
        // make a toString()
      }
    }
  }
  
  // Calculate amount and price of ingredients after amount of portions.
  public void calculateIng(int wantedPortions) {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    for (int i = 0; i < savedIngredients.size(); i++) {
      savedIngredients.get(i).value = savedIngredients.get(i).value / savedIngredients.get(i).value;
      //Math.ceil(ingredients.get(i).value);
      savedIngredients.get(i).price = savedIngredients.get(i).price / savedIngredients.size();
    }
    for (int j = 0; j < savedIngredients.size(); j++) {
      savedIngredients.get(j).value = savedIngredients.get(j).value * wantedPortions;
      savedIngredients.get(j).price = savedIngredients.get(j).price * wantedPortions;
    }
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    if (!this.ingredients.isEmpty()) { // If there is no ingredients return empty string.
      str.append(this.ingredients.get(0));
    }
    for (int i = 0; i < this.ingredients.size(); i++) {
      str.append(" ");
      str.append(ingredients.get(i).name);
      str.append(" ");
      str.append(ingredients.get(i).value);
      str.append(" ");
      str.append(ingredients.get(i).unit);
      str.append(" ");
      str.append("Price:" + ingredients.get(i).price);
    }
    return str.toString();
  }

  public void removeIngredient(String name) {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    System.out.println(savedIngredients);
    for (int i = 0; i < savedIngredients.size(); i++) {
      if (savedIngredients.get(i).name.equals(name)) {
        savedIngredients.remove(savedIngredients.get(i));
        System.out.println(savedIngredients);
       // Update saved file.
        try {
          FileOutputStream fos = new FileOutputStream("ingredients.txt");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(savedIngredients);
      oos.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }   
        return; // return true and false
      }
    }
    return;
  }

  // Calculate total cost of recipe.
  public int getTotalCost() {
    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    int sum = 0;
    for (int i = 0; i < savedIngredients.size(); i++) {
      sum += savedIngredients.get(i).price;
    }
    return sum;
  }

  public void saveIngredientsToDisk() {
    File ingredientsDisk = new File("ingredients.txt");

    TextMenu menu = new TextMenu();
    var savedIngredients = menu.readIngredients();
    savedIngredients.addAll(ingredients);
    try {
      FileOutputStream fos = new FileOutputStream(ingredientsDisk);
  ObjectOutputStream oos = new ObjectOutputStream(fos);
  oos.writeObject(savedIngredients);
  oos.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }   
}
}

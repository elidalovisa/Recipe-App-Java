package Assignment;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.Serializable;

import java.io.*;

public class ListIngredients implements Serializable {
  private static final long serialVersionUID = 1234567L;

  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  private double totalCost;

  // Constructor
  public ListIngredients() {
    this.totalCost = 0;
  }

  public int size() {
    return ingredients.size();
  }

  public Ingredient get(int index) {
    return ingredients.get(index);
  }

  public boolean contains(String name) {
    int i = 0;
    boolean contain = ingredients.get(i).name.equals(name);
    for (i = 0; i < ingredients.size(); i++) {
      if (contain)
        break;
    }
    return contain;
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

  public void addIngredientRecipe(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  public boolean checkIfNameExist(String name) {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    if (savedIngredients.size() == 0) {
      return false;
    }

    for (int i = 0; i < savedIngredients.size(); i++) {
      if (savedIngredients.get(i).name.equals(name)) {
        return true;
      }
    }
    return false;
  }

  // Iterate and return all names.
  public ArrayList<String> getAllNames() {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    return savedIngredients.stream().map(s -> s.name).collect(Collectors.toCollection(ArrayList<String>::new));
  }

  // Iterate and return selected ingredient.
  public void getIngredient(String name) {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    for (int i = 0; i < savedIngredients.size(); i++) {
      if (savedIngredients.get(i).name.equals(name)) {
        System.out.println(savedIngredients.get(i).toString());
      }
    }
  }

  // Calculate amount and price of ingredients after amount of portions.
  public double calculatePrice(int wantedPortions) {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    int total = 0;
    for (int i = 0; i < savedIngredients.size(); i++) {
      total += savedIngredients.get(i).baseCost * wantedPortions;
    }
    return total;
  }

  // Calculate amount and price of ingredients after amount of portions.
  public int calculateTotalPrice() {
    int sum = 0;
    for (int i = 0; i < ingredients.size(); i++) {
      // System.out.println(ingredients.get(i).price);
      sum += ingredients.get(i).price;
    }
    return sum;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    if (this.ingredients.isEmpty()) { // If there is no ingredients return empty string.
      str.append("");
    }
    for (int i = 0; i < this.ingredients.size(); i++) {
      str.append(ingredients.get(i).name);
      str.append(" ");
      str.append(ingredients.get(i).value);
      str.append(" ");
      str.append(ingredients.get(i).unit);
      str.append(",");
    }
    return str.toString();
  }

  public void removeIngredient(String name) {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    for (int i = 0; i < savedIngredients.size(); i++) {
      if (savedIngredients.get(i).name.equals(name)) {
        savedIngredients.remove(savedIngredients.get(i));
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
        return;
      }
    }
    return;
  }

  // Calculate total cost of recipe.
  public int getTotalCost(int portions) {
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
    int sum = 0;
    for (int i = 0; i < savedIngredients.size(); i++) {
      sum += savedIngredients.get(i).baseCost * portions;
    }
    return sum;
  }

  public void saveIngredientsToDisk() {
    File ingredientsDisk = new File("ingredients.txt");
    TextMenu menu = new TextMenu();
    ArrayList<Ingredient> savedIngredients = new ArrayList<Ingredient>();
    savedIngredients = menu.readIngredients();
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

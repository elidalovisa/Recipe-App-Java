import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListIngredients {
  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

  // Constructor
  public ListIngredients() {
    // ingredients = new ArrayList<Ingredient>());
  }

  // Add ingredient and check so name is unique.
  public int addIngredient(Ingredient ingredient) {
    if (checkIfNameExist(ingredient.name)) {
      return -1;
    }
    ingredients.add(ingredient);
    return 0;
  }

  public boolean checkIfNameExist(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      System.out.println(name);
      if (ingredients.get(i).name.equals(name)) {
        return true;
      }
    }
    return false;
  }

  // Iterate and return all names.
  public ArrayList<String> getAllNames() {
    return ingredients.stream().map(s -> s.name).collect(Collectors.toCollection(ArrayList<String>::new));
  }

  // Iterate and return all ingredients.
  public void getAllIngredients(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
        System.out.println(ingredients.get(i).name + " " + ingredients.get(i).unit + " " + ingredients.get(i).value
            + " " + ingredients.get(i).price);
      }
    }
  }

  public void removeIngredient(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
      ingredients.remove(ingredients.get(i));
        return; // return true and false
      }
    }
    return;
  }
}

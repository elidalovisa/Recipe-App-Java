import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListIngredients {

  ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
  ArrayList<Double> portionCost = new ArrayList<Double>(); // Array to store portion cost.
  ArrayList<Double> portionValue = new ArrayList<Double>(); // Array to store value of each ingredient.
  ArrayList<Double> onePortion = new ArrayList<Double>(); // Array to store one portion.
  ArrayList<Double> wholePortions = new ArrayList<Double>(); 

  ArrayList<String> portionsForRecipe = new ArrayList<String>(); // Array to store wanted portions for a recipe.
  ArrayList<Double> onePortionPrice = new ArrayList<Double>(); // Array to store price per ingredient for 1 portion.
  ArrayList<Double> wantedPortionsPrice = new ArrayList<Double>(); // Array to store price per ingredient for wanted portions.
  ArrayList<String> recipeToReturn = new ArrayList<String>(); 

  // Constructor
  public ListIngredients() {
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

  // Iterate and return selected ingredient.
  public void getIngredient(String name) {
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
        System.out.println(ingredients.get(i).name + " " + ingredients.get(i).unit + " " + ingredients.get(i).value
            + " " + ingredients.get(i).price);
        // make a toString()
      }
    }
  }

  // Iterate and return value for ingredients in recipe.
  public ArrayList<Double> getValue(int number) {
    for (int i = 0; i < number; i++) {
      portionValue.add(ingredients.get(i).value);
    }
    return portionValue;
  }

  public ArrayList<Double> getPrice(int number) {
    for (int i = 0; i < number; i++) {
      portionCost.add(ingredients.get(i).price);
    }
    return portionCost;
  }

  // Find out how many ingredients a recipe has.
  public int numberOfIngredients() {
    int result = 0;
    for (int i = 0; i < this.ingredients.size(); i++) {
      result++;
    }
    return result;
  }

  // Iterate and return value of each ingredient.
  public ArrayList<String> updatePortions(int number, ArrayList<Double> ingredientsValue, double wantedPortions, double actualPortions, ArrayList<Double> pricePerIngredient, String name, String instruction, String comment) {
    for (int i = 0; i < number; i++) {
      // Find out how much is needed for 1 portion
      onePortion.add(ingredients.get(i).value / actualPortions);
    }

    for (int i = 0; i < number; i++) {
      // Find out how much is needed for 1 portion
      wholePortions.add(onePortion.get(i) * wantedPortions);
      Math.round(wholePortions.get(i));
    }

    for (int j = 0; j < number; j++) {
      // Find out how much is needed for wanted portions
      portionsForRecipe.add(ingredients.get(j).name + " " + wholePortions.get(j) + " " + ingredients.get(j).unit);
      //  Math.round(portionsForRecipe.get(j));
    }
    
    for (int k = 0; k < number; k++) {
      // Find out what each ingredient cost for 1 portion.
      onePortionPrice.add(pricePerIngredient.get(k) / actualPortions);
    }

    double sum = 0;
    for (int l = 0; l < number; l++) {
      // Find out what each ingredient cost for wanted portion.
      wantedPortionsPrice.add(pricePerIngredient.get(l) * actualPortions);
      sum += wantedPortionsPrice.get(l);
    }


    for (int m = 0; m < number; m++) {
      recipeToReturn.add("Name of Recipe: " + name + " " + ingredients.get(m).name + " " + portionsForRecipe.get(m) + " " + ingredients.get(m).unit + " " + "Instructions: " + instruction + " " + comment + "Total price: " + sum);
    }


   
   // System.out.println(onePortion);
   // System.out.println(onePortionPrice);
  // System.out.println(wantedPortionsPrice);
  //  System.out.println(portionsForRecipe);
    return recipeToReturn;
  }

public void calculateIng(int wantedPortions) {
  for(int i = 0; i < this.ingredients.size(); i++) {
    ingredients.get(i).value = ingredients.get(i).value / ingredients.get(i).value;
    ingredients.get(i).price = ingredients.get(i).price / ingredients.size();
    }
    for(int j = 0; j < this.ingredients.size(); j++) {
      ingredients.get(j).value = ingredients.get(j).value * wantedPortions;
      ingredients.get(j).price = ingredients.get(j).price * wantedPortions;
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
    for (int i = 0; i < this.ingredients.size(); i++) {
      if (ingredients.get(i).name.equals(name)) {
        ingredients.remove(ingredients.get(i));
        return; // return true and false
      }
    }
    return;
  }

  // Calculate total cost of recipe.
  public int getTotalCost() {
    int sum = 0;
    for (int i = 0; i < ingredients.size(); i++) {
      sum += ingredients.get(i).price;
    }
    return sum;
  }

  // Update ingredients after portions.
  public void updateIngredients(double wantedPortions) {
    double result;
    for (int i = 0; i < ingredients.size(); i++) {
      // find out how much is needed for 1 portion
      double onePortion = ingredients.get(i).value / ingredients.get(i).value;
      // result = this.ingredients.get(i).value / wantedPortions;
      System.out.println(this.ingredients.get(i).value);
    }
  }
}

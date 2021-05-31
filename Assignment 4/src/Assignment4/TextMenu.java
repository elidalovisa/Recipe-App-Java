import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.Serializable;

public class TextMenu {

  ListIngredients ingredients;
  ArrayList<Recipe> recipe = new ArrayList<Recipe>();
  

  public TextMenu() {
    ingredients = new ListIngredients();
  }

  public void MainMenu() {
    Scanner scanner = new Scanner(System.in);

    var run = true;
    while (run) {
      System.out.println("______________________");
      System.out.println(" ");
      System.out.println("Press 0 to exit program");
      System.out.println("Press 1 to add ingredient");
      System.out.println("Press 2 to list all ingredients");
      System.out.println("Press 3 to add recipe");
      System.out.println("Press 4 to show all recipes and select one recipe.");
      System.out.println("Press 5 to delete a recipe");

      System.out.println("______________________");
      int choice = scanner.nextInt();
      if(choice <= 5) {
      switch (choice) {
        case 0:
        saveRecipeToDisk();
        ingredients.saveIngredientsToDisk();          
          run = false;
          return;

        case 1: // To add ingredient
          var ingredient = addIngredientsMenu(scanner);
          ingredients.addIngredient(ingredient);
          break;

        case 2: // List all ingredients
          listAllIngredients(scanner);
          break;

        case 3: // List all recipes
          addRecipeMenu(scanner);
          break;

        case 4: // List all recipes
          showRecipe(scanner);
          break;

          case 5: // Delete a recip5
          removeRecipe(scanner);
          break;

        default:
          break;
      }
    } else {
      System.out.println("Please try again");
      run = true;
    }
    }
    scanner.close();
  }

  private Ingredient addIngredientsMenu(Scanner scanner) {
    System.out.println("What ingredient do you want to add?");
    scanner.nextLine();
    String name = scanner.nextLine();
    // Check if name already exist.
    while (ingredients.checkIfNameExist(name)) {
      System.out.println("Ingredient already exist, type an other one.");
      name = scanner.nextLine();
    }

    System.out.println("What unit press 1 for Liter, press 2 for pieces");
    String unit = " ";
    Integer btn = scanner.nextInt();
    if(btn == 1) {
     unit = "Liter";
    } if (btn == 2) {
     unit = "Pieces";
    }
    System.out.println("How much?");
    double value = scanner.nextDouble();
    System.out.println("Whats the price");
    Double price = scanner.nextDouble();

    // Create a new ingredient
    Ingredient ingredient = new Ingredient(name, unit, value, price);
    System.out.println("Ingredient added.");
    return ingredient;
  }

  private Ingredient addIngredientsToRecipe(Scanner scanner) {
    System.out.println("What ingredient do you want to add?");
    scanner.nextLine();
    String name = scanner.nextLine();
    // Check if ingredient exist.
    while (!ingredients.checkIfNameExist(name)) {
      System.out.println("Ingredient missing.");
      name = scanner.nextLine();
    }

    System.out.println("What unit?");
    String unit = scanner.nextLine();
    System.out.println("How much?");
    double value = scanner.nextDouble();
    System.out.println("Whats the price");
    Double price = scanner.nextDouble();

    // Create a new ingredient
    Ingredient ingredient = new Ingredient(name, unit, value, price);
    System.out.println("Ingredient added.");
    return ingredient;
  }

  private void listAllIngredients(Scanner scanner) {
    var allNames = ingredients.getAllNames();
    for (int i = 0; i < allNames.size(); i++) {
      System.out.printf("%d %s", i, allNames.get(i) + "\n");
    } 
    readIngredients();

    System.out.println("Please write ingredient name to show details and/or remove ingredient.");
    scanner.nextLine();
    String name = scanner.nextLine();
    ingredients.getIngredient(name);
    System.out.println("Press 0 to remove ingredient.");
    System.out.println("Press 1 to keep ingredient.");
    int option = scanner.nextInt();
    if (option == 0) {
      ingredients.removeIngredient(name);
      System.out.println("Ingredient removed.");
    }
  }

  public void readIngredients() {
    StringBuilder ingredients = new StringBuilder();
  
    try {
  File file = new File("C:\\users\\Elida\\Desktop\\ingredients.txt");
  
  
  Scanner scan = new Scanner(file);
  while(scan.hasNext()) {
  String str = scan.nextLine();
  ingredients.append(str + "\n"); 
  }
  System.out.println(ingredients);
  scan.close();
  
  
  } catch (IOException e) {
  System.out.println("An error occurred.");
  e.printStackTrace();
  }
  }

  private void addRecipeMenu(Scanner scanner) {
    System.out.println("Add recipe name");
    scanner.nextLine();
    String name = scanner.nextLine();
    Recipe recipe = new Recipe(name);

    System.out.println("How many portions?");
    int portions = scanner.nextInt();

    recipe.setPortions(portions);
    System.out.println("______________________");
    System.out.println(" ");
    System.out.println("Press 1 to add ingredients to recipe" + "\n" + "Press 0 to stop adding ingredients.");
    System.out.println("______________________");

    boolean addIngredientToRecipe = true;
    while (addIngredientToRecipe) {
      var ingredient = addIngredientsToRecipe(scanner);
      recipe.addIngredient(ingredient);
      int stop = scanner.nextInt();
      if (stop == 0) {
        addIngredientToRecipe = false;
      }
    }
    // Calculate total cost for recipe after all ingredients are added.
    ingredients.getTotalCost();

    System.out.println("Add instructions");
    scanner.nextLine();
    String instruction = scanner.nextLine();
    recipe.addInstructions(instruction);

    System.out.println("Add comments");
    String comment = scanner.nextLine();
    recipe.addComment(comment);
    System.out.println("Recipe added.");
    System.out.println(recipe);
    this.recipe.add(recipe);
  }

  private void showRecipe(Scanner scanner) {
    readRecipe();
    /*System.out.println("List of all recipes:");
    // Print list with all recipes.
    for (int i = 0; i < recipe.size(); i++) {
      System.out.println(recipe.get(i).name + "\n");
    } */
    System.out.println("Write the name of the recipe that you would like to see.");
    scanner.nextLine();
    String name = scanner.nextLine();
    System.out.println("How many portions would you like?");
    int portions = scanner.nextInt();
    getRecipe(name, portions);
    scanner.nextLine();
  }
 
  // Get specific recipe and specific ingredients and amount.
  public void getRecipe(String name, int wantedPortions) {
    for (int i = 0; i < recipe.size(); i++) {
      if (recipe.get(i).name.equals(name)) {
        recipe.get(i).portions = wantedPortions;
        recipe.get(i).calculateIngredients(wantedPortions);
        System.out.println(recipe.get(i));

      }
    }
  }

  public void removeRecipe(Scanner scanner) {
    System.out.println("List of all recipes:");
    // Print list with all recipes.
    for (int i = 0; i < recipe.size(); i++) {
      System.out.println(recipe.get(i).name + "\n");
    }
    System.out.println("Write the name of the recipe that you would like delete.");
    scanner.nextLine();
    String name = scanner.nextLine();
    for (int i = 0; i < recipe.size(); i++) {
      if (recipe.get(i).name.equals(name)) {
        recipe.remove(recipe.get(i));
        System.out.println("Recipe " + name + " deleted.");
        return; // return true and false
      }
    }
    return;
  }
  
  private void saveRecipeToDisk() {
try {
    FileOutputStream fos = new FileOutputStream("t.tmp");
ObjectOutputStream oos = new ObjectOutputStream(fos);
oos.writeObject(recipe);
oos.close();
   /* try {
      File outFileRecipe = new File("C:\\users\\Elida\\Desktop\\recipe.txt");
      PrintWriter printer = new PrintWriter(outFileRecipe);
      for (int i = 0; i < recipe.size(); i++) {
       printer.print(recipe.get(i));
      }
      printer.close();
      */
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } 
  }
   

  public void readRecipe() {
   ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

    try {
      FileInputStream fis = new FileInputStream("t.tmp");
      ObjectInputStream ois = new ObjectInputStream(fis);
      recipeList = (ArrayList<Recipe>) ois.readObject();
      ois.close();

/*File file = new File("C:\\users\\Elida\\Desktop\\recipe.txt");

Scanner scan = new Scanner(file);
while(scan.hasNext()) {
 // String str = scan.nextLine();
  recipe.add(scan.next()); 
}
System.out.println(recipe);
scan.close();
*/

} catch (IOException e) {
  System.out.println("An error occurred.");
  e.printStackTrace();
} catch (ClassNotFoundException e) {
  System.out.println("An error occurred.");
}
System.out.println(recipeList);
}

private ArrayList<Recipe> list() {
  return new ArrayList<Recipe>();
}
}
package Assignment;

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

    boolean run = true;
    while (run) {
      System.out.println("______________________");
      System.out.println(" ");
      System.out.println("Press 0 to exit program");
      System.out.println("Press 1 to add ingredient");
      System.out.println("Press 2 to list all ingredients");
      System.out.println("Press 3 to add recipe");
      System.out.println("Press 4 to show all recipes and select one recipe.");
      System.out.println("Press 5 to delete a recipe");
      System.out.println("Press 6 to search for a recipe");

      System.out.println("______________________");
      int choice = scanner.nextInt();
      if(choice <= 6) {
      switch (choice) {
        case 0:
       // saveRecipeToDisk();
       // ingredients.saveIngredientsToDisk();          
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

          case 5: // Delete a recipe
          removeRecipe(scanner);

          break;

          case 6: // Search for a recipe
          Search(scanner);
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

    System.out.println("What unit press 1 for liter, press 2 for pieces, press 3 for dl");
    String unit = " ";
    Integer btn = scanner.nextInt();
    if(btn == 1) {
     unit = "Liter";
    } if (btn == 2) {
     unit = "Pieces";
    } if (btn == 3) {
      unit = "dl";
    }
    System.out.println("How much?");
    double value = scanner.nextDouble();
    System.out.println("Whats the price");
    Double price = scanner.nextDouble();

    // Create a new ingredient
    Ingredient ingredient = new Ingredient(name, unit, value, price);
    System.out.println("Ingredient added.");
    ingredients.saveIngredientsToDisk();
    return ingredient;
  }

  private Ingredient addIngredientsToRecipe(Scanner scanner) {
    System.out.println("What ingredient do you want to add?");
    scanner.nextLine();
    String name = scanner.nextLine();
    // Check if ingredient exist.
    while (!ingredients.checkIfNameExist(name)) {
      System.out.println("Ingredient missing. Type an other one.");
      name = scanner.nextLine();
    }
    System.out.println("What unit? Press 1 for liter, press 2 for pieces, press 3 for dl");
    String unit = " ";
    Integer btn = scanner.nextInt();
    if(btn == 1) {
     unit = "Liter";
    } if (btn == 2) {
     unit = "Pieces";
    } if (btn == 3) {
      unit = "dl";
    }
    System.out.println("How much?");
    double value = scanner.nextDouble();
    System.out.println("Whats the price");
    Double price = scanner.nextDouble();

    // Create a new ingredient
    Ingredient ingredient = new Ingredient(name, unit, value, price);
    System.out.println("Ingredient added.");
    System.out.println(ingredient);

    return ingredient;
  }

  private void listAllIngredients(Scanner scanner) {
    var allNames = ingredients.getAllNames();
    for (int i = 0; i < allNames.size(); i++) {
      System.out.printf("%d %s", i, allNames.get(i) + "\n");
    } 
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

  public ArrayList<Ingredient> readIngredients() {
    ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
    try {
      FileInputStream fis = new FileInputStream("ingredients.txt");
      ObjectInputStream ois = new ObjectInputStream(fis);
      ingredientsList = (ArrayList<Ingredient>) ois.readObject();
      ois.close();
    } 
    catch(FileNotFoundException e) {
      return ingredientsList;
    } 
    catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("An error occurred.");
    }
    return ingredientsList;
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

    boolean keepAddIngredient = true;
    while (keepAddIngredient) {
      var ingredient = addIngredientsToRecipe(scanner);
      System.out.println(ingredient);
      recipe.addIngredient(ingredient);
      int stop = scanner.nextInt();
      if (stop == 0) {
        keepAddIngredient = false;
      }
    }
   
    // Calculate total cost for recipe after all ingredients are added.
 
    // ingredients.getTotalCost(portions);
      recipe.calculatePrice();
    System.out.println("Add instructions");
    scanner.nextLine();
    String instruction = scanner.nextLine();
    recipe.addInstructions(instruction);

    System.out.println("Add comments");
    String comment = scanner.nextLine();
    recipe.addComment(comment);
    //recipe.addTotalCost();
    System.out.println("Recipe added.");
    System.out.println(recipe);
    this.recipe.add(recipe);
    saveRecipeToDisk();
  }

  private void showRecipe(Scanner scanner) {
   var savedRecipes = readRecipe();
    System.out.println("List of all recipes:");
    // Print list with all recipes.
    for (int i = 0; i < savedRecipes.size(); i++) {
      System.out.println(savedRecipes.get(i).name);
    } 
    System.out.println("Write the name of the recipe that you would like to see.");
    scanner.nextLine();
    String name = scanner.nextLine();
    System.out.println("How many portions would you like?");
    int portions = scanner.nextInt();
    getRecipe(name, portions);
    //scanner.nextLine();
  }
 
  // Get specific recipe and specific ingredients and amount.
  public void getRecipe(String name, int wantedPortions) {
    var savedRecipes = readRecipe();
    for (int i = 0; i < savedRecipes.size(); i++) {
      if (savedRecipes.get(i).name.equals(name)) {
        savedRecipes.get(i).updatePortions(wantedPortions);
       // savedRecipes.get(i).addTotalCost();
        System.out.println(savedRecipes.get(i));
      }
    }
  }

  public void removeRecipe(Scanner scanner) {
    
    var savedRecipes = readRecipe();
    System.out.println("List of all recipes:");
    // Print list with all recipes.
    for (int i = 0; i < savedRecipes.size(); i++) {
      System.out.println(savedRecipes.get(i).name);
    }
    System.out.println("Write the name of the recipe that you would like to delete.");
    scanner.nextLine();
    String name = scanner.nextLine();
    for (int i = 0; i < savedRecipes .size(); i++) {
      if (savedRecipes .get(i).name.equals(name)) {
        savedRecipes .remove(savedRecipes.get(i));
        System.out.println("Recipe " + name + " deleted.");
         // Update saved file.
         try {
          FileOutputStream fos = new FileOutputStream("recipe.txt");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(savedRecipes);
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
  
  private void saveRecipeToDisk() {
    File fileRecipe = new File("recipe.txt");
    // Check if there is anything saved to file.
    var savedRecipes = readRecipe();
    // Add new recipes to already saved recipes.
    savedRecipes.addAll(recipe);
    try {		
    FileOutputStream fos = new FileOutputStream(fileRecipe);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(savedRecipes);
    oos.close();
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        } 
  }
   

  public ArrayList<Recipe> readRecipe() {
   ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    try {
      FileInputStream fis = new FileInputStream("recipe.txt");
      ObjectInputStream ois = new ObjectInputStream(fis);
      recipeList = (ArrayList<Recipe>) ois.readObject();
      ois.close();
      } catch (FileNotFoundException e) {
        return recipeList;
      }
      catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        System.out.println("An error occurred.");
      }
      return recipeList;
  }


public void Search(Scanner scanner) {
System.out.println("Press 1 to search for max price of recipe. Press 2 to search for ingredient in recipe.");
 // scanner.nextInt();
  int choice = scanner.nextInt();
  if(choice == 1) {
    System.out.println("Write maxprice of recipe that you would like to serach for.");
    int number = scanner.nextInt();
    SearchForRecipe searchRecipe = new SearchForRecipe();
    searchRecipe.searchMaxPrice(number);
  } 
  if (choice == 2) {
  System.out.println("Write ingredient that you would like the recipe to contain.");
  scanner.nextLine();
  String name = scanner.nextLine();
  SearchForRecipe searchRecipe = new SearchForRecipe();
  searchRecipe.searchIngredient(name);
  }
}
}
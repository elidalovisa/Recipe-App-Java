import java.util.ArrayList;
import java.util.Scanner;

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
      System.out.println("______________________");
      int choice = scanner.nextInt();
      switch (choice) {
        case 0:
          run = false;
          // todo save file.
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

        default:
          break;
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

    System.out.println("What unit");
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

  private Ingredient addIngredientsToRecipe(Scanner scanner) {
    System.out.println("What ingredient do you want to add?");
    scanner.nextLine();
    String name = scanner.nextLine();
    // Check if ingredient exist.
    while (!ingredients.checkIfNameExist(name)) {
      System.out.println("Ingredient missing.");
      name = scanner.nextLine();
    }

    System.out.println("What unit");
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
    System.out.println("List of all recipes:");
    // Print list with all recipes.
    for (int i = 0; i < recipe.size(); i++) {
      System.out.println(recipe.get(i).name + "\n");
    }
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
        double actualPortions = recipe.get(i).portions;
        recipe.get(i).portions = wantedPortions;

        int number = recipe.get(i).ingredients.numberOfIngredients(); // Find out how many ingredients there is in the
        recipe.get(i).ingredients.getValue(number); // Get value of each ingredient.

        recipe.get(i).calculateIngredients(wantedPortions);
        System.out.println(recipe.get(i));
        
        var pricePerIngredient = recipe.get(i).ingredients.getPrice(number);
        var instruction = recipe.get(i).instruction;
        var comment = recipe.get(i).instruction;
       //System.out.println(recipe.get(i).ingredients.updatePortions(number, ingredientsValue, wantedPortions, actualPortions pricePerIngredient, name, instruction, comment));


        // return array with updated value and change value in existing array?

        // Check if actual and wanted protions diff.
        // if (actualPortions < wantedPortions || actualPortions > wantedPortions) {

        // }

      }
    }
  }
}
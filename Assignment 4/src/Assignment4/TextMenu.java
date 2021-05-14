import java.util.Scanner;

public class TextMenu {

  ListIngredients ingredients;
  Recipe recipe;

  public TextMenu() {
    ingredients = new ListIngredients();
  }

  public void MainMenu() {
    Scanner scanner = new Scanner(System.in);

    var run = true;
    while (run) {
      System.out.println("Hello welcome!");
      System.out.println("Press 1 to add ingredient");
      System.out.println("Press 2 to list all ingredients");
      System.out.println("Press 0 to exit program");
      int choice = scanner.nextInt();
      switch (choice) {
        case 0:
          run = false;
          // todo save file.
          return;

        case 1: // To add ingredient
          // ingredients.addIngredient();
          addIngredientsMenu(scanner);
          // scanner.close();
          // MainMenu();
          break;

        case 2: // Lista ll ingredients
          listAllIngredients(scanner);
          break;

        default:
          break;
      }
    }

    scanner.close();
  }

  private void addIngredientsMenu(Scanner scanner) {
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
    ingredients.addIngredient(ingredient);
    System.out.println("Ingredient added.");
  }

  private void listAllIngredients(Scanner scanner) {
    var allNames = ingredients.getAllNames();
    for (int i = 0; i < allNames.size(); i++) {
      System.out.printf("%d %s", i, allNames.get(i) + "\n");
    }

    System.out.println("Please write ingredient name to show details and/or remove ingredient.");
    scanner.nextLine();
    String name = scanner.nextLine();
    ingredients.getAllIngredients(name);

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
    // Check if name already exist.
    /*
     * while (ingredients.checkIfNameExist(name)) {
     * System.out.println("Ingredient already exist, type an other one."); name =
     * scanner.nextLine(); }
     */
    System.out.println("How many portions?");
    int portions = scanner.nextInt();

    // Add ingredients (list)
    // Add amount of each ingredient
    // Add comments
    // Add total price

  }
}

import java.util.Scanner;

public class TextMenu {

  ListIngredients ingredients;

  public TextMenu() {
    ingredients = new ListIngredients();
  }

  public void MainMenu() {
    System.out.println("Hello welcome! Press 1 to add ingredient");
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();

    switch (choice) {
      case 1: // To add ingredient
        // ingredients.addIngredient();
        addIngredientsMenu();
        break;

      default:
        break;
    }

    scanner.close();
  }

  private void addIngredientsMenu() {
    System.out.println("What ingredient do you want to add?");
    Scanner scanner = new Scanner(System.in);
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
    scanner.close();

    // Create a new ingredient
    Ingredient ingredient = new Ingredient(name, unit, value, price);
    ingredients.addIngredient(ingredient);
    System.out.println("Ingredient added.");
  }
}

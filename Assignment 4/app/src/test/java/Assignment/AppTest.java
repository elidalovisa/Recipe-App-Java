/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Assignment;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();
       // assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test 
    public void testMethodAdd() {
    ArrayList<String> ingredients = new ArrayList<String>();
    ingredients.add("milk");
    assertEquals(1,ingredients.size());
    }
}

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * This class should not be modified in any way.
 * Develop the Hero class to satisfy all of the tests below.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HeroTest {
	
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(output));
	}

	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	@Order(1)
	void makeHero() {
		Hero t = new Hero("Tester");
		assertNotNull(t);
	}

	@Test
	@Order(2)
	void setRole() {
		Hero t = new Hero("Tester");
		
		String roles[] = {"Warrior", "Priest", "Wizard", "Thief"};
		
		//Valid
		for (String role: roles) {
			t.setRole(role);
			assertEquals(role, t.getRole());
		}
		
		//Invalid
		t.setRole("Farmer");
		assertEquals("Unassigned", t.getRole());
		//Should print "Invalid role"
		assertEquals("Invalid role", output.toString().trim());
	}
	
	@Test
	@Order(3)
	void levelUpExactly() {
		Hero t = new Hero("Tester");
		
		int prevExp = t.getExperience();
		
		assertEquals(0, prevExp);
		
		for (int i = 1; i < 100; i++) {
			t.gainExperience((int)Math.pow(i, 2));
			assertEquals("Tester is now level " + (i + 1) + "!", output.toString().trim());
			output.reset();
			assertEquals(0, t.getExperience());
		}
	}
	
	@Test
	@Order(4)
	void levelUpMultiple() {
		Hero t = new Hero("Tester");
		
		int prevExp = t.getExperience();
		
		assertEquals(0, prevExp);
		
		t.gainExperience(50);
		assertEquals(5, t.getLevel());
		assertEquals(20, t.getExperience());
		
		t.gainExperience(500);
		assertEquals(12, t.getLevel());
		assertEquals(44, t.getExperience());
		
		t.gainExperience(5000);
		assertEquals(26, t.getLevel());
		assertEquals(25, t.getExperience());
		
		t.gainExperience(50000);
		assertEquals(55, t.getLevel());
		assertEquals(1595, t.getExperience());
		
		t.gainExperience(100000);
		assertEquals(78, t.getLevel());
		assertEquals(395, t.getExperience());
		
		t.gainExperience(200000);
		assertEquals(100, t.getLevel());
		assertEquals(27200, t.getExperience());
	}
	
	@Test
	@Order(5)
	void heroToString() {
		Hero t = new Hero("Tester");
		t.setRole("Wizard");
		
		t.gainExperience(10000);
		output.reset();
		
		String description = t.toString();
		assertEquals("Tester the Wizard is level 31 with 545 experience.", description);
	}
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
 * Develop the Party class to satisfy all of the tests below.
 * 
 * Hint: Finish testing the Hero class first 
 * 		 before working on the Party class.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PartyTest {
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
	void makeParty() {
		Party p = new Party();
		assertNotNull(p);
	}
	
	@Test
	@Order(2)
	void addHeroes() {
		Party p = new Party();
		
		Hero h0 = new Hero("Tester 1");
		Hero h1 = new Hero("Tester 2");
		Hero h2 = new Hero("Tester 3");
		
		p.addHero(h0, 0);
		assertEquals(h0, p.getHero(0));
		
		p.addHero(h1, 1);
		assertEquals(h1, p.getHero(1));

		p.addHero(h2, 2);
		assertEquals(h2, p.getHero(2));
	}
	
	@Test
	@Order(3)
	void addSameHeroTwice() {
		Party p = new Party();
		
		Hero h = new Hero("Tester");
		
		p.addHero(h, 0);
		
		p.addHero(h, 0);
		assertEquals("Tester is already in the party.", output.toString().trim());
		output.reset();
		
		p.addHero(h, 2);
		assertEquals("Tester is already in the party.", output.toString().trim());
		
	}
	
	@Test
	@Order(4)
	void removeHeroes() {
		Party p = new Party();
		
		Hero h0 = new Hero("Tester 1");
		Hero h1 = new Hero("Tetser 2");
		Hero h2 = new Hero("Tester 3");
		
		p.addHero(h0, 0);
		p.addHero(h1, 1);
		p.addHero(h2, 2);
		
		p.removeHero(0);
		assertNull(p.getHero(0));
		
		p.removeHero(1);
		assertNull(p.getHero(1));
		
		p.removeHero(2);
		assertNull(p.getHero(2));
	}
	
	@Test
	@Order(5)
	void partyExperience() {
		Party p = new Party();
		
		Hero h0 = new Hero("Tester 1");
		Hero h1 = new Hero("Tester 2");
		Hero h2 = new Hero("Tester 3");
		
		p.addHero(h0, 0);
		
		p.gainExperience(100);
		
		assertEquals("The party gained 100 experience.", output.toString().split(System.lineSeparator())[0]);
		assertEquals(7, h0.getLevel());
		assertEquals(1, h1.getLevel());
		assertEquals(1, h2.getLevel());
		
		p.addHero(h1, 1);
		
		p.gainExperience(200);
		
		assertEquals(10, h0.getLevel());
		assertEquals(8, h1.getLevel());
		assertEquals(1, h2.getLevel());
		
		p.addHero(h2, 2);
		p.removeHero(0);
		
		p.gainExperience(1000);
		
		assertEquals(10, h0.getLevel());
		assertEquals(15, h1.getLevel());
		assertEquals(14, h2.getLevel());
	}
	
	@Test
	@Order(6)
	void partyToString() {
		Party p = new Party();
		
		Hero h0 = new Hero("Aragorn");
		h0.setRole("Warrior");
		Hero h1 = new Hero("Gandalf");
		h1.setRole("Wizard");
		Hero h2 = new Hero("Bilbo");
		h2.setRole("Thief");
		
		p.addHero(h0, 0);
		
		p.gainExperience(100);
		
		p.addHero(h1, 1);
		
		p.gainExperience(200);
		
		p.addHero(h2, 2);
		p.removeHero(0);
		
		p.gainExperience(1000);
		
		p.addHero(h0, 0);
		
		String description = p.toString();
		assertEquals("""
				Party:
				Aragorn the Warrior is level 10 with 15 experience.
				Gandalf the Wizard is level 15 with 185 experience.
				Bilbo the Thief is level 14 with 181 experience.
				"""
				, 
				description);
	}
}
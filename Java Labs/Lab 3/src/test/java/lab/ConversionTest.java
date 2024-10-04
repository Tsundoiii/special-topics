package lab;
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
 * Develop the Controller class to satisfy all of the tests below.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConversionTest {
	
	@Test
	void convertM() {
		double m = 25;
		
		assertEquals(2500, Controller.m2cm(m), .01);
		
		assertEquals(27.340249999999997, Controller.m2y(m), .01);
		
		assertEquals(984.2525, Controller.m2in(m), .01);
	}

	@Test
	void convertCM() {
		double cm = 250;
		
		assertEquals(2.5, Controller.cm2m(cm), .01);
		
		assertEquals(2.7340332458442695, Controller.cm2y(cm), .01);
		
		assertEquals(98.4251968503937, Controller.cm2in(cm), .01);
	}
	
	@Test
	void convertY() {
		double y = 15;
		
		assertEquals(540, Controller.y2in(y), .01);
		
		assertEquals(1371.6, Controller.y2cm(y), .01);
		
		assertEquals(13.716, Controller.y2m(y), .01);
	}
	
	@Test
	void convertIN() {
		double in = 175;
		
		assertEquals(4.861111111111111, Controller.in2y(in), .01);
		
		assertEquals(4.44500889001778, Controller.in2m(in), .01);
		
		assertEquals(444.5, Controller.in2cm(in), .01);
	}
}

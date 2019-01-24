package cfranc.ilc;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarkovWordTest {

	String[] when2SimpleWords = new String[] {"parapluie", "parachute"};
	@Test
	public void getSimilarity_2SimpleWords_26() {
		
		MarkovWord m = new MarkovWord();
		double expected = 0.33;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[1], 2);
		assertEquals(expected, actual,0.01);		
	}

	@Test
	public void getSimilarity_SameWord_100() {
		
		MarkovWord m = new MarkovWord();
		double expected = 1.0;
		double actual = m.getSimilarity(when2SimpleWords[0],when2SimpleWords[0], 4);
		assertEquals(expected, actual,0.000000001);		
	}
	

}

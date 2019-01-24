package cfranc.ilc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OUAFIK {

	String[] when2SimpleWords = new String[] {"parapluie", "parachute"};
	
	MarkovData d1 = new MarkovData("parapluie",9);
	MarkovData d2 = new MarkovData("parachute",9);
	MarkovData d3 = new MarkovData("dropet",9);
	MarkovData d4 = new MarkovData("draps",9);

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
	
	@Test
	public void common_partialSimilarity() {
		MarkovWord m = new MarkovWord();
		List<MarkovData> dataOne = new ArrayList<MarkovData>();
		List<MarkovData> dataTwo = new ArrayList<MarkovData>();
		dataOne.add(d2);
		dataOne.add(d2);
		
		dataTwo.add(d1);
		dataTwo.add(d2);
		int expected = 2;
		int actual = m.common(dataOne,dataTwo);
		assertEquals(expected, actual);		
	}
	
	@Test
	public void common_SameList() {
		List<MarkovData> dataOne = new ArrayList<MarkovData>();
		List<MarkovData> dataTwo = new ArrayList<MarkovData>();
		dataOne.add(d1);
		dataOne.add(d2);
		dataOne.add(d1);
		MarkovWord m = new MarkovWord();
		int expected = 5;
		int actual = m.common(dataOne,dataOne);
		assertEquals(expected, actual);		
	}
	@Test
	public void union_SameList() {
		List<MarkovData> dataOne = new ArrayList<MarkovData>();
		List<MarkovData> dataTwo = new ArrayList<MarkovData>();
		dataOne.add(d2);
		dataOne.add(d2);
		MarkovWord m = new MarkovWord();
		int expected = 2;
		int actual = m.union(dataOne,dataOne);
		assertEquals(expected, actual);
		
	}
	@Test
	public void union_notSameList() {
		MarkovWord m = new MarkovWord();
		List<MarkovData> dataOne = new ArrayList<MarkovData>();
		List<MarkovData> dataTwo = new ArrayList<MarkovData>();
		dataOne.add(d2);
		dataOne.add(d2);
		
		dataTwo.add(d4);
		dataTwo.add(d3);
		int expected = 4;
		int actual = m.union(dataOne,dataTwo);
		assertEquals(expected, actual);
	}
	  @Test
		public void processString_emptyString() {
		  MarkovWord m = new MarkovWord();
		        String sentence = "";
	                List<MarkovData> expected =  new ArrayList<MarkovData>();
	                expected.add(new MarkovData("%%", 1));
	        List<MarkovData> actual = m.processString(sentence, 2);
	        assertArrayEquals(expected.toArray(), actual.toArray());
		}
	        
	        
	        @Test
		public void processString_oneWord() {
	        	MarkovWord m = new MarkovWord();
	        	String sentence = "hellw";
	                List<MarkovData> expected =  new ArrayList<MarkovData>();
	                expected.add(new MarkovData("%h", 1));
	                expected.add(new MarkovData("he", 1));
	                expected.add(new MarkovData("el", 1));
	                expected.add(new MarkovData("ll", 1));
	                expected.add(new MarkovData("lw", 1));
	                expected.add(new MarkovData("w%", 1));
			List<MarkovData> actual = m.processString(sentence, 2);
			assertArrayEquals(expected.toArray(), actual.toArray());
		}
	        
	        
	        @Test
		public void contains_empty_String() {
	        	MarkovWord m = new MarkovWord();
		     MarkovData data = new MarkovData("hellw", 2);
	             String sentence = "";
	             double expected = 0;
	             double actual = m.contains(sentence);
	             assertEquals(expected, actual,0.000000001);
		}
	        
	        
	        
	        @Test
		public void contains_differentsWords() {
	        	MarkovWord m = new MarkovWord();
	        	MarkovData data = new MarkovData("world", 1);
	             String sentence = "mur";
	             double expected = 0;
	             double actual = m.contains(sentence);
	             assertEquals(expected, actual,0.000000001);
		}
	        
	        @Test
		public void contains_wordContainedAtIndex3() {
	             MarkovWord markovWord = new MarkovWord("patate", 2);
	             String phrase = "ta";
	             double expected = 3.0;
	             double actual = markovWord.contains(phrase);
	             assertEquals(expected, actual,0.000000001);
		}    
	        
	         @Test
		public void contains_sameWords() {

	             MarkovWord markovWord = new MarkovWord("patate", 2);
	             String phrase = "patate";
	             int expected = 0;
	             int actual = markovWord.contains(phrase);
	             assertEquals(expected, actual,0.000000001);
		}
	        
	        
	      
	    
	}
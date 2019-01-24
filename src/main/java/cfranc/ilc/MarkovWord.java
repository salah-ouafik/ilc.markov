package cfranc.ilc;

import java.util.ArrayList;
import java.util.List;

public class MarkovWord {
	 
	private ArrayList<MarkovData> data = new ArrayList<MarkovData>();

	public MarkovWord() {

	}

	public MarkovWord(String dataword, int n) {
		data = (ArrayList<MarkovData>)processString(dataword, n);
	}
	
	public double getSimilarity(String wordOne, String wordTwo, int n) {
		List<MarkovData> res1 = processString(wordOne, n);
		 //displayResult(res1);
		List<MarkovData> res2 = processString(wordTwo, n);
		// displayResult(res2);
		int c = common(res1, res2);
		int u = union(res1, res2);
		double sim = (double) c / (double) u;

		return sim;
	}

	int common(List<MarkovData> One, List<MarkovData> Two) {
		int res = 0;

		for (int i = 0; i < One.size(); i++) {
			for (int j = 0; j < Two.size(); j++) {
				if (One.get(i).theWord.equalsIgnoreCase(Two.get(j).theWord))
					res++;
			}
		}
		return res;
	}

	int union(List<MarkovData> One, List<MarkovData> Two) {
		List<MarkovData> t = One;

		for (int i = 0; i < Two.size(); i++) {
			int pos = -1;
			boolean found = false;
			for (int j = 0; j < t.size() && !found; j++) {
				if (Two.get(i).theWord.equalsIgnoreCase(t.get(j).theWord)) {
					found = true;
				}
				pos = j;
			}

			if (!found) {
				MarkovData r = Two.get(i);
				t.add(r);
			}
		}

		return t.size();
	}

	List<MarkovData> processString(String c, int n) {
		List<MarkovData> t = new ArrayList<MarkovData>();

		String spacer = "";
		for (int i = 0; i < n - 1; i++) {
			spacer = spacer + "%";
		}
		c = spacer + c + spacer;

		for (int i = 0; i < c.length(); i++) {
			if (i <= (c.length() - n)) {
				if (contains(c.substring(i, n + i)) > 0) {
					t.get(i).setTheCount(data.get(i).getTheCount() + 1);
				} else {
					t.add(new MarkovData(c.substring(i, n + i), 1));
				}
			}
		}
		return t;
	}

	int contains(String c) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).theWord.equalsIgnoreCase(c))
				return i;
		}
		return 0;
	}
	 
	private void displayResult(List<MarkovData> d) {
		for (int i = 0; i < d.size(); i++) {
			System.out.println(d.get(i).theWord + " occurred " + d.get(i).theCount + " times");
		}
	}
}

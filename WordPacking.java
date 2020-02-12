import java.util.*;

public class WordPacking {
	
	public static String getAuthorName() {
		
		return "Brandon Haripersad";
	}
	
	public static String getRyersonID() {
		
		return "500769285";
	}
	
	public static int maxWordCount(List<String> words, List<String> used) {
		
		int minbinsize = 0;
		int index = 0;
		int bindex = 0;
		boolean WordsLeft = true;
		boolean firstpass = true;
		List<List<String>> tester = new ArrayList<List<String>>();
		List<String> usedtemp = new ArrayList<String>(used); 
		boolean incomp = false;
		int worddex = 0;
		
		while (worddex < words.size()) {
			
			while (WordsLeft == true) {
				
				String table[] = {" ", " ", " ", " ", " "};

				if (firstpass) {
	
					tester.add(new ArrayList<String>());
					tester.get(bindex).add(words.get(worddex));
				
					for (int i=0; i<table.length; i++) {
					
						table[i] = table[i] + words.get(worddex).charAt(i);
					}
				
					usedtemp.add(words.get(worddex));
				}
			
				for (String word: words)  {
				
					incomp = false;
			
					if (usedtemp.contains(word)) {
					
						continue;
					}
				
					if (bindex == tester.size()) {
						
						tester.add(new ArrayList<String>());
						tester.get(bindex).add(word);
					
						for (int i=0; i<table.length; i++) {
						
							table[i] = table[i] + word.charAt(i);
						}
					
						usedtemp.add(word);
					}
			
					for (int p=0; p<word.length(); p++) {
					
						if (table[p].indexOf(word.charAt(p)) >= 0) {
				
						incomp = true;
						break;
						
					} 
				}
				
					if (incomp == false) {
					
						tester.get(bindex).add(word);
						usedtemp.add(word);
					
						for (int q=0; q<table.length; q++) {
						
							table[q] += word.charAt(q);
						}
					
						continue;
					
					} else {
					
						continue;
					}
				}
				
				if (usedtemp.size() == words.size()) {
					
					WordsLeft = false;
				}
			
				bindex++;
				firstpass = false;
			}
			
			if (minbinsize == 0) {
				
				minbinsize = tester.size();
			} else {
				
				if (tester.size() < minbinsize) {
					
					minbinsize = tester.size();
					index = words.indexOf(tester.get(0).get(0));
				}
			}
			
			tester.clear();
			usedtemp.clear();
			WordsLeft = true;
			firstpass = true;
			bindex = 0;
			worddex++;
			
		}
		
		return index;
		}
	
	public static List<List<String>> wordPack(List<String> words) {
		
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> used = new ArrayList<String>();
		List<String> random = new ArrayList<String>(words);
		Collections.shuffle(random);
		int bindex = 0;
		int maxWordIndex;
		boolean incomp = false;
		boolean WordsLeft = true;
		boolean firstpass = true;
		
		maxWordIndex = maxWordCount(random, used);
		
		while (WordsLeft == true) {
			
			String table[] = {" ", " ", " ", " ", " "};
			
			if (firstpass) {
				
				result.add(new ArrayList<String>());
				result.get(bindex).add(random.get(maxWordIndex));
				
				for (int i=0; i<table.length; i++) {
					
					table[i] = table[i] + random.get(maxWordIndex).charAt(i);
				}
				
				used.add(random.get(maxWordIndex));
			}
			
			for (String word: random) {
				
				incomp = false;
			
				if (used.contains(word)) {
				
					continue;
				}
				
				if (bindex == result.size()) {
					
					result.add(new ArrayList<String>());
					result.get(bindex).add(word);
				
					for (int i=0; i<table.length; i++) {
					
						table[i] = table[i] + word.charAt(i);
					}
				
					used.add(word);
				}
			
				for (int p=0; p<word.length(); p++) {
					
					if (table[p].indexOf(word.charAt(p)) >= 0) {
					
							incomp = true;
							break;
					}
				}
			
				if (incomp == false) {
					
					result.get(bindex).add(word);
					used.add(word);
				
					for (int q=0; q<table.length; q++) {
						
						table[q] += word.charAt(q);
					}
			
					continue;
				
					} else {
				
					continue;
				}
			}
	
		if (used.size() == words.size()) {
			
			WordsLeft = false;
		}
		
		firstpass = false;
		bindex++;
	}
		return result;
	}
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class WordPool {

	LinkedList<String> wordPool = new LinkedList<String>();
	int wordLength;
	char[] patternSet;
	Pattern p;
	
	public WordPool(int length){
		wordLength = length;
		patternSet = new char[wordLength];
		Arrays.fill(patternSet,'_');
		p = calcPattern(patternSet,false,'i');
		try {
			initWP();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public char[] getPatternSet() {
		return patternSet;
	}
	
	public Pattern calcPattern(char[] patternSet, boolean invert, char i) {
		String regex = "(^)";
		for (char c : patternSet) {
			if (c=='_')
				regex+="(.)";
			else
				regex+=(invert&&c==i)?"([^"+c+"])":"(["+c+"])";
		}
		regex+="($)";
		return Pattern.compile(regex);
	}
	
	public Pattern calcPattern(char[] patternSet, char fix) {
		String regex = "(^)";
		for (char c : patternSet) {
			if (c=='_')
				regex+="([^"+fix+"])";
			else
				regex+="(["+c+"])";
		}
		regex+="($)";
		return Pattern.compile(regex);
	}
	
	public boolean randomRoll() {
		int a = (int)(Math.random()*10);
		if (a==3) {
			return false;
		}else {
			return true;
		}
	}
	
	
	public void initWP() throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("dictionary.txt"));
		StringTokenizer st;
		Matcher m;
		String curWord;
		while(true) {
			try {
				st = new StringTokenizer(f.readLine());
				curWord = st.nextToken();
				m = p.matcher(curWord);
				if (m.find()&&randomRoll()) {
					wordPool.add(curWord);
				}
			}catch(Exception e) {
				break;
			}
		}
	}
	
	public LinkedList<String> filter(Pattern p){
		LinkedList<String> filteredList = new LinkedList<String>();
		Matcher m;
		for (String s: wordPool) {
			m = p.matcher(s);
			if (m.find()) {
				filteredList.add(s);
			}
		}
		return filteredList;
	}
	
	public boolean narrow(char c) {
		boolean acceptLetter = true;
		int maxSize = 0;
		char[] tempPattern = new char[wordLength];
		char[] maxTempPattern = new char[wordLength];
		LinkedList<String> largestWordFamily = new LinkedList<String>();
		LinkedList<String> wordFamily;
		System.arraycopy(patternSet, 0, tempPattern, 0, wordLength);
		//try having letter
		for (int i = 0;i<wordLength;i++) {
			if (tempPattern[i]=='_') {
				tempPattern[i]=c;
				wordFamily = filter(calcPattern(tempPattern,c));
				if (wordFamily.size()>maxSize) {
					largestWordFamily = new LinkedList<String>(wordFamily);
					maxSize = wordFamily.size();
					System.arraycopy(tempPattern, 0, maxTempPattern, 0, wordLength);
				}
				tempPattern[i] = '_';
			}
		}
		//--try not having the letter
		for (int i = 0;i<wordLength;i++) {
			if (tempPattern[i]=='_') {
				tempPattern[i]=c;
			}
		}
		wordFamily = filter(calcPattern(tempPattern,true,c));
		if (wordFamily.size()>maxSize) {
			acceptLetter = false;
			largestWordFamily = new LinkedList<String>(wordFamily);
			maxSize = wordFamily.size();
		}
		//---check for duplicates
		if (maxSize==0) {
			String greatestKey ="";
			int freq = 0;
			HashMap<String,Integer> map = new HashMap<String,Integer>();
			for (String x: wordPool) {
				String location = "";
				for (int i = 0;i<wordLength;i++) {
					if (x.charAt(i)==c) {
						location+=i+" ";
					}
				}
				if (map.containsKey(location)) {
					map.replace(location, map.get(location)+1);
				}else {
					map.put(location, 1);
				}
			}
			for (String key : map.keySet()) {
				int val = map.get(key);
				if (val>freq) {
					freq = val;
					greatestKey = key;
				}
			}
			System.arraycopy(patternSet, 0, tempPattern, 0, wordLength);
			String[] seg = greatestKey.split(" ");
			for (String s : seg) 
				tempPattern[Integer.parseInt(s)] = c;
			largestWordFamily = filter(calcPattern(tempPattern,false,'i'));
			System.arraycopy(tempPattern, 0, maxTempPattern, 0, wordLength);
		}
		
		
		if (acceptLetter) {
			System.arraycopy(maxTempPattern, 0, patternSet, 0, wordLength);
			wordPool = new LinkedList<String>(largestWordFamily);
			p = calcPattern(maxTempPattern,false,'i');
		}else {
			wordPool = new LinkedList<String>(largestWordFamily);
		}
		return acceptLetter;
	}
	
	public LinkedList<String> getWordPool(){
		return wordPool;
	}
	
}

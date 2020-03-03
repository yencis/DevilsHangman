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
		p = getPattern();
		try {
			initWP();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Pattern getPattern() {
		String regex = "(^)";
		for (char c : patternSet) {
			if (c=='_')
				regex+="(.)";
			else
				regex+="(["+c+"])";
		}
		regex+="($)";
		return Pattern.compile(regex);
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
				if (m.find()) {
					wordPool.add(curWord);
				}
			}catch(Exception e) {
				break;
			}
		}
	}
	
	public LinkedList<String> getWordPool(){
		return wordPool;
	}
	
}

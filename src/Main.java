import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("dictionary.txt"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		/*
		String line = "calcium";
		String pattern = "(^)(.{7})($)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(line);
		if (m.find()) {
			System.out.println(m.group(8));
		}else {
			System.out.println("No solute");
		}*/
		
		/*WordPool wp = new WordPool(20);
		for (String w:wp.getWordPool())System.out.println(w);
		System.out.println(wp.getWordPool().size());*/
		
	}

}

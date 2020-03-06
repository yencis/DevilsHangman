import java.util.*;
public class Hangman {
	
	private WordPool wp;
	private int guesses;
	private char[] letters;
	private PriorityQueue<Character> usedLetters;
	private Scanner sc;
	private boolean isWin = false;
	
	public Hangman() {
		int length = (int)(Math.random()*15)+2;
		wp = new WordPool(length);
		initialize();
	}
	public void initialize() {
		guesses = 10;
		letters = wp.getPatternSet();
		usedLetters = new PriorityQueue<Character>();
		run();
	}
	
	public void run() {
		
		while(guesses>0&&!isWin) {
			printBoard();
			getGuess();
			checkIfWin();
		}
		System.out.println("The word was "+wp.wordPool.get(0));
		if (isWin) {
			System.out.println("You won!");
		}else {
			System.out.println("You lost :(");
		}
	}
	
	public void printBoard() {
		//System.out.println(wp.wordPool.size()+" "+wp.wordPool.get(0));
		System.out.println("Used characters: ");
		PriorityQueue<Character> pq = new PriorityQueue<Character>(usedLetters);
		while(!pq.isEmpty())System.out.print(pq.remove()+" ");
		System.out.println();
		System.out.println("Guesses: "+guesses);
		for(int i:new int[2])System.out.println();
		for (char c:letters)System.out.print(c+" ");
		System.out.println();
		System.out.println("Your guess: ");
	}
	
	public void getGuess() {
		sc = new Scanner(System.in);
		String s = sc.next();
		char c;
		boolean result = true;
		if (s.length()==1) {
			c = s.charAt(0);
			if (usedLetters.contains(c)) {
				return;
			}
			result = wp.narrow(c);
			usedLetters.add(c);
		}else {
			if (s.contentEquals(wp.wordPool.get(0))&&wp.wordPool.size()==1) {
				result = true;
				isWin = true;
			}else {
				if (wp.wordPool.contains(s)) {
					wp.wordPool.remove(s);
				}
			}
			
		}
		if (result) {
			letters = wp.getPatternSet();
			System.out.println("Correct");
		}else {
			guesses--;
			System.out.println("Incorrect");
		}
	}
	
	public void checkIfWin() {
		for (char c:letters) {
			if (c=='_') {
				return;
			}
		}
		isWin = true;
	}
	
	
}

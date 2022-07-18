package qa.example.hangman;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

	private int lives;
	private List<String> previousLetters;
	private String word;
	private String clue;
	
	public GameLogic() {
		
		lives = 10;
		
		previousLetters = new ArrayList<>();
		
		word = "test";
		
		clue = "-".repeat(word.length());
		
	}

	
	public boolean makeGuess(String guess) {
		
		guess.toLowerCase();
		lives--;
		
		if (guess.length() == 1) { // One Letter

			char guessChar = guess.charAt(0);
			char[] clueSplit = clue.toCharArray();
			for (int i = 0; i < clue.length(); i++) {
				if (guessChar == word.charAt(i)) {
					clueSplit[i] = guessChar;
				}
			}
			clue = String.valueOf(clueSplit);
			
			previousLetters.add(guess);
			
			return word.equals(clue);
		} else { // Word
			return word.equals(guess);
		}
		
	}
	
	
	/// Setters and Getters
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public List<String> getPreviousLetters() {
		return previousLetters;
	}

	public void setPreviousLetters(List<String> previousLetters) {
		this.previousLetters = previousLetters;
	}

	public String getClue() {
		return clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}
	
}

package qa.example.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {

	private int lives;
	private List<String> previousLetters;
	private String word;
	private String clue;
	
	public GameLogic() {

		
		lives = 10;
		previousLetters = new ArrayList<>();
		word = newWord();
		clue = "-".repeat(word.length());
		
		
	}

	
	public boolean makeGuess(String guess) {
		
		guess.toLowerCase();
		lives--;
		
		if (guess.length() == 1) { // One Letter
			
			boolean correct = false;
			
			char guessChar = guess.charAt(0);
			char[] clueSplit = clue.toCharArray();
			for (int i = 0; i < clue.length(); i++) {
				if (guessChar == word.charAt(i)) {
					clueSplit[i] = guessChar;
					correct = true;
				}
			}
			
			if (correct) lives++;
			
			clue = String.valueOf(clueSplit);
			
			letterAdd(guess);
			
			return word.equals(clue);
		} else { // Word
			return word.equals(guess);
		}
		
	}
	
	private void letterAdd(String letter) {
		if (!previousLetters.contains(letter)) {
			previousLetters.add(letter);
		}
	}
	
	private String newWord() {
		try{
		    BufferedReader reader = new BufferedReader(new FileReader("./src/qa/example/hangman/resources/dictionary.txt"));
		    String line = reader.readLine();
		    List<String> words = new ArrayList<String>();
		    while(line != null) {
		        String[] wordsLine = line.split(" ");
		        for(String word : wordsLine) {
		            words.add(word);
		        }
		        line = reader.readLine();
		    }
		    reader.close();
		    Random rand = new Random(System.currentTimeMillis());
		    return words.get(rand.nextInt(words.size()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return "error";
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

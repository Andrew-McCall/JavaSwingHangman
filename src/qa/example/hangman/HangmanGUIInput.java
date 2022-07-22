package qa.example.hangman;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class HangmanGUIInput implements ActionListener {

	private GameLogic logic = new GameLogic();
	
	private HangmanGUIOuput parent;
	public HangmanGUIInput(HangmanGUIOuput parent) {
		this.parent = parent;

		parent.getImage().setIcon(getLivesImage());
		parent.getPreviousGuess().setText(logic.getPreviousLetters().toString());
		parent.getWordClue().setText(logic.getClue());

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/// On submit
		String guess = parent.getGuessInput().getText().replaceAll("[^a-zA-Z]", "");
		parent.getGuessInput().setText("");
		
		if (guess.length() == 0) return;

		if (logic.makeGuess(guess)) {
			
			parent.getWordClue().setText("You Won! ("+logic.getWord()+")");
			parent.getGuessSubmit().setEnabled(false);
			parent.getGuessInput().setEnabled(false);
			
			try {
				BufferedImage imageFile = ImageIO.read(new File("./src/qa/example/hangman/resources/Win.png"));
				parent.getImage().setIcon(new ImageIcon(imageFile.getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
			}catch (Exception err){
				err.printStackTrace();
			}
			
			
		}else if (logic.getLives() == 0){
			
			parent.getWordClue().setText("You Lose! ("+logic.getWord()+")");
			parent.getGuessInput().setEnabled(false);
			parent.getGuessInput().setEnabled(false);
			parent.getImage().setIcon(getLivesImage());	
			
		}else {
			
			parent.getPreviousGuess().setText(logic.getPreviousLetters().toString());
			parent.getWordClue().setText(logic.getClue());
			parent.getImage().setIcon(getLivesImage());	
			
		}
		
		parent.pack();
		
	
	}
	
	private ImageIcon getLivesImage() {
		try {
			BufferedImage imageFile = ImageIO.read(new File("./src/qa/example/hangman/resources/"+logic.getLives()+".png"));
			return new ImageIcon(imageFile.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

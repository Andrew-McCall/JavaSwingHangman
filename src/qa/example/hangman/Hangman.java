package qa.example.hangman;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Hangman implements ActionListener {

	private GameLogic logic = new GameLogic();
	
	private JLabel wordClue;
	private JLabel image;
	private JLabel previousGuess;
	private JTextField guessInput;
	
	private JButton guessSubmit;
	
	public Hangman() {

		// The game window - Parent of game Jpanel which is the parent of all other components
		JFrame window = new JFrame("Hangman");
		
		
		
		JPanel game = new JPanel(new GridLayout(5,1,10,10));
		game.setBorder(new EmptyBorder(20, 20, 20, 20));
		window.add(game);
		
		
		// Text Output 1 - Shows word _ for each missing letter
		wordClue = new JLabel(logic.getClue(), SwingConstants.CENTER);
		wordClue.setFont(new Font("Serif", Font.BOLD, 28));
		game.add(wordClue);
		
		
		// Image Output - Shows the stage of the game
		image = new JLabel(getLivesImage());
		game.add(image);
				
		
		// Text Output 2 - Shows each letter guessed
		previousGuess = new JLabel(logic.getPreviousLetters().toString(), SwingConstants.CENTER);
		previousGuess.setFont(new Font("Serif", Font.BOLD, 14));
		game.add(previousGuess);
		
		
		// Guess - Input and Button
		guessInput = new JTextField();
		guessInput.setHorizontalAlignment(JTextField.CENTER);
		game.add(guessInput);
		
		guessSubmit = new JButton("Guess!");
		
		guessSubmit.addActionListener(this);
		
		game.add(guessSubmit);
		
		
		
		// Window boilerplate - Size and placement
		window.pack();
		window.setSize(300,500);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/// On submit
		String guess = guessInput.getText().replaceAll("[^a-zA-Z]", "");
		guessInput.setText("");
		
		if (guess.length() == 0) return;
		
		if (logic.makeGuess(guess)) {
			
			wordClue.setText("You Won! ("+logic.getWord()+")");
			guessSubmit.setEnabled(false);
			guessInput.setEnabled(false);
			
			try {
				BufferedImage imageFile = ImageIO.read(new File("./src/qa/example/hangman/images/Win.png"));
				image.setIcon(new ImageIcon(imageFile.getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
			}catch (Exception err){
				err.printStackTrace();
			}
			
			
		}else if (logic.getLives() == 0){
			
			wordClue.setText("You Lose! ("+logic.getWord()+")");
			guessSubmit.setEnabled(false);
			guessInput.setEnabled(false);
			image.setIcon(getLivesImage());	
			
		}else {
			
			previousGuess.setText(logic.getPreviousLetters().toString());
			wordClue.setText(logic.getClue());
			image.setIcon(getLivesImage());	
			
		}
		
		
	
	}
	
	private ImageIcon getLivesImage() {
		try {
			BufferedImage imageFile = ImageIO.read(new File("./src/qa/example/hangman/images/"+logic.getLives()+".png"));
			return new ImageIcon(imageFile.getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

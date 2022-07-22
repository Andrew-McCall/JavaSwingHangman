package qa.example.hangman;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HangmanGUIOuput extends JFrame{

	private static final long serialVersionUID = 8454142541070900259L;

	private ActionListener actionListener;
	
	private JLabel wordClue;
	private JLabel image;
	private JLabel previousGuess;
	private JTextField guessInput;
	
	private JButton guessSubmit;
	
	public HangmanGUIOuput() {

		// The game window - Parent of game Jpanel which is the parent of all other components
		this.setTitle("Hangman");
				
		
		JPanel game = new JPanel(new GridLayout(5,1,10,10));
		game.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.add(game);
		
		
		// Text Output 1 - Shows word _ for each missing letter
		wordClue = new JLabel("Loading", SwingConstants.CENTER);
		wordClue.setFont(new Font("Serif", Font.BOLD, 28));
		game.add(wordClue);
		
		
		// Image Output - Shows the stage of the game
		image = new JLabel("", SwingConstants.CENTER);
		game.add(image);
				
		
		// Text Output 2 - Shows each letter guessed
		previousGuess = new JLabel("Loading", SwingConstants.CENTER);
		previousGuess.setFont(new Font("Serif", Font.BOLD, 14));
		game.add(previousGuess);
		
		
		// Guess - Input and Button
		guessInput = new JTextField();
		guessInput.setHorizontalAlignment(JTextField.CENTER);
		game.add(guessInput);
		
		guessSubmit = new JButton("Guess!");
		
		// ActionListener. This child sets all the default variables and so must be after all labels exist;
		actionListener = new HangmanGUIInput(this);
		guessSubmit.addActionListener(actionListener);
		
		game.add(guessSubmit);
		
		// Window boilerplate - Size and placement
		
		this.setMinimumSize(new Dimension(300,500));
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
	}

    // Getters
	public JLabel getWordClue() {
		return wordClue;
	}

	public JLabel getImage() {
		return image;
	}

	public JLabel getPreviousGuess() {
		return previousGuess;
	}

	public JTextField getGuessInput() {
		return guessInput;
	}

	public JButton getGuessSubmit() {
		return guessSubmit;
	}
}

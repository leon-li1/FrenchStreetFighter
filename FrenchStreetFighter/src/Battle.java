import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//This class is the battle sequence in the game
public class Battle extends JFrame implements KeyListener, ActionListener {

	//Constant
	private final int MOVE_AMOUNT = 15;

	//Instance fields
	private Font font = new Font ("Calibri", Font.BOLD, 38);
	private Font font2 = new Font ("Calibri", Font.BOLD, 22);
	private Font font3 = new Font ("Calibri", Font.BOLD, 16);
	private JLabel lblInfo = new JLabel();
	//private JLabel lblInfo2 = new JLabel();
	private JLabel lblCorrect = new JLabel();
	private JLabel lblIncorrect = new JLabel();
	private JLabel player = new JLabel();
	private JLabel enemy = new JLabel();
	private JLabel background = new JLabel();
	private JLabel playerHealthBar = new JLabel();
	private JLabel enemyHealthBar = new JLabel();
	private JLabel lblAnimation = new JLabel();
	private JTextArea question = new JTextArea();
	private JLabel[] lblChoice = new JLabel[4];
	private JTextArea input = new JTextArea();
	private JButton confirm = new JButton();
	private JPanel bPanel = new JPanel();
	private JPanel qPanel = new JPanel();
	private int playerHealth = 200;
	private int enemyHealth = 200;
	private int dx;
	private int key;
	private int keyReleased;
	private boolean leftKey;
	private boolean rightKey;
	private boolean kick;
	private Timer tmrMove = new Timer(20, this);
	private Timer tmrMove2 = new Timer(20, this);
	private Timer tmrAnswer = new Timer(2000, this);
	private QuestionList ql = new QuestionList(new File("vocab.csv"));
	private int index = (int)(Math.random()*42);
	private int stage;
	private int questionsAsked;
	private int correctAnswer;
	private Clip clip;
	private boolean check;

	public	Battle(int stage) throws FileNotFoundException{

		//Initialize stage number
		this.stage = stage;

		//Call other methods
		frameSetup();
		labelSetup();
		updateQuestion(ql.getQuestion().get(index).getQuestion(), ql.getQuestion().get(index).getAnswerA(), ql.getQuestion().get(index).getAnswerB(), ql.getQuestion().get(index).getAnswerC(), ql.getQuestion().get(index).getAnswerD());
		repaint();

		//Add a key listener too the frame
		addKeyListener(this);

		//Show the respective enemy 
		enemy.setIcon(new ImageIcon(new ImageIcon("Images/enemy" + stage + ".gif").getImage().getScaledInstance(250, 380, 0)));

		//Show the respective background
		background.setIcon(new ImageIcon(new ImageIcon("Images/background" + stage + ".gif").getImage().getScaledInstance(900, Start.FRAME_HEIGHT, 0)));

	}

	private void labelSetup() {

		//Create the player
		player.setIcon(new ImageIcon(new ImageIcon("Images/player" + CharacterCreation.isMale + ".gif").getImage().getScaledInstance(180, 320, 0)));
		player.setBounds(50, 325, 180, 320);
		bPanel.add(player);

		//Create the enemy
		enemy.setBounds(650, 280, 250, 380);
		bPanel.add(enemy);

		//Create the player health bar
		playerHealthBar.setBackground(Color.RED);
		playerHealthBar.setOpaque(true);
		playerHealthBar.setBounds(20, 50, playerHealth, 50);
		bPanel.add(playerHealthBar);

		//Create the enemy health bar
		enemyHealthBar.setBackground(Color.RED);
		enemyHealthBar.setOpaque(true);
		enemyHealthBar.setBounds(670, 50, enemyHealth, 50);
		bPanel.add(enemyHealthBar);

		//Create the "fight" label
		lblInfo.setIcon(new ImageIcon(new ImageIcon("Images/fight.png").getImage().getScaledInstance(100, 100, 0)));
		lblInfo.setBounds(400, 50, 100, 100);
		bPanel.add(lblInfo);

//		//Create the "ko" label
//		lblInfo2.setIcon(new ImageIcon(new ImageIcon("Images/ko.png").getImage().getScaledInstance(200, 200, 0)));
//		lblInfo2.setBounds(350, 10, 200, 200);
//		bPanel.add(lblInfo2);
//		lblInfo2.setVisible(false);

		//Create an animation
		lblAnimation.setIcon(new ImageIcon (new ImageIcon ("Images/question.gif").getImage().getScaledInstance(250, 150, 0)));
		lblAnimation.setOpaque(false);
		qPanel.add(lblAnimation);
		lblAnimation.setBounds(30, 240, 250, 150);

		//Create the question label
		question.setFont(font2);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setEditable(false);
		qPanel.add(question);
		question.setBounds(25, 20, 250, 80);

		//Create the choice labels
		for (int i = 0; i < lblChoice.length; i++) {

			lblChoice[i] = new JLabel();
			lblChoice[i].setFont(font3);
			qPanel.add(lblChoice[i]);

		}

		//Place the choice labels
		lblChoice[0].setBounds(25, 110, 300, 25);
		lblChoice[1].setBounds(25, 140, 300, 25);
		lblChoice[2].setBounds(25, 170, 300, 25);
		lblChoice[3].setBounds(25, 200, 300, 25);

		//Create the input box
		input.setFont(font2);
		input.setLineWrap(true);
		input.setWrapStyleWord(true);
		qPanel.add(input);
		input.setBounds(50, 410, 200, 160);

		//Create the confirm button
		confirm.setFont(font);
		confirm.setText("Confirm");
		confirm.setFocusPainted(false);
		confirm.setFocusable(false);
		confirm.addActionListener(this);
		qPanel.add(confirm);
		confirm.setBounds(50, 580, 200, 50);

		//Create the correct answer label
		lblCorrect.setIcon(new ImageIcon(new ImageIcon("Images/correct.png").getImage().getScaledInstance(150, 150, 0)));
		lblCorrect.setBounds(35, 110, 150, 150);
		lblCorrect.setVisible(false);
		bPanel.add(lblCorrect);

		//Create the incorrect answer label
		lblIncorrect.setIcon(new ImageIcon(new ImageIcon("Images/incorrect.png").getImage().getScaledInstance(120, 120, 0)));
		lblIncorrect.setBounds(720, 125, 120, 120);
		lblIncorrect.setVisible(false);
		bPanel.add(lblIncorrect);

		//Create the background label
		background.setBounds(0, 0, 900, Start.FRAME_HEIGHT);
		bPanel.add(background);

	}

	private void updateQuestion(String questionName, String Choice1, String Choice2, String Choice3, String Choice4) {

		//Update each question label respectively
		question.setText(questionName);
		lblChoice[0].setText("A. "+ Choice1);
		lblChoice[1].setText("B. "+ Choice2);
		lblChoice[2].setText("C. "+ Choice3);
		lblChoice[3].setText("D. "+ Choice4);

	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setSize(Start.FRAME_WIDTH, Start.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);

		//Panel setup
		bPanel.setBounds(0, 0, 900, Start.FRAME_HEIGHT - 28);
		bPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		bPanel.setOpaque(false);
		bPanel.setLayout(null);
		add(bPanel);

		//Panel setup
		qPanel.setBounds(895, 0, 300, Start.FRAME_HEIGHT - 28); 
		qPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		qPanel.setOpaque(false);
		qPanel.setLayout(null);
		add(qPanel);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//Check if the confirm button is pressed
		if (event.getSource() == confirm) {

			//Check if user's answer is right
			if (input.getText().equalsIgnoreCase(ql.getQuestion().get(index).getCorrect())) {

				//start the move timer for the player and make the key listener listen to the frame
				tmrMove.start();
				requestFocus();

				//Show the correct answer icon and increment the number of questions asked
				lblCorrect.setVisible(true);
				correctAnswer++;

			}
			//If user's answer is wrong
			else {

				//Start the move timer for the enemy and show the incorrect answer icon
				tmrMove2.start();
				lblIncorrect.setVisible(true);

			}

			//Clear the input box, update the question and start the answer timer
			input.setText(null);
			newQuestion();
			tmrAnswer.start();

		}

		//If the player's move timer is on
		if (event.getSource() == tmrMove) {

			//Set dx and dy to 0
			dx = 0;

			//Depending on which arrow key is pressed and if player is within the screen, set dx to corresponding move amount
			if (leftKey && player.getX() > 10)		

				dx = -MOVE_AMOUNT;

			else if (rightKey && player.getX() < Start.FRAME_WIDTH - 75) {

				dx = MOVE_AMOUNT;

			}

			//Move the player according to key pressed 
			player.setLocation(player.getX() + dx, player.getY());

			//If the player kicks the enemy
			if (player.getX() + player.getWidth() >= enemy.getX() && kick) {

				//Play the "kick" sound
				try {

					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("Sounds/kick.wav")));
					clip.start();

				} catch (Exception e) {

					System.out.println(e);

				}

				//Decrease the enemy's health by 50 and update the enemy's health bar
				enemyHealth-= 50;
				enemyHealthBar.setSize(enemyHealth, 50);

				//Reset the players position and stop this timer
				player.setLocation(50,  325);
				tmrMove.stop();

				//Check if enemy is dead
				if (enemyHealth <= 0) {

					//Close the current frame and call the next class
					dispose();
					new BattleSum(stage, correctAnswer, questionsAsked, true);

				}

			}

		}

		//If the enemy's move timer is on
		if (event.getSource() == tmrMove2) {

			//Move the enemy to the left
			enemy.setLocation(enemy.getX() - 10, enemy.getY());

			//If enemy touches the player
			if (enemy.getX() <= player.getX() + player.getWidth()) {

				//Play the "kick" sound
				try {

					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("Sounds/kick.wav")));
					clip.start();

				} catch (Exception e) {

					System.out.println(e);

				}

				//Decrease the player's health by 50 and update the player's health bar
				playerHealth-= 50;
				playerHealthBar.setSize(playerHealth, 50);

				//Reset the enemy position and stop this timer
				enemy.setLocation(650,  280);
				tmrMove2.stop();

				//Check if player is dead
				if (playerHealth <= 0) {

					//Close the current frame and call the next class
					dispose();
					new BattleSum(stage, correctAnswer, questionsAsked, false);

				}

			}
			
		}

		//If answer timer is on
		if (event.getSource() == tmrAnswer) {

			//Set the answer icons to invisible and stop this timer
			lblCorrect.setVisible(false);
			lblIncorrect.setVisible(false);
			tmrAnswer.stop();
			
		}

	}

	private void newQuestion() {

		//Add the old question index to the respective array list
		Start.qIndex.add(index);

		do {

			//Set check to false
			check = false;

			//Create a new random index
			index = (int)(Math.random()*42);

			//Loop through previous indices to make sure there are no repeats
			for (int i = 0; i < Start.qIndex.size(); i++) {

				//If there is a repeat
				if (Start.qIndex.get(i) == index) {

					//Set check to true and exit this loop
					check = true;
					break;

				}

			}

		} while (check); //if check is true, repeat this loop

		//Update the question and increment the number of questions asked
		updateQuestion(ql.getQuestion().get(index).getQuestion(), ql.getQuestion().get(index).getAnswerA(), ql.getQuestion().get(index).getAnswerB(), ql.getQuestion().get(index).getAnswerC(), ql.getQuestion().get(index).getAnswerD());
		questionsAsked++;

	}

	@Override
	public void keyReleased(KeyEvent event) {

		//Stores the key released temporarily
		keyReleased = event.getKeyCode();

		//Checks which key has been released and switches off the corresponding condition
		if (keyReleased == 37)

			leftKey = false;

		else if (keyReleased == 39)

			rightKey = false;

		else if (keyReleased == 32) {

			kick = false;
			
			//Change the player icon back to the original
			player.setIcon(new ImageIcon(new ImageIcon("Images/player" + CharacterCreation.isMale + ".gif").getImage().getScaledInstance(180, 320, 0)));

		}

	}

	@Override
	public void keyPressed(KeyEvent event) {

		//Stores the key pressed temporarily
		key = event.getKeyCode();

		//Checks which key has been pressed and switches on the corresponding condition
		if (key == 37) 

			leftKey = true;

		else if (key == 39)

			rightKey = true;

		else if (key == 32) {

			kick = true;
			
			//Change the player icon to a kicking icon
			player.setIcon(new ImageIcon(new ImageIcon("Images/player" + CharacterCreation.isMale + "kick.png").getImage().getScaledInstance(180, 320, 0)));

		}

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

}
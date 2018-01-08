import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//This class gives the player a brief summary of the previous battle
public class BattleSum extends JFrame implements MouseListener {

	//Instance fields
	private Font font = new Font ("Calibri", Font.BOLD, 72);
	private Font font2 = new Font ("Calibri", Font.BOLD, 55);
	private Font font3 = new Font ("Calibri", Font.BOLD, 36);
	private JTextArea info = new JTextArea();
	private JLabel lblTitle = new JLabel();
	private JLabel player = new JLabel();
	private JLabel enemy = new JLabel();
	private JLabel next = new JLabel();
	private JPanel bsPanel = new JPanel();
	private int stage;
	private String[] enemyNames = {"Ryu", "Akuma", "Alex", "Guile", "Balrog"};
	private Clip clip;

	public BattleSum (int stage, int correctAnswer, int questionsAsked, boolean isDefeated) {

		//Initialize stage number
		this.stage = stage;

		//Call other methods
		frameSetup();
		labelSetup();
		showPlayers(correctAnswer, questionsAsked);
		repaint();

		//Display the enemy picture
		enemy.setIcon(new ImageIcon(new ImageIcon("Images/enemy" + stage + ".gif").getImage().getScaledInstance(250, 380, 0)));

		//Check if you have defeated the boss
		if (isDefeated) {

			//Increment the stage number and display the respective message
			this.stage++;
			info.setText("Vous avez vaincu " + enemyNames[stage] + ", vous avez maintenant vaincu " + (stage + 1) + " grands combattants. Vous devez les vaincre tous pour être le plus grand combattant de tous les temps."
					+ "\n\n           Vous avez " + correctAnswer + "/" + questionsAsked + " correct!");

			//Play the winning sound
			try {

				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("Sounds/win.wav")));
				clip.start();

			} catch (Exception e) {

				System.out.println(e);

			}

		}
		//If you have not defeated the boss
		else {

			//Display the respective message
			info.setText("Vous avez été vaincu par " + enemyNames[stage] + ". Vous devez réessayer pour compléter votre quête.\n\n"
					+ "            Vous avez " + correctAnswer + "/" + questionsAsked + " correct!");
			//Play the loseing sound
			try {

				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("Sounds/lose.wav")));
				clip.start();

			} catch (Exception e) {

				System.out.println(e);

			}

		}

	}

	private void labelSetup() {

		//Add the continue label
		next.setFont(font2);
		next.setForeground(Color.WHITE);
		next.setText("Prochain");
		next.addMouseListener(this);
		next.setBounds(450, 580, 300, 50);
		bsPanel.add(next);

		//Add the message box
		info.setFont(font3);
		info.setForeground(Color.WHITE);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setEditable(false);
		info.setOpaque(false);
		info.setBounds(320, 180, 550, 350);
		bsPanel.add(info);

		//Add the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setText("Bataille Résumé");
		lblTitle.setOpaque(false);
		lblTitle.setBounds(350, 60, 600, 60);
		bsPanel.add(lblTitle);

	}

	private void showPlayers(int correctAnswer, int questionsAsked) {

		//Show the player
		player.setIcon(new ImageIcon (new ImageIcon ("Images/player" + CharacterCreation.isMale + ".gif").getImage().getScaledInstance(180, 320, 0)));
		player.setBounds(75, 185, 180, 320);
		bsPanel.add(player);

		//Show the enemy
		enemy.setBounds(this.getWidth() - 300, 140, 250, 380);
		bsPanel.add(enemy);

	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setSize(Start.FRAME_WIDTH, Start.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(new JLabel(new ImageIcon(new ImageIcon("Images/background.jpg").getImage().getScaledInstance(Start.FRAME_WIDTH, Start.FRAME_HEIGHT, 0))));
		setVisible(true);

		//Panel setup
		bsPanel.setBounds(0, 0, Start.FRAME_WIDTH - 5, Start.FRAME_HEIGHT - 28); 
		bsPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		bsPanel.setOpaque(false);
		bsPanel.setLayout(null);
		add(bsPanel);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		//Close the current frame and call the quest overview class
		dispose();
		new QuestOverview(stage);

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

//This class is the game over screen in the game
public class GameOver extends JFrame implements MouseListener{

	//Instance fields
	private Font font = new Font ("Calibri", Font.BOLD, 72);
	private Font font2 = new Font ("Calibri", Font.BOLD, 56);
	private Font font3 = new Font ("Calibri", Font.BOLD, 36);
	private JTextArea info = new JTextArea();
	private JLabel lblTitle = new JLabel();
	private JLabel lblName = new JLabel();
	private JLabel player = new JLabel();
	private JLabel[] boss = new JLabel[5];
	private JLabel next = new JLabel();
	private JPanel gameOverPanel = new JPanel();
	private Clip clip;

	public GameOver() {

		frameSetup();
		labelSetup();



		repaint();


		//Play the winning sound
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("Sounds/win.wav")));
			clip.start();

		} catch (Exception e) {

			System.out.println(e);

		}

	}

	private void labelSetup() {

		//Add the player
		player.setIcon(new ImageIcon (new ImageIcon ("Images/player" + CharacterCreation.isMale + ".gif").getImage().getScaledInstance(180, 320, 0)));
		player.setBounds(75, 185, 180, 320);
		gameOverPanel.add(player);

		//Add the player name
		lblName.setFont(font2);
		lblName.setForeground(Color.WHITE);
		lblName.setText(CharacterCreation.playerName);
		lblName.setBounds(80, 520, 250, 50);
		gameOverPanel.add(lblName);

		//Add the finish button
		next.setFont(font2);
		next.setForeground(Color.WHITE);
		next.setText("Fini");
		next.addMouseListener(this);
		next.setBounds(800, 550, 120, 50);
		gameOverPanel.add(next);

		//Add the boss labels
		for (int i = 0; i < boss.length; i++) {

			boss[i] = new JLabel();
			boss[i].setIcon(new ImageIcon (new ImageIcon ("Images/enemy" + i + ".gif").getImage().getScaledInstance(120, 120, 0)));
			gameOverPanel.add(boss[i]);
		}

		//Place the boss labels
		boss[0].setBounds(420, 140, 120, 120);
		boss[1].setBounds(420, 300, 120, 120);
		boss[2].setBounds(420, 480, 120, 120);
		boss[3].setBounds(270, 180, 120, 120);
		boss[4].setBounds(270, 390, 120, 120);

		//Add the message
		info.setFont(font3);
		info.setForeground(Color.WHITE);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setEditable(false);
		info.setText("Toutes nos félicitations! Vous avez terminé le jeu et nous espérons que vous avez bien appris! Vous avez terminé la quête de vaincre les cinq guerriers et maintenant vous avez pris leur place en tant que plus grand guerrier vivant!");
		info.setOpaque(false);
		info.setBounds(620, 180, 550, 350);
		gameOverPanel.add(info);

		//Add the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setText("Toutes nos félicitations!");
		lblTitle.setOpaque(false);
		lblTitle.setBounds(300, 60, 800, 60);
		gameOverPanel.add(lblTitle);

	}

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
		gameOverPanel.setBounds(0, 0, Start.FRAME_WIDTH - 5, Start.FRAME_HEIGHT - 28); 
		gameOverPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		gameOverPanel.setOpaque(false);
		gameOverPanel.setLayout(null);
		add(gameOverPanel);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		//Exit the program
		System.exit(0);

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

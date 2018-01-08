import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//This class is the start screen for the game
public class Start extends JFrame implements MouseListener {

	//Create a static array list to keep track of each used question
	public static ArrayList <Integer> qIndex = new ArrayList <Integer>();
	
	//Constants
	public static final int FRAME_WIDTH = 1200;
	public static final int FRAME_HEIGHT = 700;

	//Instance fields
	private JLabel lblTitle = new JLabel();
	private Font font = new Font ("Calibri", Font.BOLD, 72);
	private JLabel instructions = new JLabel();
	private JLabel start = new JLabel();
	private JPanel startPanel = new JPanel();
	private Clip clip;

	public	Start() {

		//Play the game music
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("Sounds/fightmusic.wav")));
			clip.start();

		} catch (Exception e) {

			System.out.println(e);

		}

		//Call other methods
		frameSetup();
		labelSetup();
		repaint();

	}

	private void labelSetup() {

		//Add the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setText("Le combattant de rue français");
		lblTitle.setOpaque(false);
		lblTitle.setBounds(180, 80, 900, 60);
		startPanel.add(lblTitle);

		//Add instructions button
		instructions.setOpaque(false);
		instructions.setFont(font);
		instructions.setForeground(Color.WHITE);
		instructions.setText("Instructions");
		instructions.addMouseListener(this);
		instructions.setBounds(this.getWidth() / 2 - 210, this.getHeight() - 250, 480, 70);
		startPanel.add(instructions);

		//Add start button
		start.setOpaque(false);
		start.setFont(font);
		start.setForeground(Color.WHITE);
		start.setText("Commencer");
		start.addMouseListener(this);
		start.setBounds(this.getWidth() / 2 - 210, this.getHeight() - 150, 480, 70);
		startPanel.add(start);
		
	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setTitle("Start Screen");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(new JLabel (new ImageIcon(new ImageIcon("Images/background5.gif").getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT,0))));
		setVisible(true);

		//Panel setup
		startPanel.setBounds(0, 0, FRAME_WIDTH - 5, FRAME_HEIGHT - 28); 
		startPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		startPanel.setOpaque(false);
		startPanel.setLayout(null);
		add(startPanel);

	}

	@Override
	public void mouseClicked(MouseEvent event) {

		//If instructions label is clicked
		if (event.getSource() == instructions) {

			//Show instructions
			JOptionPane.showMessageDialog(this, "1) Répondez à la question sur la barre latérale en tapant la bonne solution.\2) S'il s'agit d'un choix multiple, n'écrivez que la lettre, pas d'espace supplémentaire.\n3) Si vous devez écrire la réponse, tapez la réponse de manière appropriée.\n4) Si votre réponse est correcte, vous pourrez vous déplacer à gauche et à droite avec les touches fléchées, et vous pouvez vous déplacer vers l'ennemi et le frapper en appuyant sur la barre d'espace.\n5)Si votre réponse est fausse, l'ennemi attaquera et vous perdrez la santé.\n6) Bonne chance! Étudiez dur!");

		}
		//If start label is clicked
		else if (event.getSource() == start) {

			//Close the current frame and call the next class
			dispose();
			new CharacterCreation();

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//This class shows the player his progress in the game
public class QuestOverview extends JFrame implements MouseListener {

	//Instance fields
	private Font font = new Font ("Calibri", Font.BOLD, 72);
	private Font font2 = new Font ("Calibri", Font.BOLD, 38);
	private Font font3 = new Font ("Calibri", Font.BOLD, 50);
	private JLabel[] boss = new JLabel[5];
	private JLabel[] lblBoss = new JLabel[5];
	private JLabel lblPlayer = new JLabel();
	private JLabel lblTitle = new JLabel();
	private JLabel lblProgress = new JLabel();
	private JLabel instructions = new JLabel();
	private JLabel next = new JLabel();
	private JPanel iPanel = new JPanel();
	private int stage;

	public QuestOverview (int stage) {

		//Initialize stage number
		this.stage = stage;
		
		//Call other methods
		frameSetup();
		labelSetup();
		showProgressLabels();
		showButtons();
		repaint();

	}

	private void showProgressLabels() {
		
		//Add boss labels
		for (int i = 0; i < lblBoss.length; i++) {

			lblBoss[i] = new JLabel();
			lblBoss[i].setFont(font2);
			lblBoss[i].setForeground(Color.WHITE);
			lblBoss[i].setText("Ennemi "+ (i + 1));
			iPanel.add(lblBoss[i]);
			
		}

		//Place the boss labels
		lblBoss[0].setBounds(100, 200, 200, 50);
		lblBoss[1].setBounds(100, 360, 200, 50);
		lblBoss[2].setBounds(100, 520, 200, 50);
		lblBoss[3].setBounds(950, 190, 200, 50);
		lblBoss[4].setBounds(950, 400, 200, 50);
		
		//Add the bosses defeated
		for (int i = 0; i < boss.length; i++) {

			boss[i] = new JLabel();
			iPanel.add(boss[i]);
		
		}

		//Place the bosses defeated
		boss[0].setBounds(250, 140, 120, 120);
		boss[1].setBounds(250, 300, 120, 120);
		boss[2].setBounds(250, 460, 120, 120);
		boss[3].setBounds(820, 140, 120, 120);
		boss[4].setBounds(820, 350, 120, 120);
	
		//Update each boss image WRT how many bosses are defeated
		for (int i = 0; i < stage; i++) 

			boss[i].setIcon(new ImageIcon (new ImageIcon ("Images/enemy" + i + ".gif").getImage().getScaledInstance(120, 120, 0)));
	
	}

	private void labelSetup() {

		//Add the player
		lblPlayer.setIcon(new ImageIcon (new ImageIcon ("Images/player" + CharacterCreation.isMale + ".gif").getImage().getScaledInstance(235, 360, 0)));
		lblPlayer.setBounds(480, 180, 235, 360);
		iPanel.add(lblPlayer);

		//Add the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setText("Progrès");
		lblTitle.setOpaque(false);
		lblTitle.setBounds(450, 60, 500, 60);
		iPanel.add(lblTitle);

		//Add the # of bosses defeated
		lblProgress.setFont(font2);
		lblProgress.setForeground(Color.WHITE);
		lblProgress.setText(stage + " ennemi battu!");
		lblProgress.setBounds(850, 70, 300, 40);
		iPanel.add(lblProgress);

	}

	private void showButtons() {

		//Add instructions button
		instructions.setFont(font3);
		instructions.setForeground(Color.WHITE);
		instructions.setText("Instructions");
		instructions.addMouseListener(this);
		instructions.setBounds(500, 590, 300, 50);
		iPanel.add(instructions);

		//Add next battle button
		next.setFont(font3);
		next.setForeground(Color.WHITE);
		next.setText("Prochain bataille");
		next.addMouseListener(this);
		next.setBounds(820, 590, 350, 50);
		iPanel.add(next);

	}

	//This method sets up the frame
	private void frameSetup() {

		//Frame setup
		setSize(Start.FRAME_WIDTH, Start.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(new JLabel (new ImageIcon(new ImageIcon("Images/worldmap.png").getImage().getScaledInstance(Start.FRAME_WIDTH, Start.FRAME_HEIGHT, 0))));
		setVisible(true);

		//Panel setup
		iPanel.setBounds(0, 0, Start.FRAME_WIDTH - 5, Start.FRAME_HEIGHT - 28); 
		iPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		iPanel.setOpaque(false);
		iPanel.setLayout(null);
		add(iPanel);

	}

	@Override
	public void mouseClicked(MouseEvent event) {

		//If next game label is clicked
		if (event.getSource() == next) {

			//Close the current frame
			dispose();

			//If all bosses have been defeated, call the game over class
			if (stage == 5) {

				new GameOver();

			}
			else {

				//If not, call the battle class
				try {

					new Battle(stage);

				} catch (FileNotFoundException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			}

		}
		//If instructions label is clicked
		else if (event.getSource() == instructions)

			//Show instructions
			JOptionPane.showMessageDialog(this, "1) Répondez à la question sur la barre latérale en tapant la bonne solution.\n2) S'il s'agit d'un choix multiple, n'écrivez que la lettre, pas d'espace supplémentaire.\n3) Si vous devez écrire la réponse, tapez la réponse de manière appropriée.\n4) Si votre réponse est correcte, vous pourrez vous déplacer à gauche et à droite avec les touches fléchées, et vous pouvez vous déplacer vers l'ennemi et le frapper en appuyant sur la barre d'espace.\n5)Si votre réponse est fausse, l'ennemi attaquera et vous perdrez la santé.\n6) Bonne chance! Étudiez dur!");

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
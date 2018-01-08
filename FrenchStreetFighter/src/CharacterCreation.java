import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//This class is the character creation screen in the game
public class CharacterCreation extends JFrame implements ActionListener {

	//Static variables
	public static String playerName;
	public static boolean isMale;

	//Instance fields
	private Font font = new Font ("Calibri", Font.BOLD, 72);
	private Font font2 = new Font ("Calibri", Font.BOLD, 38);
	private Font font3 = new Font ("Calibri", Font.BOLD, 28);
	private JLabel lblName = new JLabel();
	private JLabel lblTitle = new JLabel();
	private JLabel lblMale = new JLabel();
	private JLabel lblFemale = new JLabel();
	private JButton bttMale = new JButton();
	private JButton bttFemale = new JButton();
	private JLabel lblAnimation = new JLabel();
	private JTextArea inputBox = new JTextArea();
	private JButton confirm = new JButton();
	private JPanel ccPanel = new JPanel();
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton selectMale = new JRadioButton();
	private JRadioButton selectFemale = new JRadioButton();
	
	public	CharacterCreation() {

		//Call other methods
		frameSetup();
		labelSetup();
		buttonSetup();
		repaint();

	}

	private void labelSetup() {

		//Add the title
		lblTitle.setFont(font);
		lblTitle.setText("Création de Personnage");
		lblTitle.setOpaque(false);
		lblTitle.setBounds(280, 60, 800, 60);
		ccPanel.add(lblTitle);

		//Add the question
		lblName.setFont(font2);
		lblName.setText("S'il vous plaît entrez votre nom:");
		lblName.setOpaque(false);
		lblName.setBounds(340, 470, 550, 50);
		ccPanel.add(lblName);

		//Add the animation
		lblAnimation.setIcon(new ImageIcon (new ImageIcon ("Images/question.gif").getImage().getScaledInstance(480, 325, 0)));
		lblAnimation.setBounds(365, 135, 480, 325);
		lblAnimation.setOpaque(false);
		ccPanel.add(lblAnimation);

		//Add the male label
		lblMale.setFont(font2);
		lblMale.setText("Mâle");
		lblMale.setOpaque(false);
		lblMale.setBounds(150, 505, 150, 35);
		ccPanel.add(lblMale);

		//Add the female label
		lblFemale.setFont(font2);
		lblFemale.setText("Femelle");
		lblFemale.setOpaque(false);
		lblFemale.setBounds(this.getWidth() - 225 , 505, 150, 35);
		ccPanel.add(lblFemale);

	}

	private void buttonSetup() {

		//Add the male button
		bttMale.setIcon(new ImageIcon (new ImageIcon ("Images/playertrue.gif").getImage().getScaledInstance(235, 360, 0)));
		bttMale.setBounds(75, 140, 235, 360);
		bttMale.addActionListener(this);
		ccPanel.add(bttMale);

		//Add the female button
		bttFemale.setIcon(new ImageIcon (new ImageIcon ("Images/playerfalse.gif").getImage().getScaledInstance(235, 360, 0)));
		bttFemale.setBounds(this.getWidth() - 300, 140, 235, 360);
		bttFemale.addActionListener(this);
		ccPanel.add(bttFemale);

		//Add the male option button
		selectMale.setFocusPainted(false);
		selectMale.setBounds(190, 115, 25, 25);
		buttonGroup.add(selectMale);
		ccPanel.add(selectMale);

		//Add the female option button
		selectFemale.setFocusPainted(false);
		selectFemale.setBounds(this.getWidth() - 180 , 115, 25, 25);
		buttonGroup.add(selectFemale);
		ccPanel.add(selectFemale);

		//Add the inputBox
		inputBox.setFont(font3);
		inputBox.setBounds(380, 520, 420, 35);
		ccPanel.add(inputBox);
		
		//Add the confirm button
		confirm.setFont(font3);
		confirm.setText("Créer");
		confirm.setFocusPainted(false);
		confirm.addActionListener(this);
		confirm.setBounds(470, 580, 250, 50);
		ccPanel.add(confirm);


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
		ccPanel.setBounds(0, 0, Start.FRAME_WIDTH - 5, Start.FRAME_HEIGHT - 28); 
		ccPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		ccPanel.setOpaque(false);
		ccPanel.setLayout(null);
		add(ccPanel);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		//If male button is pressed, select the male option
		if (event.getSource() == bttMale)

			selectMale.setSelected(true);

		//If female button is pressed, select the female option
		else if (event.getSource() == bttFemale)

			selectFemale.setSelected(true);

		//If confirm button is pressed
		else if (event.getSource() == confirm) {

			//Initialize the player name to what is in the input box
			playerName = inputBox.getText();

			//If male option is selected, set isMale to true
			if (selectMale.isSelected())

				isMale = true;

			//Close the current screen and call the next class
			dispose();
			new QuestOverview(0);

		}

	}

}
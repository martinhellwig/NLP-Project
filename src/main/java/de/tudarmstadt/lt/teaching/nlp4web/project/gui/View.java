package de.tudarmstadt.lt.teaching.nlp4web.project.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.ChosenOnes;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	public final static int WINDOWSIZE_X = 600;
	public final static int WINDOWSIZE_Y = 450;
	
	private JButton getResultButton;
	private JButton newGameButton;
	
	private JTextField questionTextField;
	private JTextField answer1TextField;
	private JTextField answer2TextField;
	private JTextField answer3TextField;
	private JTextField answer4TextField;
	
	private JLabel resultLabel;
	
	public View() {
		super("Who wants to be a millionaire?");
		getContentPane().setBackground(Color.WHITE);
		initForm();
	}
	
	private void initForm() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setBounds (200, 200, WINDOWSIZE_X, WINDOWSIZE_Y);
		
		JLabel questionDescription = new JLabel("Question:");
		questionDescription.setVerticalAlignment(JLabel.TOP);
		questionDescription.setBounds(250, 10, 150, 300);
		getContentPane().add(questionDescription);
		questionTextField = new JTextField();
		questionTextField.setBounds(90, 30, 400, 30);
		getContentPane().add(questionTextField);
		
		JLabel answer1Description = new JLabel("Answer 1:");
		answer1Description.setVerticalAlignment(JLabel.TOP);
		answer1Description.setBounds(150, 70, 150, 300);
		getContentPane().add(answer1Description);
		answer1TextField = new JTextField();
		answer1TextField.setBounds(90, 90, 180, 30);
		getContentPane().add(answer1TextField);
		
		JLabel answer2Description = new JLabel("Answer 2:");
		answer2Description.setVerticalAlignment(JLabel.TOP);
		answer2Description.setBounds(370, 70, 150, 300);
		getContentPane().add(answer2Description);
		answer2TextField = new JTextField();
		answer2TextField.setBounds(310, 90, 180, 30);
		getContentPane().add(answer2TextField);
		
		JLabel answer3Description = new JLabel("Answer 3:");
		answer3Description.setVerticalAlignment(JLabel.TOP);
		answer3Description.setBounds(150, 120, 150, 300);
		getContentPane().add(answer3Description);
		answer3TextField = new JTextField();
		answer3TextField.setBounds(90, 140, 180, 30);
		getContentPane().add(answer3TextField);
		
		JLabel answer4Description = new JLabel("Answer 4:");
		answer4Description.setVerticalAlignment(JLabel.TOP);
		answer4Description.setBounds(370, 120, 150, 300);
		getContentPane().add(answer4Description);
		answer4TextField = new JTextField();
		answer4TextField.setBounds(310, 140, 180, 30);
		getContentPane().add(answer4TextField);
		
		
		this.getResultButton = new JButton();
		this.getResultButton.setText("Get Result");
		this.getResultButton.setBounds(215, 190, 150, 40);
		getContentPane().add(this.getResultButton);
		this.newGameButton = new JButton();
		this.newGameButton.setText("New Game");
		this.newGameButton.setBounds(430, 370, 150, 40);
		getContentPane().add(this.newGameButton);
		
		this.resultLabel = new JLabel();
		this.resultLabel.setBounds(20, 210, 430, 150);
		getContentPane().add(this.resultLabel);
	}
	
	public String[] getAnswers() {
		String[] output = new String[4];
		output[0] = answer1TextField.getText().trim();
		output[1] = answer2TextField.getText().trim();
		output[2] = answer3TextField.getText().trim();
		output[3] = answer4TextField.getText().trim();
		
		return output;
	}
	
	public String getQuestion() {
		return questionTextField.getText().trim();
	}
	
	public void showResults(ArrayList<ChosenOnes> results) {
		String text = "<html>";
		
		float possibilityOne = 0;
		float possibilityTwo = 0;
		float possibilityThree = 0;
		float possibilityFour = 0;
		
		for(ChosenOnes chosenOne : results) {
			possibilityOne += chosenOne.getPossibilityAnswer1();
			possibilityTwo += chosenOne.getPossibilityAnswer2();
			possibilityThree += chosenOne.getPossibilityAnswer3();
			possibilityFour += chosenOne.getPossibilityAnswer4();
			
			if(chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer2() && 
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer4())
				text += chosenOne.getRessourceName() + " says answer 1 is the right one<br>";
			else if(chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer4())
				text += chosenOne.getRessourceName() + " says answer 2 is the right one<br>";
			else if(chosenOne.getPossibilityAnswer3() >= chosenOne.getPossibilityAnswer4())
				text += chosenOne.getRessourceName() + " says answer 3 is the right one<br>";
			else text += chosenOne.getRessourceName() + " says answer 4 is the right one<br>";
		}
		
		if(possibilityOne >= possibilityTwo && possibilityOne >= possibilityThree && possibilityOne >= possibilityFour) {
			text += "Overall answer 1 should be the right one</html>";
		}
		else if(possibilityTwo >= possibilityThree && possibilityTwo >= possibilityFour) {
			text += "Overall answer 2 should be the right one</html>";
		}
		else if(possibilityThree >= possibilityFour) {
			text += "Overall answer 3 should be the right one</html>";
		}
		else text += "Overall answer 4 should be the right one</html>";
		
		this.resultLabel.setText(text);
		this.repaint();
	}
	
	
	public void eraseAllEditFields() {
		questionTextField.setText("");
		answer1TextField.setText("");
		answer2TextField.setText("");
		answer3TextField.setText("");
		answer4TextField.setText("");
		
		resultLabel.setText("");
	}
	
	public void setGetResultButtonClickListener(ActionListener l) {
		this.getResultButton.addActionListener(l);
	}
	
	public void setNewGameButtonClickListener(ActionListener l) {
		this.newGameButton.addActionListener(l);
	}
}

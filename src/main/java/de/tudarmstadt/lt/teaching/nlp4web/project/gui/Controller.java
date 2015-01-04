package de.tudarmstadt.lt.teaching.nlp4web.project.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.apache.uima.UIMAException;

import de.tudarmstadt.lt.teaching.nlp4web.project.Pipeline;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.ChosenOnes;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;

public class Controller {

	private View view;
	private Model model;

	public Controller()  {
		this.model = new Model();
		this.view = new View();
		addViewListener();
		
		this.view.setVisible(true);
	}
	
	private void addViewListener() {
		this.view.setGetResultButtonClickListener(new GetResultButtonListener());
		this.view.setNewGameButtonClickListener(new NewGameButtonListener());
	}
	
	class GetResultButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String question = view.getQuestion();
			String[] answers = view.getAnswers();
			
			QuestionObject questionObject = new QuestionObject(question, answers[0], answers[1], answers[2], answers[3], RightAnswer.FIRST);
			ArrayList<ChosenOnes> results;
			try {
				results = Pipeline.runPipelineForOneQuestion(questionObject);
				model.setResults(results);
				model.setQuestion(question);
				model.setAnswer1(answers[0]);
				model.setAnswer2(answers[1]);
				model.setAnswer3(answers[2]);
				model.setAnswer4(answers[3]);
				
				view.showResults(results);
			} catch (UIMAException e1) { 
			}
		}
	}
	
	class NewGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setAnswer1("");
			model.setAnswer1("");
			model.setAnswer1("");
			model.setAnswer1("");
			model.setQuestion("");
			model.setResults(new ArrayList<ChosenOnes>());;
			
			view.eraseAllEditFields();
		}
	}
}


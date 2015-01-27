package de.tudarmstadt.lt.teaching.nlp4web.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;

public class TSVReader extends JCasCollectionReader_ImplBase {
	
	private int index;
	public static final String PARAM_INPUT_DATA = "data.tsv";
	
	private ArrayList<QuestionObject> questions = new ArrayList<>();

	@Override
	public void initialize(UimaContext context) {
		try {
			super.initialize(context);
			index = 0;
			
			//reads the given file and creates a new question-object out of each line
			BufferedReader in = new BufferedReader(new FileReader(PARAM_INPUT_DATA));
			String line = null;
			while ((line = in.readLine()) != null) {
				String[] values = line.split("\t"); //the values are split by tabs
				String question = values[0];
				String answer1 = values[1];
				String answer2 = values[2];
				String answer3 = values[3];
				String answer4 = values[4];
				
				RightAnswer rightAnswer = null;
				if(values[5].trim().equals(answer1.trim())) rightAnswer = RightAnswer.FIRST;
				else if(values[5].trim().equals(answer2.trim())) rightAnswer = RightAnswer.SECOND;
				else if(values[5].trim().equals(answer3.trim())) rightAnswer = RightAnswer.THIRD;
				else if(values[5].trim().equals(answer4.trim())) rightAnswer = RightAnswer.FOURTH;
				
				questions.add(new QuestionObject(question, answer1, answer2, answer3, answer4, rightAnswer));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Progress[] getProgress() {
		return new Progress[] {new ProgressImpl(index, questions.size(), Progress.ENTITIES)};
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return index < questions.size();
	}

	@Override
	public void getNext(JCas j) throws IOException, CollectionException {
		//Simply get the next question-object from the list and add this to jcas
		Question question = new Question(j);
		question.setBegin(0);
    	question.setEnd(questions.get(index).getQuestion().length());
		question.setQuestion(questions.get(index).getQuestion());
		question.setAnswer1(questions.get(index).getAnswer1());
		question.setAnswer2(questions.get(index).getAnswer2());
		question.setAnswer3(questions.get(index).getAnswer3());
		question.setAnswer4(questions.get(index).getAnswer4());
		question.setRightAnswer(questions.get(index).getRightAnswer().toString());
		question.addToIndexes();
		
		j.setDocumentText(questions.get(index).getQuestion());
		j.setDocumentLanguage("EN"); //Need this to run the Stanford-Segmenter
		
		index++;
	}

}

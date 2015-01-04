package de.tudarmstadt.lt.teaching.nlp4web.project;


import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.objects.ChosenOnes;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class Evaluate extends JCasConsumer_ImplBase {
	
	public static ArrayList<Integer> precisions = new ArrayList<>();
	public static ArrayList<String> names = new ArrayList<>();
	public static int mainPrecision;
	public static int amountData;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {		
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		} 
		
		ArrayList<ChosenOnes> results = new ArrayList<>();		
		for (Result r : JCasUtil.select(jcas, Result.class)) { 
			results.add(new ChosenOnes(r.getRessouceType(), r.getAnswer1Possibility(), r.getAnswer2Possibility(), 
					r.getAnswer3Possibility(), r.getAnswer4Possibility()));
		}
		
		System.out.println("Question:" + question.getQuestion());
		float possibilityOne = 0;
		float possibilityTwo = 0;
		float possibilityThree = 0;
		float possibilityFour = 0;
		int amount = 0;
		
		for(ChosenOnes chosenOne : results) {
			possibilityOne += chosenOne.getPossibilityAnswer1();
			possibilityTwo += chosenOne.getPossibilityAnswer2();
			possibilityThree += chosenOne.getPossibilityAnswer3();
			possibilityFour += chosenOne.getPossibilityAnswer4();
			
			if(amountData == 0) {
				names.add(chosenOne.getRessourceName());
				precisions.add(0);
			}
			if(chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer2() && 
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.FIRST)
				precisions.set(amount, precisions.get(amount) + 1);
			else if(chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.SECOND)
				precisions.set(amount, precisions.get(amount) + 1);
			else if(chosenOne.getPossibilityAnswer3() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.THIRD)
				precisions.set(amount, precisions.get(amount) + 1);
			else if(question.getRightAnswer() == RightAnswer.FOURTH)
				precisions.set(amount, precisions.get(amount) + 1);
			
			System.out.println(chosenOne.getRessourceName() + ": " + chosenOne.getPossibilityAnswer1() + ", " + 
					chosenOne.getPossibilityAnswer2() + ", " + chosenOne.getPossibilityAnswer3() + ", " + 
					chosenOne.getPossibilityAnswer4() + "");
			amount++;
		}
		
		System.out.println("Overall: " + possibilityOne/amount + ", " + possibilityTwo/amount + ", " +
				possibilityThree/amount + ", " + possibilityFour/amount + "");
		RightAnswer chosenAnswer;
		if(possibilityOne >= possibilityTwo && possibilityOne >= possibilityThree && possibilityOne >= possibilityFour) {
			chosenAnswer = RightAnswer.FIRST;
		}
		else if(possibilityTwo >= possibilityThree && possibilityTwo >= possibilityFour) {
			chosenAnswer = RightAnswer.SECOND;
		}
		else if(possibilityThree >= possibilityFour) {
			chosenAnswer = RightAnswer.THIRD;
		}
		else chosenAnswer = RightAnswer.FOURTH;
		
		if(chosenAnswer == question.getRightAnswer()) {
			System.out.println("Chosen right\n");
			mainPrecision++;
		}
		else System.out.println("Chosen wrong\n");	
		
		amountData++;
	}

}

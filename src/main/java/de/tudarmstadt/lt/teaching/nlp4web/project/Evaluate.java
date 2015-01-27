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
	
	public static ArrayList<Integer> precisions = new ArrayList<>(); //Holds the amount of right chosen answers of each decider-method
	public static ArrayList<String> names = new ArrayList<>(); //Holds the name of each decider-method
	public static int mainPrecision; //amount of all right chosen answers
	public static int amountData; //amount of all data

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		//First create a question-Object out of the jcas-data
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		} 
		
		//create a list with the results of the deciders for this question
		ArrayList<ChosenOnes> results = new ArrayList<>();		
		for (Result r : JCasUtil.select(jcas, Result.class)) { 
			results.add(new ChosenOnes(r.getRessouceType(), r.getAnswer1Possibility(), r.getAnswer2Possibility(), 
					r.getAnswer3Possibility(), r.getAnswer4Possibility(), r.getUsedTime()));
		}
		
		System.out.println("Question:" + question.getQuestion());
		//summed possibilities for all deciders (only used to output to user)
		float possibilityOne = 0;
		float possibilityTwo = 0;
		float possibilityThree = 0;
		float possibilityFour = 0;
		int deciderID = 0;
		
		//Now evaluate each of the deciders for this question
		for(ChosenOnes chosenOne : results) {
			possibilityOne += chosenOne.getPossibilityAnswer1();
			possibilityTwo += chosenOne.getPossibilityAnswer2();
			possibilityThree += chosenOne.getPossibilityAnswer3();
			possibilityFour += chosenOne.getPossibilityAnswer4();
			
			//if this is the first dataSet, do initialize the lists
			if(amountData == 0) {
				names.add(chosenOne.getRessourceName());
				precisions.add(0);
			}
			
			//Check if this decider found the right answer; if so, increment the overall counter for this decider
			boolean foundRightAnswer = false;
			if(chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer2() && 
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer1() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.FIRST) {
				precisions.set(deciderID, precisions.get(deciderID) + 1);
				foundRightAnswer = true;
			} else if(chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer1() &&
					chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer3() &&
					chosenOne.getPossibilityAnswer2() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.SECOND) {
				precisions.set(deciderID, precisions.get(deciderID) + 1);
				foundRightAnswer = true;
			} else if(chosenOne.getPossibilityAnswer3() >= chosenOne.getPossibilityAnswer1() &&
					chosenOne.getPossibilityAnswer3() >= chosenOne.getPossibilityAnswer2() &&
					chosenOne.getPossibilityAnswer3() >= chosenOne.getPossibilityAnswer4() &&
					question.getRightAnswer() == RightAnswer.THIRD) {
				precisions.set(deciderID, precisions.get(deciderID) + 1);
				foundRightAnswer = true;
			} else if(chosenOne.getPossibilityAnswer4() >= chosenOne.getPossibilityAnswer1() &&
					chosenOne.getPossibilityAnswer4() >= chosenOne.getPossibilityAnswer2() &&
							chosenOne.getPossibilityAnswer4() >= chosenOne.getPossibilityAnswer3() &&
					question.getRightAnswer() == RightAnswer.FOURTH) {
				precisions.set(deciderID, precisions.get(deciderID) + 1);
				foundRightAnswer = true;
			}
			
			//Output some info of this decider for user
			System.out.println(chosenOne.getRessourceName() + ": " + chosenOne.getPossibilityAnswer1() + ", " + 
					chosenOne.getPossibilityAnswer2() + ", " + chosenOne.getPossibilityAnswer3() + ", " + 
					chosenOne.getPossibilityAnswer4() + "; Found right Answer: " + foundRightAnswer + 
					"; Needed Time: " + chosenOne.getUsedTimeInSeconds() + "s");
			deciderID++;
		}
		
		//Output the summed result of all deciders for this question to user
		System.out.println("Overall: " + possibilityOne/deciderID + ", " + possibilityTwo/deciderID + ", " +
				possibilityThree/deciderID + ", " + possibilityFour/deciderID + "");
		
		//Now get the answer, whcih would be chosen of all deciders combined
		RightAnswer chosenAnswer = null;
		if(possibilityOne >= possibilityTwo && possibilityOne >= possibilityThree && possibilityOne >= possibilityFour) {
			chosenAnswer = RightAnswer.FIRST;
		}
		else if(possibilityTwo >= possibilityOne && possibilityTwo >= possibilityThree && possibilityTwo >= possibilityFour) {
			chosenAnswer = RightAnswer.SECOND;
		}
		else if(possibilityThree >= possibilityOne && possibilityThree >= possibilityTwo && possibilityThree >= possibilityFour) {
			chosenAnswer = RightAnswer.THIRD;
		}
		else if(possibilityFour >= possibilityOne && possibilityFour >= possibilityTwo && possibilityFour >= possibilityThree) {
			chosenAnswer = RightAnswer.FOURTH;
		}
		
		if(chosenAnswer != null && chosenAnswer == question.getRightAnswer()) {
			System.out.println("Chosen right\n");
			mainPrecision++;
		}
		else System.out.println("Chosen wrong\n");	
		
		//Increment the overall amount of data
		amountData++;
	}

}

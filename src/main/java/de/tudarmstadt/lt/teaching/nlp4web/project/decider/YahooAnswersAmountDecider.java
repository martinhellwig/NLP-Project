package de.tudarmstadt.lt.teaching.nlp4web.project.decider;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.internet.YahooAnswersRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class YahooAnswersAmountDecider extends JCasConsumer_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		//Create the question-object out of jcas-data
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) {
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(),
					q.getAnswer2(), q.getAnswer3(), q.getAnswer4(),
					RightAnswer.valueOf(q.getRightAnswer()));
		}

		//Ask the question in yahooAnswers and count how often each of the answers occur
		YahooAnswersRequest yahooRequest = new YahooAnswersRequest(question.getQuestion());
		int answer1 = yahooRequest.getAmountInText(question.getAnswer1());
		int answer2 = yahooRequest.getAmountInText(question.getAnswer2());
		int answer3 = yahooRequest.getAmountInText(question.getAnswer3());
		int answer4 = yahooRequest.getAmountInText(question.getAnswer4());

		//If no noun of the answers was found anywhere, set possibilities to 25% each
		if (answer1 == 0 && answer2 == 0 && answer3 == 0 && answer4 == 0) {
			answer1++;
			answer2++;
			answer3++;
			answer4++;
		}

		//Write back the possibilities of each answer
		float amountOfAll = answer1 + answer2 + answer3 + answer4;
		Result result = new Result(jcas);
		result.setRessouceType("Yahoo Answers Amount");
		result.setAnswer1Possibility((float) answer1 / (float) amountOfAll);
		result.setAnswer2Possibility((float) answer2 / (float) amountOfAll);
		result.setAnswer3Possibility((float) answer3 / (float) amountOfAll);
		result.setAnswer4Possibility((float) answer4 / (float) amountOfAll);
		result.setUsedTime((int) (System.currentTimeMillis() - startTime));
		result.addToIndexes();
	}

}

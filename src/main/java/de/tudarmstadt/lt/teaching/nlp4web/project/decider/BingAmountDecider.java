package de.tudarmstadt.lt.teaching.nlp4web.project.decider;


import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.BingRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.JoBimRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class BingAmountDecider extends JCasConsumer_ImplBase{
	
	private static final boolean USE_NEGOTIATION_PER_SENTENCE = true;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		//Do JoBim things
		JoBimRequest joBimRequest = new JoBimRequest(question.getQuestion());
		
		BingRequest answer1Request = new BingRequest(question.getQuestion() + " " + question.getAnswer1());
		int amountAnswer1 = answer1Request.getResultAmount();
		BingRequest answer2Request = new BingRequest(question.getQuestion() + " " + question.getAnswer2());
		int amountAnswer2 = answer2Request.getResultAmount();
		BingRequest answer3Request = new BingRequest(question.getQuestion() + " " + question.getAnswer3());
		int amountAnswer3 = answer3Request.getResultAmount();
		BingRequest answer4Request = new BingRequest(question.getQuestion() + " " + question.getAnswer4());
		int amountAnswer4 = answer4Request.getResultAmount();
		int amountOfAll = amountAnswer1 + amountAnswer2 + amountAnswer3 + amountAnswer4;
		
		
		Result result = new Result(jcas);
		result.setRessouceType("Bing Amount");
		if(USE_NEGOTIATION_PER_SENTENCE && joBimRequest.isWholeSentenceNegotiated()) {
			float[] toNegotiate = {(float) amountAnswer1 / (float) amountOfAll, 
					(float) amountAnswer2 / (float) amountOfAll, 
					(float) amountAnswer3 / (float) amountOfAll,
					(float) amountAnswer4 / (float) amountOfAll};
			float[] negotiatedOnes = HelpFunctions.getOppositePossibilities(toNegotiate);
			result.setAnswer1Possibility(negotiatedOnes[0]);
			result.setAnswer2Possibility(negotiatedOnes[1]);
			result.setAnswer3Possibility(negotiatedOnes[2]);
			result.setAnswer4Possibility(negotiatedOnes[3]);
		}
		else {
			result.setAnswer1Possibility((float) amountAnswer1 / (float) amountOfAll);
			result.setAnswer2Possibility((float) amountAnswer2 / (float) amountOfAll);
			result.setAnswer3Possibility((float) amountAnswer3 / (float) amountOfAll);
			result.setAnswer4Possibility((float) amountAnswer4 / (float) amountOfAll);
		}
		result.setUsedTime((int) (System.currentTimeMillis() - startTime));
		result.addToIndexes();
	}

}

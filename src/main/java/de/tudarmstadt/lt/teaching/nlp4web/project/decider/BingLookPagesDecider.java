package de.tudarmstadt.lt.teaching.nlp4web.project.decider;


import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.jsoup.Jsoup;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.BingRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.JoBimRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class BingLookPagesDecider extends JCasConsumer_ImplBase{
	
	private static final boolean USE_NEGOTIATION_PER_SENTENCE = false;
	private static final int AMOUNT_USAGE_URLS = 3;

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		//Create the question-object out of jcas-data
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		int amountAnswer1 = 0;
		int amountAnswer2 = 0;
		int amountAnswer3 = 0;
		int amountAnswer4 = 0;
		
		//Is there a quoted term in question? Use this!
		String requestToAsk = question.getQuestion();
		Pattern pattern = Pattern.compile("\"(.*?)\"");
		Matcher matcher = pattern.matcher(question.getQuestion());
		while (matcher.find()) {
		    if(matcher.group(1) != null) {
		    	requestToAsk = matcher.group(1);
		    }
		}
		pattern = Pattern.compile("\\'(.*?)\\'");
		matcher = pattern.matcher(question.getQuestion());
		while (matcher.find()) {
		    if(matcher.group(1) != null) {
		    	requestToAsk = matcher.group(1);
		    }
		}
		
		//Do JoBim things to find out if something is negotiated
		JoBimRequest joBimRequest = new JoBimRequest(question.getQuestion());
		
		//request the question (or the quoted term) to bing and get the content of the first few pages
		BingRequest request = new BingRequest(requestToAsk);
		ArrayList<String> urls = request.getResultURLs(AMOUNT_USAGE_URLS);
		
		//Now look in each retrieved page if there occurs the answer-terms
		for(int i = 0; i < urls.size(); i++) {
			String temp;
			try {
				temp = Jsoup.connect(urls.get(i)).get().text();
				amountAnswer1 += HelpFunctions.countWord(temp, question.getAnswer1());
				amountAnswer2 += HelpFunctions.countWord(temp, question.getAnswer2());
				amountAnswer3 += HelpFunctions.countWord(temp, question.getAnswer3());
				amountAnswer4 += HelpFunctions.countWord(temp, question.getAnswer4());
			} catch (IOException e) {
			}
		}
		
		//If no term was found on any page, set the possibility of all four to 25%
		if(amountAnswer1 == 0 && amountAnswer2 == 0 && amountAnswer3 == 0 && amountAnswer4 == 0) {
			amountAnswer1++;
			amountAnswer2++;
			amountAnswer3++;
			amountAnswer4++;
		}
		
		int amountOfAll = amountAnswer1 + amountAnswer2 + amountAnswer3 + amountAnswer4;
		
		//Write back the possibilities of each answer
		Result result = new Result(jcas);
		result.setRessouceType("Bing Look Pages");
		
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

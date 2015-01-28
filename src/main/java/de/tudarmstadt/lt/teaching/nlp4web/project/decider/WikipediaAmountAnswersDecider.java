package de.tudarmstadt.lt.teaching.nlp4web.project.decider;

import java.util.ArrayList;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.JoBimRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.JoRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.internet.WikipediaRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class WikipediaAmountAnswersDecider extends JCasConsumer_ImplBase{
	
	private static final boolean USE_JOES = true;
	private static final boolean USE_NEGOTIATION_PER_SENTENCE = true;
	private static final boolean USE_DISAMBIGUATIONS = true;	

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		//Create the question-object out of jcas-data
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		//Do JoBim things to find out if something is negotiated
		JoBimRequest joBimRequest = new JoBimRequest(question.getQuestion());
	
		//Find out all (lemmatized) nouns, which are contained in the question
		String nouns = "";	
		for(POS pos : JCasUtil.select(jcas, POS.class)) {
			if(pos.getPosValue().contains("NN")) {
				for(Lemma lemma : JCasUtil.select(jcas, Lemma.class)) {
					if(pos.getCoveredText().equals(lemma.getCoveredText())) {
						//Get all similar words of this noun with help of JoBim
						ArrayList<String> similarWords;
						if(USE_JOES) {
							JoRequest joRequest = new JoRequest(lemma.getValue());
							similarWords = joRequest.getSimilarWords(5);
							if(similarWords.size() == 0) similarWords.add(lemma.getValue());
						}
						else {
							similarWords = new ArrayList<>();
							similarWords.add(lemma.getValue());
						}
						
						//Add this noun and all similar words to the list of nouns
						for(int i = 0; i < similarWords.size(); i++) {
							nouns += similarWords.get(i) + " ";
						}
					}
				}
			}
		}
		
		//Get the wikipedia results for each answer and count, how often the each of the above nouns occur
		WikipediaRequest answer1Request = new WikipediaRequest(question.getAnswer1(), USE_DISAMBIGUATIONS);
		int answer1 = answer1Request.getAmountInText(nouns);
		WikipediaRequest answer2Request = new WikipediaRequest(question.getAnswer2(), USE_DISAMBIGUATIONS);
		int answer2 = answer2Request.getAmountInText(nouns);
		WikipediaRequest answer3Request = new WikipediaRequest(question.getAnswer3(), USE_DISAMBIGUATIONS);
		int answer3 = answer3Request.getAmountInText(nouns);
		WikipediaRequest answer4Request = new WikipediaRequest(question.getAnswer4(), USE_DISAMBIGUATIONS);
		int answer4 = answer4Request.getAmountInText(nouns);
		
		//If no noun of the question was found anywhere, set possibilities to 25% each
		if(answer1 == 0 && answer2 == 0 && answer3 == 0 && answer4 == 0) {
			answer1++;
			answer2++;
			answer3++;
			answer4++;
		}
		
		//Write back the possibilities of each answer
		float amountOfAll = answer1 + answer2 + answer3 + answer4;
		Result result = new Result(jcas);
		result.setRessouceType("Wikipedia Amount (Answers)");
		
		if(USE_NEGOTIATION_PER_SENTENCE && joBimRequest.isWholeSentenceNegotiated()) {
			float[] toNegotiate = {(float) answer1 / (float) amountOfAll, 
					(float) answer2 / (float) amountOfAll, 
					(float) answer3 / (float) amountOfAll,
					(float) answer4 / (float) amountOfAll};
			float[] negotiatedOnes = HelpFunctions.getOppositePossibilities(toNegotiate);
			result.setAnswer1Possibility(negotiatedOnes[0]);
			result.setAnswer2Possibility(negotiatedOnes[1]);
			result.setAnswer3Possibility(negotiatedOnes[2]);
			result.setAnswer4Possibility(negotiatedOnes[3]);
		}
		else {
			result.setAnswer1Possibility((float) answer1 / (float) amountOfAll);
			result.setAnswer2Possibility((float) answer2 / (float) amountOfAll);
			result.setAnswer3Possibility((float) answer3 / (float) amountOfAll);
			result.setAnswer4Possibility((float) answer4 / (float) amountOfAll);
		}
		result.setUsedTime((int) (System.currentTimeMillis() - startTime));
		result.addToIndexes();
	}

}

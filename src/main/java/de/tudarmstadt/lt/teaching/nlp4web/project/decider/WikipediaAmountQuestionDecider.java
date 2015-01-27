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

public class WikipediaAmountQuestionDecider extends JCasConsumer_ImplBase{
	
	private static final boolean USE_JOES = true;
	private static final boolean USE_NEGOTIATION_PER_NOUN = true;
	private static final boolean USE_NEGOTIATION_PER_SENTENCE = true;
	private static final boolean USE_DISAMBIGUATIONS = true;	

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		float amountAnswer1 = 0;
		float amountAnswer2 = 0;
		float amountAnswer3 = 0;
		float amountAnswer4 = 0;	
		
		//Do JoBim things
		JoBimRequest joBimRequest = new JoBimRequest(question.getQuestion());
		ArrayList<String> negotiatedNouns = joBimRequest.getNegotiatedNouns();
		
		for(POS pos : JCasUtil.select(jcas, POS.class)) {
			if(pos.getPosValue().contains("NN")) {
				for(Lemma lemma : JCasUtil.select(jcas, Lemma.class)) {
					if(pos.getCoveredText().equals(lemma.getCoveredText())) {
						//Get all similar words with help of JoBim
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
						
						//Now make a wiki-request for all similar words
						for(int i = 0; i < similarWords.size(); i++) {
							WikipediaRequest wikiRequest = new WikipediaRequest(lemma.getValue(), USE_DISAMBIGUATIONS);
							int answer1 = wikiRequest.getAmountInText(question.getAnswer1());
							int answer2 = wikiRequest.getAmountInText(question.getAnswer2());
							int answer3 = wikiRequest.getAmountInText(question.getAnswer3());
							int answer4 = wikiRequest.getAmountInText(question.getAnswer4());
							
							if(answer1 == 0 && answer2 == 0 && answer3 == 0 && answer4 == 0) {
								answer1++;
								answer2++;
								answer3++;
								answer4++;
							}
							int amountAll = answer1 + answer2 + answer3 + answer4;
							
							
							//Now change all values to the opposite if this noun is negotiated
							boolean foundNounAsNegotiated = false;
							for(String negotiatedNoun : negotiatedNouns) {
								if(negotiatedNoun.equals(lemma.getValue())) foundNounAsNegotiated = true;
							}
							
							if(USE_NEGOTIATION_PER_NOUN && foundNounAsNegotiated) {
								float[] toNegotiate = {(float) answer1 / (float) amountAll, 
										(float) answer2 / (float) amountAll, 
										(float) answer3 / (float) amountAll,
										(float) answer4 / (float) amountAll};
								float[] negotiatedOnes = HelpFunctions.getOppositePossibilities(toNegotiate);
								amountAnswer1 += negotiatedOnes[0];
								amountAnswer2 += negotiatedOnes[1];
								amountAnswer3 += negotiatedOnes[2];
								amountAnswer4 += negotiatedOnes[3];
							}
							else {
								amountAnswer1 += (float) answer1 / (float) amountAll;
								amountAnswer2 += (float) answer2 / (float) amountAll;
								amountAnswer3 += (float) answer3 / (float) amountAll;
								amountAnswer4 += (float) answer4 / (float) amountAll;
							}
						}
					}
				}
			}
		}
		
		float amountOfAll = amountAnswer1 + amountAnswer2 + amountAnswer3 + amountAnswer4;
		Result result = new Result(jcas);
		result.setRessouceType("Wikipedia Amount (Question)");
		
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

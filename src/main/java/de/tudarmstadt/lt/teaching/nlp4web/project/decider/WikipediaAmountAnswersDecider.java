package de.tudarmstadt.lt.teaching.nlp4web.project.decider;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.internet.WikipediaRequest;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class WikipediaAmountAnswersDecider extends JCasConsumer_ImplBase{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
	
		String posTags = "";	
		for(POS pos : JCasUtil.select(jcas, POS.class)) {
			if(pos.getPosValue().contains("NN")) {
				for(Lemma lemma : JCasUtil.select(jcas, Lemma.class)) {
					if(pos.getCoveredText().equals(lemma.getCoveredText())) {
						posTags += lemma.getValue() + " ";
					}
				}
			}
		}
		
		WikipediaRequest answer1Request = new WikipediaRequest(question.getAnswer1());
		int answer1 = answer1Request.getAmountInText(posTags);
		WikipediaRequest answer2Request = new WikipediaRequest(question.getAnswer2());
		int answer2 = answer2Request.getAmountInText(posTags);
		WikipediaRequest answer3Request = new WikipediaRequest(question.getAnswer3());
		int answer3 = answer3Request.getAmountInText(posTags);
		WikipediaRequest answer4Request = new WikipediaRequest(question.getAnswer4());
		int answer4 = answer4Request.getAmountInText(posTags);
		
		if(answer1 == 0 && answer2 == 0 && answer3 == 0 && answer4 == 0) {
			answer1++;
			answer2++;
			answer3++;
			answer4++;
		}
		
		float amountOfAll = answer1 + answer2 + answer3 + answer4;
		Result result = new Result(jcas);
		result.setRessouceType("Wikipedia Amount (Answers)");
		result.setAnswer1Possibility((float) answer1 / (float) amountOfAll);
		result.setAnswer2Possibility((float) answer2 / (float) amountOfAll);
		result.setAnswer3Possibility((float) answer3 / (float) amountOfAll);
		result.setAnswer4Possibility((float) answer4 / (float) amountOfAll);
		result.addToIndexes();
	}

}

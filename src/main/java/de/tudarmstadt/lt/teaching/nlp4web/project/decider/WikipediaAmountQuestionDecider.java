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

public class WikipediaAmountQuestionDecider extends JCasConsumer_ImplBase{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		float amountAnswer1 = 0;
		float amountAnswer2 = 0;
		float amountAnswer3 = 0;
		float amountAnswer4 = 0;		
		
		for(POS pos : JCasUtil.select(jcas, POS.class)) {
			if(pos.getPosValue().contains("NN")) {
				for(Lemma lemma : JCasUtil.select(jcas, Lemma.class)) {
					if(pos.getCoveredText().equals(lemma.getCoveredText())) {
						WikipediaRequest answer1Request = new WikipediaRequest(lemma.getValue());
						int answer1 = answer1Request.getAmountInText(question.getAnswer1());
						WikipediaRequest answer2Request = new WikipediaRequest(lemma.getValue());
						int answer2 = answer2Request.getAmountInText(question.getAnswer2());
						WikipediaRequest answer3Request = new WikipediaRequest(lemma.getValue());
						int answer3 = answer3Request.getAmountInText(question.getAnswer3());
						WikipediaRequest answer4Request = new WikipediaRequest(lemma.getValue());
						int answer4 = answer4Request.getAmountInText(question.getAnswer4());
						
						if(answer1 == 0 && answer2 == 0 && answer3 == 0 && answer4 == 0) {
							answer1++;
							answer2++;
							answer3++;
							answer4++;
						}
						int amountAll = answer1 + answer2 + answer3 + answer4;
						
						
						amountAnswer1 += (float) answer1 / (float) amountAll;
						amountAnswer2 += (float) answer2 / (float) amountAll;
						amountAnswer3 += (float) answer3 / (float) amountAll;
						amountAnswer4 += (float) answer4 / (float) amountAll;
					}
				}
			}
		}
		
		float amountOfAll = amountAnswer1 + amountAnswer2 + amountAnswer3 + amountAnswer4;
		Result result = new Result(jcas);
		result.setRessouceType("Wikipedia Amount (Question)");
		result.setAnswer1Possibility((float) amountAnswer1 / (float) amountOfAll);
		result.setAnswer2Possibility((float) amountAnswer2 / (float) amountOfAll);
		result.setAnswer3Possibility((float) amountAnswer3 / (float) amountOfAll);
		result.setAnswer4Possibility((float) amountAnswer4 / (float) amountOfAll);
		result.addToIndexes();
	}

}

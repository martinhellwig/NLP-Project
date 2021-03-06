package de.tudarmstadt.lt.teaching.nlp4web.project.decider;


import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.RightAnswer;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class DemoRessourceDecider extends JCasConsumer_ImplBase{

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		long startTime = System.currentTimeMillis(); //Need to calculate time for this decider
		
		//Create the question-object out of jcas-data
		QuestionObject question = null;
		for (Question q : JCasUtil.select(jcas, Question.class)) { 
			question = new QuestionObject(q.getQuestion(), q.getAnswer1(), q.getAnswer2(), q.getAnswer3(), 
					q.getAnswer4(), RightAnswer.valueOf(q.getRightAnswer()));
		}
		
		//Write back the possibilities of each answer
		Result result = new Result(jcas);
		result.setRessouceType("Demo");
		result.setAnswer1Possibility(0.45f);
		result.setAnswer2Possibility(0.15f);
		result.setAnswer3Possibility(0.15f);
		result.setAnswer4Possibility(0.25f);
		result.setUsedTime((int) (System.currentTimeMillis() - startTime));
		result.addToIndexes();
	}

}

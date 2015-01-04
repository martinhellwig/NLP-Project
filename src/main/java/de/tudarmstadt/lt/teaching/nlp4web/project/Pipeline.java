package de.tudarmstadt.lt.teaching.nlp4web.project;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.lt.teaching.nlp4web.project.decider.DemoRessourceDecider;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.ChosenOnes;
import de.tudarmstadt.lt.teaching.nlp4web.project.objects.QuestionObject;
import de.tudarmstadt.ukp.dkpro.core.jazzy.SpellChecker;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.tudarmstadt.ukp.teaching.general.type.Question;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class Pipeline {

	public static void main(String[] args)
	    	throws UIMAException, IOException {
		CollectionReader reader = createReader(TSVReader.class);
		AnalysisEngine writer = createEngine(Evaluate.class);
		
		AnalysisEngine[] analysisEngines = getAnalysisEngines();
		
		if(analysisEngines.length == 1) SimplePipeline.runPipeline(reader, analysisEngines[0], writer);
		else if(analysisEngines.length == 2) SimplePipeline.runPipeline(reader, analysisEngines[0], 
				analysisEngines[1], writer);
		else if(analysisEngines.length == 3) SimplePipeline.runPipeline(reader, analysisEngines[0], 
				analysisEngines[1], analysisEngines[2], writer);
		else if(analysisEngines.length == 4) SimplePipeline.runPipeline(reader, analysisEngines[0], 
				analysisEngines[1], analysisEngines[2], analysisEngines[3], writer);
		else if(analysisEngines.length == 5) SimplePipeline.runPipeline(reader, analysisEngines[0], 
				analysisEngines[1], analysisEngines[2], analysisEngines[3], analysisEngines[4], writer);
		
		System.out.println(100 * ((float) Evaluate.mainPrecision/ (float) Evaluate.amountData) + "% of all were chosen right");
		for(int i = 0; i < Evaluate.precisions.size(); i++) {
			System.out.println(100 * ((float) Evaluate.precisions.get(i)/ (float) Evaluate.amountData) + "% of " + Evaluate.names.get(i) + " were chosen right");
		}
	}

	private static AnalysisEngine[] getAnalysisEngines() throws ResourceInitializationException {
		AnalysisEngine[] output = new AnalysisEngine[3];
		output[0] = createEngine(StanfordSegmenter.class);	        
	    output[1] = createEngine(SpellChecker.class, SpellChecker.PARAM_MODEL_LOCATION, "res/words");
	    output[2] = createEngine(DemoRessourceDecider.class);

	    return output;
	}
	
	public static ArrayList<ChosenOnes> runPipelineForOneQuestion(QuestionObject questionObject) throws UIMAException {
    	JCas jcas =JCasFactory.createJCas();
    	
    	// Make sure that the document language is set to "en"
    	jcas.setDocumentLanguage("en");
    	jcas.setDocumentText(questionObject.getQuestion());
    	
    	Question question = new Question(jcas);
    	question.setBegin(0);
    	question.setEnd(questionObject.getQuestion().length());
		question.setQuestion(questionObject.getQuestion());
		question.setAnswer1(questionObject.getAnswer1());
		question.setAnswer2(questionObject.getAnswer2());
		question.setAnswer3(questionObject.getAnswer3());
		question.setAnswer4(questionObject.getAnswer4());
		question.setRightAnswer(questionObject.getRightAnswer().toString());
		question.addToIndexes();
		
		AnalysisEngine[] analysisEngines = getAnalysisEngines();
		
		if(analysisEngines.length == 1) SimplePipeline.runPipeline(jcas, analysisEngines[0]);
		else if(analysisEngines.length == 2) SimplePipeline.runPipeline(jcas, analysisEngines[0], analysisEngines[1]);
		else if(analysisEngines.length == 3) SimplePipeline.runPipeline(jcas, analysisEngines[0], analysisEngines[1], analysisEngines[2]);
		else if(analysisEngines.length == 4) SimplePipeline.runPipeline(jcas, analysisEngines[0], analysisEngines[1], analysisEngines[2], analysisEngines[3]);
		else if(analysisEngines.length == 5) SimplePipeline.runPipeline(jcas, analysisEngines[0], analysisEngines[1], analysisEngines[2], analysisEngines[3], analysisEngines[4]);
		
		//Output in ChosenOnes Objects and give them back
		ArrayList<ChosenOnes> results = new ArrayList<>();
		for (Result s : JCasUtil.select(jcas, Result.class)) { 
			results.add(new ChosenOnes(s.getRessouceType(), s.getAnswer1Possibility(), 
					s.getAnswer2Possibility(), s.getAnswer3Possibility(), s.getAnswer4Possibility()));
		}
		return results;
	}	
}
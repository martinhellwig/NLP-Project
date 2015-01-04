package de.tudarmstadt.lt.teaching.nlp4web.project.decider;


import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import de.tudarmstadt.ukp.teaching.general.type.Result;

public class DemoRessourceDecider extends JCasConsumer_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		Result result = new Result(jcas);
		result.setRessouceType("Demo");
		result.setAnswer1Possibility(0.45f);
		result.setAnswer2Possibility(0.65f);
		result.setAnswer3Possibility(0.85f);
		result.setAnswer4Possibility(0.25f);
		result.addToIndexes();
	}

}

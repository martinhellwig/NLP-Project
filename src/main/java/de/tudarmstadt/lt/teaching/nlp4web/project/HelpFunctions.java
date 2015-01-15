package de.tudarmstadt.lt.teaching.nlp4web.project;

public class HelpFunctions {

	
	public static float[] getOppositePossibilities(float[] possibilities) {
		float newAnswer1Possibility = 1 - possibilities[0];
		float newAnswer2Possibility = 1 - possibilities[1];
		float newAnswer3Possibility = 1 - possibilities[2];
		float newAnswer4Possibility = 1 - possibilities[3];
		
		float all = newAnswer1Possibility + newAnswer2Possibility + newAnswer3Possibility + newAnswer4Possibility;
		
		float[] output = new float[4];
		output[0] = newAnswer1Possibility / all;
		output[1] = newAnswer2Possibility / all;
		output[2] = newAnswer3Possibility / all;
		output[3] = newAnswer4Possibility / all;
		
		return output;
	}
}

package de.tudarmstadt.lt.teaching.nlp4web.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static String escapeWebRequest(String input) {
		input = input.replaceAll("\\%", "%25"); //Has to be first, because it would delete all % in all upcoming replacements
		input = input.replaceAll(" ", "%20");
		input = input.replaceAll("\\$", "%24");
		input = input.replaceAll("&", "%26");
		input = input.replaceAll("\\?", "%3F");
		input = input.replaceAll("\"", "%22");
		input = input.replaceAll("\\(", "%28");
		input = input.replaceAll("\\)", "%29");
		input = input.replaceAll("\\'", "%27");
		return input;
	}
	
	public static int countWord(String text, String word){
        int count = 0;
        
        //count for every word in variable word
        String[] parts = word.split(" ");
        for(int i = 0; i < parts.length; i++) {
	        Pattern pat = Pattern.compile(Pattern.quote(parts[i].toLowerCase()));
	        Matcher m;
	        for(m = pat.matcher(text.toLowerCase()); m.find(); count++);
        }
        return count;
    }
}

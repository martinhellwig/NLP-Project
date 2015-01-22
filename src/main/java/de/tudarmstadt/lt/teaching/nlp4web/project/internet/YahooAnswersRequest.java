package de.tudarmstadt.lt.teaching.nlp4web.project.internet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.tudarmstadt.lt.teaching.nlp4web.project.HelpFunctions;

public class YahooAnswersRequest {

	private final String addressSearch = "https://answers.yahoo.com/search/search_result?p=";
	private final String addressAnswer = "https://answers.yahoo.com/question/index?qid=";
	private String searchResults;
	private boolean resultsFound;

	public YahooAnswersRequest(String request) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ServerConnection serverConnection = new ServerConnection();

		Document d = null;
		try {
			d = Jsoup
					.connect(
							addressSearch
									+ HelpFunctions.escapeWebRequest(request))
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements allAnswers = d.select("#yan-questions");

		resultsFound = false;

		Element answer = allAnswers.select("li").first();
		if (answer != null) {
			resultsFound = true;

			String answerID = answer.id();
			answerID = answerID.substring(2, answerID.length());

			params = new ArrayList<NameValuePair>();
			serverConnection = new ServerConnection();
			searchResults = serverConnection.getJSONFromUrl(addressAnswer
					+ HelpFunctions.escapeWebRequest(answerID), params);
		}

	}

	public int getAmountInText(String keyWord) {
		return resultsFound ? HelpFunctions.countWord(searchResults, keyWord) : 0;
	}

}

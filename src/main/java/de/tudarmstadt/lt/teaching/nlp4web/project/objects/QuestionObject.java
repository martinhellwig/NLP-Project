package de.tudarmstadt.lt.teaching.nlp4web.project.objects;


public class QuestionObject {
	
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private RightAnswer rightAnswer;
	
	public QuestionObject(String question, String answer1, String answer2,
			String answer3, String answer4, RightAnswer rightAnswer) {
		super();
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.rightAnswer = rightAnswer;
	}
	
	public void setRightAnswer(RightAnswer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public RightAnswer getRightAnswer() {
		return rightAnswer;
	}
}

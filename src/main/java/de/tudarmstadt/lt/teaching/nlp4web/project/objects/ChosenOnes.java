package de.tudarmstadt.lt.teaching.nlp4web.project.objects;

public class ChosenOnes {

	private String ressourceName;
	private float possibilityAnswer1;
	private float possibilityAnswer2;
	private float possibilityAnswer3;
	private float possibilityAnswer4;
	private long usedTime;
	
	public ChosenOnes(String ressourceName, float possibilityAnswer1, float possibilityAnswer2,
			float possibilityAnswer3, float possibilityAnswer4, long usedTime) {
		super();
		this.ressourceName = ressourceName;
		this.possibilityAnswer1 = possibilityAnswer1;
		this.possibilityAnswer2 = possibilityAnswer2;
		this.possibilityAnswer3 = possibilityAnswer3;
		this.possibilityAnswer4 = possibilityAnswer4;
		this.usedTime = usedTime;
	}
	
	public String getRessourceName() {
		return ressourceName;
	}
	public float getPossibilityAnswer1() {
		return possibilityAnswer1;
	}
	public float getPossibilityAnswer2() {
		return possibilityAnswer2;
	}
	public float getPossibilityAnswer3() {
		return possibilityAnswer3;
	}
	public float getPossibilityAnswer4() {
		return possibilityAnswer4;
	}	
	public int getUsedTimeInSeconds() {
		return (int) (this.usedTime/1000);
	}	
}

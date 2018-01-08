//This is a class to hold information for each question
public class Question {
	
	//Fields
	private String question;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String Correct;
	
	public Question(String question, String answerA, String answerB,String answerC, String answerD, String Correct){
		
		this.question = question;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.Correct = Correct;
		
	}
	
	public String getQuestion() {
		
		return question;
	
	}
	
	public String getAnswerA() {
	
		return answerA;
	
	}
	
	public String getAnswerB() {
	
		return answerB;
	
	}
	
	public String getAnswerC() {
	
		return answerC;
	
	}
	
	public String getAnswerD() {
	
		return answerD;
	
	}
		
	public String getCorrect() {
	
		return Correct;
	
	}
}
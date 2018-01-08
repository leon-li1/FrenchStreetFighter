import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//This class creates a list of questions from a CSV file
public class QuestionList {
	
	//Instance fields
	private ArrayList<Question> question = new ArrayList<Question>();
	private Question currentQuestion;
	
	public QuestionList(File load) throws FileNotFoundException{
		
		Scanner sc = new Scanner(load);
		
		sc.useDelimiter(",");
		
		//Loop through each line of the question file
		while (sc.hasNextLine()) {
			
			//Create a new question using the file and add it to the question list
			currentQuestion = new Question(sc.next().replaceAll("\r\n", ""), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
			question.add(currentQuestion);
			
		}
		
	}

	//Getter and setter for the question list
	public ArrayList<Question> getQuestion() {
		
		return question;
	
	}

	public void setQuestion(ArrayList<Question> question) {
		this.question = question;
	}
	
	
}
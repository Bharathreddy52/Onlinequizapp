package online_quiz_app;

import java.util.ArrayList;

public class Quiz {
	private ArrayList<Question> questions = new ArrayList<>();

	public Quiz() {
		String questionText1 = "Who invented Java? ";
		ArrayList<String> options1 = new ArrayList<>();
		options1.add("Guido van Rossum");
		options1.add("James Gosling");
		options1.add("Dennis Ritchie");
		options1.add("Bjarne Stroustrup");
		String correctAnswer1 = "James Gosling";
		Question question1 = new Question(questionText1, options1, correctAnswer1);
		this.questions.add(question1);
		
		
		String questionText2 = "Which of the following is not a keyword? ";
		ArrayList<String> options2 = new ArrayList<>();
		options2.add("this");
		options2.add("continue");
		options2.add("class");
		options2.add("it");
		String correctAnswer2 = "it";
		Question question2 = new Question(questionText2, options2, correctAnswer2);
		this.questions.add(question2);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
}

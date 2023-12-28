package online_quiz_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.Scanner;

public class OnlineQuizApp {
	
	public static boolean flag = false;
	
	private static boolean validateAlpha(String str) {
		return ((str != null)
				&& (!str.equals(""))
				&& (str.matches("^[a-zA-Z]*$")));
	}
	
	private static boolean validateAlphaNumeric(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(str);
		
		return ((str != null)
				&& (!str.equals("")) 
				&& matcher.matches());
	}
	
	private static boolean validateDigits(String str) {
		return ((str.length() == 1)
				&& (str != null)
				&& (!str.equals(""))
				&& (str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4"))
				);
	}
	
	private static void quizInvoke() throws IOException {
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char ch = 'y';
		
		do{
			// count of the student score
			int score = 0;
			
			System.out.println("Please enter your name: (should contain only alphabets)");
//			String studentName = sc.nextLine();
			String studentName = br.readLine();
			if(!validateAlpha(studentName)) {
				System.out.println("Enter the valid name containing only alphabets");
				flag = true;
				break;
			}
			
			System.out.println("Please enter your roll number: ");
//			String studentRollNumber = sc.nextLine();
			String studentRollNumber = br.readLine();
			if(!validateAlphaNumeric(studentRollNumber)) {
				System.out.println("Enter the valid roll number containing only alpha-numerics");
				flag = true;
				break;
			}
			Student student = new Student(studentName, studentRollNumber);
			System.out.println("Please answer the following questions: ");
			
			Quiz studentQuiz = new Quiz();
			ArrayList<Question> studentQuestions = studentQuiz.getQuestions();
			
			for(int i=0; i<studentQuestions.size(); i++) {
				System.out.print("Question " + (i+1) + ": ");
				System.out.println(studentQuestions.get(i).getQuestionText());

				ArrayList<String> studentQuizOptions = studentQuestions.get(i).getOptions();
				for(int j=0; j<studentQuizOptions.size(); j++) {
					System.out.println((j+1) + ": " + studentQuizOptions.get(j));
				}
				System.out.println("Please enter your option (in numbers 1,2,3,4): ");
//				int enteredOption = sc.nextInt();
				
				String str_enteredOption = br.readLine();
				
				if(!validateDigits(str_enteredOption)) {
					System.out.println("Enter the valid option between 1 and 4 (both are inclusive)");
					flag = true;
					break;
				}
				int enteredOption = Integer.parseInt(str_enteredOption);
				
				// for finding the crct answers's index
				int crctOption = studentQuizOptions.indexOf(studentQuestions.get(i).getCorrectAnswer());
				
				// checking the enteredOption with crctOption
				if(enteredOption == crctOption+1) {
					score++;
					System.out.println("Great! It is the correct answer!!");
				} else {
					System.out.println("Yikes.. its the wrong answer!");
				}
				
			}
			if(flag) {
				break;
			}
			
			System.out.println("Student: " + student.getName() + " with Roll number: " + student.getRollNumber() + " score is " + score);
			
			
			System.out.print("\n\n------------------------------------------------------------\n\n");
			System.out.println("Do you want to take the quiz again? (enter 'y' or any other key)");
//			ch = sc.next().charAt(0);
			ch = (char)br.read();
			// error detection to get rid of default java behavoiur
			br.readLine();
			// OR else dont use int input instead take input as string and convert it into int
			// then the dummy readline is not required
			// ex:- ch = br.readLine().charAt(0);
			
			if(ch!='y' && ch!='Y') {
//				sc.close();
				br.close();
			}
		}while(ch=='y' || ch=='Y');
		
		
		if(!flag) {
			System.out.println("Quiz attempted successfully!");
		}
	}
	
	public static void main(String[] args) throws IOException {
		do {
			flag = false;
			quizInvoke();
		}while(flag);
	}
	
}

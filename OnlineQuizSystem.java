package wiprotrainingday2;


	import java.util.*;

	// Class to represent a Question
	class Question {
	    private String questionText;
	    private String[] options;
	    private int correctAnswer; // Index of the correct answer (0-based)

	    public Question(String questionText, String[] options, int correctAnswer) {
	        this.questionText = questionText;
	        this.options = options;
	        this.correctAnswer = correctAnswer;
	    }

	    public String getQuestionText() {
	        return questionText;
	    }

	    public String[] getOptions() {
	        return options;
	    }

	    public boolean isCorrect(int answer) {
	        return answer == correctAnswer;
	    }

	    @Override
	    public String toString() {
	        StringBuilder builder = new StringBuilder(questionText + "\n");
	        for (int i = 0; i < options.length; i++) {
	            builder.append((i + 1) + ". " + options[i] + "\n");
	        }
	        return builder.toString();
	    }
	}

	// Class to represent a User
	class User {
	    private String username;
	    private int score;

	    public User(String username) {
	        this.username = username;
	        this.score = 0;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void updateScore(int score) {
	        this.score += score;
	    }

	    @Override
	    public String toString() {
	        return "User[Name: " + username + ", Score: " + score + "]";
	    }
	}

	// Class to represent a Quiz
	class Quiz {
	    private List<Question> questions = new ArrayList<>();
	    private Map<String, User> users = new HashMap<>();

	    // Add a question to the quiz
	    public void addQuestion(String questionText, String[] options, int correctAnswer) {
	        questions.add(new Question(questionText, options, correctAnswer));
	        System.out.println("Question added successfully!");
	    }

	    // Take the quiz
	    public void takeQuiz(String username) {
	        User user = users.computeIfAbsent(username, User::new);
	        Scanner scanner = new Scanner(System.in);
	        int score = 0;

	        System.out.println("\nStarting Quiz for " + username + "...");
	        for (int i = 0; i < questions.size(); i++) {
	            Question question = questions.get(i);
	            System.out.println("Question " + (i + 1) + ":");
	            System.out.println(question);
	            System.out.print("Your answer (1-" + question.getOptions().length + "): ");

	            int answer = scanner.nextInt() - 1; // Convert to 0-based index
	            if (answer >= 0 && answer < question.getOptions().length) {
	                if (question.isCorrect(answer)) {
	                    System.out.println("Correct!\n");
	                    score++;
	                } else {
	                    System.out.println("Wrong. The correct answer was: " +
	                            question.getOptions()[question.isCorrect(0) ? 0 : 1] + "\n");
	                }
	            } else {
	                System.out.println("Invalid choice. Moving to next question.\n");
	            }
	        }

	        user.updateScore(score);
	        System.out.println("Quiz Completed! " + username + ", you scored: " + score);
	    }

	    // Show scores of all users
	    public void showScores() {
	        if (users.isEmpty()) {
	            System.out.println("No scores available.");
	        } else {
	            System.out.println("User Scores:");
	            users.values().forEach(System.out::println);
	        }
	    }
	}

	// Main Class
	public class OnlineQuizSystem {
	    public static void main(String[] args) {
	        Quiz quiz = new Quiz();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\nOnline Quiz System Menu:");
	            System.out.println("1. Add Question");
	            System.out.println("2. Take Quiz");
	            System.out.println("3. Show Scores");
	            System.out.println("4. Exit");
	            System.out.print("Choose an option: ");

	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume the newline

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter question text: ");
	                    String questionText = scanner.nextLine();

	                    System.out.print("Enter number of options: ");
	                    int numberOfOptions = scanner.nextInt();
	                    scanner.nextLine(); // Consume the newline

	                    String[] options = new String[numberOfOptions];
	                    for (int i = 0; i < numberOfOptions; i++) {
	                        System.out.print("Enter option " + (i + 1) + ": ");
	                        options[i] = scanner.nextLine();
	                    }

	                    System.out.print("Enter the correct answer index (1-" + numberOfOptions + "): ");
	                    int correctAnswer = scanner.nextInt() - 1; // Convert to 0-based index

	                    quiz.addQuestion(questionText, options, correctAnswer);
	                    break;

	                case 2:
	                    System.out.print("Enter your username: ");
	                    String username = scanner.nextLine();
	                    quiz.takeQuiz(username);
	                    break;

	                case 3:
	                    quiz.showScores();
	                    break;

	                case 4:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    return;

	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }
	}



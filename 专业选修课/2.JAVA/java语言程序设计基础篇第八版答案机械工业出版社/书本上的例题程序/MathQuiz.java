public class MathQuiz {
  private String user;
  private int totalCount;
  private int correctCount;

  /** Construct a MathQuiz object with the specified user */
  public MathQuiz(String user) {
    this.user = user;
  }

  /** Construct a default MathQuiz object */
  public MathQuiz() {
    user = ""; // Anonymous user
  }

  /** Return the user for the test */
  public String getUser() {
    return user;
  }

  /** Return the total number of the questions */
  public int getTotalCount() {
    return totalCount;
  }

  /** Return the number of the correct answers */
  public int getCorrectCount() {
    return correctCount;
  }

  /** Increase the total number of the questions */
  public void increaseTotalCount() {
    totalCount++;
  }

  /** Increase the number of the correct answers */
  public void increaseCorrectCount() {
    correctCount++;
  }
}

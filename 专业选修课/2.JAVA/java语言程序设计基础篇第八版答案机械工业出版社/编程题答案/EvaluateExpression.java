public class EvaluateExpression {
  public static void main(String[] args) {
    // Check number of arguments passed
    if (args.length < 1) {
      System.out.println(
        "Usage: java EvaluateExpression expression");
      System.exit(0);
    }

    String expression = "";
    for (int i = 0; i < args.length; i++)
      expression += args[i];

    try {
      System.out.println(evaluateExpression(expression));
    }
    catch (Exception ex) {
      System.out.println("Wrong expression");
    }
  }

  /** Evaluate an expression */
  public static int evaluateExpression(String expression) {
    // Create operandStack to store operands
    java.util.Stack<Integer> operandStack
      = new java.util.Stack<Integer>();

    // Create operatorStack to store operators
    java.util.Stack<Character> operatorStack
      = new java.util.Stack<Character>();

    // Extract operands and operators
    java.util.StringTokenizer tokens =
      new java.util.StringTokenizer(expression, "()+-/*", true);

    // Phase 1: Scan tokens
    while (tokens.hasMoreTokens()) {
      String token = tokens.nextToken().trim(); // Extract a token
      if (token.length() == 0) // Blank space
        continue; // Back to the while loop to extract the next token
      else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
        // Process all +, -, *, / in the top of the operator stack
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek().equals('+') ||
           operatorStack.peek().equals('-') ||
           operatorStack.peek().equals('*') ||
           operatorStack.peek().equals('/')
          )) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the + or - operator into the operator stack
        operatorStack.push(new Character(token.charAt(0)));
      }
      else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
        // Process all *, / in the top of the operator stack
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek().equals('*') ||
          operatorStack.peek().equals('/')
          )) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the * or / operator into the operator stack
        operatorStack.push(new Character(token.charAt(0)));
      }
      else if (token.trim().charAt(0) == '(') {
        operatorStack.push(new Character('(')); // Push '(' to stack
      }
      else if (token.trim().charAt(0) == ')') {
        // Process all the operators in the stack until seeing '('
        while (!operatorStack.peek().equals('(')) {
          processAnOperator(operandStack, operatorStack);
        }

        operatorStack.pop(); // Pop the '(' symbol from the stack
      }
      else { // An operand scanned
        // Push an operand to the stack
        operandStack.push(new Integer(token));
      }
    }

    // Phase 2: process all the remaining operators in the stack
    while (!operatorStack.isEmpty()) {
      processAnOperator(operandStack, operatorStack);
    }

    // Return the result
    return ((Integer)(operandStack.pop())).intValue();
  }

  /** Process one opeator: Take an operator from operatorStack and
   *  apply it on the operands in the operandStack */
  public static void processAnOperator(
      java.util.Stack<Integer> operandStack,
      java.util.Stack<Character> operatorStack) {
    if (operatorStack.peek().equals('+')) {
      operatorStack.pop();
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 + op1));
    }
    else if (operatorStack.peek().equals('-')) {
      operatorStack.pop();
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 - op1));
    }
    else if (operatorStack.peek().equals('*')) {
      operatorStack.pop();
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 * op1));
    }
    else if (operatorStack.peek().equals('/')) {
      operatorStack.pop();
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 / op1));
    }
  }
}

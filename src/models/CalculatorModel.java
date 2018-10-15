/**
 * CalculatorModel.java : Concrete class using stack data structure to evaluate infix math expression
 * This class will take input from the Controller and update the View. This Model  also throws appropriate exceptions
 * for errors and handle the errors in CalculatorView.actionPerformed() appropriately. This model uses a stack data structure
 * to evaluate integer expressions, account for the order of operations for addition, subtraction, multiplication, division and parentheses.
 * It also integrates :
 * Polynomial Arithmetic.
 * Integrate your previously built Polynomial and Term classes to allow for addition, subtraction and multiplication of a term unto a polynomial.
 * Ex. 3x * (4x^2 + 2x) = 12x^3 + 6x^2
 *
 *
 * @author Danny Lee, Zoyah
 * @version 1.0
 *
 */

package models;

import edu.miracosta.cs113.Term;
import java.util.Stack;
import java.util.EmptyStackException;

public class CalculatorModel implements CalculatorInterface {

    private Stack<Character> operatorStack;
    private Stack<Character> tempOperatorStack;
    private Stack<Integer> operandStack;
    private static final String OPERATORS = "+-*/()";
    private static final int[] PRECEDENCE = {1, 1, 2, 2, -1, -1};
    private StringBuilder postfix;

    /**
     * Default Constructor
     */
    public CalculatorModel(){
        operatorStack = new Stack<Character>();
        operandStack = new Stack<Integer>();
        tempOperatorStack = new Stack<Character>();
    }

    /**
     * A method that takes the first derivative of a given Term.
     * @param givenString a string to take derivative of.
     * @return derivative of givenString term.
     */
    public String derive(String givenString){
        String termWithoutWhiteSpace = givenString.replaceAll("\\s","");
        Term givenTerm = new Term(termWithoutWhiteSpace);
        String result = "";
        if(givenTerm.getExponent() == 0){
            givenTerm.setExponent(0);
            givenTerm.setCoefficient(0);
        }else{
            givenTerm.setCoefficient(givenTerm.getCoefficient() * givenTerm.getExponent());
            givenTerm.setExponent(givenTerm.getExponent() - 1);
        }
        result = givenTerm.toString();
        System.out.println("Result : " + result);
        return result;
    }


    /**
     * Convert a string from infix to postfix
     * @param infix The infix expression
     * @return postfix expression.
     */
    public String convert(String infix) throws SyntaxErrorException{
        postfix = new StringBuilder();
        char nextFirstChar;

        try{
            for(int i = 0; i < infix.length(); i++){
                nextFirstChar = infix.charAt(i);
                if(Character.isJavaIdentifierStart(nextFirstChar) || Character.isDigit(nextFirstChar)){
                    postfix.append(nextFirstChar);
                    postfix.append(' ');
                    operandStack.push(nextFirstChar - 48);
                }
                else if(isOperator(nextFirstChar)){
                    if(nextFirstChar == '-' && operandStack.empty()){
                        int nextOperand = infix.charAt(i+1)-48;
                        nextOperand = -1 * nextOperand;
                        operandStack.push(nextOperand);
                        postfix.append(nextOperand);
                        i = i + 1;
                    }else if(i + 1 < infix.length() && isOperator(infix.charAt(i+1))){
                        char nextOperator = infix.charAt(i+1);
                        if(nextOperator == '-'){
                            postfix.append((infix.charAt(i+2) - 48) *(-1));
                            operandStack.push((infix.charAt(i+2) - 48) *(-1));
                            i = i + 2;
                        }
                        processOperator(nextFirstChar);
                    }else{
                        processOperator(nextFirstChar);
                    }
                }else{
                    throw new SyntaxErrorException("Unexpected Character Encountered: " + nextFirstChar);
                }
            }


            while(!operatorStack.empty()){
                char operator = operatorStack.pop();
                if(operator == '('){
                    throw new SyntaxErrorException("Unmatched opening parenthesis");
                }
                postfix.append(operator);
                postfix.append(' ');
                tempOperatorStack.push(operator);
            }

            //Restoring the oprator stack
            while(!tempOperatorStack.empty()){
                char operator = tempOperatorStack.pop();
                operatorStack.push(operator);
            }

        }catch(EmptyStackException ex){
            throw new SyntaxErrorException("Syntax Error: The stack is empty");
        }

        return postfix.toString();
    }

    /**
     * It checks the precedence of an operator and pushes it to appropriate position in operatorStack.
     * @param operator the operator that needs to be checked.
     */
    private void processOperator(char operator){

        if(operatorStack.empty() || operator == '('){
            operatorStack.push(operator);
        }else{
            char topOperator = operatorStack.peek();
            if(precedence(operator) > precedence(topOperator)){
                operatorStack.push(operator);
            }else{
                while(!operatorStack.empty() && precedence(operator) <= precedence(topOperator)){
                    operatorStack.pop();
                    System.out.println(topOperator + "was popped out. line182.");
                    tempOperatorStack.push(topOperator);
                    if(topOperator == '('){
                        break;
                    }
                    postfix.append(topOperator);
                    System.out.println(topOperator+ "is appended to postfix.(line 181)");
                    postfix.append(' ');
                    if(!operatorStack.empty()){
                        topOperator = operatorStack.peek();
                    }
                }
                if(operator != ')'){
                    operatorStack.push(operator);
                }
            }
        }
    }//end of processOperator

    /**
     * Determines whether a character is an operator.
     * @param character The character to be tested
     * @return true if character is an operator.
     */
    private boolean isOperator(char character){
        return OPERATORS.indexOf(character) != -1;
    }

    /**
     * Returns the precedence value of an operator.
     * @param operator the operator to be checked.
     * @return the precedence value of the operator.
     */
    private int precedence(char operator){
        return PRECEDENCE[OPERATORS.indexOf(operator)];
    }

    /**
     * Evaluates a postfix expression.
     * @param expression The expression to be evaluated
     * @return The value of the expression.
     */
    @Override
    public int evaluate(String expression) throws SyntaxErrorException {
        String postfixExpression = "";
        int leftOperand = 0;
        int rightOperand = 0;
        char operatorPopped = ' ';
        int result = 0;

        System.out.println("(evalulate() method.)Input expression : " + expression);
        String expressionWithoutWhiteSpace = expression.replaceAll("\\s","");
        postfixExpression = this.convert(expressionWithoutWhiteSpace);
        System.out.println("==========Converted infix to postfix expression : " + postfixExpression);

        String postfixExpressionWithoutWhiteSpace = postfixExpression.replaceAll("\\s","");
        String[] tokens = postfixExpressionWithoutWhiteSpace.split("[-+*/]");
        for (String split: tokens) {
            System.out.println(split);
        }
        System.out.println("(evalulate() method.) Is operator stack empty? " + operatorStack.isEmpty());
        while(!(operatorStack.isEmpty())){
            operatorPopped = operatorStack.pop();
            System.out.println("Popped operator : " + operatorPopped);
            rightOperand = operandStack.pop();
            System.out.println("rightOperand : " + rightOperand);
            leftOperand = operandStack.pop();
            System.out.println("leftOperand : " + leftOperand);

            switch(operatorPopped){
                case '+' : result = leftOperand + rightOperand;
                    operandStack.push(result);
                    break;
                case '-' : result = leftOperand - rightOperand;
                    operandStack.push(result);
                    break;
                case '*' : result = leftOperand * rightOperand;
                    operandStack.push(result);
                    break;
                case '/' : result = leftOperand / rightOperand;
                    operandStack.push(result);
                    break;
            }
        }

        System.out.println("Result : " + operandStack.peek());
        return operandStack.peek();
    }

    //Nested Class
    /**
     * Class to report a syntax error
     */
    public static class SyntaxErrorException extends Exception{
        /**
         * Construct a SyntaxErrorException with the specified message.
         * @param message The message
         */
        SyntaxErrorException(String message){
            super(message);
        }
    }//end of inner class SyntaxErrorException

}//end of CalculatorModel class
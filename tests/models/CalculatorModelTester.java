package models;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CalculatorModelTester {

    private CalculatorModel tester =  new CalculatorModel();

    //this method will run at the start of every test!
    @Before
    public void setUp(){
        tester = new CalculatorModel();
    }

    @Test
    public void testConvert(){
        String infixExpression = "3+4*2";
        String postfixExpression = "";
        try{
            postfixExpression = tester.convert(infixExpression);
            System.out.println("PostfixExpression : " + postfixExpression);
        }catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Failed to convert infix to postfix expression!");
        }
        assertEquals("Expectd and actual don't match.","3 4 2 * + ", postfixExpression);
    }

    @Test
    public void testAddTwoPositives(){
        int result = 0;
        try{
            result =  tester.evaluate(" 2 + 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(5, result);
    }

    @Test
    public void testAddDifferntSigns(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 + 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(1, result);
    }

    @Test
    public void testAddTwoNegatives(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 + -3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(-5, result);
    }

    @Test
    public void testSubtractTwoPositives(){
        int result = 0;
        try{
            result =  tester.evaluate(" 2 - 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testSubtractDifferentSigns(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 - 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(-5, result);
    }

    @Test
    public void testSubtractTwoNegatives(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 - -3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals( 1, result);
    }

    @Test
    public void testDividingTwoPositives(){
        int result = 0;
        try{
            result =  tester.evaluate(" 3 / 2");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(1, result);
    }

    @Test
    public void testDividingDifferntSigns(){
        int result = 0;
        try{
            result =  tester.evaluate(" -3 / 2");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testDividingTwoNegatives(){
        int result = 0;
        try{
            result =  tester.evaluate(" -3 / -2");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(1, result);
    }


    @Test
    public void testMultiplyTwoPositives(){
        int result = 0;
        try{
            result =  tester.evaluate(" 2 * 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(6, result);
    }

    @Test
    public void testMultiplyTwoNegatives(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 * -3");
            System.out.println("@@Result : " + result);
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(6, result);
    }

    @Test
    public void testMultiplyPosiveAndNegative(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 * 3");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(-6, result);
    }
    @Test
    public void testMultiplyPositiveBy0(){
        int result = 0;
        try{
            result =  tester.evaluate(" 2 * 0");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(0, result);
    }
    @Test
    public void testMultiplyNegativeBy0(){
        int result = 0;
        try{
            result =  tester.evaluate(" -2 * 0");
        }
        catch(CalculatorModel.SyntaxErrorException e){
            System.out.println("Syntax Error Occurred!");
        }
        Assert.assertEquals(0, result);
    }
    @Test
    public void testDeriveConstantNumber(){
        String result = tester.derive("1");
        Assert.assertEquals("0", result);
    }    @Test
    public void testDeriveX1(){
        String result = tester.derive("x");
        Assert.assertEquals("1", result);
    }
    @Test
    public void testDeriveX2(){
        String result = tester.derive("x^2");
        Assert.assertEquals("2x", result);
    }
    @Test
    public void testDerivePositiveCoefficient(){
        String result = tester.derive("2x^3");
        Assert.assertEquals("6x^2", result);
    }

    @Test
    public void testDeriveNegativeCoefficient(){
        String result = tester.derive("-2x^3");
        Assert.assertEquals("-6x^2", result);
    }

}

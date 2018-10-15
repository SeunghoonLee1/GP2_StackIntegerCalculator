package edu.miracosta.cs113;

/**
 * Term.java : This class contains the exponents and coefficient. It also implements the Comparable interface
 * by comparing the values of the exponents.
 *
 *  @author Danny Lee
 *  @version 1.0
 */

public class Term implements Comparable<Term>{
    
    private int exponent = 0;
    private int coefficient = 0;

    /**
     * Default constructor, initializes instance variables.
     */
    public Term(){
        exponent = 0;
        coefficient = 0;
    }

    /**
     * Full constructor, specifying each part of Term.
     *
     * @param givenExponent integer representing exponent of the term.
     * @param givenCoefficient  integer representing coefficient of the term.
     */
    public Term(int givenExponent, int givenCoefficient){
        exponent = givenExponent;
        coefficient = givenCoefficient;
    }

    /**
     * Copy constructor, deep copies Term object
     *
     * @param anotherTerm used to get all Term parts and deep copy.
     */
    public Term(Term anotherTerm){
        this(anotherTerm.getExponent(), anotherTerm.getCoefficient());
    }

    /**
     * String constructor, gets the exponent and coefficient of a term from givenTerm and sets them to
     * this object's instance variables.
     *
     * @param givenTerm String value that is used for setting the exponent and coefficient of a term.
     */
    public Term(String givenTerm){

        //when term does not contain x (when the exponent is 0)
        if(!(givenTerm.contains("x"))){
            coefficient = Integer.parseInt(givenTerm);
            exponent = 0;
        }else if(givenTerm.contains("x") && !(givenTerm.contains("^"))){//when the exponent is 1;
            exponent = 1;
            if(givenTerm.charAt(0) == 'x'){
                coefficient = 1;
            }else if(givenTerm.charAt(0) == '-' && givenTerm.charAt(1) == 'x'){
                coefficient = -1;
            }else{
                coefficient = Integer.parseInt(givenTerm.substring(0, givenTerm.indexOf("x")));
            }

        }else{//when it contains ax^n
            if(givenTerm.charAt(0) == 'x'){//when it starts with 1
                coefficient = 1;
                exponent =  Integer.parseInt(givenTerm.substring(givenTerm.indexOf("^") + 1));
            }else if(givenTerm.charAt(0) == '-' && givenTerm.charAt(1) == 'x'){//when it starts with -1
                coefficient = -1;
                exponent =  Integer.parseInt(givenTerm.substring(givenTerm.indexOf("^") + 1));
            }else{
                coefficient= Integer.parseInt(givenTerm.substring(0, givenTerm.indexOf("x")));
                exponent =  Integer.parseInt(givenTerm.substring(givenTerm.indexOf("^") + 1));
            }
        }

    }

    /**
     * Mutator for exponent value.
     *
     * @param givenExponent integer value representing the exponent of the term.
     */
    public void setExponent(int givenExponent){
        exponent = givenExponent;
    }

    /**
     * Mutator for coefficient value.
     *
     * @param givenCoefficient integer value representing the coefficient of the term.
     */
    public void setCoefficient(int givenCoefficient){
        coefficient = givenCoefficient;
    }

    /**
     * Accessor for exponent value.
     *
     * @return exponent integer value representing the exponent of the term.
     */
    public int getExponent(){
        return exponent;
    }

    /**
     * Accessor for coefficient value.
     *
     * @return coefficient integer value representing the coefficient of the term.
     */
    public int getCoefficient(){
        return coefficient;
    }

    /**
     * Mutator for exponent and coefficient value.
     *
     * @param givenExponent integer value representing the exponent of the term.
     * @param givenCoefficient integer value representing the coefficient of the term.
     */
    public void setAll(int givenExponent, int givenCoefficient){
        exponent = givenExponent;
        coefficient = givenCoefficient;
    }



    /**
     * compareTo method
     * It compares the exponents of the two terms.
     *
     * @return integer value that represents the result of comparing two Term objects.
     */
    @Override
    //It compares the values of the "exponents".
    public int compareTo(Term anotherTerm) {
        return (this.getExponent() - anotherTerm.getExponent());
    }

    /**
     * clone method
     * It creates a copy of a Term object.
     *
     * @return clonedTerm a Term which is a copy of this Term object.
     */
    public Term clone(){
        Term clonedTerm = new Term();
        clonedTerm.setCoefficient(this.getCoefficient());
        clonedTerm.setExponent(this.getExponent());

        return clonedTerm;
    }


    /**
     *toString representing the values of the Term.
     *
     * @return stringToReturn representing the term.
     */
    @Override
    public String toString(){
        String stringToReturn = "";
        if(exponent == 0){
            stringToReturn = coefficient + "";
        }else if(exponent == 1){
            stringToReturn = coefficient + "x";
        }else{
            stringToReturn = coefficient + "x^" + exponent;
        }
        return stringToReturn;
    }

    /**
     * Equals method checks all instance variables are equal.
     *
     * @param otherObject another object that is being compared.
     * @return boolean value that represents if two Terms are equal or not.
     */
    @Override
    public boolean equals(Object otherObject){
        if(otherObject == null){
            return false;
        }else if(getClass() != otherObject.getClass()){
            return false;
        }else{
            Term otherTerm = (Term)otherObject;
            return(exponent == otherTerm.getExponent() && coefficient == otherTerm.getExponent());
        }
    }

}//end of class
package Application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hashim on 16.3.2016 г..
 */
public class Fraction {
    private int numerator;
    private int denumerator;

    public Fraction(int numerator, int denumerator) {
        this.setNumerator(numerator);
        this.setDenumerator(denumerator);
        this.reduceFractionIfNeed();
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenumerator() {
        return denumerator;
    }

    public void setDenumerator(int denumerator) {
        if (denumerator == 0){
            throw new InvalidParameterException("Denumerator cannot be 0");
        }

        this.denumerator = denumerator;
    }

    public double getResult(){
        return (double)this.numerator / this.denumerator;
    }

    public void add(Fraction fractionToAdd){
        if (this.denumerator == fractionToAdd.denumerator){
            this.numerator = this.numerator + fractionToAdd. numerator;
        }
        else{
            int lcm = this.getLeastCommonMultiplier(this.denumerator, fractionToAdd.denumerator);
            int numberToMultiply = lcm / this.denumerator;
            this.numerator *= numberToMultiply;
            this.denumerator *= numberToMultiply;
            numberToMultiply = lcm / fractionToAdd.denumerator;
            fractionToAdd.numerator *= numberToMultiply;
            this.numerator = this.numerator + fractionToAdd.numerator;
        }

        this.reduceFractionIfNeed();
    }

    public void substract(Fraction subtrahend){
        if (this.denumerator != subtrahend.denumerator){
           int lcm = this.getLeastCommonMultiplier(this.denumerator, subtrahend.denumerator);
            int numberToMultiply = lcm / this.denumerator;
            this.denumerator *= numberToMultiply;
            this.numerator *= numberToMultiply;
            numberToMultiply = lcm / subtrahend.denumerator;
            subtrahend.denumerator *= numberToMultiply;
            subtrahend.numerator *= numberToMultiply;
        }

        this.numerator -= subtrahend.numerator;
    }

    public void multiply(Fraction fractionToMultiply){
        this.numerator *= fractionToMultiply.numerator;
        this.denumerator *= fractionToMultiply.denumerator;
        this.reduceFractionIfNeed();
    }

    public void divide(Fraction dividerFraction){
        this.numerator *= dividerFraction.denumerator;
        this.denumerator *= dividerFraction.numerator;
        this.reduceFractionIfNeed();
    }

    private int getLeastCommonMultiplier(int number, int secondDenumerator){
        List<Integer> firstIntegerFactorization = this.getIntegerFactorization(number);
        List<Integer> secondCommonNumbers =this.getIntegerFactorization(secondDenumerator);
        int lcm = 1;
        int iterations = firstIntegerFactorization.size();
        for (int i = 0; i < iterations; i++) {
            int integer = firstIntegerFactorization.get(i);
            lcm*= integer;
            secondCommonNumbers.remove((Object)integer);
        }

        for (int i = 0; i <secondCommonNumbers.size(); i++) {
            lcm *= secondCommonNumbers.get(i);
        }

        return lcm;
    }

    private void reduceFractionIfNeed(){
        if (this.numerator == this.denumerator){
            this.numerator = 1;
            this.denumerator = 1;
            return;
        }

        int gcd = this.getGreatestCommonDivisor(this.numerator, this.denumerator);
        if (gcd > 1){
            this.numerator/= gcd;
            this.denumerator/= gcd;
        }
    }

    private int getGreatestCommonDivisor(int firstNumber, int secondNumber){
        List<Integer> firstNumberFactorization = this.getIntegerFactorization(firstNumber);
        List<Integer> secondNumberFactorization = this.getIntegerFactorization(secondNumber);
        int gcd = 1;
        for (int i = 0; i < firstNumberFactorization.size(); i++) {
            int number = firstNumberFactorization.get(i);
            if (secondNumberFactorization.remove((Object)number)){
                gcd *= number;
            }
        }

        return gcd;
    }

    private List<Integer> getIntegerFactorization(int number){
        List<Integer> firstIntegerFactorization = new ArrayList<>();
        number = Math.abs(number);
        while(number != 1){
            boolean divided = false;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0){
                    firstIntegerFactorization.add(i);
                    number = number / i;
                    divided = true;
                }
            }
            if (!divided){
                firstIntegerFactorization.add(number);
                number = 1;
            }
        }
        
        return firstIntegerFactorization;
    }

    @Override
    public String toString() {
        if (this.denumerator == 1 || this.denumerator == -1){
            return Integer.toString((int)this.getResult());
        }
        else{
            if (this.numerator == 0){
                return "0";
            }

            return Integer.toString(this.numerator) + "/" + Integer.toString(this.denumerator);
        }
    }
}

import java.util.ArrayList;

public class Number {

    private int number;
    private ArrayList<Integer> factorList, primeFactorList;

    /**
     * 
     * @param number The number to retrieve the factors & prime factors of
     */

    public Number(int number) {
        this.number = number;
    }

    /**
     * @return the number
     */

    public int getNumber() {
        return this.number;
    }

    /**
     * Check if the trial number is a factor of the number by returning whether there is a remainder or not
     * @return whether there is a remainder or not
     */

    private boolean isFactor(int trialNumber) {
        return this.number % trialNumber == 0;
    }

    /**
     * @return the factor list
     */

    public ArrayList<Integer> getFactorList() {
        this.populateFactorList();
        return this.factorList;
    }

    /**
     * 
     * @return the prime factor list, a subset of the factor list
     */

    public ArrayList<Integer> getPrimeFactorList() {
        this.populatePrimeFactorList();
        return this.primeFactorList;
    }

    /**
     * Checks a trial number against the original number & checks to see whether the trial number will divide evenly into the number.
     * Increment the trial number by 1 each time until the trial number is equal to the number itself. If the trial number does not have a remainder, it is a factor & gets added to the factor list. If the trial number has a remainder, then it is not a factor & does not get added to the factor list.
     */

    private void populateFactorList() {
        this.factorList = new ArrayList<>();
        int i = 1;
        while (i <= number) {
            if (this.isFactor(i)) this.factorList.add(i);
            i++;
        }
    }

    /**
     * Goes through the original factor list. Instead of iterating from 1 to the number, the position of the trial number inside the original factor list is incremented by 1. If the factor in question is prime, then that factor gets added to the list of prime factors. If not, then the factor does not get added to the list of prime factors.
     */

    private void populatePrimeFactorList() {
        this.primeFactorList = new ArrayList<>();
        int n = this.sizeOfFactorList(), i = 0;
        this.populateFactorList();
        while (i < n) {
            int trialNumber = this.factorList.get(i);
            if (isPrime(trialNumber)) this.primeFactorList.add(trialNumber);
            i++;
        }
    }

    /**
     * Creates a temporary instance of the {@link Number} class to retrieve the number of factors of that number.
     * @param trialNumber the number to test the number of factors
     * @return whether the number is prime or not
     */

    private boolean isPrime(int trialNumber) {
        Number demo = new Number(trialNumber);
        return demo.sizeOfFactorList() < 3;
    }

    /**
     * 
     * @return the quantity of factors
     */

    public int sizeOfFactorList() {
        return this.getFactorList().size();
    }

    /**
     * 
     * @return the quantity of prime factors
     */

    public int sizeOfPrimeFactorList() {
        return this.getPrimeFactorList().size();
    }

    /**
     * @return a string consisting of the number, factors, prime factors, quantity of factors & quantity of prime factors
     */

    public String toString() {
        this.populateFactorList();
        this.populatePrimeFactorList();
        String ret = "Number: " + this.number + "\n" + "Factors: ";
        int i = 0, m = this.sizeOfFactorList();
        int j = 0, n = this.sizeOfPrimeFactorList();
        while (i < m) {
            ret += this.getFactorList().get(i++) + " ";
        }
        ret += "\n" + "Prime Factors: ";
        while (j < n) {
            ret += this.getPrimeFactorList().get(j++) + " ";
        }
        ret += "\n" + "Number of Factors: " + this.sizeOfFactorList();
        ret += "\n" + "Number of Prime Factors: " + this.sizeOfPrimeFactorList() + "\n";
        return ret;
    }

}
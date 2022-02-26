import java.util.ArrayList;

public class CommonFactor {
    
    private ArrayList<Number> numbers;

    /**
     * 
     * @param commonFactorComparisons The list of numbers to derive common factors from
     */
    
    public CommonFactor(int [] commonFactorComparisons) {
        this.numbers = new ArrayList<>();
        int i = 0, n = commonFactorComparisons.length;
        while (i < n) {
            Number current = new Number(commonFactorComparisons[i]);
            this.numbers.add(current);
            i++;
        }
    }

    /**
     * 
     * @param commonFactorComparisons the list of numbers to derive common factors from
     */

    public CommonFactor(ArrayList<Integer> commonFactorComparisons) {
        this.numbers = new ArrayList<>();
        int i = 0, n = commonFactorComparisons.size();
        while (i < n) {
            int x = commonFactorComparisons.get(i);
            Number current = new Number(x);
            this.numbers.add(current);
            i++;
        }
    }

    /**
     * 
     * @return a list of integers
     */

    private ArrayList<Integer> getIntegers() {
        ArrayList<Integer> integers = new ArrayList<>();
        int i = 0, n = numbers.size();
        while (i < n) {
            integers.add(this.numbers.get(i++).getNumber());
        }
        return integers;
    }

    /**
     * 
     * @return an array of integers
     */

    private int [] getIntegerArray() {
        ArrayList<Integer> list = this.getIntegers();
        int i = 0, n = list.size();
        int [] arr = new int[n];
        while (i < n) {
            arr[i] = list.get(i++);
        }
        return arr;
    }

    /**
     * 
     * @return a list of numbers
     */

    private ArrayList<Number> getNumbers() {
        return this.numbers;
    }

    /**
     * 
     * @return a list of all the factors derived
     */

    private ArrayList<ArrayList<Integer>> expandedFactorList() {
        ArrayList<ArrayList<Integer>> commFactorList = new ArrayList<>();
        int i = 0, n = this.getNumbers().size();
        while (i < n) {
            commFactorList.add(this.getNumbers().get(i).getFactorList());
            i++;
        }
        return commFactorList;
    }

    /**
     * Sifts through the {@link expandedFactorList()} to retrieve the concrete array representation of all the expanded factors
     * @return an array of common factors
     */

    private int [][] expandedFactorArray() {
        ArrayList<ArrayList<Integer>> expandFactorList = this.expandedFactorList();
        int i = 0, j = 0, m = expandFactorList.size(), n;
        int [][] arr = new int[m][];
        while (i < m) {
            ArrayList<Integer> list = expandFactorList.get(i);
            n = list.size();
            arr[i] = new int[n];
            while (j < n) {
                arr[i][j] = list.get(j);
                j++;
            }
            i++;
            j = 0;
        }
        return arr;
    }

    /**
     * 
     * @return the string representation of the expanded factor list
     */

    private String expandedFactorListString() {
        ArrayList<ArrayList<Integer>> list = this.expandedFactorList();
        String ret = "";
        for (ArrayList<Integer> innerList : list) {
            for (Integer element : innerList) {
                ret += Integer.toString(element) + " ";
            }
            ret += "\n";
        }
        return ret;
    }

    /**
     * 
     * @param trialFactor the trial factor, the factor that is being checked against all other existing factors across all numbers
     * @return whether the trial factor is a common factor of all numbers. This value is false if even one of the numbers does not share the trial factor.
     */

    private boolean isSharedFactor(int trialFactor) {
        int [][] sharedFactorArray = this.expandedFactorArray();
        int i = 0, m = sharedFactorArray.length, j = 0, n, count = this.getIntegerArray().length, tick = 0;
        while (i < m) {
            int [] innerArray = sharedFactorArray[i];
            n = innerArray.length;
            while (j < n) {
                if (innerArray[j] == trialFactor) tick++;
                j++;
            }
            i++;
            j = 0;
        }
        return tick == count;
    }

    /**
     * Used to generate the list of common factors 
     * @return the highest of the numbers input
     */

    private int highestNumber() {
        ArrayList<Integer> list = this.getIntegers();
        int highestNumber = list.get(0), i = 0, n = list.size();
        while (i < n) {
            int trialNumber = list.get(i);
            if (highestNumber < trialNumber) highestNumber = trialNumber;
            i++;
        }
        return highestNumber;
    }

    /**
     * 
     * @return a list of factors shared between all numbers
     */

    public ArrayList<Integer> commonFactors() {
        int i = 0, n = this.highestNumber();
        ArrayList<Integer> commonFactors = new ArrayList<>();
        while (i < n) {
            if (isSharedFactor(i)) {
                commonFactors.add(i);
            }
            i++;
        }
        return commonFactors;
    }

    /**
     * 
     * @return the greatest common factor, the common factor at the end of the list
     */

    public int greatestCommonFactor() {
        return this.commonFactors().get(this.commonFactors().size() - 1);
    }

    /**
     * 
     * @return the string representation of the list of common factors
     */

    private String printCommonFactors() {
        ArrayList<Integer> list = this.commonFactors();
        String ret = "";
        for (Integer element : list) {
            ret += Integer.toString(element) + " ";
        }
        return ret;
    }

    /**
     * 
     * @return the string representation of the concrete number array
     */

    private String printIntegerArray() {
        int [] arr = this.getIntegerArray();
        String ret = "";
        for (Integer element : arr) {
            ret += Integer.toString(element) + " ";
        }
        return ret;
    }

    /**
     * @return the string representation of this "Common Factor" object
     */

    public String toString() {
        String ret = "";
        ret += "Numbers: " + this.printIntegerArray();
        ret += "\n" + "Factors: " + this.expandedFactorListString();
        ret += "\n" + "Common Factors: " + this.printCommonFactors();
        ret += "\n" + "Greatest Common Factor: " + this.greatestCommonFactor();
        return ret;
    }

}
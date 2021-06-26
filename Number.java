package numbers;

import java.util.Arrays;

import static java.lang.System.out;

public class Number {
    long value;

    public void printProperties() {
        String resultString = this.value + " is ";
        for(Property property : Property.values()) {
            if (this.hasProperty(property)) {
                resultString +=  property.name().toLowerCase() + ", ";
            }
        }
        out.println(resultString.substring(0, resultString.length() - 2));
    }

    Number(long value) {
        this.value = value;
    }

    public int length() {
        return (int) Math.log10(this.value) + 1;
    }

    public boolean isEven(){
        return this.value % 2 == 0;
    }

    public boolean isOdd(){
        return !this.isEven();
    }

    public boolean isBuzz() {
        return (this.value % 7 == 0) || (this.value % 10 == 7);
    }

    public boolean isDuck() {
        for (int i = 0; i < length(); i++) {
            if (this.getDigit(i) == 0) return true;
        }
        return false;
    }

    public boolean isPalindromic() {
        for(int i = 0; i < this.length()/2 ; i++ ) {
            if(this.getDigit(i) != this.getDigit(length() - i - 1)) return false;
        }
        return true;
    }

    public boolean isGapful() {
        if (this.length() < 3) return false;
        int divisor = this.getDigit(this.length() - 1 ) * 10 + this.getDigit(0);
        return this.value % divisor == 0;
    }

    public boolean isSpy() {
        int allProduct = 1;
        int allSum = 0;
        for(int i = 0; i < this.length(); i++) {
            allSum += this.getDigit(i);
            allProduct *= this.getDigit(i);
        }
        return allSum == allProduct;
    }

    public boolean isSquare() {
        int root = (int) Math.round(Math.sqrt(this.value));
        return root * root == this.value;
    }

    public boolean isSunny() {
        Number num = new Number(this.value + 1);
        return num.isSquare();
    }

    public boolean isJumping() {
        for(int i = 0; i < this.length() - 1; i++) {
            if(Math.abs(this.getDigit(i) - this.getDigit(i + 1)) != 1) return false;
        }
        return true;
    }

    public boolean isHappy() {
        long[] emptyArray = new long[0];
        return happyHelper(this.value,emptyArray);
    }

    public boolean isSad() {
        return !this.isHappy();
    }

    /* the natural algorithm to find happy numbers needs to remember all the numbers that
       came before it, so we need a method which allows us to look those up  */
    public static boolean happyHelper(long num, long[] seenNumbers) {
        Number number = new Number(num);

        long newNum = 0;
        for(int i = 0; i < number.length(); i++) {
            newNum += number.getDigit(i) * number.getDigit(i);
        }
        for(long x : seenNumbers) {
            if( newNum == x) {
                return false;
            }
        }
        if(newNum == 1) {
            return true;
        }

        long[] newSeenNumbers = new long[seenNumbers.length + 1];

        for(int i = 0; i < seenNumbers.length; i++) {
            newSeenNumbers[i] = seenNumbers[i];
        }
        newSeenNumbers[seenNumbers.length] = newNum;


        return happyHelper(newNum, newSeenNumbers);


    }

    public boolean hasProperty(Property property) {
        switch(property) {
            case EVEN:
                return this.isEven();
            case ODD:
                return this.isOdd();
            case BUZZ:
                return this.isBuzz();
            case DUCK:
                return this.isDuck();
            case PALINDROMIC:
                return this.isPalindromic();
            case GAPFUL:
                return this.isGapful();
            case SPY:
                return this.isSpy();
            case SQUARE:
                return this.isSquare();
            case SUNNY:
                return this.isSunny();
            case JUMPING:
                return this.isJumping();
            case HAPPY:
                return this.isHappy();
            case SAD:
                return this.isSad();
            default:
                System.out.println("you are looking for a propery that is not implemented (Number.hasProperty)");
                return false;
        }
    }

    public int getDigit(int position) {
        return (int) (this.value / ((long) Math.pow(10, position)) % 10);
    }
}

public class JustinBCD {
    private int[] digits;

    JustinBCD(int[] BcdDigits) {
        digits = new int[BcdDigits.length];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = BcdDigits[i];
        }
    }

    public int numberOfDigits() {
        return digits.length;
    }

    public int nthDigit(int n) {
        if (n >= digits.length) {
            return 0;
        } else
            return digits[n];
    }

    public void addADigit(int newdigit) {
        int[] NewA = new int[digits.length + 1];
        for (int i = 0; i < digits.length; i++) {
            NewA[i] = digits[i];
        }
        NewA[digits.length] = newdigit;
        digits = NewA;
    }

    JustinBCD(int num) {
        int digit;
        // digits = new int[1];
        digits = new int[0];
        while (num > 0) {
            digit = num % 10;
            addADigit(digit);
            // digits[1] = digit;
            num = num / 10;
        }
    }

    public String toString() {
        String array = "";
        // int point = digits.length + 1;
        int point = digits.length - 1;
        // for (int x = digits.length - 1; x > 0; x--) {
        for (int x = digits.length - 1; x >= 0; x--) {
            array += digits[x];
            if (point % 3 == 0 && point > 0) {
                array += ",";
            }
            point--;
        }
        return array;
    }

    public static void printArray(int[] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + "}");
    }

    public static void main(String args[]) {
        JustinBCD first = new JustinBCD(1234);
        printArray(first.digits);
        System.out.println(first.toString());
        JustinBCD second = new JustinBCD(1234567);
        printArray(second.digits);
        System.out.println(second.toString());
        JustinBCD Third = new JustinBCD(123456789);
        printArray(Third.digits);
        System.out.println(Third.toString());

    }
}
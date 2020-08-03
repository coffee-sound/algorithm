package algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Merge {

    public static final int N = 16;
    
    public static void main(String[] args) {
        int[] data = generateData();
        output(data); // 生成したデータ配列を表示
        Division division = new Division(data);
        division.output(); // 分けられた後の配列を表示
        division.merge(); // マージする
        output(division.getNumbers()); // 結果を出力
    }

    private static int[] generateData() {
        Random rand = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(i + 1);
        }
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            int element = rand.nextInt(numbers.size());
            data[i] = numbers.get(element);
            numbers.remove(numbers.indexOf(data[i]));
        }
        return data;
    }

    private static void output(int[] data) {
        System.out.print("[ ");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }
}

class Division {

    private Division left;
    private Division right;
    private int[] numbers;

    public Division(int[] data) {
        if (data.length <= 2) {
            numbers = data;
            if (data.length > 1 && data[0] > data[1]) {
                int tmp = numbers[0];
                numbers[0] = numbers[1];
                numbers[1] = tmp;
            }
        } else {
            int half = data.length / 2;
            int[] leftData = new int[half];
            int[] rightData = new int[data.length - half];
            for (int i = 0; i < data.length; i++) {
                if (i < half) {
                    leftData[i] = data[i];
                } else {
                    rightData[i - half] = data[i];
                }
            }
            left = new Division(leftData);
            right = new Division(rightData);
        }
    }

    public boolean hasNumbers() {
        return numbers != null;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int[] getNumbersWithHugeNumber() {
        if (this.numbers == null) {
            return null;
        }
        int[] updateData = new int[this.numbers.length + 1];
        for (int i = 0; i < this.numbers.length; i++) {
            updateData[i] = this.numbers[i];
        }
        updateData[this.numbers.length] = Integer.MAX_VALUE;
        return updateData;
    }

    public void output() {
        this.output(true);
    }

    private void output(boolean hasLine) {
        System.out.print("[ ");
        if (this.hasNumbers()) {
            for (int i = 0; i < this.numbers.length; i++) {
                System.out.print(this.numbers[i]);
                if (i < this.numbers.length - 1) {
                    System.out.print(", ");
                }
            }
        } else {
            left.output(false);
            System.out.print(", ");
            right.output(false);
        }
        System.out.print(" ]");
        if (hasLine) {
            System.out.println();
        }
    }

    public void merge() {
        if (left.hasNumbers() && right.hasNumbers()) {
            int leftIndex = 0, rightIndex = 0;
            int[] leftData = left.getNumbersWithHugeNumber();
            int[] rightData = right.getNumbersWithHugeNumber();
            int[] result = new int[leftData.length + rightData.length - 2];
            for (int i = 0; i < result.length; i++) {
                int numL = leftData[leftIndex], numR = rightData[rightIndex];
                if (numL < numR) {
                    result[i] = numL;
                    leftIndex++;
                } else {
                    result[i] = numR;
                    rightIndex++;
                }
            }
            this.numbers = result;
            this.left = this.right = null;
        } else {
            if (!left.hasNumbers()) {
                left.merge();
            }
            if (!right.hasNumbers()) {
                right.merge();
            }
            this.merge();
        }
    }
}

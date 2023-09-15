import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "Nums.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            int[] sequence = parseSequence(line);

            int minMultiple37 = findMinMultiple(sequence, 37);
            int maxMultiple73 = findMaxMultiple(sequence, 73);

            int count = 0;
            int minSum = Integer.MAX_VALUE;

            for (int i = 0; i < sequence.length - 1; i++) {
                if ((sequence[i] > minMultiple37 && sequence[i] < maxMultiple73) ||
                        (sequence[i + 1] > minMultiple37 && sequence[i + 1] < maxMultiple73)) {
                    if (!((sequence[i] > minMultiple37 && sequence[i] < maxMultiple73) &&
                            (sequence[i + 1] > minMultiple37 && sequence[i + 1] < maxMultiple73))) {
                        count++;
                        minSum = Math.min(minSum, sequence[i] + sequence[i + 1]);
                    }
                }
            }

            System.out.println("Количество найденных пар: " + count);
            System.out.println("Минимальная сумма элементов среди пар: " + minSum);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
    private static int[] parseSequence(String line) {
        String[] elements = line.split(" ");
        int[] sequence = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            sequence[i] = Integer.parseInt(elements[i]);
        }

        return sequence;
    }
    private static int findMinMultiple(int[] sequence, int num) {
        int minMultiple = Integer.MAX_VALUE;

        for (int element : sequence) {
            if (element % num == 0) {
                minMultiple = Math.min(minMultiple, element);
            }
        }

        return minMultiple;
    }
    private static int findMaxMultiple(int[] sequence, int num) {
        int maxMultiple = Integer.MIN_VALUE;

        for (int element : sequence) {
            if (element % num == 0) {
                maxMultiple = Math.max(maxMultiple, element);
            }
        }

        return maxMultiple;
    }
}
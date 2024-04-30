package algorithm.equations;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Equations {
    public static BigDecimal[] algorithm(int tableSize, int iterationCount, BigDecimal accuracy, BigDecimal[][] table){
        try{
            Matrix matrix = new Matrix(table);
            Console.print("Программа получила на вход следующую матрицу:", "а");
            matrix.print();
            /*try {
                MatrixReformer.diagonalPrevalence(matrix);
                Console.print("\nУдалось диагонализировать матрицу:", "а");
            }
            catch (CannotBeDiagonalException e){
                Console.print(e.getMessage(), "к");
                Console.print("\nМатрица после попытки диагонализирования:", "а");
            }
            matrix.print();
            matrix.normalize();
            table = matrix.getTable();
            Console.print("\nНормализованная матрица:", "п");
            matrix.print();*/
            //BigDecimal[] delta = new BigDecimal[tableSize];
            BigDecimal[] answer = new BigDecimal[tableSize];
            //BigDecimal[] compare = new BigDecimal[tableSize];
            //BigDecimal[] vector = matrix.getVector();
            int count = 0;
            //answer = Arrays.copyOf(vector, vector.length);*/
            /*try {
                boolean finishAnswer = false;
                while (count < iterationCount) {
                    //Console.printEquation(answer);
                    count += 1;
                    BigDecimal maximumDelta = BigDecimal.ZERO;
                    compare = Arrays.copyOf(answer, tableSize);
                    for (int i = 0; i < tableSize; i++) {
                        answer[i] = vector[i];
                        for (int i1 = 0; i1 < tableSize; i1++) {
                            answer[i] = answer[i].add(table[i][i1].multiply(compare[i1]));
                        }
                        delta[i] = answer[i].subtract(compare[i]).abs();
                        maximumDelta = maximumDelta.max(delta[i]);

                    }
                    if (maximumDelta.compareTo(accuracy) <= 0) {
                        finishAnswer = true;
                        break;
                    }
                }
                if (finishAnswer == false) {
                    throw new NoAnswersException();
                }
            }
            catch (NoAnswersException e){
                Console.print("Решения с такой погрешностью за такое количество интервалов не удалось найти!", "к");
            }*/
            while (count < tableSize){
                BigDecimal maximum = new BigDecimal(0);
                int c = 0;
                for (int i = count; i < tableSize; i++){
                    if (table[i][count].abs().compareTo(maximum) > 0) {
                        maximum = table[i][count].abs();
                        c = i;
                    }
                }
                if (count != c){
                    MatrixReformer.swapArray(table[c], table[count]);
                }
                for (int i = 0; i < tableSize; i++){
                    Console.printEquation(table[i]);
                }
                Console.print("", "б");
                for (int i = count + 1; i < tableSize; i++){
                    BigDecimal first = table[i][count].divide(table[count][count], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
                    table[i][count] = new BigDecimal(0);
                    for (int i1 = count + 1; i1 < (tableSize + 1); i1++) {
                        table[i][i1] = table[i][i1].subtract(table[count][i1].multiply(first));
                    }
                }
                for (int i = 0; i < tableSize; i++){
                    Console.printEquation(table[i]);
                }
                Console.print("", "б");
                count += 1;
            }
            count = tableSize - 1;
            while (count >= 0){
                BigDecimal temp = table[count][tableSize];
                for (int i = tableSize - 1; i > count; i--){
                    temp = temp.subtract(answer[i].multiply(table[count][i]));
                }
                temp = temp.divide(table[count][count], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
                answer[count] = temp;
                count -= 1;
            }
            /*int scale = 0;
            BigDecimal temp = accuracy;
            while ((temp.compareTo(new BigDecimal("1")) <= 0) && (scale <= 10)){
                scale += 1;
                temp = temp.multiply(new BigDecimal("10"));
            }
            for (int i = 0; i < answer.length; i++){
                answer[i] = answer[i].setScale(scale, RoundingMode.HALF_EVEN);
            }
            for (int i = 0; i < delta.length; i++){
                delta[i] = delta[i].setScale(scale, RoundingMode.HALF_EVEN);
            }
            Console.print("\nРешения: ", "п");
            Console.printArray(answer, "б");
            Console.print("\nАбсолютные отклонения на последней итерации: ", "п");
            Console.printArray(delta, "б");
            Console.print("\nИтерации: " + count, "п");*/
            return answer;


        }
        catch (MatrixIsNotValidException | NotInIntervalException e){
            Console.print(e.getMessage(), "к");
            return new BigDecimal[]{};
        }
        catch (NumberFormatException e){
            Console.print(e.getMessage(), "к");
            Console.print("Число введено некорректно!", "к");
            return new BigDecimal[]{};
        }
        catch (NoSuchElementException e){
            Console.print(e.getMessage(), "к");
            Console.print("Входные данные неполны!", "к");
            return new BigDecimal[]{};
        }
    }
}

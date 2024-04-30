package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AlgorithmManager {
    public ArrayList<BigDecimal> solve(int function, ArrayList<BigDecimal> data){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        BigDecimal temp = new BigDecimal(0);
        switch (function) {
            case 1:
                answer = linear(data);
                temp = standartDeviation(data, answer, 1);
                answer.add(temp);
                break;
            case 2:
                answer = quadratic(data);
                temp = standartDeviation(data, answer, 2);
                answer.add(temp);
                break;
            case 3:
                answer = cubic(data);
                temp = standartDeviation(data, answer, 3);
                answer.add(temp);
                break;
            case 4:
                answer = exponential(data);
                temp = standartDeviation(data, answer, 4);
                answer.add(temp);
                break;
            case 5:
                answer = logarithmic(data);
                temp = standartDeviation(data, answer, 5);
                answer.add(temp);
                break;
            case 6:
                answer = grade(data);
                temp = standartDeviation(data, answer, 6);
                answer.add(temp);
                break;
            case 7:
                BigDecimal[] e = new BigDecimal[6];
                answer = linear(data);
                temp = standartDeviation(data, answer, 1);
                e[0] = temp;
                answer = quadratic(data);
                temp = standartDeviation(data, answer, 2);
                e[1] = temp;
                answer = cubic(data);
                temp = standartDeviation(data, answer, 3);
                e[2] = temp;
                answer = exponential(data);
                temp = standartDeviation(data, answer, 4);
                e[3] = temp;
                answer = logarithmic(data);
                temp = standartDeviation(data, answer, 5);
                e[4] = temp;
                answer = grade(data);
                temp = standartDeviation(data, answer, 6);
                e[5] = temp;
                int c = 0;
                BigDecimal minimum = new BigDecimal(1000);
                for (int i = 0; i < 6; i++){
                    if (e[i].compareTo(minimum) < 0){
                        c = i;
                        minimum = e[i];
                    }
                }
                if (c == 0){
                    answer = linear(data);
                }
                else if (c == 1){
                    answer = quadratic(data);
                }
                else if (c == 2){
                    answer = cubic(data);
                }
                else if (c == 3){
                    answer = exponential(data);
                }
                else if (c == 4){
                    answer = logarithmic(data);
                }
                else if (c == 5){
                    answer = grade(data);
                }
                answer.add(minimum);
                for (int i = 0; i < answer.size(); i++){
                    //System.out.println(answer.get(i));
                }
                answer.add(new BigDecimal(c));
                break;
        }
        return answer;
    }

    public ArrayList<BigDecimal> linear(ArrayList<BigDecimal> data){
        Linear method = new Linear(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> quadratic(ArrayList<BigDecimal> data){
        Quadratic method = new Quadratic(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> cubic(ArrayList<BigDecimal> data){
        Cubic method = new Cubic(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> exponential(ArrayList<BigDecimal> data){
        Exponential method = new Exponential(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> logarithmic(ArrayList<BigDecimal> data){
        Logarithmic method = new Logarithmic(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public ArrayList<BigDecimal> grade(ArrayList<BigDecimal> data){
        Grade method = new Grade(data);
        ArrayList<BigDecimal> answer = method.solve();
        return answer;
    }

    public BigDecimal standartDeviation(ArrayList<BigDecimal> data, ArrayList<BigDecimal> a, int function){
        BigDecimal answer = new BigDecimal(0);
        for (int i = 0; i < a.size(); i++){
            //System.out.println(a.get(i));
        }
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                /*System.out.println("Число");
                System.out.println(data.get(i));
                System.out.println(Functions.function_approximation(data.get(i), a, function));*/
                answer = answer.add((Functions.function_approximation(data.get(i), a, function).subtract(data.get(i + 1))).pow(2));
            }
        }
        //System.out.println(answer);
        answer = new BigDecimal(Math.sqrt(answer.divide(new BigDecimal(data.size() / 2), 10, RoundingMode.HALF_EVEN).doubleValue()));
        //System.out.println(answer);
        return answer;
    }


}

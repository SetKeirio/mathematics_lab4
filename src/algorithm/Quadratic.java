package algorithm;

import algorithm.equations.Equations;
import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Quadratic {

    ArrayList<BigDecimal> data;
    BigDecimal a, b, c;
    BigDecimal r;
    int n;
    BigDecimal e;

    public Quadratic(ArrayList<BigDecimal> y){
        data = y;
        n = data.size() / 2;
    }

    public ArrayList<BigDecimal> solve(){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        BigDecimal x = new BigDecimal(0);
        BigDecimal y = new BigDecimal(0);
        BigDecimal x2 = new BigDecimal(0);
        BigDecimal x3 = new BigDecimal(0);
        BigDecimal x4 = new BigDecimal(0);
        BigDecimal xy = new BigDecimal(0);
        BigDecimal x2y = new BigDecimal(0);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                x = x.add(data.get(i));
                x2 = x2.add(data.get(i).pow(2));
                x3 = x3.add(data.get(i).pow(3));
                x4 = x4.add(data.get(i).pow(4));
                xy = xy.add(data.get(i).multiply(data.get(i + 1)));
                x2y = x2y.add(data.get(i).pow(2).multiply(data.get(i + 1)));
            }
            else if (i % 2 == 1){
                y = y.add(data.get(i));
            }
        }
        BigDecimal[][] table = new BigDecimal[3][4];
        table[0][0] = new BigDecimal(n);
        table[0][1] = x;
        table[0][2] = x2;
        table[0][3] = y;
        table[1][0] = x;
        table[1][1] = x2;
        table[1][2] = x3;
        table[1][3] = xy;
        table[2][0] = x2;
        table[2][1] = x3;
        table[2][2] = x4;
        table[2][3] = x2y;
        /*for (int i = 0; i < table.length; i++){
            Console.printEquation(table[i]);
        }
        BigDecimal first = x.divide(new BigDecimal(n), 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        table[1][0] = new BigDecimal(0);
        table[1][1] = table[1][1].subtract(table[0][1].multiply(first));
        table[1][2] = table[1][2].subtract(table[0][2].multiply(first));
        table[1][3] = table[1][3].subtract(table[0][3].multiply(first));
        for (int i = 0; i < table.length; i++){
            Console.printEquation(table[i]);
        }
        BigDecimal second = x2.divide(new BigDecimal(n), 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        table[2][0] = new BigDecimal(0);
        table[2][1] = table[2][1].subtract(table[0][1].multiply(second));
        table[2][2] = table[2][2].subtract(table[0][2].multiply(second));
        table[2][3] = table[2][3].subtract(table[0][3].multiply(second));
        for (int i = 0; i < table.length; i++){
            Console.printEquation(table[i]);
        }
        first = table[2][1].divide(table[1][1], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        table[2][1] = new BigDecimal(0);
        table[2][2] = table[2][2].subtract(table[1][2].multiply(first));
        table[2][3] = table[2][3].subtract(table[1][3].multiply(first));
        for (int i = 0; i < table.length; i++){
            Console.printEquation(table[i]);
        }
        c = table[2][3].divide(table[2][2], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        b = table[1][3].subtract(c.multiply(table[1][2])).divide(table[1][1], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        a = table[0][3].subtract(c.multiply(table[0][2])).subtract(b.multiply(table[0][1])).divide(table[0][0], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        System.out.println(x);
        System.out.println(x2);
        System.out.println(y);
        System.out.println(xy);
        System.out.println(n);*/
        BigDecimal[] temp = Equations.algorithm(3, 10, new BigDecimal(0.001), table);
        for (int i = temp.length - 1; i >= 0; i--){
            answer.add(temp[i]);
        }
        /*answer.add(a);
        answer.add(b);
        answer.add(c);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);*/
        return answer;
    }
}

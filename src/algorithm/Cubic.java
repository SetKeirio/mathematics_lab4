package algorithm;

import algorithm.equations.Equations;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cubic {
    ArrayList<BigDecimal> data;
    BigDecimal a, b, e;
    BigDecimal r;
    int n;

    public Cubic(ArrayList<BigDecimal> y){
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
        BigDecimal x5 = new BigDecimal(0);
        BigDecimal x6 = new BigDecimal(0);
        BigDecimal xy = new BigDecimal(0);
        BigDecimal x2y = new BigDecimal(0);
        BigDecimal x3y = new BigDecimal(0);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                x = x.add(data.get(i));
                x2 = x2.add(data.get(i).pow(2));
                x3 = x3.add(data.get(i).pow(3));
                x4 = x4.add(data.get(i).pow(4));
                x5 = x5.add(data.get(i).pow(5));
                x6 = x6.add(data.get(i).pow(6));
                xy = xy.add(data.get(i).multiply(data.get(i + 1)));
                x2y = x2y.add(data.get(i).pow(2).multiply(data.get(i + 1)));
                x3y = x3y.add(data.get(i).pow(3).multiply(data.get(i + 1)));
            }
            else if (i % 2 == 1){
                y = y.add(data.get(i));
            }
        }
        /*System.out.println(x);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(xy);
        System.out.println(x2y);
        System.out.println(y);*/
        BigDecimal[][] table = new BigDecimal[4][5];
        table[0][0] = new BigDecimal(n);
        table[0][1] = x;
        table[0][2] = x2;
        table[0][3] = x3;
        table[0][4] = y;
        table[1][0] = x;
        table[1][1] = x2;
        table[1][2] = x3;
        table[1][3] = x4;
        table[1][4] = xy;
        table[2][0] = x2;
        table[2][1] = x3;
        table[2][2] = x4;
        table[2][3] = x5;
        table[2][4] = x2y;
        table[3][0] = x3;
        table[3][1] = x4;
        table[3][2] = x5;
        table[3][3] = x6;
        table[3][4] = x3y;
        BigDecimal[] temp = Equations.algorithm(4, 10, new BigDecimal(0.001), table);
        for (int i = temp.length - 1; i >= 0; i--){
            answer.add(temp[i]);
        }
        return answer;
    }
}

package algorithm;

import algorithm.equations.Equations;

import javax.sound.sampled.Line;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Linear {

    ArrayList<BigDecimal> data;
    BigDecimal a, b, e;
    BigDecimal r;
    int n;

    public Linear(ArrayList<BigDecimal> y){
        data = y;
        n = data.size() / 2;
    }

    public ArrayList<BigDecimal> solve(){
        ArrayList<BigDecimal> answer = new ArrayList<BigDecimal>();
        BigDecimal x = new BigDecimal(0);
        BigDecimal y = new BigDecimal(0);
        BigDecimal x2 = new BigDecimal(0);
        BigDecimal xy = new BigDecimal(0);
        for (int i = 0; i < data.size(); i++){
            if (i % 2 == 0){
                x = x.add(data.get(i));
                x2 = x2.add(data.get(i).pow(2));
                xy = xy.add(data.get(i).multiply(data.get(i + 1)));
            }
            else if (i % 2 == 1){
                y = y.add(data.get(i));
            }
        }
        BigDecimal[][] table = new BigDecimal[2][3];
        table[0][0] = x2;
        table[0][1] = x;
        table[0][2] = xy;
        table[1][0] = x;
        table[1][1] = new BigDecimal(n);
        table[1][2] = y;
        /*BigDecimal first = x.divide(x2, 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        table[1][0] = new BigDecimal(0);
        table[1][1] = table[1][1].subtract(table[0][1].multiply(first));
        table[1][2] = table[1][2].subtract(table[0][2].multiply(first));
        System.out.println(table[1][2]);
        System.out.println(table[1][1]);
        b = table[1][2].divide(table[1][1], 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        a = xy.subtract(b.multiply(x)).divide(x2, 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
        System.out.println(x);
        System.out.println(x2);
        System.out.println(y);
        System.out.println(xy);
        System.out.println(n);*/
        BigDecimal[] temp = Equations.algorithm(2, 10, new BigDecimal(0.001), table);
        for (int i = 0; i < temp.length; i++){
            answer.add(temp[i]);
        }
        //answer.add(a);
        //answer.add(b);
        //System.out.println(a);
        //System.out.println(b);
        return answer;
    }
}

import algorithm.AlgorithmManager;
import algorithm.Graphics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import support.input.Console;
import support.input.Validate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Main {
    private static AlgorithmManager solver = new AlgorithmManager();
    private static ChartPanel p;
    private static Validate validator = new Validate();
    private static Graphics g = new Graphics();

    private static DefaultTableModel model = new DefaultTableModel();
    private static JTable table = new JTable(model);


    static class InputTableModel extends AbstractTableModel{

        Validate validator = new Validate();
        class Point {
            BigDecimal x, y;

            public BigDecimal getX() {
                return x;
            }

            public void setX(BigDecimal x) {
                this.x = x;
            }

            public BigDecimal getY() {
                return y;
            }

            public void setY(BigDecimal y) {
                this.y = y;
            }
            public Point(){
                x = new BigDecimal(0);
                y = new BigDecimal(0);
            }

            public Point(BigDecimal x, BigDecimal y){
                this.x = x;
                this.y = y;
            }
        }

        private ArrayList<Point> numbers = new ArrayList<Point>();

        private String[] numbers1 = new String[] {"x", "y"};

        @Override
        public String getColumnName(int column) {
            return numbers1[column];
        }



        @Override
        public int getRowCount() {
            return numbers.size();
        }

        @Override
        public int getColumnCount() {
            return numbers1.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            BigDecimal answer = new BigDecimal(-1);
            if (col == 0){
                answer = numbers.get(row).x;
            }
            else if (col == 1){
                answer = numbers.get(row).y;
            }

            return answer;

        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        public void setValueAt(Object value, int row, int col) {

            if (!validator.validateNumber((String) value)){
                return;
            }
            else {
                String temp = (String) value;
                temp = temp.replace(",", ".");
                if (col == 0) {
                    numbers.get(row).setX(new BigDecimal(temp));
                } else if (col == 1) {
                    numbers.get(row).setY(new BigDecimal(temp));
                }
            }
            //fireTableCellUpdated(row, col);
        }

        public void addPoint(BigDecimal x, BigDecimal y){
            numbers.add(new Point(x, y));
            fireTableDataChanged();
        }
    }
    private static InputTableModel input = new InputTableModel();

    public static JFrame getWindow() {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(0, 0, dimension.width, dimension.height);
        window.setTitle("mathematics_lab3_335099");
        return window;

    }

    public static void main(String[] args) {
        int functionNumber = 1;
        int methodNumber = 1;
        JFrame window = getWindow();
        JPanel panel = new JPanel();
        Label label = new Label();
        GridLayout dataAndGraphics = new GridLayout(1, 4);
        GridLayout grid = new GridLayout(3, 1);
        panel.setLayout(grid);
        grid.setHgap(10);
        CheckboxGroup oneFunction = new CheckboxGroup();
        Checkbox firstFunction = new Checkbox("Аппроксимация линейной функцией", oneFunction, true);
        Checkbox secondFunction = new Checkbox("Аппроксимация квадратичной функцией", oneFunction, false);
        Checkbox thirdFunction = new Checkbox("Аппроксимация кубической функцией", oneFunction, false);
        Checkbox fourthFunction = new Checkbox("Аппроксимация экспоненциальной функцией", oneFunction, false);
        Checkbox fifthFunction = new Checkbox("Аппроксимация логарифмической функцией", oneFunction, false);
        Checkbox sixthFunction = new Checkbox("Аппроксимация степенной функцией", oneFunction, false);
        Checkbox function = new Checkbox("Аппроксимация удачной функцией", oneFunction, false);

        /*panel.add(firstFunction);
        panel.add(secondFunction);
        panel.add(thirdFunction);
        panel.add(fourthFunction);
        panel.add(fifthFunction);
        panel.add(sixthFunction);
        panel.add(function);*/
        input.addPoint(new BigDecimal(-3), new BigDecimal(-2.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(-2), new BigDecimal(-1.6).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(-1), new BigDecimal(-0.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(0), new BigDecimal(1));
        input.addPoint(new BigDecimal(1), new BigDecimal(1.9).setScale(1, RoundingMode.HALF_EVEN));
        input.addPoint(new BigDecimal(10), new BigDecimal(10));
        JTable binarySearchInput = new JTable(input);
        String[] temp = new String[]{"x", "y"};
        JButton binarySearchButton = new JButton("Аппроксимировать данную фукнцию, заданную с помощью таблицы!");
        Label binarySearchLabel = new Label();
        JButton add = new JButton("Добавить точку для функции, заданной таблично!");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.addPoint(new BigDecimal(0), new BigDecimal(0));
            }
        });

        /*JTextField secantsInput = new JTextField();
        JButton secantsButton = new JButton("Посчитать методом секущих!");
        Label secantsLabel = new Label();
        JTextField iterationInput = new JTextField();
        JButton iterationButton = new JButton("Посчитать методом простой итерации!");
        Label iterationLabel = new Label();*/
        panel.add(binarySearchInput);
        //panel.add(binarySearchLabel);
        panel.add(binarySearchButton);
        panel.add(add);
        /*panel.add(secantsInput);
        panel.add(secantsButton);
        panel.add(secantsLabel);
        panel.add(iterationInput);
        panel.add(iterationButton);
        panel.add(iterationLabel);*/

        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (oneFunction.getSelectedCheckbox() == null){
                    binarySearchLabel.setText("Выберите, пожалуйста, одну функцию из списка");
                }*/
                //if (validator.validateBorders(binarySearchInput.getText())){
                if (1 == 1){
                    binarySearchLabel.setText("");
                    BigDecimal first, second, accuracy;
                    ArrayList<BigDecimal> data = new ArrayList<BigDecimal>();
                    for (int i = 0; i < input.getRowCount(); i++){
                        data.add((BigDecimal) input.getValueAt(i, 0));
                        data.add((BigDecimal) input.getValueAt(i, 1));
                    }
                    String function = oneFunction.getSelectedCheckbox().getLabel();
                    int functionNumber, methodNumber;
                    functionNumber = -1;
                    methodNumber = -1;
                    switch (function){
                        case "Аппроксимация линейной функцией":
                            functionNumber = 1;
                            break;
                        case "Аппроксимация квадратичной функцией":
                            functionNumber = 2;
                            break;
                        case "Аппроксимация кубической функцией":
                            functionNumber = 3;
                            break;
                        case "Аппроксимация экспоненциальной функцией":
                            functionNumber = 4;
                            break;
                        case "Аппроксимация логарифмической функцией":
                            functionNumber = 5;
                            break;
                        case "Аппроксимация степенной функцией":
                            functionNumber = 6;
                            break;
                        case "Аппроксимация удачной функцией":
                            functionNumber = 7;
                            break;
                    }



                    ArrayList<BigDecimal> answer = solver.solve(functionNumber, data);
                    //DefaultTableModel model = new DefaultTableModel();
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    table = new JTable(model);
                    /*for (int i = 0; i < answer.get(0).size(); i++){
                        Console.print(answer.get(0).get(i).toString(), "г");
                    }*/
                    /*for (int i = 0; i < answer.get(2).size(); i++){
                        Console.print(answer.get(2).get(i).toString(), "г");
                    }*/
                    BigDecimal[] temp1 = new BigDecimal[4];
                    String[] temp2;
                    switch (functionNumber){
                        case 1:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        case 2:
                            temp2 = new String[]{"a", "b", "c", "e"};
                            break;
                        case 3:
                            temp2 = new String[]{"a", "b", "c", "d", "e"};
                            break;
                        case 4:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        case 5:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        case 6:
                            temp2 = new String[]{"a", "b", "e"};
                            break;
                        default:
                            if (answer.size() == 4){
                                temp2 = new String[]{"a", "b", "e"};
                            }
                            else if (answer.size() == 5){
                                temp2 = new String[]{"a", "b", "c", "e"};
                            }
                            else {
                                temp2 = new String[]{"a", "b", "c", "d", "e"};
                            }
                        }
                    //String[] temp2 = new String[]{"Индекс", "x", "y", "Добавлено в значение интеграла"};
                    for (int i = 0; i < temp2.length; i++){
                        model.addColumn(i);
                    }
                    model.addRow(temp2);
                    model.addRow(answer.toArray());
                    /*for (int i = 0; i < answer.get(0).size(); i++){
                        if (i < (answer.get(0).size() - 1)) {
                            temp1[0] = new BigDecimal(i);
                        }
                        else{
                            temp1[0] = new BigDecimal(0);
                        }
                        temp1[1] = answer.get(0).get(i);
                        temp1[2] = answer.get(1).get(i);
                        temp1[3] = answer.get(2).get(i);
                        model.addRow(temp1);
                    }*/
                    /*JFrame tableWindow = getWindow();
                    JPanel tablePanel = new JPanel();
                    tablePanel.add(table);
                    tableWindow.add(tablePanel);
                    tableWindow.setVisible(true);*/
                    JFreeChart chart;
                    if (functionNumber != 7) {
                        chart = g.generateApproximation(data, answer, functionNumber);
                        switch (functionNumber){
                            case 1:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 2:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 3:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^3 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(3).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 4:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "e^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x)"};
                                break;
                            case 5:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "ln(x) + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 6:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + ")"};
                                break;
                            default:
                                temp2 = new String[]{};
                        }
                        model.addRow(temp2);
                    }
                    else{
                        functionNumber = answer.get(answer.size() - 1).intValue();
                        switch (functionNumber){
                            case 0:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 1:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 2:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^3 + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^2 + ", answer.get(2).setScale(3, RoundingMode.HALF_EVEN).toString() + "x + ", answer.get(3).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 3:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "e^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + "x)"};
                                break;
                            case 4:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "ln(x) + ", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString()};
                                break;
                            case 5:
                                temp2 = new String[]{"f(x) = ", answer.get(0).setScale(3, RoundingMode.HALF_EVEN).toString() + "x^(", answer.get(1).setScale(3, RoundingMode.HALF_EVEN).toString() + ")"};
                                break;
                            default:
                                temp2 = new String[]{};
                        }
                        model.addRow(temp2);
                        System.out.println(functionNumber);
                        answer.remove(answer.size() - 1);
                        for (int i = 0; i < answer.size(); i++){
                            System.out.println(answer.get(i));
                        }
                        chart = g.generateApproximation(data, answer, functionNumber + 1);
                    }
                    p.setChart(chart);
                }
                else{
                    //solver.solve(1, oneFunction.getSelectedCheckbox());
                    binarySearchLabel.setText("Число пробел число пробел число(0.000001 <= точность <= 1.0)");
                }
            }
        });
        //panel.add(label);
        window.add(panel);
        window.setLayout(dataAndGraphics);
        JPanel functions = new JPanel();
        GridLayout grid2 = new GridLayout(7, 1);
        functions.setLayout(grid2);
        functions.add(firstFunction);
        functions.add(secondFunction);
        functions.add(thirdFunction);
        functions.add(fourthFunction);
        functions.add(fifthFunction);
        functions.add(sixthFunction);
        functions.add(function);
        window.add(functions);
        JPanel newPanel = new JPanel();
        JFreeChart chart = g.generateChart(new BigDecimal(-3), new BigDecimal(3), 3);
        p = new ChartPanel(chart);
        p.setPreferredSize(new Dimension(400, 700));
        p.setMouseZoomable(true);
        p.setMouseWheelEnabled(true);
        newPanel.add(p);
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        window.add(newPanel);
        window.add(tablePanel);
        window.getLayout().removeLayoutComponent(newPanel);
        window.setVisible(true);
        /*JTable table = new JTable(100, 10);
        JFrame tableWindow = getWindow();
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        tableWindow.add(tablePanel);
        tableWindow.setVisible(true);*/

        /*secantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });

        iterationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });*/


    }

}
package bsu.rfe.java.group6.lab2.Zhibul.vxarB7;
// Импортируются классы, используемые в приложении

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 800;
    private static final int HEIGHT = 640;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private final JTextField textFieldX;
    private final JTextField textFieldY;
    private final JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private final JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private final ButtonGroup radioButtons = new ButtonGroup();
    private final ButtonGroup memoryButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private final Box hboxFormulaType = Box.createHorizontalBox();
    private final Box hboxMemoryType = Box.createHorizontalBox();
    private int formulaId = 1;
    private int memId;
    private double[] mem = new double[3];
    Double result;

    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.exp(y)) + Math.exp(Math.pow(y, 2)) + Math.sqrt(1 / x), 1. / 4)
                / Math.pow(Math.cos(Math.PI * Math.pow(z, 3)) + Math.pow(Math.log(1 + z), 2), Math.sin(y));
    }

    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return Math.pow(1 + Math.pow(x, 2), 1 / y) / Math.exp(Math.sin(z) + x);
    }

    // Вспомогательный метод для добавления кнопок на панель

    private JRadioButton addRadioButton(ButtonGroup group, Box box, String buttonName, ActionListener listener) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(listener);
        group.add(button);
        box.add(button);
        return button;
    }

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton(radioButtons, hboxFormulaType, "Формула 1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = 1;
            }
        });
        addRadioButton(radioButtons, hboxFormulaType, "Формула 2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = 2;
            }
        });
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y и Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createHorizontalStrut(200));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createHorizontalStrut(200));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        /////////////////////////

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        textFieldResult.setEditable(false);
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    result = 0.;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));
        // box for memory
        addRadioButton(memoryButtons, hboxMemoryType, "M1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memId = 0;
                textFieldResult.setText(Double.toString(mem[memId]));
            }
        });
        addRadioButton(memoryButtons, hboxMemoryType, "M2", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                memId = 1;
                textFieldResult.setText(Double.toString(mem[memId]));
            }
        });
        addRadioButton(memoryButtons, hboxMemoryType, "M3", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memId = 2;
                textFieldResult.setText(Double.toString(mem[memId]));
            }
        });
        memoryButtons.setSelected(memoryButtons.getElements().nextElement().getModel(), true);
        Box hboxMemButtons = Box.createHorizontalBox();
        hboxMemButtons.add(Box.createHorizontalGlue());
        JButton buttonMP = new JButton("M+");
        buttonMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mem[memId] += result;
                textFieldResult.setText(Double.toString(mem[memId]));
            }
        });
        hboxMemButtons.add(buttonMP);
        hboxMemButtons.add(Box.createHorizontalStrut(30));
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mem[memId] = 0;
                textFieldResult.setText("0");
            }
        });
        ;
        hboxMemButtons.add(buttonMC);
        hboxMemButtons.add(Box.createHorizontalGlue());
        hboxMemButtons.setBorder(BorderFactory.createLineBorder(Color.magenta));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMemoryType);
        contentBox.add(hboxMemButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}

package GUI;

import Solution.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;


/**
 * admin on 03.02.2016.
 */

public class MainForm extends JFrame{

    String base = "1999-10";
    String calc = "2016-01";        // значения по-умолчанию


    public MainForm(final Calculator ic){

        super ("Расчет индексации с 2016 года");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 14);

        String[] listBasePer = ic.getEnumBasePer();
        String[] listCalcPer =ic.getEnumCalcPer();

        Container content = getContentPane();

        content.setLayout(new GridLayout(5,2));


        //Lable
        JLabel baseLable = new JLabel("Базовый период");
        baseLable.setAlignmentX(LEFT_ALIGNMENT);
        baseLable.setFont(font);

        JLabel comentsLable = new JLabel("              =) ");
        comentsLable.setAlignmentX(LEFT_ALIGNMENT);
        comentsLable.setFont(new Font("ComicSansMC", Font.BOLD, 10));


        JLabel calcLable = new JLabel("Период расчета");
        calcLable.setAlignmentX(LEFT_ALIGNMENT);
        calcLable.setFont(font);

        JLabel coefLabel = new JLabel("Коэффициент");
        coefLabel.setAlignmentX(LEFT_ALIGNMENT);
        coefLabel.setFont(font);

        final JLabel coefOut = new JLabel("x.xxx");
        coefOut.setAlignmentX(LEFT_ALIGNMENT);
        coefOut.setFont(new Font("Verdana", Font.BOLD, 17));


        JLabel keep = new JLabel("Сумма с мин. зп:");
        keep.setAlignmentX(LEFT_ALIGNMENT);
        keep.setFont(font);

        final JLabel sumindex = new JLabel("");
        sumindex.setAlignmentX(LEFT_ALIGNMENT);
        sumindex.setFont(font);


        JComboBox baseBox = new JComboBox(listBasePer);
        baseBox.setFont(font);
        baseBox.setAlignmentX(LEFT_ALIGNMENT);
        baseBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                base = (String)box.getSelectedItem();
            }
        });

        JComboBox calcBox = new JComboBox(listCalcPer);
        calcBox.setEditable(true);
        calcBox.setAlignmentX(LEFT_ALIGNMENT);
        calcBox.setFont(font);
        calcBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                calc = (String)box.getSelectedItem();
            }
        });

        JButton solveButton = new JButton("Расчитать");
        solveButton.isDefaultButton();
        solveButton.setActionCommand("startSolve");
        solveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                BigDecimal answer = ic.solve(base,calc);
                coefOut.setText(String.valueOf(answer));
                sumindex.setText(String.valueOf(answer.multiply(ic.getMinzp())));
            }
        });

        content.add(baseLable);
        content.add(baseBox);
        content.add(calcLable);
        content.add(calcBox);
        content.add(solveButton);
        content.add(comentsLable);
        content.add(coefLabel);
        content.add(coefOut);
        content.add(keep);
        content.add(sumindex);



        setPreferredSize(new Dimension(360, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}

package com.lemon;

import com.lemon.GUI.MainForm;
import com.lemon.Solution.IndexCalculator;
import com.lemon.Solution.IndexCalculatorImpl;

import javax.swing.*;


public class Start {


    public static void main(String[] args) {
        String file;
        if(args.length != 0){
            file = args[0];
        }
        else{
            file = "resources/index.txt";

        }
        final IndexCalculator ic = new IndexCalculatorImpl(file);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                                   public void run() {
                                                       JFrame.setDefaultLookAndFeelDecorated(true);
                                                       new MainForm(ic);
                                                   }
                                               }
        );

    }
}

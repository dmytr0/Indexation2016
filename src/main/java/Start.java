import GUI.MainForm;
import Solution.IndexCalculator;
import Solution.IndexCalculatorImpl;

import javax.swing.*;

/**
 * Created by admin on 03.02.2016.
 */
public class Start {


    public static void main(String[] args) {
        String file;
        if(args.length != 0){
            file = args[0];
        }
        else{
            file = "src//main//resources//index.txt";
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

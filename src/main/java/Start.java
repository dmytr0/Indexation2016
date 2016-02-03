import GUI.MainForm;
import Solution.IndexCalculator;

import javax.swing.*;

/**
 * Created by admin on 03.02.2016.
 */
public class Start {


    public static void main(String[] args) {
        String file;

        file = "src//main//resources//index.txt";
        final IndexCalculator ic = new IndexCalculator(file);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                                                   public void run() {
                                                       JFrame.setDefaultLookAndFeelDecorated(true);
                                                       new MainForm(ic);
                                                   }
                                               }
        );

    }
}

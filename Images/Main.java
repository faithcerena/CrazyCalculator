import javax.swing.JFrame;
import java.awt.Color;
public class Main{
  public static void main(String[] args){
    CalculatorFrame calculatorFrame = new CalculatorFrame();
    calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // calculatorFrame.setSize(356, 684);
    calculatorFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    calculatorFrame.setUndecorated(true);
    calculatorFrame.setUndecorated(true);
    calculatorFrame.setVisible(true);
    calculatorFrame.setLocationRelativeTo(null);

  }
}

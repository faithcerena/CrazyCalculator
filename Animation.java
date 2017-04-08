import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Animation extends Thread {
  private Thread t;
  private JLabel label;
  private String[] s;
  private int length = 0;
  volatile boolean bool = false;
  Stack stack;
  volatile double ans = 0;
  volatile double result = 0;
  public Animation(String[] s, Stack stack, int length, double result){
    this.s = s;
    this.stack = stack;
    this.length = length;
    stack = new Stack(length);
  }
  public void run(){
      try{
        for(int i = 0; i < s.length; i++){
          CalculatorFrame.answerLabel.setFont(new Font("Open Sans", Font.PLAIN, 60));

          if(CalculatorFrame.flag2){
            break;
          }

          if(!CalculatorFrame.flag2){
            if(CalculatorFrame.flag2){
              break;
            }
            Thread.sleep(500);
            CalculatorFrame.evaluate_read.setText(s[i]);
            CalculatorFrame.evaluate_read.setFont(new Font("Open Sans", Font.BOLD, 25));
            CalculatorFrame.evaluate_read.setForeground(new Color(10,10,10));
            CalculatorFrame.evaluate_read.setBounds(980, 424, 192, 31);
            // TimeUnit.SECONDS.sleep(1);
            Thread.sleep(500);

            if(CalculatorFrame.flag2){
              break;
            }


            if(CalculatorFrame.isNum(s[i])){
              if(CalculatorFrame.flag2){
                break;
              }

              stack.push(s[i]);
              CalculatorFrame.evaluate_stack.setText(s[i]);
              CalculatorFrame.evaluate_stack.setFont(new Font("Open Sans", Font.BOLD, 25));
              CalculatorFrame.evaluate_stack.setForeground(new Color(10,10,10));
              CalculatorFrame.evaluate_stack.setBounds(980, 460, 192, 31);
              // TimeUnit.SECONDS.sleep(1);
              Thread.sleep(500);

            }else if(s[i].equals("*") || s[i].equals("/") || s[i].equals("+") || s[i].equals("-") ){
              double digit1 = Double.parseDouble(stack.pop());
              double digit2 = Double.parseDouble(stack.pop());
              CalculatorFrame.evaluate_evaluate.setText("" + digit2 + "  " + s[i] + "  " + digit1);
              CalculatorFrame.evaluate_evaluate.setFont(new Font("Open Sans", Font.BOLD, 25));
              CalculatorFrame.evaluate_evaluate.setForeground(new Color(10,10,10));
              CalculatorFrame.evaluate_evaluate.setBounds(980, 496, 192, 31);
              result = result(s[i], digit2, digit1);
              // TimeUnit.SECONDS.sleep(1);
              Thread.sleep(500);

              if(CalculatorFrame.flag2){
                break;
              }


              stack.push(String.valueOf(result));
              CalculatorFrame.evaluate_stack.setText(String.valueOf(result));
              CalculatorFrame.evaluate_result.setText(String.valueOf(result));
              CalculatorFrame.evaluate_result.setFont(new Font("Open Sans", Font.BOLD, 25));
              CalculatorFrame.evaluate_result.setForeground(new Color(10,10,10));
              CalculatorFrame.evaluate_result.setBounds(980, 530, 192, 31);
              // TimeUnit.SECONDS.sleep(1);
              Thread.sleep(500);

              // System.out.println(result);
            }
          }
          if(CalculatorFrame.flag2){
            break;
          }
        }
      }catch(Exception e){
      }

      if(!CalculatorFrame.flag2){
        CalculatorFrame.evaluate_read.setText("END");
        CalculatorFrame.evaluate_stack.setText("EMPTY");

      }




  }
  // public void stop(){
  //   bool = true;
  // }

  public double result(String s, double digit1, double digit2){
    if(s.equals("*"))
      ans = digit1 * digit2;
    if(s.equals("/")){

      ans = digit1 / digit2;
    }
    if(s.equals("+"))
      ans = digit1 + digit2;
    if(s.equals("-"))
      ans = digit1 - digit2;
    return ans;
  }




  public void start(){
    if(t == null){
      t = new Thread(this);
      t.start();
    }
  }
}

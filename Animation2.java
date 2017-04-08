import javax.swing.*;
import java.awt.*;

public class Animation2 implements Runnable {
  private Thread t;
  private JLabel label;
  private String[] s;
  private int bool;

  public Animation2(String[] s, int bool){
    this.s = s;
    this.bool = bool;
  }

  public void run(){
    try{

      if(bool == 0){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            if(s[i].equals("END")){
              CalculatorFrame.readLabel.setText("END");
              break;
            }
            CalculatorFrame.readLabel.setText(s[i]);
            if(!s[i].equals(" "))
              CalculatorFrame.parsedLabel.setText(CalculatorFrame.parsedLabel.getText() + s[i]);
            Thread.sleep(1000);
          }
        }
      }

      if(bool == 1){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            if(s[i] != null){
              CalculatorFrame.writtenLabel.setText(CalculatorFrame.writtenLabel.getText()+s[i]);
            }
            Thread.sleep(1000);
          }
        }
      }
      if(bool == 2){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            if(s[i] != null){
              CalculatorFrame.statusLabel.setText(s[i]);
            }else if(s[i].equals("END")){
              CalculatorFrame.statusLabel.setText("END");
              break;
            }
            else{
              CalculatorFrame.statusLabel.setText(" ");
            }
            Thread.sleep(1000);
          }
        }
      }
      if(bool == 3){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            if(s[i] != null){
              CalculatorFrame.stackLabel.setText(s[i]);
            }
            Thread.sleep(1000);
          }
        }
      }
      if(bool == 4){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            Thread.sleep(300);
            if(s[i] != null){
              CalculatorFrame.queueLabel.setText(s[i]);
            }
            Thread.sleep(700);
          }
        }
      }
      if(bool == 5){
        for(int i = 0; i < s.length; i++){
          if(CalculatorFrame.flag5){
            Thread.sleep(500);
            if(s[i] != null){
              CalculatorFrame.arrayLabel.setText(s[i]);
            }
            Thread.sleep(500);
          }
        }
      }
    }catch(Exception e){}
  }

  public void start(){
    if(t == null){
      t = new Thread(this);
      t.start();
    }
  }
}

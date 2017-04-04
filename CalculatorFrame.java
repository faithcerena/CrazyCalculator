import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalculatorFrame extends JFrame {

  private int yLabel = 0;
  private int index = 0;
  private int ctr4 = 0;
  private int count = 0;
  private int left = 0;
  private int right = 0;

  private double ans = 0;
  private double result = 0;

  private boolean flag = true;
  private boolean flag2 = true;
  private boolean flag3 = false;
  private boolean flag4 = false;

  private boolean isError = false;

  private String temp = "";
  private String[] tempo;
  private String string = "";
  private String spaceString = "";

  private String[] arrayString = new String[20];
  private String[] parsedString = new String[20];
  private String[] stackString = new String[20];
  public static String stacker = "";

  private String[] postfix;

  private String buttonName[] = {"AC", "DEL", "EXT", "DIVIDE", "7", "8", "9", "MULTIPLY", "4", "5", "6", "MINUS", "1", "2", "3", "PLUS", "0", "LEFT", "RIGHT", "EQUAL"};

  private JPanel mainPanel;

  private JLabel inputLabel = new JLabel("0" + string, SwingConstants.RIGHT);
  private JLabel postFixLabel = new JLabel("", SwingConstants.RIGHT);
  private JLabel answerLabel = new JLabel("", SwingConstants.RIGHT);
  private JLabel barLabel = new JLabel(new ImageIcon("Images/bar.png"));
  private JLabel closeBarLabel = new JLabel(new ImageIcon("Images/closeBar.png"));
  private JLabel barLabel2 = new JLabel(new ImageIcon("Images/bar2.png"));
  private JLabel closeBarLabel2 = new JLabel(new ImageIcon("Images/closeBar2.png"));

  private JLabel[] buttons = new JLabel[20];
  private JLabel[] read = new JLabel[20];
  private JLabel[] parsed = new JLabel[20];
  private JLabel[] written = new JLabel[20];
  private JLabel[] stacks = new JLabel[20];

  private Stack stack;

  private LabelHandler handler = new LabelHandler();

  public CalculatorFrame(){

    mainPanel = new BackgroundPanel();
    mainPanel.setLayout(null);
    add(mainPanel);

    for(int i = 0, x = 0, y = 300; i < 20; i++, x+=77){
      buttons[i] = new JLabel(new ImageIcon("Images/" + buttonName[i] + ".png"));
      buttons[i].setBounds(524+x, y, 75, 57);
      buttons[i].addMouseListener(handler);
      mainPanel.add(buttons[i]);
      if(x == 231){
        y += 59;
        x = -77;
      }
    }

    postFixLabel.setBounds(519, 178, 300, 80);
    postFixLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
    postFixLabel.setForeground(new Color(180,180,180));
    mainPanel.add(postFixLabel);

    answerLabel.setBounds(519, 223, 300, 80);
    answerLabel.setFont(new Font("Open Sans", Font.PLAIN, 60));
    answerLabel.setForeground(new Color(80,80,80));
    mainPanel.add(answerLabel);

    inputLabel.setBounds(519, 138, 300, 80);
    inputLabel.setFont(new Font("Open Sans", Font.PLAIN, 30));
    inputLabel.setForeground(Color.GRAY);
    mainPanel.add(inputLabel);

    barLabel.setBounds(499, 158, 23, 407);
    barLabel.addMouseListener(handler);
    mainPanel.add(barLabel);

    closeBarLabel.setBounds(171, 157, 23, 407);
    closeBarLabel.addMouseListener(handler);
    closeBarLabel.setVisible(false);
    mainPanel.add(closeBarLabel);

    barLabel2.setBounds(832, 158, 23, 407);
    barLabel2.addMouseListener(handler);
    mainPanel.add(barLabel2);

    closeBarLabel2.setBounds(1160, 157, 23, 407);
    closeBarLabel2.addMouseListener(handler);
    closeBarLabel2.setVisible(false);
    mainPanel.add(closeBarLabel2);
  }

  public void evaluate(String[] s){
    Stack stack = new Stack(index);

    for(int i = 0; i < s.length; i++){
      if(isNum(s[i])){
        stack.push(s[i]);
      }else if(s[i].equals("*") || s[i].equals("/") || s[i].equals("+") || s[i].equals("-") ){
        double digit1 = Double.parseDouble(stack.pop());
        double digit2 = Double.parseDouble(stack.pop());
        result = result(s[i], digit2, digit1);
        stack.push(String.valueOf(result));
      }
    }

    String stringFinal = "";
    for(int i = s.length-1, n = 0; i >= 0; i--, n+=15){
      stringFinal = s[i] + " " + stringFinal;
    }

    String ans = String.valueOf(result);
    if(ans.charAt(ans.length()-1) == '0' && ans.charAt(ans.length()-2) == '.'){
      ans = ans.substring(0, ans.length() - 2);
      answerLabel.setText(ans);
    }else{
      int val = 0;
      for(int i = 0; i < ans.length(); i++){
        if(ans.charAt(i) == '.'){
          val = i;
        }
      }
      try{
        ans = ans.substring(0, val) + ans.substring(val, 7);
      }catch(StringIndexOutOfBoundsException e){}

      answerLabel.setText(ans);
    }

    postFixLabel.setText(stringFinal);

    if(isError){
      answerLabel.setFont(new Font("Open Sans", Font.PLAIN, 30));
      answerLabel.setText("SYNTAX ERROR");
      postFixLabel.setText("");

      for(int i =0; i < 20; i++){
        arrayString[i] = "";
        parsedString[i] = "";
        parsed[i].setText(parsedString[i]);
        read[i].setText(arrayString[i]);
        written[i].setText("");
        stacks[i].setText("");
      }
    }
  }

  public double result(String s, double digit1, double digit2){
    if(s.equals("*"))
      ans = digit1 * digit2;
    if(s.equals("/")){
      if(digit2 == 0)
        isError = true;

      ans = digit1 / digit2;
    }
    if(s.equals("+"))
      ans = digit1 + digit2;
    if(s.equals("-"))
      ans = digit1 - digit2;
    return ans;
  }

  public static boolean isNum(String strNum) {
    boolean ret = true;
    try {
      Double.parseDouble(strNum);
    }catch (NumberFormatException e) {
      ret = false;
    }
    return ret;
  }

  private class BackgroundPanel extends JPanel{
    public void paintComponent(Graphics page){
      super.paintComponent(page);
      page.drawImage((new ImageIcon("Images/BACKGROUND3.PNG")).getImage(), 0, 0, null);
    }
  }

  private class LabelHandler extends MouseAdapter {
    public void mouseEntered(MouseEvent event){
      for(int i = 0; i < 20; i++){
        if(event.getSource() == buttons[i]){
          buttons[i].setIcon(new ImageIcon("Images/" + buttonName[i] + "inv.png"));
        }
      }
    }
    public void mouseExited(MouseEvent event){
      for(int i = 0; i < 20; i++){
        if(event.getSource() == buttons[i]){
          buttons[i].setIcon(new ImageIcon("Images/" + buttonName[i] + ".png"));
        }
      }
    }

    public void mousePressed(MouseEvent event){

      if(event.getSource() == buttons[2]){
        System.exit(0);
      }

      if(event.getSource() == barLabel){
        barLabel.setIcon(new ImageIcon("Images/window3.png"));
        barLabel.setBounds(194, 158, 330, 404);
        closeBarLabel.setVisible(true);
        try{
          for(int i =0; i < 20; i++){
            read[i].setVisible(true);
            parsed[i].setVisible(true);
            written[i].setVisible(true);
            stacks[i].setVisible(true);
            repaint();
            revalidate();
          }
        }catch(Exception e){}
        flag3 = true;
      }

      if(event.getSource() == barLabel2){
        barLabel2.setIcon(new ImageIcon("Images/window2.png"));
        barLabel2.setBounds(830, 158, 330, 404);
        closeBarLabel2.setVisible(true);
      }

      if(event.getSource() == closeBarLabel){
        barLabel.setIcon(new ImageIcon("Images/bar.png"));
        barLabel.setBounds(499, 158, 23, 407);
        closeBarLabel.setVisible(false);

        for(int i =0; i < 20; i++){
          try{
            read[i].setVisible(false);
            parsed[i].setVisible(false);
            written[i].setVisible(false);
            stacks[i].setVisible(false);

            repaint();
            revalidate();
          }catch(Exception e){}
        }
        flag3 = false;
      }

      if(event.getSource() == buttons[0]){
        flag2 = true;
        inputLabel.setText("0");
        postFixLabel.setText("");
        answerLabel.setText("");

        for(int i =0; i < 20; i++){
          try{
            LinkedList.finalString[i] = "";
            arrayString[i] = "";
            parsedString[i] = "";
            parsed[i].setText(parsedString[i]);
            read[i].setText(arrayString[i]);
            written[i].setText("");
            stacks[i].setText("");
          }catch(Exception e){}
        }

        inputLabel.setBounds(519, 138, 300, 80);
        string = "";
        spaceString = "";
        index = 0;
        ctr4 = 0;
        postfix = null;
        stack = null;
        isError = false;
        left = 0;
        right = 0;
        answerLabel.setFont(new Font("Open Sans", Font.PLAIN, 60));
      }

      if(flag2){
        if(inputLabel.getText().length() != 16){
          if(event.getSource() == buttons[16]){
            //restricted #1
            if(inputLabel.getText().equals("0")){
              inputLabel.setText("0");
            }else{
              string = string + "0";
              spaceString = spaceString + "0";
              flag = true;

            }
          }
          if(event.getSource() == buttons[12]){
            string = string + "1";
            spaceString = spaceString + "1";
            flag = true;
          }
          if(event.getSource() == buttons[13]){
            string = string + "2";
            spaceString = spaceString + "2";
            flag = true;
          }
          if(event.getSource() == buttons[14]){
            string = string + "3";
            spaceString = spaceString + "3";
            flag = true;
          }
          if(event.getSource() == buttons[8]){
            string = string + "4";
            spaceString = spaceString + "4";
            flag = true;
          }
          if(event.getSource() == buttons[9]){
            string = string + "5";
            spaceString = spaceString + "5";
            flag = true;
          }
          if(event.getSource() == buttons[10]){
            string = string + "6";
            spaceString = spaceString + "6";
            flag = true;
          }
          if(event.getSource() == buttons[4]){
            string = string + "7";
            spaceString = spaceString + "7";
            flag = true;
          }
          if(event.getSource() == buttons[5]){
            string = string + "8";
            spaceString = spaceString + "8";
            flag = true;
          }
          if(event.getSource() == buttons[6]){
            string = string + "9";
            spaceString = spaceString + "9";
            flag = true;
          }

          if((event.getSource() == buttons[17] && flag == false )||(event.getSource() == buttons[17] && inputLabel.getText().equals("0") ) ){
            string = string + "(";
            spaceString = spaceString + "( ";
            flag = false;
            flag4 = true;
            left++;
          }
          if((event.getSource() == buttons[18])){
            string = string + ")";
            spaceString = spaceString + " )";
            flag = true;
            flag4 = false;
            right++;
          }
          if(!inputLabel.getText().equals("0") && flag){
            if(event.getSource() == buttons[15]){
              string = string + "+";
              spaceString = spaceString + " + ";
              flag = false;
            }
            if(event.getSource() == buttons[11]){
              string = string + "-";
              spaceString = spaceString + " - ";
              flag = false;
            }
            if(event.getSource() == buttons[7]){
              string = string + "*";
              spaceString = spaceString + " * ";
              flag = false;
            }
            if(event.getSource() == buttons[3]){
              string = string + "/";
              spaceString = spaceString + " / ";
              flag = false;
            }

            parsedString[count++] = string;
          }

          if(event.getSource() == buttons[1]){
            try{
              int zxc = ctr4;
              ctr4 = 0;
              postfix = null;
              stack = null;
              string = string.substring(0, string.length() - 1);
              spaceString = spaceString.substring(0, spaceString.length() - 1);
              postFixLabel.setText("");
              answerLabel.setText("");
              inputLabel.setText(string);
              inputLabel.setBounds(519, 138, 300, 80);
            }catch(StringIndexOutOfBoundsException e){}
          }

          if(event.getSource() != buttons[19] && event.getSource() != buttons[1]){
            if(flag == false && (event.getSource() == buttons[3] || event.getSource() == buttons[7] || event.getSource() == buttons[11] || event.getSource() == buttons[15])){
              inputLabel.setBounds(519, 138, 300, 80);
              inputLabel.setText(string);
            }else{
              inputLabel.setText(string);
              inputLabel.setBounds(519, 138, 300, 80);
            }
          }
        }else{
          if(event.getSource() == buttons[1]){
            ctr4 = 0;
            postfix = null;
            stack = null;
            string = string.substring(0, string.length() - 1);
            spaceString = spaceString.substring(0, spaceString.length() - 1);
            postFixLabel.setText("");
            answerLabel.setText("");
            inputLabel.setText(string);
            inputLabel.setBounds(519, 138, 300, 80);
          }
        }

        if(event.getSource() == buttons[19]){

          buttons[19].setIcon(new ImageIcon("Images/" + buttonName[19] + ".png"));
          flag2 = false;
          spaceString = spaceString + " ";
          for(int i = 0; i < spaceString.length(); i++){
            if(spaceString.charAt(i) != ' '){
              temp += spaceString.charAt(i);
            }else{
              arrayString[index] = temp;
              index++;
              temp = "";
            }
          }
          arrayString[index] = "END";

          for(int i = 0, y = 0; i < arrayString.length; i++, y+=20){
            written[i] = new JLabel("", SwingConstants.CENTER);
            stacks[i] = new JLabel("", SwingConstants.CENTER);

            read[i] = new JLabel("", SwingConstants.CENTER);
            read[i].setText(arrayString[i]);
            read[i].setFont(new Font("Open Sans", Font.PLAIN, 11));
            read[i].setForeground(new Color(10,10,10));
            read[i].setBounds(184, 160+y, 50, 80);
            mainPanel.add(read[i]);
            read[i].setVisible(false);
            repaint();
            revalidate();
          }

          stack = new Stack(index);
          postfix = new String[index];

          String previousChar = "", previousOperation = "", previousOperation2 = "";

          if(left >= right){
            for(int i = 0; i < index; i++){
              String current = arrayString[i];
              if(current.equals("+")||current.equals("-")||current.equals("*")||current.equals("/")){
                if(stack.isEmpty()){
                  stack.push(current);
                }else{
                  if(current.equals("-") || current.equals("+")){
                        if(previousOperation.equals("*") || previousOperation.equals("/")){
                          postfix[ctr4++] = stack.pop();
                          if(previousOperation2.equals("+") || previousOperation2.equals("-"))
                            postfix[ctr4++] = stack.pop();
                        }
                        else if(previousOperation.equals("+") || previousOperation.equals("-")){
                          postfix[ctr4++] = stack.pop();
                        }
                      //}
                    stack.push(current);
                  }if(current.equals("*") || current.equals("/")){
                    if(previousOperation.equals("*") || previousOperation.equals("/")){
                      postfix[ctr4++] = stack.pop();
                    }
                    stack.push(current);
                  }
                }
                previousOperation2 = previousOperation;
                previousOperation = current;
              }
              else if(current.equals("(")){
                left++;
                if(i == index - 1){
                  isError = true;
                  break;
                }
                stack.push(current);
                previousOperation = "";
              }else if(current.equals(")")){
                right++;
                if(i == 0){
                  isError = true;
                  break;
                }
                else{
                  while(true){
                    String temp = stack.pop();

                    if(!temp.equals("("))
                      postfix[ctr4++] = temp;

                    else break;
                  }
                }
              }else{
                if(current.equals("0") && previousChar.equals("/")){
                  isError = true;
                  break;
                }
                postfix[ctr4++] = current;
              }

              previousChar = current;

              if(i == index - 1){
                while(!stack.isEmpty()){
                  String lol = stack.pop();
                  postfix[ctr4++] = lol;
                }
              }

              String st = "";
              for(int j = 0; j < ctr4; j++){
                if(!postfix[j].equals(")") && !postfix[j].equals("(")){
                  st += postfix[j] + " ";
                }
              }
              stack.print();
              written[i].setText(st);
              written[i].setFont(new Font("Open Sans", Font.PLAIN, 11));
              written[i].setForeground(new Color(10,10,10));
              written[i].setBounds(317, 160+yLabel, 200, 80);
              written[i].setVisible(false);
              mainPanel.add(written[i]);

              for(int j = LinkedList.storeIn-1; j >= 0; j--){
                stacks[i].setText(stacker);
                stacks[i].setFont(new Font("Open Sans", Font.PLAIN, 11));
                stacks[i].setForeground(new Color(10,10,10));
                stacks[i].setBounds(407, 160+yLabel, 200, 80);
                mainPanel.add(stacks[i]);
                stacks[i].setVisible(false);
                repaint();
                revalidate();
              }

              repaint();
              revalidate();
              yLabel += 20;
              tempo = st.split(" ");

            }
          }
          if(left != right){
            isError = true;
          }
          for(int i = 0, yLabel = 0; i < 20; i++, yLabel+=20){
            if(parsedString[0].equals("") && i ==0){
              yLabel-=20;
            }
            parsed[i] = new JLabel("", SwingConstants.CENTER);
            parsed[i].setText(parsedString[i]);
            parsed[i].setFont(new Font("Open Sans", Font.PLAIN, 11));
            parsed[i].setForeground(new Color(10,10,10));
            parsed[i].setBounds(190, 160+yLabel, 200, 80);
            mainPanel.add(parsed[i]);
            parsed[i].setVisible(false);
            repaint();
            revalidate();
          }
          yLabel= 0;
          count = 0;


          try{
            evaluate(tempo);
          }catch(NullPointerException e){}
            if(isNum(inputLabel.getText()) && event.getSource() == buttons[19]){
              string = inputLabel.getText();
              answerLabel.setText(string);
            }

          if(event.getSource() == buttons[19] && flag3 == true){
            try{
              for(int i =0; i < 20; i++){
                read[i].setVisible(true);
                parsed[i].setVisible(true);
                written[i].setVisible(true);
                stacks[i].setVisible(true);
              }
            }catch(Exception e){}
          }
          index = 0;
          arrayString = new String[20];
          spaceString = spaceString.substring(0, spaceString.length() - 1);
          count = 0;
          result = 0;
          isError = false;
          left = 0;
          right = 0;
        }

        if(inputLabel.getText().equals("") || inputLabel.getText().equals("0")){
          inputLabel.setText("0");
          inputLabel.setBounds(519, 138, 300, 80);
        }
      }
    }
  }
}

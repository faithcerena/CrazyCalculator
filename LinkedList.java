public class LinkedList {
  Link first;
  Link last;
  static Link tempo;
  static int storeIn = 0;
  static String[] finalString = new String[20];
  static String str = "";
  String tempStr = "";

  public LinkedList(){
    first = null;
    last = null;
  }

  public boolean isEmpty(){
    return first == null;
  }

  public void add(String string){
    Link link = new Link(string);
    link.next = first;
    first = link;
  }

  public Link del(){
    Link temp = first;
    first = first.next;
    return temp;
  }

  public void print(){
    Link aa = first;
    int i = 0;
    while(aa != null){
      finalString[i++] = aa.toString();
      aa = aa.next;
      storeIn = i;
    }
    i = 0;
    for(int j = storeIn-1; j >= 0; j--){
      str += finalString[j];
      tempStr += finalString[j];

      str = "";
    }
    CalculatorFrame.stacker = tempStr;
    tempStr = "";
  }
}

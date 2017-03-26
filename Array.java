public class Array {
  LinkedList linkedList;
  String string;

  public Array(){
    linkedList = new LinkedList();
  }

  public void add(String string){
    linkedList.add(string);
  }

  public void print(){
    System.out.println("\nLINKED LIST");
    linkedList.print();
  }

  public String del(){
      return linkedList.del().string;
  }

}

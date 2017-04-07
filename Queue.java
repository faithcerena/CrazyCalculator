public class Queue extends Thread{
  Array array;
  String[] arrays;
  int ctr;

  public Queue(int size){
    array = new Array();
    arrays = new String[size];
    ctr = size - 1;
  }

  public void enqueue(String string){
    arrays[ctr--] = string;
    array.add(string);
  }

  public String dequeue(){
    ctr++;
    return array.del();
  }

  public void print(){
    array.print();
  }
}

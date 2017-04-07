public class Stack extends Thread{
  Queue queue;
  String array[];
  int ctr = 0;
  Thread t;
  public Stack(int size){
    queue = new Queue(size);
    array = new String[size];
  }

  public void push(String string){
    array[ctr++] = string;
    queue.enqueue(string);
  }

  public String pop(){
    --ctr;
    return queue.dequeue();
  }

  public boolean isEmpty(){
    return ctr == 0;
  }

  public void print(){
    queue.print();
  }
}

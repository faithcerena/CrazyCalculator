public class Stack {
  Queue queue;
  String array[];
  int ctr = 0;

  public Stack(int size){
    queue = new Queue(size);
    array = new String[size];
  }

  public void push(String string){
    array[ctr++] = string;
    //System.out.println(string);
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
//     for(int i = 0; i < array.length; i++){
//       if(array[i] != null)
//       System.out.println(array[i]);
//     }
//     System.out.println("\nQUEUE");
    queue.print();
  }
}

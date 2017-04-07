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
    // System.out.println(string);
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
    // System.out.print("STACK: ");
    // for(int i = 0; i < array.length; i++){
    //   if(array[i] != null){
    //     System.out.print(array[i]);
    //     try{
    //
    //       Thread.sleep(500);
    //     }catch(Exception e){
    //
    //     }      }
    // }
    // try{
    //   Thread.sleep(1000);
    // }catch(Exception e){}
    //
    // System.out.println();
    queue.print();
  }
}

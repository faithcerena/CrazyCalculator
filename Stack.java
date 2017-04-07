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
<<<<<<< HEAD
    // System.out.println(string);
=======
    //System.out.println(string);
>>>>>>> a2d25e275ddd9e1838397125f432ae5bb4eb0da3
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
<<<<<<< HEAD
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
=======
//     for(int i = 0; i < array.length; i++){
//       if(array[i] != null)
//       System.out.println(array[i]);
//     }
//     System.out.println("\nQUEUE");
>>>>>>> a2d25e275ddd9e1838397125f432ae5bb4eb0da3
    queue.print();
  }
}

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
    // System.out.print("QUEUE: ");
    // for(int i = 0; i < arrays.length; i++){
    //   if(arrays[i] != null)
    //   System.out.print(arrays[i]);
    //   try{
    //
    //     Thread.sleep(500);
    //   }catch(Exception e){
    //
    //   }
    // }
    // try{
    //
    //   Thread.sleep(1000);
    // }catch(Exception e){}
    //
    // System.out.println();
    // // System.out.println("\nARRAY");
    array.print();
  }
}

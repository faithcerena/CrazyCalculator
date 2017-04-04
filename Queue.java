public class Queue {
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
    return array.del();
  }

  public void print(){
    for(int i = 0; i < arrays.length; i++){
      if(arrays[i] != null)
      // System.out.println(arrays[i]);
    }
    // System.out.println("\nARRAY");
    array.print();
  }
}

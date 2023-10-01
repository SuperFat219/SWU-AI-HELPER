public class Exercise25_2 {
  public static void main(String[] args) {
    Exercise25_2 exercise24_2 = new Exercise25_2();
  }

  public class MyQueue<E> {
    private java.util.LinkedList<E> list = new java.util.LinkedList<E>();

    public void enqueue(E o) {
      list.addLast(o);
    }

    public Object dequeue() {
      return list.removeFirst();
    }

    public int getSize() {
      return list.size();
    }

    public String toString() {
      return "Queue: " + list.toString();
    }
  }
}
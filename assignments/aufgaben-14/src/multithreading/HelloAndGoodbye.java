package multithreading;

public class HelloAndGoodbye {

   public static void main(String[] args) {
      Runnable hello = () -> {
         for (int i = 1; i <= 1000; i++) {
            System.out.println("Hello " + i);
         }
      };
      Runnable goodbye = () -> {
         for (int i = 1; i <= 1000; i++) {
            System.out.println("Goodbye " + i);
         }
      };

      var t1 = new Thread(hello);
      var t2 = new Thread(goodbye);
      t1.start();
      t2.start();
   }
}

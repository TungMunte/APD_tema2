import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tema2 {
    static public String working_dir = System.getProperty("user.dir");
    static public String working_folder;
    static Integer NUMBER_OF_THREADS = 2;

    public static void main(String[] args) throws IOException {
        working_folder = args[0];
        NUMBER_OF_THREADS = Integer.parseInt(args[1]);
        ExecutorService tpe = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        Thread[] threads = new Thread[NUMBER_OF_THREADS];

        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            threads[i] = new HandleOrders(i, tpe);
            threads[i].start();
        }

        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

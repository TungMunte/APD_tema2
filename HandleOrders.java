import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class HandleOrders extends Thread {
    private int id;
    private ExecutorService tpe;

    public HandleOrders(int id, ExecutorService tpe) {
        this.id = id;
        this.tpe = tpe;
    }

    public void run() {
        BufferedReader reader;
        List<String> commands = Collections.synchronizedList(new ArrayList<>());
        try {
            int i = 1;
            reader = new BufferedReader(new FileReader(Tema2.working_folder + "/orders.txt"));
            String line = reader.readLine();

            // find the starting line appropriate cu thread id
            while (line != null) {
                if (i - 1 == this.id) {
                    String[] strings = line.split(",");
                    int number = Integer.parseInt(strings[1]);
                    if (number != 0) {
                        commands.add(line);
                    }
                    break;
                }
                line = reader.readLine();
                i++;
            }

            // start reading line with nr_thread step
            i = 0;
            while (line != null) {
                if (i == Tema2.NUMBER_OF_THREADS) {
                    String[] strings = line.split(",");
                    int number = Integer.parseInt(strings[1]);
                    if (number != 0) {
                        commands.add(line);
                    }
                    i = 0;
                }
                line = reader.readLine();
                i++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String command : commands) {
            this.tpe.submit(new HandleOrderProduct(this.tpe, command));
        }

    }
}

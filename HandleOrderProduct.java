import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class HandleOrderProduct implements Runnable {
    ExecutorService tpe;
    String command;

    public HandleOrderProduct(ExecutorService tpe, String command) {
        this.tpe = tpe;
        this.command = command;
    }

    public void run() {
        BufferedReader reader;
        FileWriter writer1;
        FileWriter writer2;
        String[] strings = command.split(",");
        String id_command = strings[0];
        int number = Integer.parseInt(strings[1]);

        try {
            reader = new BufferedReader(new FileReader(Tema2.working_folder + "/order_products.txt"));
            writer1 = new FileWriter(Tema2.working_dir + "/order_products_out.txt", true);
            String contentLine = reader.readLine();

            while (contentLine != null) {
                String[] splited_strings = contentLine.split(",");
                if (Objects.equals(splited_strings[0], id_command)) {
                    writer1.write(contentLine + ",shipped\n");
                    number--;
                }
                if (number == 0) {
                    break;
                }
                contentLine = reader.readLine();
            }
            reader.close();
            writer1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            writer2 = new FileWriter(Tema2.working_dir + "/orders_out.txt", true);
            writer2.write(command + ",shipped\n");
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tpe.shutdown();
    }
}

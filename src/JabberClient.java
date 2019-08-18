import java.net.*;

import java.io.*;

import static java.lang.Thread.sleep;

public class JabberClient {
    public static void main(String[] args) throws IOException {
        // Передаем null в getByName(), получая
        // специальный IP адрес "локальной заглушки"
        // для тестирования на машине без сети:
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        // Альтернативно, вы можете использовать
        // адрес или имя:
        // InetAddress addr =
        // InetAddress.getByName("127.0.0.1");
        // InetAddress addr =
        // InetAddress.getByName("localhost");
        System.out.println("addr эм = " + addr);
        Socket socket = new Socket(addr, JabberServer.PORT);
        // Помещаем все в блок try-finally, чтобы
        // быть уверенным, что сокет закроется:
        try {
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            // Вывод автоматически Output быталкивается PrintWriter'ом.
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++) {
                out.println("howdy " + i);
                sleep(10000);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
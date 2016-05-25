package HomeWork.Lesson12.P2PChat;

import java.util.Date;
import java.util.Scanner;

class Client {
    private static Message createMessage(String s, String login) {
        Message message = new Message();
        message.setText(s);
        message.setFrom(login);
        message.setDate(new Date(System.currentTimeMillis()));
        return message;
    }

    private static void chat(Scanner scanner, String login, ClientConnecting clientConnecting) {
        while (true) {
            String s = scanner.nextLine();
            if (s.isEmpty())
                break;
            Message message = createMessage(s, login);
            clientConnecting.sendMessage(message);
            System.out.println(message.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("1) Start chat alone");
        System.out.println("2) Start chat and connect to someone");
        final int chatMainPort = 15000;
        ClientConnecting clientConnecting;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while ( ! (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2")) ) {
            System.out.println("No such option!");
            input = scanner.nextLine();
        }
        if (input.equalsIgnoreCase("1")) {
            clientConnecting = new ClientConnecting(true, chatMainPort);
            clientConnecting.start();
            System.out.print("Type in your name: ");
            chat(scanner, scanner.nextLine(), clientConnecting);
        } else {
            System.out.print("Type in IpAdress to connect to: ");
            input = scanner.nextLine();
            clientConnecting = new ClientConnecting(false, chatMainPort, input);
            clientConnecting.start();
            System.out.print("Type in your name: ");
            chat(scanner, scanner.nextLine(), clientConnecting);
        }
    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IPAddressFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "ip_addresses.txt"; // Имя файла для записи
        System.out.println("Введите строку IP-адрес:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String ipPattern =
                    "(?:\\b(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                            "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(?=\\b)";
            Pattern pattern = Pattern.compile(ipPattern);
            Matcher matcher = pattern.matcher(input);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                if (matcher.find()) {
                    String foundIP = matcher.group();
                    System.out.println("Найден корректный IP-адрес: " + foundIP);
                    writer.write("Найден IP-адрес: " + foundIP + "\n");
                } else {
                    System.out.println("Корректный IP-адрес не найден.");
                    writer.write("Корректный IP-адрес не найден в строке " + input + "\n");
                }
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
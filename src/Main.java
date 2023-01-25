import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.MemoryNotificationInfo;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        String text = "{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}";


        String textPars1 = text.replaceAll("фамилия", "Студент").replaceAll("оценка", "Получил оценку").replaceAll("предмет", "По предмету");
        StringBuilder textPars = new StringBuilder(textPars1);
        task1_1(textPars);
    }

    static void task1_1(StringBuilder textPars) {
        /*
        Дана json строка { { "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
        {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}} Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
        Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика. Студент Краснов получил 5 по
        предмету Физика. Используйте StringBuilder для подготовки ответа
        Создать метод, который запишет результат работы в файл Обработайте исключения и запишите ошибки в лог файл
         */

        for (int index = 0; index < textPars.length(); index++) {
            if (textPars.charAt(index) == '{' || textPars.charAt(index) == ',' || textPars.charAt(index) == '"' ||
                    textPars.charAt(index) == ':') {
                textPars.setCharAt(index, ' ');
            } else if (textPars.charAt(index) == '}') {
                textPars.setCharAt(index, '.');

            }
        }

        for (int i = 1; i < textPars.length(); i++) {
            for (int j = 1; j < textPars.length(); j++) {
                if (textPars.charAt(i) == ' ') {
                    if (textPars.charAt(j - 1) == textPars.charAt(j))
                        textPars.delete(j - 1, j + 1);
                }

            }

        }


        System.out.println(textPars);
        logging(textPars);


    }

    static void logging(StringBuilder textPars) {
        String resultFile = "result.txt";
        Logger logging = Logger.getAnonymousLogger();
        logging.log(Level.INFO, "Тестдрайв");
        FileHandler logHandler = null;
        SimpleFormatter style = new SimpleFormatter();
        try {
            logHandler = new FileHandler("log.txt");
            logHandler.setFormatter(style);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logging.addHandler(logHandler);
        try (FileWriter writeIn = new FileWriter(resultFile)) {
            writeIn.write(textPars.toString());

        } catch (Exception e) {
            logging.log(Level.INFO, e.getMessage());
            e.printStackTrace();
        }

    }
}

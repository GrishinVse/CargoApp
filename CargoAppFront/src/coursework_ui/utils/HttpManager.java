package coursework_ui.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Класс создает запросы к серверу и возвращает его результат
 */
public class HttpManager {
    /**
     * Метод GET запроса к серверу
     * @param STRING_URL ссылка на GET запрос
     * @return строку с JSON ответом или null
     */
    public static String GetRequest(String STRING_URL) {
        try {
            URL url = new URL(STRING_URL);
            URLConnection conn = url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String message = "";

            while ((message = bufferedReader.readLine()) != null) {
                stringBuilder.append(message);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод POST запроса к серверу
     * @param STRING_URL ссылка на POST запрос
     * @param STRING_JSON новые значения для добавления в БД
     * @return строку с JSON ответом или null
     */
    public static String PostRequest(String STRING_URL, String STRING_JSON) {
        try {
            URL url = new URL(STRING_URL);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("POST");
            http.setDoOutput(true);

            byte[] out = STRING_JSON.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String message = "";

            while ((message = br.readLine()) != null) {
                sb.append(message);
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Метод DELETE запроса к серверу
     * @param STRING_URL ссылка на DELETE запрос
     * @return true если удаление прошло успешно, false если было отклонено
     */
    public static Boolean DeleteRequest(String STRING_URL) {
        try {
            URL url = new URL(STRING_URL);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("DELETE");
            http.setDoOutput(true);

            http.connect();
            Integer code = http.getResponseCode();

            System.out.println("RESPONSE CODE = " + code);
            if (code == 200){
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Метод PUT запроса к серверу
     * @param STRING_URL ссылка на PUT запрос
     * @param STRING_JSON изменения в объекте которые нужно внести
     * @return строку с JSON ответом или null
     */
    public static String PutRequest(String STRING_URL, String STRING_JSON) {
        try {
            URL url = new URL(STRING_URL);
            URLConnection conn = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            http.setRequestMethod("PUT");
            http.setDoOutput(true);

            byte[] out = STRING_JSON.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();

            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }

            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String message = "";

            while ((message = bufferedReader.readLine()) != null) {
                stringBuilder.append(message);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

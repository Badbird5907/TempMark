package dev.badbird.markdown.util;

import lombok.SneakyThrows;

import java.net.HttpURLConnection;
import java.net.URL;

public class WebUtil {
    @SneakyThrows
    public static String getURLContents(URL url) {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        StringBuilder inline = new StringBuilder();
        java.util.Scanner scanner = new java.util.Scanner(url.openStream());
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();
        return inline.toString();
    }

    @SneakyThrows
    public static String getURLContents(String url) {
        return getURLContents(new URL(url));
    }
}

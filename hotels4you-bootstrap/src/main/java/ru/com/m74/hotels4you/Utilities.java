package ru.com.m74.hotels4you;

/**
 * @author mixam
 * @since 13.06.16 20:34
 */
public class Utilities {

    public static String removeHtml(String html) {
        return html != null ? html.replaceAll("<[^>]*>", "").replaceAll("\\n|\\r", "") : null;
    }

}

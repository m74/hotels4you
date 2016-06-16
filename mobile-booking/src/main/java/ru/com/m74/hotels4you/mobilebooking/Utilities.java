package ru.com.m74.hotels4you.mobilebooking;

/**
 * @author mixam
 * @since 13.06.16 20:34
 */
public class Utilities {


    public static String removeHtml(String html) {
        return html != null ? html.replaceAll("<[^>]*>", "").replaceAll("\\n|\\r", "") : null;
    }

    public static String getImageFilename(String iconId, String contentType) {
        return iconId != null ? iconId + "." + getFileExtension(contentType) : null;
    }

    private static String getFileExtension(String contentType) {
        if (contentType != null) {
            if (contentType.equals("image/jpeg"))
                return "jpg";
            if (contentType.equals("image/gif"))
                return "gif";
            if (contentType.matches("image/png"))
                return "png";
            if (contentType.matches("image/bmp"))
                return "bmp";
            if (contentType.matches("image/pjpeg"))
                return "jpg";
        }
        return "jpg";
    }
}

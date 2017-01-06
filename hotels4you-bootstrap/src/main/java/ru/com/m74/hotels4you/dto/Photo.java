package ru.com.m74.hotels4you.dto;

/**
 * @author mixam
 * @since 07.01.17 0:30
 */
//@XmlRootElement(name = "photo")
public class Photo {
    private String title;
    private String filename;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String id, String contentType) {
        this.filename = getFilename(id, contentType);
    }

    public static String getIconUrl(String id, String contentType, String size) {
        return id != null ? "http://www.h4y.ru/images/" + size + "/" + Photo.getFilename(id, contentType)
                : "http://placehold.it/" + size + "&text=NO IMAGE";
    }

    public static String getFilename(String id, String contentType) {
        return id + "." + getFileExtention(contentType);
    }

    private static String getFileExtention(String contentType) {
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

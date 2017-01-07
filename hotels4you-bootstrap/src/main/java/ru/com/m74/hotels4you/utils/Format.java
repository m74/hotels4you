package ru.com.m74.hotels4you.utils;

/**
 * @author mixam
 * @since 07.01.17 15:49
 */
public class Format {
    private static final java.text.DecimalFormat currencyFormat = new java.text.DecimalFormat(
            "#,##0.00");

    public static String currency(String strval) {
        return currencyFormat.format(Double.parseDouble(strval));
    }
}

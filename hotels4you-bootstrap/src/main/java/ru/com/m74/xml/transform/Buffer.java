package ru.com.m74.xml.transform;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author mixam
 * @since 08.01.17 13:53
 */
public class Buffer extends ServletOutputStream {
    private final ByteArrayOutputStream bout = new ByteArrayOutputStream();

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void write(int b) throws IOException {
        bout.write((byte) b);
    }

    public byte[] getContent() {
        return bout.toByteArray();
    }
}

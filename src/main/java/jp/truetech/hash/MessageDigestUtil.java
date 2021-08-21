package jp.truetech.hash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class MessageDigestUtil {

    static private int BUFSIZE = 1024;
    
    private MessageDigest md;
    private int bufSize;
    
    static public MessageDigestUtil getInstance(String algorithm) throws NoSuchAlgorithmException {
        return getInstance(algorithm, BUFSIZE);
    }
        
    static public MessageDigestUtil getInstance(String algorithm, int bufSize) throws NoSuchAlgorithmException {
        MessageDigestUtil hash = new MessageDigestUtil();
        hash.md = MessageDigest.getInstance(algorithm);
        hash.bufSize = bufSize;
        return hash;
    }
    
    static public String digest(String algorithm, File file) throws NoSuchAlgorithmException, IOException {
        return MessageDigestUtil.getInstance(algorithm).digest(file);
    }
    
    private MessageDigestUtil() {
        
    }
    
    public String digest(File file) throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream(file))) {
            return digest(in);
        }
    }

    public String digest(InputStream in) throws IOException {
        return toHexString(digest_(in));
    }
    
    private byte[] digest_(InputStream in) throws IOException {
        byte[] buf = new byte[bufSize];
        while (true) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }
            md.update(buf, 0, len);
        }
        return md.digest();
    }
    
    private String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        formatter.close();
        return sb.toString();
    }

}

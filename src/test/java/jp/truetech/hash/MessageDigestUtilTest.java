package jp.truetech.hash;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.junit.Test;

public class MessageDigestUtilTest {

    @Test
    public void メッセージダイジェスト算出() throws Exception {
        String algorithm = "SHA-512";
        String data = "abc";
        MessageDigestUtil md = MessageDigestUtil.getInstance(algorithm);
        String expected = 
                "ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a"
              + "2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f";
        String actual;
        try (InputStream in = new ByteArrayInputStream(data.getBytes());) {
            actual = md.digest(in);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void メッセージダイジェスト算出_ファイル() throws Exception {
        String algorithm = "SHA-512";
        String file = "abc.txt";
        MessageDigestUtil md = MessageDigestUtil.getInstance(algorithm);
        String expected = 
                "ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a"
              + "2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f";
        String actual;
        actual = md.digest(new File(file));
        assertEquals(expected, actual);
    }

}

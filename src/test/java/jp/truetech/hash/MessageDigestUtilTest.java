package jp.truetech.hash;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;

import org.junit.Test;

public class MessageDigestUtilTest {

    @Test
    public void getInstanceTest() throws Exception {
        String algorithm = "SHA-512";
        MessageDigestUtil md = MessageDigestUtil.getInstance(algorithm);
        Field bufsize = md.getClass().getDeclaredField("bufSize");
        bufsize.setAccessible(true);
        assertThat(bufsize.get(md)).isEqualTo(1024);
        md = MessageDigestUtil.getInstance(algorithm, 512);
        assertThat(bufsize.get(md)).isEqualTo(512);
    }
    
    @Test
    public void スタティックメソッド() throws Exception {
        String algorithm = "SHA-512";
        String file = "abc.txt";
        String expected = 
                "ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a"
              + "2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f";
        String actual;
        actual = MessageDigestUtil.digest(algorithm, new File(file));
        assertThat(actual).isEqualTo(expected);
    }
    
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
        assertThat(actual).isEqualTo(expected);
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
        assertThat(actual).isEqualTo(expected);
    }

}

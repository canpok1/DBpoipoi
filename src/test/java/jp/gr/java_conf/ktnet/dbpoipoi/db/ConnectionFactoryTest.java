package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ConnectionFactoryTest {

    private static final String URL = "jdbc:sqlite:./src/test/resources/TestSchema.sqlite";
    
    public static class ドライバクラス指定でのcreateを呼び出す際 {
        
        private static final String DRIVER = "org.sqlite.JDBC";
        
        @Test
        public void createでConnectionを生成できる() throws Exception {
            try (Connection con = ConnectionFactory.create(DRIVER, URL, "", "")) {
                assertThat(con, is(not(nullValue())));
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() throws Exception {
            try (Connection con = ConnectionFactory.create("", URL, "", "")) {
                fail("例外未発生");
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数がnullだと例外発生() throws Exception {
            try (Connection con = ConnectionFactory.create(DRIVER, null, "", "")) {
                fail("例外未発生");
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数が空文字だと例外発生() throws Exception {
            try (Connection con = ConnectionFactory.create(DRIVER, "", "", "")) {
                fail("例外未発生");
            }
        }
    }
    
}

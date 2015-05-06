package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.Connection;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ConnectionFactoryTest {

    private static final String url = "jdbc:sqlite:./src/test/resources/TestSchema.sqlite";
    
    public static class ドライバクラス指定でのcreateを呼び出す際 {
        
        private static final String driver = "org.sqlite.JDBC";
        
        @Test
        public void createでConnectionを生成できる() throws Exception{
            try (Connection con = ConnectionFactory.create(driver, url, "", "")) {
                assertThat(con, is(not(nullValue())));
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() throws Exception{
            try (Connection con = ConnectionFactory.create("", url, "", "")) {
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数がnullだと例外発生() throws Exception{
            try (Connection con = ConnectionFactory.create(driver, null, "", "")) {
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数が空文字だと例外発生() throws Exception{
            try (Connection con = ConnectionFactory.create(driver, "", "", "")) {
            }
        }
    }
    
}

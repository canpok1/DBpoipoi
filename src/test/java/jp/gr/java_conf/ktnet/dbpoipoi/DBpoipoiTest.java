package jp.gr.java_conf.ktnet.dbpoipoi;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting.SqlSetting;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DBpoipoiTest {

    public static class コンストラクタを呼び出した際 {
        
        @Test
        public void 例外が発生しないこと() {
            new DBpoipoi();
        }
        
    }
    
    public static class saveメソッドを呼び出した際 {
        
        private DBpoipoi sut;
        
        @Before
        public void setup() {
            this.sut = new DBpoipoi();
        }
        
        @Test(expected = NullPointerException.class)
        public void 第一引数がnullだと例外発生(
            @Mocked Connection connection) throws Exception {
            sut.save(null, connection, "abcde");
        }
        
        @Test(expected = NullPointerException.class)
        public void 第二引数がnullだと例外発生(
            @Mocked List<SqlSetting> list) throws Exception {
            sut.save(list, null, "abcde");
        }
        
        @Test(expected = NullPointerException.class)
        public void 第三引数がnullだと例外発生(
            @Mocked List<SqlSetting> list,
            @Mocked Connection connection) throws Exception {
            sut.save(list, connection, null);
        }
        
    }
}

package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.junit.Assert.*;

import java.sql.Connection;

import jp.gr.java_conf.ktnet.dbpoipoi.db.SqlExecuter;
import mockit.MockUp;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SqlExecuterTest {

    public static class コンストラクタの呼び出しで {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がNullだと例外発生() {
            new SqlExecuter(null);
        }
        
    }
    
    public static class selectの呼び出しで {
        
        private SqlExecuter sut;
        
        @Before
        public void setup() {
            Connection connection = new MockUp<Connection>() {
            }.getMockInstance();
            this.sut = new SqlExecuter(connection);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がNullの場合は例外発生() {
            sut.select(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字の場合は例外発生() {
            sut.select("");
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が非SELECT文の場合は例外発生() {
            sut.select("DELETE ");
        }
        
    }
    
}

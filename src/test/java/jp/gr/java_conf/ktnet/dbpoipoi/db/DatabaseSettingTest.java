package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting.SqlSetting;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class DatabaseSettingTest {
    
    public static class loadする際に {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がNullだと例外発生() throws Exception {
            DatabaseSetting.load(null, "aaa");
        }

        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() throws Exception {
            DatabaseSetting.load("", "aaa");
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第二引数がNullだと例外発生() throws Exception {
            DatabaseSetting.load("aaa", null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void 第二引数が空文字だと例外発生() throws Exception {
            DatabaseSetting.load("aaa", "");
        }
        
        @Test(expected = IOException.class)
        public void 設定ファイルが見つからない場合は例外発生() throws Exception {
            DatabaseSetting.load(
                "./src/test/resources/notExist.ini",
                "./src/test/resources/sql");
        }
        
        @Test(expected = IOException.class)
        public void SQLフォルダが見つからない場合は例外発生() throws Exception {
            DatabaseSetting.load(
                "./src/test/resources/databaseSettingTest.ini",
                "./src/test/resources/notExist");
        }
    }
    
    public static class 設定値がすべて書いてあってSQLが2つある場合 {
        
        private DatabaseSetting sut;
        
        @Before
        public void setup() throws Exception {
            sut = DatabaseSetting.load(
                    "./src/test/resources/databaseSettingTest.ini",
                    "./src/test/resources/sql");
        }
        
        @Test
        public void loadでDB設定値が読み込めること() {
            assertThat(sut.getJdbcDriverClass(), is("org.sqlite.JDBC"));
            assertThat(sut.getUrl(), is("jdbc:sqlite:./sample.db"));
            assertThat(sut.getUser(), is("abc"));
            assertThat(sut.getPassword(), is("def"));
        }
        
        @Test
        public void loadでSQL設定が読み込めること() {
            assertThat(sut.getSqlSettings(), hasSize(2));
            
            assertThat(
                sut.getSqlSettings(),
                hasItem(samePropertyValuesAs(
                    new SqlSetting("User", "select * from User")
                ))
            );
            assertThat(
                sut.getSqlSettings(),
                hasItem(samePropertyValuesAs(
                    new SqlSetting("Book", "select * from Book")
                ))
            );
        }
        
    }
    
}

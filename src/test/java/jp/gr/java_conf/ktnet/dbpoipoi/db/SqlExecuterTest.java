package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
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
        
        private static final String CLASS_NAME = "org.sqlite.JDBC";
        private static final String URL = "jdbc:sqlite:./src/test/resources/TestSchema.sqlite";
        private static final String TEST_DATA = "./src/test/resources/TestData.xls";
        
        private IDatabaseTester databaseTester;
        private Connection connection;
        
        private SqlExecuter sut;
        
        @Before
        public void setup() throws Exception {
            databaseTester = new JdbcDatabaseTester(CLASS_NAME, URL, "", "");
            databaseTester.setDataSet(new XlsDataSet(new File(TEST_DATA)));
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.onSetup();
            
            Class.forName(CLASS_NAME);
            connection = DriverManager.getConnection(URL, "", "");
            this.sut = new SqlExecuter(connection);
        }
        
        @After
        public void tearDown() throws Exception {
            connection.close();
            databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
            databaseTester.onTearDown();
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がNullの場合は例外発生() throws Exception {
            sut.select(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字の場合は例外発生() throws Exception {
            sut.select("");
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が非SELECT文の場合は例外発生() throws Exception {
            sut.select("DELETE ");
        }
        
        @Test
        public void Userテーブルのカラム名を取得できる() throws Exception {
            RecordContainer records = sut.select("select * from User");
            assertThat(
                records.getColumnNames(),
                is(contains("id", "name", "age", "birthday"))
            );
        }
        
        @Test
        public void Userテーブルの型情報を取得できる() throws Exception {
            RecordContainer records = sut.select("select * from User");
            assertThat(
                records.getColumnType(),
                is(contains(
                    Types.INTEGER,
                    Types.VARCHAR,
                    Types.INTEGER,
                    Types.DATE
                ))
            );
        }
        
        @Test
        public void UserテーブルのIDカラムの値を取得できる() throws Exception {
            final int columnIndex = 0;
            final int columnValue = 1;
            
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(columnIndex), is(instanceOf(Integer.class)));
            
            Integer intValue = (Integer)(records.getRecords(0).get(columnIndex));
            assertThat(intValue, is(columnValue));
        }
        
        @Test
        public void UserテーブルのNAMEカラムの値を取得できる() throws Exception {
            final int columnIndex = 1;
            final String columnValue = "tanabe";
            
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(columnIndex), is(instanceOf(String.class)));
            
            String strValue = (String)(records.getRecords(0).get(columnIndex));
            assertThat(strValue, is(columnValue));
        }
        
        @Test
        public void UserテーブルのAGEカラムの値を取得できる() throws Exception {
            final int columnIndex = 2;
            final int columnValue = 28;
            
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(columnIndex), is(instanceOf(Integer.class)));
            
            Integer intValue = (Integer)(records.getRecords(0).get(columnIndex));
            assertThat(intValue, is(columnValue));
        }
        
        @Test
        public void UserテーブルのBIRTHDAYカラムの値を取得できる() throws Exception {
            final int columnIndex = 3;
            final String columnValue = "1987/04/14";
            
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(columnIndex), is(instanceOf(Date.class)));
            
            Date dateValue = (Date)(records.getRecords(0).get(columnIndex));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            
            assertThat(format.format(dateValue), is(columnValue));
        }
        
    }
    
}

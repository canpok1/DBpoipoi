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
        
        private static final String className = "org.sqlite.JDBC";
        private static final String url = "jdbc:sqlite:./src/test/resources/TestSchema.sqlite";
        private static final String testData = "./src/test/resources/TestData.xls";
        
        private IDatabaseTester databaseTester;
        private Connection connection;
        
        private SqlExecuter sut;
        
        @Before
        public void setup() throws Exception {
            databaseTester = new JdbcDatabaseTester(className, url, "", "");
            databaseTester.setDataSet(new XlsDataSet(new File(testData)));
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.onSetup();
            
            Class.forName(className);
            connection = DriverManager.getConnection(url, "", "");
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
                is(contains("id", "name", "age", "birthday")
            ));
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
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(0), is(instanceOf(Integer.class)));
            
            Integer intValue = (Integer)(records.getRecords(0).get(0));
            assertThat(intValue, is(1));
        }
        
        @Test
        public void UserテーブルのNAMEカラムの値を取得できる() throws Exception {
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(1), is(instanceOf(String.class)));
            
            String strValue = (String)(records.getRecords(0).get(1));
            assertThat(strValue, is("tanabe"));
        }
        
        @Test
        public void UserテーブルのAGEカラムの値を取得できる() throws Exception {
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(2), is(instanceOf(Integer.class)));
            
            Integer intValue = (Integer)(records.getRecords(0).get(2));
            assertThat(intValue, is(28));
        }
        
        @Test
        public void UserテーブルのBIRTHDAYカラムの値を取得できる() throws Exception {
            RecordContainer records = sut.select("select * from User");
            assertThat(records.getRecords(), hasSize(1));
            assertThat(records.getRecords(0).get(3), is(instanceOf(Date.class)));
            
            Date dateValue = (Date)(records.getRecords(0).get(3));
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            
            assertThat(format.format(dateValue), is("1987/04/14"));
        }
        
    }
    
}

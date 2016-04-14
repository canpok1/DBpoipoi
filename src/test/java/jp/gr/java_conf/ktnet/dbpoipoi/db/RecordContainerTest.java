package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RecordContainerTest {

    public static class コンストラクタの呼び出しで {
        
        private List<String> makeRecord(String... values) {
            return Arrays.asList(values);
        }
        private List<Integer> makeRecord(Integer... values) {
            return Arrays.asList(values);
        }
        
        private List<List<Object>> makeRecords(Object[]... records) {
            List<List<Object>> result = new ArrayList<List<Object>>();
            
            for(Object[] record : records) {
                result.add(Arrays.asList(record));
            }
            
            return result;
        }
        
        @Test(expected = NullPointerException.class)
        public void カラム名がnullだと例外発生() {
            List<Integer> types = makeRecord(new Integer[] {
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
            });
            
            List<List<Object>> records = makeRecords(new Object[][] {
                {"aaa"},
                {"bbb"},
                {"ccc"}
            });
            
            new RecordContainer(null, types, records);
        }
        
        @Test(expected = NullPointerException.class)
        public void カラム型がnullだと例外発生() {
            List<String> columnNames = makeRecord(new String[] {
                "aaa", "bbb", "ccc"
            });
            List<List<Object>> records = makeRecords(new Object[][] {
                {"aaa"},
                {"bbb"},
                {"ccc"}
            });
            
            new RecordContainer(columnNames, null, records);
        }
        
        @Test(expected = NullPointerException.class)
        public void レコードがnullだと例外発生() {
            List<Integer> types = makeRecord(new Integer[] {
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
            });
            List<String> columnNames = makeRecord(new String[] {
                "aaa", "bbb", "ccc"
            });
            new RecordContainer(columnNames, types, null);
        }
        
        @Test
        public void レコードが0件だと例外未発生() {
            List<String> columnNames = makeRecord(new String[] {
                "aaa", "bbb", "ccc"
            });
            List<Integer> types = makeRecord(new Integer[] {
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
            });
            List<List<Object>> records = new ArrayList<List<Object>>();
            new RecordContainer(columnNames, types, records);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void カラム名とカラム型のサイズが異なると例外未発生() {
            List<String> columnNames = makeRecord(new String[] {
                "aaa", "bbb", "ccc"
            });
            List<Integer> types = makeRecord(new Integer[] {
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
            });
            List<List<Object>> records = makeRecords(new Object[][] {
                {"aaa"},
                {"bbb"},
                {"ccc"}
            });
            new RecordContainer(columnNames, types, records);
        }

        @Test(expected = IllegalArgumentException.class)
        public void カラム名とレコードのサイズが異なると例外未発生() {
            List<String> columnNames = makeRecord(new String[] {
                "Name1", "Name2", "Name3"
            });
            List<Integer> types = makeRecord(new Integer[] {
                Types.INTEGER,
                Types.INTEGER,
                Types.INTEGER
            });
            List<List<Object>> records = makeRecords(new Object[][]{
                {"value1-1", "value1-2", "value1-3"},
                {"value2-1", "value2-2", "value2-3", "value2-4"}});
            new RecordContainer(columnNames, types, records);
        }
    }
    
}

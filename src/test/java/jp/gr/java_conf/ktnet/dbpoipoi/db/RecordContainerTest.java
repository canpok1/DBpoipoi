package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.junit.Assert.*;
import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import mockit.Mocked;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class RecordContainerTest {

    public static class コンストラクタの呼び出しで {
        
        @Test(expected = IllegalArgumentException.class)
        public void カラム名がnullだと例外発生() {
            String[][] records = {
                { "aaa", "bbb", "ccc"}
            };
            new RecordContainer(null, records);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void レコードがnullだと例外発生() {
            String[] columnNames = { "aaa", "bbb", "ccc"};
            new RecordContainer(columnNames, null);
        }
        
        @Test
        public void レコードが0件だと例外未発生() {
            String[] columnNames = { "aaa", "bbb", "ccc"};
            String[][] records = {};
            new RecordContainer(columnNames, records);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void カラム名とレコードのサイズが異なると例外未発生() {
            String[] columnNames = { "Name1", "Name2", "Name3"};
            String[][] records = {
                    {"value1-1", "value1-2", "value1-3"},
                    {"value2-1", "value2-2", "value2-3", "value2-4"}};
            new RecordContainer(columnNames, records);
        }
    }
    
}

package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelWriter;
import mockit.Mocked;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ExcelWriterTest {

    public static class コンストラクタを呼び出した時 {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がnullだと例外発生() {
            new ExcelWriter(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() {
            new ExcelWriter("");
        }
        
    }
    
    public static class 書き込みファイルが存在しない時 {
        
        private final String filePath = "./ExcelWriterTest_notExist.xls";
        private File file;
        private ExcelWriter sut;
        
        @Before
        public void setup() {
            file = new File(filePath);
            if(file.exists()) {
                file.delete();
            }
            this.sut = new ExcelWriter(filePath);
        }
        
        @After
        public void cleanup() {
            if(file.exists()) {
                file.delete();
            }
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void addRecordsの第一引数がnullだと例外発生(
                @Mocked RecordContainer records
                ) throws Exception {
            sut.addRecords(null, "abc", records);
        }

        @Test(expected = IllegalArgumentException.class)
        public void addRecordsの第二引数がnullだと例外発生(
                @Mocked RecordContainer records
                ) throws Exception {
            sut.addRecords("abc", null, records);
        }

        @Test(expected = IllegalArgumentException.class)
        public void addRecordsの第三引数がnullだと例外発生() throws Exception {
            sut.addRecords("abc", "abc", null);
        }

        @Test
        public void writeでファイルが作成される() throws Exception {
            sut.write();
            assertThat(file.exists(), is(true));
        }
        
    }
}

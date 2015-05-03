package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import static org.junit.Assert.*;
import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelWriter;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

public class ExcelWriterTest {

    private ExcelWriter sut;
    
    @Before
    public void setup() {
        this.sut = new ExcelWriter();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void writeの第一引数がnullだと例外発生() {
        sut.write(null, "aaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeの第二引数がnullだと例外発生(@Mocked RecordContainer container) {
        sut.write(container, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeの第二引数が空文字だと例外発生(@Mocked RecordContainer container) {
        sut.write(container, "");
    }
}

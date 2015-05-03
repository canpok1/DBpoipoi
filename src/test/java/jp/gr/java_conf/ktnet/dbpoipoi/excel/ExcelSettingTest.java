package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import static org.junit.Assert.*;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelSetting;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ExcelSettingTest {

    public static class loadを呼んだ時に {
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がnullの場合は例外発生() {
            ExcelSetting.load(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字の場合は例外発生() {
            ExcelSetting.load("");
        }
    }
    
}

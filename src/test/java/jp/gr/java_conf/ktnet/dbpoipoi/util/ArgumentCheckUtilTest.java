package jp.gr.java_conf.ktnet.dbpoipoi.util;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ArgumentCheckUtilTest {

    public static class checkNotEmptyを呼んだ時 {
        
        @Test
        public void 第一引数がNullだと例外未発生() {
            ArgumentCheckUtil.checkNotEmpty(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() {
            ArgumentCheckUtil.checkNotEmpty("");
        }
        
        @Test
        public void 第一引数が非空文字だと例外未発生() {
            ArgumentCheckUtil.checkNotEmpty("aaa");
        }
        
    }
}

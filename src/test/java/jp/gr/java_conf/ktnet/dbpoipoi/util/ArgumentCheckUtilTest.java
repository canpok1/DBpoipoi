package jp.gr.java_conf.ktnet.dbpoipoi.util;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ArgumentCheckUtilTest {

    public static class checkNotNullを呼んだ時 {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がnullだと例外発生() {
            ArgumentCheckUtil.checkNotNull(null);
        }
        
        @Test
        public void 第一引数が非Nullだと例外未発生() {
            ArgumentCheckUtil.checkNotNull(new Object());
        }
        
    }
    
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
    
    public static class checkNotNullAndEmptyを呼んだ時 {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がNullだと例外発生() {
            ArgumentCheckUtil.checkNotNullAndEmpty(null);
        }
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数が空文字だと例外発生() {
            ArgumentCheckUtil.checkNotNullAndEmpty("");
        }
        
        @Test
        public void 第一引数が非空文字だと例外未発生() {
            ArgumentCheckUtil.checkNotNullAndEmpty("aaa");
        }
        
    }
}

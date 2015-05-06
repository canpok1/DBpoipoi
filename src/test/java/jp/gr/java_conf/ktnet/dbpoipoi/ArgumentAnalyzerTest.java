package jp.gr.java_conf.ktnet.dbpoipoi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ArgumentAnalyzerTest {

    public static class コンストラクタを呼び出す際 {
        
        @Test(expected = IllegalArgumentException.class)
        public void 第一引数がnullだと例外発生() {
            new ArgumentAnalyzer(null);
        }
        
        @Test
        public void 第一引数が空の配列だと例外未発生() {
            new ArgumentAnalyzer(new String[]{"a"});
        }
        
    }
    
    public static class コンストラクタでオプションを全指定の場合 {
        
        private ArgumentAnalyzer sut;
        
        private final String sqlDir = "sqlDir";
        private final String dbSetting = "dbSetting";
        private final String output = "output";
        
        @Before
        public void setup() {
            sut = new ArgumentAnalyzer(new String[] {
                "-sql", sqlDir,
                "-db", dbSetting,
                "-o", output
            });
        }
        
        @Test
        public void 指定した値が取得できる() {
            assertThat(sut.getDbSetting(), is(dbSetting));
            assertThat(sut.getOutputFile(), is(output));
            assertThat(sut.getSqlFolder(), is(sqlDir));
        }
    }
    
}

package jp.gr.java_conf.ktnet.dbpoipoi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class ArgumentAnalyzerTest {

    @Test(expected = IllegalArgumentException.class)
    public void コンストラクタの第一引数がnullの場合は例外発生() {
        new ArgumentAnalyzer(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void コンストラクタの第一引数が要素数2未満の配列だと例外発生() {
        new ArgumentAnalyzer(new String[]{"a"});
    }
    
    @Test
    public void コンストラクタの第一引数がメンバ変数に格納される() {
        String sqlDir = "sql";
        String filePath = "file";
        ArgumentAnalyzer analyzer = new ArgumentAnalyzer(new String[] {
            sqlDir,
            filePath,
            "abcde"
        });
        assertThat(analyzer.getSqlFolder(), is(sqlDir));
        assertThat(analyzer.getOutputFile(), is(filePath));
    }
}

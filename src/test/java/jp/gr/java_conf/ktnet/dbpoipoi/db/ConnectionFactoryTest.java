package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.sql.Connection;

import jp.gr.java_conf.ktnet.dbpoipoi.db.ConnectionFactory.Type;

import org.junit.Test;

public class ConnectionFactoryTest {

    private static final String url = "jdbc:sqlite:./src/test/resources/TestSchema.sqlite";
    
    @Test
    public void createでConnectionを生成できる() throws Exception{
        try (Connection con = ConnectionFactory.create(Type.SQLITE, url, "", "")) {
            assertThat(con, is(not(nullValue())));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createの第二引数がnullだと例外発生() throws Exception{
        try (Connection con = ConnectionFactory.create(Type.SQLITE, null, "", "")) {
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void createの第二引数が空文字だと例外発生() throws Exception{
        try (Connection con = ConnectionFactory.create(Type.SQLITE, "", "", "")) {
        }
    }
}

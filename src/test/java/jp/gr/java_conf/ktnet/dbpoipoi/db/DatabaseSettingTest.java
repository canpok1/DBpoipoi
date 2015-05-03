package jp.gr.java_conf.ktnet.dbpoipoi.db;

import static org.junit.Assert.*;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting;

import org.junit.Test;

public class DatabaseSettingTest {

    @Test(expected = IllegalArgumentException.class)
    public void loadの第一引数がNullだと例外発生() {
        DatabaseSetting.load(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadの第一引数が空文字だと例外発生() {
        DatabaseSetting.load("");
    }
}

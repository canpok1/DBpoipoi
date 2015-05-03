package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.sql.Connection;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * SQLを実行するクラスです.
 * @author tanabe
 *
 */
public class SqlExecuter {
    
    /**
     * DBへの接続.
     */
    private final Connection connection;
    
    /**
     * コンストラクタ.
     * @param connection DBへの接続.
     */
    public SqlExecuter(Connection connection) {
        ArgumentCheckUtil.checkNotNull(connection);
        this.connection = connection;
    }

    /**
     * SQLを実行します.
     * @param sql 実行するSQL.
     * @return 実行結果.
     */
    public RecordContainer select(String sql) {
        // TODO 実装
        ArgumentCheckUtil.checkNotNull(sql);
        if(!sql.toUpperCase().matches("SELECT ")) {
            throw new IllegalArgumentException("SELECT文以外は指定不可");
        }
        throw new UnsupportedOperationException("未実装");
    }    
}

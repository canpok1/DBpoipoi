package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.sql.Connection;

/**
 * DBへの接続を生成するクラスです.
 * @author tanabe
 *
 */
public final class ConnectionFactory {
    
    /**
     * DBの種別を表す列挙です.
     * @author tanabe
     *
     */
    public static enum Type {
        /** SQLITE. **/
        SQLITE,
    }
    
    /**
     * コンストラクタ(使用不可).
     */
    private ConnectionFactory() { }
    
    /**
     * DBへの接続を生成します.
     * 使用後は呼び出し側でcloseして下さい.
     * @param type DBの種別
     * @param url URL
     * @param user ユーザー名
     * @param password パスワード
     * @return DBへの接続
     */
    public static Connection create(Type type, String url, String user, String password) {
        // TODO 実装
        throw new UnsupportedOperationException("未実装");
    }

}

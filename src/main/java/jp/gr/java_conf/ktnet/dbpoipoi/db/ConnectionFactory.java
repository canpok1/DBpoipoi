package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DBへの接続を生成するクラスです.
 * @author tanabe
 *
 */
public final class ConnectionFactory {
    
    /**
     * コンストラクタ(使用不可).
     */
    private ConnectionFactory() { }
    
    /**
     * DBへの接続を生成します.
     * 使用後は呼び出し側でcloseして下さい.
     * @param driverClass ドライバクラス名
     * @param url URL
     * @param user ユーザー名
     * @param password パスワード
     * @return DBへの接続
     * @throws ClassNotFoundException ドライバクラスが見つからない場合
     * @throws SQLException SQL実行エラーが発生した場合
     */
    public static Connection create(String driverClass, String url, String user, String password)
        throws ClassNotFoundException, SQLException {
        ArgumentCheckUtil.checkNotNullAndEmpty(url);
        ArgumentCheckUtil.checkNotNullAndEmpty(driverClass);
        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

}

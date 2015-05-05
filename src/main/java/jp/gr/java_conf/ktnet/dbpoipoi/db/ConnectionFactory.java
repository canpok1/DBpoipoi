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
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static Connection create(Type type, String url, String user, String password)
            throws ClassNotFoundException, SQLException {
        ArgumentCheckUtil.checkNotNullAndEmpty(url);
        Class.forName(getClassName(type));
        return DriverManager.getConnection(url, user, password);
    }
    
    /**
     * JDBCのクラス名を取得します.
     * @param type DB種別
     * @return クラス名.
     */
    private static String getClassName(Type type) {
        switch(type) {
        case SQLITE :
            return "org.sqlite.JDBC";
        default :
            throw new UnsupportedOperationException("未対応のDBタイプ");
        }
    }

}

package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.db.ConnectionFactory.Type;
import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DB関連の情報を保持するクラスです.
 * @author tanabe
 *
 */
public final class DatabaseSetting {

    /**
     * レコード取得用のSQL情報を保持するクラスです.
     * @author tanabe
     *
     */
    public static class SqlSetting {
        /**
         * 識別用の名前.
         */
        public String name;
        
        /**
         * SQL.
         */
        public String sql;
    }
    
    /**
     * コンストラクタ.
     */
    private DatabaseSetting() {
    }
    
    /**
     * DBの種類.
     */
    private ConnectionFactory.Type type;
    
    /**
     * 接続URL.
     */
    private String url;
    
    /**
     * 接続ユーザー名.
     */
    private String user;
    
    /**
     * 接続パスワード.
     */
    private String password;
    
    /**
     * レコード取得用のSQL情報.
     */
    private List<SqlSetting> sqlSettings;
    
    /**
     * DB種別を取得します.
     * @return type DB種別
     */
    public ConnectionFactory.Type getType() {
        return type;
    }

    /**
     * URLを取得します.
     * @return url URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * ユーザー名を取得します.
     * @return user ユーザー名
     */
    public String getUser() {
        return user;
    }

    /**
     * パスワードを取得します.
     * @return password パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * SQL設定を取得します.
     * @return SQL設定.
     */
    public List<SqlSetting> getSqlSettings() {
        return sqlSettings;
    }

    /**
     * 設定ファイルから情報を読み込みます.
     * @param filePath 設定ファイルのパス.
     * @return 読み込んだ情報.
     */
    public static DatabaseSetting load(String filePath) {
        ArgumentCheckUtil.checkNotNullAndEmpty(filePath);
        
        DatabaseSetting setting = new DatabaseSetting();
        
        // TODO テスト用
        setting.type = Type.SQLITE;
        setting.url = "jdbc:sqlite:./sample.db";
        setting.user = "";
        setting.password = "";
        
        setting.sqlSettings = new ArrayList<SqlSetting>();
        
        setting.sqlSettings.add(new SqlSetting());
        setting.sqlSettings.get(0).name = "sample1";
        setting.sqlSettings.get(0).name = "select * from tbl1";
        
        return setting;
    }
}

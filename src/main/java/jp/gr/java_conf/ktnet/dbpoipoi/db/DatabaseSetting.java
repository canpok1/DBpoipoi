package jp.gr.java_conf.ktnet.dbpoipoi.db;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DB関連の情報を保持するクラスです.
 * @author tanabe
 *
 */
public final class DatabaseSetting {

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
     * 設定ファイルから情報を読み込みます.
     * @param filePath 設定ファイルのパス.
     * @return 読み込んだ情報.
     */
    public static DatabaseSetting load(String filePath) {
        ArgumentCheckUtil.checkNotNullAndEmpty(filePath);
        
        DatabaseSetting setting = new DatabaseSetting();
        
        return setting;
    }
}

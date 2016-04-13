package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * DB関連の情報を保持するクラスです.
 * @author tanabe
 *
 */
@NoArgsConstructor
public final class DatabaseSetting {

    /**
     * レコード取得用のSQL情報を保持するクラスです.
     * @author tanabe
     *
     */
    @Value
    public static class SqlSetting {
        /**
         * 識別用の名前.
         */
        private String name;
        
        /**
         * SQL.
         */
        private String sql;
    }
    
    /**
     * JDBCのドライバクラス名.
     */
    @Getter
    private String jdbcDriverClass;
    
    /**
     * 接続URL.
     */
    @Getter
    private String url;
    
    /**
     * 接続ユーザー名.
     */
    @Getter
    private String user;
    
    /**
     * 接続パスワード.
     */
    @Getter
    private String password;
    
    /**
     * レコード取得用のSQL情報.
     */
    @Getter
    private List<SqlSetting> sqlSettings;
    
    /**
     * 設定ファイルから情報を読み込みます.
     * @param filePath 設定ファイルのパス(null不可).
     * @param sqlDir SQL格納フォルダのパス(null不可).
     * @return 読み込んだ情報.
     * @throws IOException 読み込みに失敗した場合
     */
    public static DatabaseSetting load(
            @NonNull String filePath,
            @NonNull String sqlDir)
        throws IOException {
        ArgumentCheckUtil.checkNotEmpty(filePath);
        ArgumentCheckUtil.checkNotEmpty(sqlDir);
        
        DatabaseSetting setting = new DatabaseSetting();
        
        Properties prop = new Properties();
        try (FileInputStream is = new FileInputStream(filePath)) {
            prop.load(is);
        }
        setting.jdbcDriverClass = prop.getProperty("driverClass");
        setting.url = prop.getProperty("url");
        setting.user = prop.getProperty("user");
        setting.password = prop.getProperty("password");
        
        File sqlDirFile = new File(sqlDir);
        if(!sqlDirFile.exists()) {
            throw new IOException("SQL格納フォルダが見つかりません[" + sqlDir + "]");
        }
        setting.sqlSettings = loadSqlDir(sqlDirFile);
        
        return setting;
    }
    
    /**
     * SQLフォルダ内の全ファイルを読み込みます.
     * @param sqlDir SQLフォルダ.
     * @return 読み込んだ情報.
     * @throws IOException 読み込みに失敗した場合.
     */
    private static List<SqlSetting> loadSqlDir(File sqlDir)
        throws IOException {
        assert (sqlDir != null);
        assert (sqlDir.isDirectory());
        
        List<SqlSetting> sqlSettings = new ArrayList<SqlSetting>();
        
        for(File file : sqlDir.listFiles()) {
            if(file.isFile()) {
                sqlSettings.add(
                    new SqlSetting(file.getName(), loadSqlFile(file))
                );
            }
        }
        
        return sqlSettings;
    }
    
    /**
     * SQLファイルを読み込みます.
     * @param sqlFile SQLファイル
     * @return 読み込んだSQL.
     * @throws IOException 読み込みに失敗した場合.
     */
    private static String loadSqlFile(File sqlFile)
        throws IOException {
        assert (sqlFile != null);
        assert (sqlFile.isFile());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFile))) {
            String line = reader.readLine();
            String sql = "";
            while(line != null) {
                if(sql.equals("")) {
                    sql += line;
                } else {
                    sql += " " + line;
                }
                line = reader.readLine();
            }
            return sql;
        }
        
    }
}

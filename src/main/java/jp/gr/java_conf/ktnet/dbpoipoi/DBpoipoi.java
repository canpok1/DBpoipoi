package jp.gr.java_conf.ktnet.dbpoipoi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.db.ConnectionFactory;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting.SqlSetting;
import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.db.SqlExecuter;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelWriter;
import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DBのレコードをエクセルに吐き出すツールです.
 * @author tanabe
 *
 */
public final class DBpoipoi {

    /**
     * プログラムのエントリポイント.
     * @param args 実行時引数.
     */
    public static void main(String[] args) {
        try {
            // 設定読み込み
            ArgumentAnalyzer analyzer = new ArgumentAnalyzer(args);
            DatabaseSetting dbSetting = DatabaseSetting.load(
                analyzer.getDbSetting(),
                analyzer.getSqlFolder()
            );
            
            try (Connection connection
                    = ConnectionFactory.create(dbSetting.getJdbcDriverClass(),
                                               dbSetting.getUrl(),
                                               dbSetting.getUser(),
                                               dbSetting.getPassword())) {
                new DBpoipoi().save(
                        dbSetting.getSqlSettings(),
                        connection,
                        analyzer.getOutputFile());
            }
            System.out.println("保存完了[" + analyzer.getOutputFile() + "]");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * レコードを取得して書き込みます.
     * @param sqlSetting レコード取得用のSQL情報.
     * @param sqlExecuter SQL実行オブジェクト.
     * @param writer Excelへの書き込みオブジェクト.
     * @throws SQLException 
     */
    private static void fetchRecordAndSave(
            SqlSetting sqlSetting,
            SqlExecuter sqlExecuter,
            ExcelWriter writer
    ) throws SQLException {
        boolean gettingIsSuccess = false;
        boolean saveIsSuccess = false;
        try {
            RecordContainer records = sqlExecuter.select(sqlSetting.getSql());
            gettingIsSuccess = true;
            writer.addRecords(sqlSetting.getName(), sqlSetting.getSql(), records);
            saveIsSuccess = true;
        } finally {
            if(!gettingIsSuccess) {
                System.out.println("[×]" + sqlSetting.getName() + "…レコード取得に失敗");
            } else if(!saveIsSuccess) {
                System.out.println("[×]" + sqlSetting.getName() + "…Excelへの書き込みに失敗");
            } else {
                System.out.println("[○]" + sqlSetting.getName() + "…成功");
            }
        }
    }
    
    /**
     * コンストラクタ.
     */
    public DBpoipoi() { }
    
    /**
     * DBから情報を取得してファイルに保存します.
     * @param sqlSettings 情報取得用のSQL
     * @param connection DB接続
     * @param outputFile 出力先ファイル
     * @throws IOException 入出力エラーが発生した場合
     * @throws SQLException SQL実行時エラーが発生した場合
     */
    public void save(
        List<SqlSetting> sqlSettings,
        Connection connection,
        String outputFile)
        throws IOException, SQLException {
        
        ArgumentCheckUtil.checkNotNull(sqlSettings);
        ArgumentCheckUtil.checkNotNull(connection);
        ArgumentCheckUtil.checkNotNullAndEmpty(outputFile);
        
        ExcelWriter writer = new ExcelWriter(outputFile);
        SqlExecuter sqlExecuter = new SqlExecuter(connection);
        
        for(SqlSetting sqlSetting : sqlSettings) {
            
            fetchRecordAndSave(sqlSetting, sqlExecuter, writer);
            
        }
        writer.write();
        
    }
    
}

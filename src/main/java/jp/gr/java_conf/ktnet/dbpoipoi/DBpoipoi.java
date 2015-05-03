package jp.gr.java_conf.ktnet.dbpoipoi;

import java.sql.Connection;
import java.sql.SQLException;

import jp.gr.java_conf.ktnet.dbpoipoi.db.ConnectionFactory;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting.SqlSetting;
import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.db.SqlExecuter;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelSetting;
import jp.gr.java_conf.ktnet.dbpoipoi.excel.ExcelWriter;

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
            DatabaseSetting dbSetting = DatabaseSetting.load("abc");
            ExcelSetting excelSetting = ExcelSetting.load("abc");
            
            ExcelWriter writer = new ExcelWriter(excelSetting.getFilePath());
            try (Connection connection
                    = ConnectionFactory.create(dbSetting.getType(),
                                               dbSetting.getUrl(),
                                               dbSetting.getUser(),
                                               dbSetting.getPassword())) {
                
                SqlExecuter sqlExecuter = new SqlExecuter(connection);
                
                for(SqlSetting sqlSetting : dbSetting.getSqlSettings()) {
                    
                    fetchRecordAndSave(sqlSetting, sqlExecuter, writer);
                    
                }
                
            }
            writer.write();
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
            RecordContainer records = sqlExecuter.select(sqlSetting.sql);
            gettingIsSuccess = true;
            writer.addRecords(sqlSetting.name, sqlSetting.sql, records);
            saveIsSuccess = true;
        } finally {
            if(!gettingIsSuccess) {
                System.out.println("[×]" + sqlSetting.name + "…レコード取得に失敗");
            } else if(!saveIsSuccess) {
                System.out.println("[×]" + sqlSetting.name + "…Excelへの書き込みに失敗");
            } else {
                System.out.println("[○]" + sqlSetting.name + "…成功");
            }
        }
    }
    
    /**
     * コンストラクタ(使用不可).
     */
    private DBpoipoi() { }
}

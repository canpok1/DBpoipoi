package jp.gr.java_conf.ktnet.dbpoipoi;

import java.sql.Connection;

import jp.gr.java_conf.ktnet.dbpoipoi.db.ConnectionFactory;
import jp.gr.java_conf.ktnet.dbpoipoi.db.DatabaseSetting;
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
            DatabaseSetting dbSetting = DatabaseSetting.load(null);
            ExcelSetting excelSetting = ExcelSetting.load(null);
            
            // DBから情報取得
            RecordContainer records = null;
            try (Connection connection
                    = ConnectionFactory.create(dbSetting.getType(),
                                               dbSetting.getUrl(),
                                               dbSetting.getUser(),
                                               dbSetting.getPassword())) {
                SqlExecuter executer = new SqlExecuter(connection);
                records = executer.select(null);
            } finally {
                if(records != null) {
                    System.out.println("[○]DBからの情報取得");
                } else {
                    System.out.println("[×]DBからの情報取得");
                }
            }
            
            // Excelへ書き込み
            boolean writeSuccess = false;
            try {
                ExcelWriter writer = new ExcelWriter();
                writer.write(records, excelSetting.getFilePath());
            } finally {
                if(writeSuccess) {
                    System.out.println("[○]Excelへの書き込み");
                } else {
                    System.out.println("[×]Excelへの書き込み");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * コンストラクタ(使用不可).
     */
    private DBpoipoi() { }
}

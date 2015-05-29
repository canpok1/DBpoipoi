package jp.gr.java_conf.ktnet.dbpoipoi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * 実行時引数を解析するクラスです.
 * @author tanabe
 *
 */
public class ArgumentAnalyzer {

    /**
     * SQLフォルダの指定オプション.
     */
    private static final String SQL_DIR_OPTION = "-sql";
    /**
     * SQLフォルダのデフォルト値.
     */
    private static final String SQL_DIR_DEFAULT = "../conf/sql";
    
    /**
     * DB設定ファイルの指定オプション.
     */
    private static final String DB_SETTING_OPTION = "-db";
    /**
     * DB設定ファイルのデフォルト値.
     */
    private static final String DB_SETTING_DEFAULT = "../conf/DBpoipoi.ini";
    
    /**
     * 出力ファイルの指定オプション.
     */
    private static final String OUTPUT_FILE_OPTION = "-o";
    /**
     * 出力ファイルの出力フォルダのデフォルト値.
     * ファイル名は現在日時となります.
     */
    private static final String OUTPUT_DIR_DEFAULT = "../save";
    
    /**
     * SQLファイルが入ったフォルダ.
     */
    private String sqlFolder;
    
    /**
     * 出力先ファイル.
     */
    private String outputFile;
    
    /**
     * DB設定ファイル.
     */
    private String dbSetting;
    
    /**
     * コンストラクタ.
     * @param args 実行時引数.
     */
    public ArgumentAnalyzer(String[] args) {
        
        ArgumentCheckUtil.checkNotNull(args);
        
        outputFile = fetchValue(args, OUTPUT_FILE_OPTION, makeOutputFile(OUTPUT_DIR_DEFAULT));
        sqlFolder = fetchValue(args, SQL_DIR_OPTION, SQL_DIR_DEFAULT);
        dbSetting = fetchValue(args, DB_SETTING_OPTION, DB_SETTING_DEFAULT);
        
    }

    /**
     * 引数から適切な値を取得します.
     * @param args 引数.
     * @param option オプション文字列.
     * @param defValue 規定値.
     * @return 対象の引数.対象が取得できない場合は規定値.
     */
    private static String fetchValue(String[] args, String option, String defValue) {
        for(int index = 0; index < args.length - 1; index++) {
            if(args[index].equals(option)) {
                return args[index + 1];
            }
        }
        return defValue;
    }
    
    /**
     * 出力先ファイルパスを組み立てます.
     * @param outputDir 出力先ディレクトリパス
     * @return 出力先ファイルパス
     */
    private static String makeOutputFile(String outputDir) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmmss");
        String fileName = format.format(new Date()) + ".xls";
        return new File(outputDir, fileName).getPath();
    }
    
    /**
     * SQLフォルダを取得します.
     * @return sqlFolder
     */
    public String getSqlFolder() {
        return sqlFolder;
    }

    /**
     * 出力ファイルのパスを取得します.
     * @return outputFile
     */
    public String getOutputFile() {
        return outputFile;
    }
    
    /**
     * DB設定ファイルのパスを取得します.
     * @return dbSetting
     */
    public String getDbSetting() {
        return dbSetting;
    }
}

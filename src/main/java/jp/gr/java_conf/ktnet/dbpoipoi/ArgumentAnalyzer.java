package jp.gr.java_conf.ktnet.dbpoipoi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.NonNull;

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
    @Getter
    private String sqlFolder;
    
    /**
     * 出力先ファイル.
     */
    @Getter
    private String outputFile;
    
    /**
     * DB設定ファイル.
     */
    @Getter
    private String dbSetting;
    
    /**
     * コンストラクタ.
     * @param args 実行時引数.
     */
    public ArgumentAnalyzer(@NonNull String[] args) {
        outputFile = fetchValue(args, OUTPUT_FILE_OPTION, null);
        sqlFolder = fetchValue(args, SQL_DIR_OPTION, null);
        dbSetting = fetchValue(args, DB_SETTING_OPTION, null);
    }
    
    /**
     * 必須値が全て指定されているかを判定します.
     * @return 必須値が全て設定されていればtrue, 足りないものがあればfalse.
     */
    public boolean validate() {
        if (outputFile == null) {
            return false;
        }
        if (sqlFolder == null) {
            return false;
        }
        if (dbSetting == null) {
            return false;
        }
        return true;
    }
    
    /**
     * 使い方の説明を取得します.
     * @return 説明.
     */
    public String getManual() {
        return "使い方" + System.lineSeparator()
                + "SQLの実行結果をファイルに出力します。" + System.lineSeparator()
                + "オプション" + System.lineSeparator()
                + SQL_DIR_OPTION + " : SQLフォルダ" + System.lineSeparator()
                + DB_SETTING_OPTION + " : DB設定ファイルパス" + System.lineSeparator()
                + OUTPUT_FILE_OPTION + " : 出力先ファイルパス";
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

}

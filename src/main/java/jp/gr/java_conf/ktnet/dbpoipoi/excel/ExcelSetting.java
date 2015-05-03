package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * Excel関連の情報を保持するクラスです.
 * @author tanabe
 *
 */
public final class ExcelSetting {
    
    /**
     * コンストラクタ.
     */
    private ExcelSetting() {
    }
    
    /**
     * ファイルパス.
     */
    private String filePath;
    
    /**
     * ファイルパスを取得します.
     * @return ファイルパス.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 設定ファイルから情報を取得します.
     * @param filePath 設定ファイルパス(Null不可).
     * @return 取得した情報.
     */
    public static ExcelSetting load(String filePath) {
        ArgumentCheckUtil.checkNotNullAndEmpty(filePath);
        
        ExcelSetting setting = new ExcelSetting();
        
        // TODO テスト用
        setting.filePath = "./sample.xls";
        
        return setting;
    }
}

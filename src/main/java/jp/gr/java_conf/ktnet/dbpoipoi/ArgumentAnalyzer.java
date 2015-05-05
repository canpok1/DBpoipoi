package jp.gr.java_conf.ktnet.dbpoipoi;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * 実行時引数を解析するクラスです.
 * @author tanabe
 *
 */
public class ArgumentAnalyzer {

    /**
     * SQLファイルが入ったフォルダ.
     */
    private String sqlFolder;
    
    /**
     * 出力先ファイル.
     */
    private String outputFile;
    
    /**
     * コンストラクタ.
     * @param args 実行時引数.
     */
    public ArgumentAnalyzer(String[] args) {
        ArgumentCheckUtil.checkNotNull(args);
        if(args.length < 2) {
            throw new IllegalArgumentException("引数が不足");
        }
        
        sqlFolder = args[0];
        outputFile = args[1];
        
    }

    /**
     * SQLフォルダを取得します.
     * @return sqlFolder
     */
    public String getSqlFolder() {
        return sqlFolder;
    }

    /**
     * 出力ファイルを取得します.
     * @return outputFile
     */
    public String getOutputFile() {
        return outputFile;
    }
}

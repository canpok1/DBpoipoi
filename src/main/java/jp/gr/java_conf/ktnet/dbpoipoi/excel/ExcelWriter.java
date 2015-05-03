package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * エクセルへの書き込みを行うクラスです.
 * @author tanabe
 *
 */
public class ExcelWriter {

    /**
     * DBのレコードを書き込みます.
     * @param records レコード.
     * @param filePath 書き込み先ファイルのパス
     */
    public void write(RecordContainer records, String filePath) {
        ArgumentCheckUtil.checkNotNull(records);
        ArgumentCheckUtil.checkNotNullAndEmpty(filePath);
        // TODO 実装
        throw new UnsupportedOperationException("未実装");
    }
}

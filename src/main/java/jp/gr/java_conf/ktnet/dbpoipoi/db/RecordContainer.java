package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DBのレコードを格納するクラスです.
 * @author tanabe
 *
 */
public class RecordContainer {

    private String[] columnNames;
    
    private String[][] records;

    /**
     * カラム名を取得します.
     * @return columnNames カラム名.
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * レコードを取得します.
     * @return records レコード.
     */
    public String[][] getRecords() {
        return records;
    }
    
    /**
     * レコードを取得します.
     * @param index レコードのインデックス.
     * @return records レコード.
     */
    public String[] getRecords(int index) {
        return records[index];
    }
    
    /**
     * コンストラクタ.
     * @param columnNames カラム名.
     * @param records レコード.
     */
    public RecordContainer(String[] columnNames, String[][] records) {
        ArgumentCheckUtil.checkNotNull(columnNames);
        ArgumentCheckUtil.checkNotNull(records);
        
        int recordIndex = 1;
        for(String[] record : records) {
            if(record.length != columnNames.length) {
                throw new IllegalArgumentException(
                        "レコードのサイズ違反"
                        + "[" + recordIndex + "行目]"
                        + "[列名数=" + columnNames.length + "]"
                        + "[列数=" + record.length + "]"
                );
            }
            recordIndex++;
        }
        
        this.columnNames = columnNames;
        this.records = records;
    }
}

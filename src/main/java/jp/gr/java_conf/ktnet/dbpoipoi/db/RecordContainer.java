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
    
    private ColumnType[] columnTypes;
    
    private String[][] records;

    /**
     * カラム名を取得します.
     * @return columnNames
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * カラムの型名を取得します.
     * @return columnTypes
     */
    public ColumnType[] getColumnType() {
        return columnTypes;
    }

    /**
     * レコードを取得します.
     * @return records
     */
    public String[][] getRecords() {
        return records;
    }
    
    /**
     * レコードを取得します.
     * @param index レコードのインデックス.
     * @return records
     */
    public String[] getRecords(int index) {
        return records[index];
    }
    
    /**
     * コンストラクタ.
     * @param columnNames カラム名.
     * @param columnTypes カラム型.
     * @param records レコード.
     */
    public RecordContainer(String[] columnNames, ColumnType[] columnTypes, String[][] records) {
        ArgumentCheckUtil.checkNotNull(columnNames);
        ArgumentCheckUtil.checkNotNull(columnTypes);
        ArgumentCheckUtil.checkNotNull(records);
        
        if(columnNames.length != columnTypes.length) {
            throw new IllegalArgumentException(
                    "カラム型のサイズ違反"
                    + "[列名数=" + columnNames.length + "]"
                    + "[型数=" + columnTypes.length + "]");
        }
        
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
        this.columnTypes = columnTypes;
        this.records = records;
    }
}

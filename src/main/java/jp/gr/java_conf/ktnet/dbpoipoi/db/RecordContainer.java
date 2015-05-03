package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * DBのレコードを格納するクラスです.
 * @author tanabe
 *
 */
public class RecordContainer {

    /**
     * カラム名.
     */
    private List<String> columnNames;
    
    /**
     * カラムの型.
     */
    private List<Integer> columnTypes;
    
    /**
     * レコード.
     */
    private List<List<Object>> records;

    /**
     * カラム名を取得します.
     * @return columnNames
     */
    public List<String> getColumnNames() {
        return columnNames;
    }

    /**
     * カラムの型名を取得します.
     * @return columnTypes
     */
    public List<Integer> getColumnType() {
        return columnTypes;
    }

    /**
     * レコードを取得します.
     * @return records
     */
    public List<List<Object>> getRecords() {
        return records;
    }
    
    /**
     * レコードを取得します.
     * @param index レコードのインデックス.
     * @return records
     */
    public List<Object> getRecords(int index) {
        return records.get(index);
    }
    
    /**
     * コンストラクタ.
     * @param columnNames カラム名.
     * @param columnTypes カラム型.
     * @param records レコード.
     */
    public RecordContainer(
            List<String> columnNames,
            List<Integer> columnTypes,
            List<List<Object>> records) {
        ArgumentCheckUtil.checkNotNull(columnNames);
        ArgumentCheckUtil.checkNotNull(columnTypes);
        ArgumentCheckUtil.checkNotNull(records);
        
        if(columnNames.size() != columnTypes.size()) {
            throw new IllegalArgumentException(
                    "カラム型のサイズ違反"
                    + "[列名数=" + columnNames.size() + "]"
                    + "[型数=" + columnTypes.size() + "]");
        }
        
        int recordIndex = 1;
        for(List<Object> record : records) {
            if(record.size() != columnNames.size()) {
                throw new IllegalArgumentException(
                        "レコードのサイズ違反"
                        + "[" + recordIndex + "行目]"
                        + "[列名数=" + columnNames.size() + "]"
                        + "[列数=" + record.size() + "]"
                );
            }
            recordIndex++;
        }
        
        this.columnNames = columnNames;
        this.columnTypes = columnTypes;
        this.records = records;
    }
}

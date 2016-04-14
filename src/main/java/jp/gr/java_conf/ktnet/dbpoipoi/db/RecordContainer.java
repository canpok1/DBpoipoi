package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;

/**
 * DBのレコードを格納するクラスです.
 * @author tanabe
 *
 */
public class RecordContainer {

    /**
     * カラム名.
     */
    @Getter
    private List<String> columnNames;
    
    /**
     * カラムの型.
     * java.sql.Typesに定義されている値を想定.
     */
    @Getter
    private List<Integer> columnTypes;
    
    /**
     * レコード.
     */
    @Getter
    private List<List<Object>> records;

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
            @NonNull List<String> columnNames,
            @NonNull List<Integer> columnTypes,
            @NonNull List<List<Object>> records) {
        
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

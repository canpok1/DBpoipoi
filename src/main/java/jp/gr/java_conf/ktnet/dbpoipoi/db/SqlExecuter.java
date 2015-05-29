package jp.gr.java_conf.ktnet.dbpoipoi.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * SQLを実行するクラスです.
 * @author tanabe
 *
 */
public class SqlExecuter {
    
    /**
     * DBへの接続.
     */
    private final Connection connection;
    
    /**
     * コンストラクタ.
     * @param connection DBへの接続.
     */
    public SqlExecuter(Connection connection) {
        ArgumentCheckUtil.checkNotNull(connection);
        this.connection = connection;
    }

    /**
     * SQLを実行します.
     * @param sql 実行するSQL.
     * @return 実行結果.
     * @throws SQLException 実行に失敗した場合
     */
    public RecordContainer select(String sql) throws SQLException {

        ArgumentCheckUtil.checkNotNull(sql);
        
        if(!sql.toUpperCase().matches("SELECT .*")) {
            throw new IllegalArgumentException("SELECT文以外は指定不可[" + sql + "]");
        }
        
        try {
                
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            // カラム名
            ArrayList<String> columnNames = new ArrayList<String>();
            int columnCount = rs.getMetaData().getColumnCount();
            for(int col = 1; col <= columnCount; col++) {
                columnNames.add(rs.getMetaData().getColumnName(col));
            }
            
            // カラム型
            ArrayList<Integer> columnTypes = new ArrayList<Integer>();
            for(int col = 1; col <= columnCount; col++) {
                columnTypes.add(rs.getMetaData().getColumnType(col));
            }
            
            // レコード
            ArrayList<List<Object>> records = new ArrayList<List<Object>>();
            while(rs.next()) {
                List<Object> record = new ArrayList<Object>();
                for(int col = 1; col <= columnCount; col++) {
                    record.add(getValue(rs, col, columnTypes.get(col - 1)));
                }
                records.add(record);
            }
            
            return new RecordContainer(columnNames, columnTypes, records);
        } catch(SQLException e) {
            throw new SQLException("SQL実行エラー[" + sql + "]", e);
        }
    }
    
    /**
     * SQLの実行結果を指定の型に合わせて取得します.
     * @param resultSet 実行結果オブジェクト
     * @param column カラム番号
     * @param type 型
     * @return 実行結果
     * @throws SQLException 取得に失敗した場合.
     */
    private Object getValue(ResultSet resultSet, int column, int type)
        throws SQLException {
        if(type == Types.INTEGER) {
            return (Object)(resultSet.getInt(column));
        }
        if(type == Types.DATE) {
            return (Object)(resultSet.getDate(column));
        }
        
        return resultSet.getString(column);
    }
}

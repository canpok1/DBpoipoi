package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;

/**
 * エクセルへの書き込みを行うクラスです.
 * @author tanabe
 *
 */
public class ExcelWriter {

    /**
     * ワークブック.
     */
    private Workbook workbook;
    
    /**
     * 書き込み先ファイルパス.
     */
    private String filePath;
    
    /**
     * コンストラクタ.
     * @param filePath エクセルのパス(Null不可).
     */
    public ExcelWriter(String filePath) {
        ArgumentCheckUtil.checkNotNullAndEmpty(filePath);
        workbook = new XSSFWorkbook();
        this.filePath = filePath;
    }
    /**
     * DBのレコードを書き込みます.
     * @param name 識別名(Null不可).
     * @param memo メモ(Null不可).
     * @param records レコード(Null不可).
     */
    public void addRecords(String name, String memo, RecordContainer records) {
        ArgumentCheckUtil.checkNotNull(name);
        ArgumentCheckUtil.checkNotNull(memo);
        ArgumentCheckUtil.checkNotNull(records);
        
        Sheet sheet;
        if(name == "") {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(name);
        }
        
        int rowIndex = 0;
        
        if(!memo.equals("")) {
            List<Object> memoList = new ArrayList<Object>();
            memoList.add(memo);
            addRecord(sheet, rowIndex++, memoList);
        }
        
        for(List<Object> record : records.getRecords()) {
            addRecord(sheet, rowIndex++, record);
        }
    }
    
    /**
     * レコード情報を追加します.
     * @param sheet 書き込み先シート.
     * @param rowIndex 書き込み先行数.
     * @param record レコード.
     */
    private void addRecord(Sheet sheet, int rowIndex, List<Object> record) {
        Row row = sheet.createRow(rowIndex);
        
        int column = 0;
        for(Object value : record) {
            Cell cell = row.createCell(column);
            cell.setCellValue(value.toString());
            column++;
        }
    }
    
    /**
     * エクセルに書き込みます.
     * @exception IOException ファイル書き込みに失敗した場合.
     */
    public void write() throws IOException {
        try (OutputStream os = new FileOutputStream(filePath)) {
            workbook.write(os);
        }
    }
}

package jp.gr.java_conf.ktnet.dbpoipoi.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jp.gr.java_conf.ktnet.dbpoipoi.db.RecordContainer;
import jp.gr.java_conf.ktnet.dbpoipoi.util.ArgumentCheckUtil;
import lombok.NonNull;

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
    public ExcelWriter(@NonNull String filePath) {
        ArgumentCheckUtil.checkNotEmpty(filePath);
        workbook = new XSSFWorkbook();
        this.filePath = filePath;
    }
    /**
     * DBのレコードを書き込みます.
     * @param name 識別名(Null不可).
     * @param memo メモ(Null不可).
     * @param records レコード(Null不可).
     */
    public void addRecords(
            @NonNull String name,
            @NonNull String memo,
            @NonNull RecordContainer records) {
        
        Sheet sheet;
        if(name == "") {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(name);
        }
        
        int rowIndex = 0;
        
        // メモ行
        if(!memo.equals("")) {
            List<Object> memoList = new ArrayList<Object>();
            memoList.add(memo);
            addRecord(sheet, rowIndex, memoList);
            
            // メモ行のセルは連結させる
            CellRangeAddress range = new CellRangeAddress(
                rowIndex,   // 最初の行
                rowIndex,   // 最後の行
                0,  // 最初の列
                records.getColumnNames().size() - 1 // 最後の列
            );
            sheet.addMergedRegion(range);
            
            rowIndex++;
        }
        
        // カラム名行
        List<Object> nameList = new ArrayList<Object>();
        nameList.addAll(records.getColumnNames());
        addRecord(sheet, rowIndex++, nameList, makeColumnNameStyle(workbook));
        
        // レコード
        for(List<Object> record : records.getRecords()) {
            addRecord(sheet, rowIndex++, record, makeRecordStyle(workbook));
        }
        
        // 列幅を調整
        for(int row = sheet.getFirstRowNum(); row <= sheet.getLastRowNum(); row++) {
            sheet.autoSizeColumn(row);
        }
    }
    
    /**
     * レコード情報を追加します.
     * @param sheet 書き込み先シート.
     * @param rowIndex 書き込み先行数.
     * @param record レコード.
     */
    private void addRecord(Sheet sheet, int rowIndex, List<Object> record) {
        addRecord(sheet, rowIndex, record, null);
    }
    
    /**
     * レコード情報を追加します.
     * @param sheet 書き込み先シート.
     * @param rowIndex 書き込み先行数.
     * @param record レコード.
     * @param style セルのスタイル.
     */
    private void addRecord(Sheet sheet, int rowIndex, List<Object> record, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        
        int column = 0;
        for(Object value : record) {
            Cell cell = row.createCell(column);
            if(value != null) {
                cell.setCellValue(value.toString());
            }
            if(style != null) {
                cell.setCellStyle(style);
            }
            column++;
        }
    }
    
    /**
     * エクセルに書き込みます.
     * @exception IOException ファイル書き込みに失敗した場合.
     */
    public void write() throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        
        if(!parent.exists()) {
            parent.mkdirs();
        }
        
        try (OutputStream os = new FileOutputStream(filePath)) {
            workbook.write(os);
        }
    }
    
    /**
     * カラム名向けのセルスタイルを生成します.
     * @param workbook ワークブック
     * @return セルスタイル
     */
    private static CellStyle makeColumnNameStyle(Workbook workbook) {
        
        CellStyle cellStyle = workbook.createCellStyle();
        
        // 前景色
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        // 枠線
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        
        return cellStyle;
        
    }
    
    /**
     * レコード行向けのセルスタイルを生成します.
     * @param workbook ワークブック
     * @return セルスタイル
     */
    private static CellStyle makeRecordStyle(Workbook workbook) {
        
        CellStyle cellStyle = workbook.createCellStyle();
        
        // 枠線
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
        
        return cellStyle;
        
    }
}

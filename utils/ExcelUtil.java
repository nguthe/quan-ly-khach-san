
package Hotel.utils;


import Hotel.dao.HoaDonThanhToanDAO;
import Hotel.dao.ThongKeDAO;
import Hotel.entity.HoaDonThanhToan;
import Hotel.ui.JpanelThongKe;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
/**
 *
 * @author Phạm Thị Thuý Huyền
 */
public class ExcelUtil {
public static Workbook printDoanhThuphongToExcel(javax.swing.JTable tblPhong, 
        javax.swing.JComboBox<String> cboNgayNam, ThongKeDAO tkDAO) throws FileNotFoundException, IOException {

    HSSFWorkbook workbook = new HSSFWorkbook();
    String sheetName = "Thống kê " + ((String) cboNgayNam.getSelectedItem()).replace("/", "-");
    HSSFSheet sheet = workbook.createSheet(sheetName);
     HoaDonThanhToanDAO dao = new HoaDonThanhToanDAO();
    String selectedMonthYear = (String) cboNgayNam.getSelectedItem();

// Split the month and year from the combined string
String[] parts = selectedMonthYear.split("/");
int thang = Integer.parseInt(parts[0]);
int nam = Integer.parseInt(parts[1]);

List<Object[]> list = tkDAO.getDoanhThuPhong(thang, nam);
    int rownum = 0;
    Cell cell = null;
    Row row = null;

    HSSFCellStyle style = createStyleForTitle(workbook);

    // Header row
    row = sheet.createRow(rownum);

    cell = row.createCell(0, CellType.NUMERIC);
    cell.setCellValue("STT");
    cell.setCellStyle(style);

    cell = row.createCell(1, CellType.STRING);
    cell.setCellValue("Mã phòng");
    cell.setCellStyle(style);

    cell = row.createCell(2, CellType.NUMERIC);
    cell.setCellValue("Phụ Thu");
    cell.setCellStyle(style);

    cell = row.createCell(3, CellType.NUMERIC);
    cell.setCellValue("Thành Tiền");
    cell.setCellStyle(style);

    // Data rows
    for (int i = 0; i < list.size(); i++) {
    System.out.println("Processing row " + (i + 1));
    rownum++;
    row = sheet.createRow(rownum);

    cell = row.createCell(0, CellType.NUMERIC);
    cell.setCellValue(String.valueOf(tblPhong.getValueAt(i, 0)));

    cell = row.createCell(1, CellType.STRING);
    cell.setCellValue(String.valueOf(tblPhong.getValueAt(i, 2)));

    cell = row.createCell(2, CellType.NUMERIC);
   cell.setCellValue((Double)tblPhong.getValueAt(i, 2)); 

    cell = row.createCell(3, CellType.NUMERIC);
    cell.setCellValue((Double)tblPhong.getValueAt(i, 3));
}
    return workbook;

}

public static Workbook printDoanhThuDVToExcel(javax.swing.JTable tblDichVu,
        javax.swing.JComboBox<String> cboNgayNam, ThongKeDAO tkDAO) throws FileNotFoundException, IOException {
    
        HSSFWorkbook workbook = new HSSFWorkbook();
        String sheetName2 = "Thống kê " + ((String) cboNgayNam.getSelectedItem()).replace("/", "-");
        HSSFSheet sheet = workbook.createSheet(sheetName2);
         HoaDonThanhToanDAO dao = new HoaDonThanhToanDAO();
    String selectedMonthYear = (String) cboNgayNam.getSelectedItem();

// Split the month and year from the combined string
String[] parts = selectedMonthYear.split("/");
int thang = Integer.parseInt(parts[0]);
int nam = Integer.parseInt(parts[1]);

List<Object[]> list = tkDAO.getDoanhThuPhong(thang, nam);
        
        int rownum = 0; 
        Cell cell; 
        Row row;
        
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        cell = row.createCell(0,CellType.NUMERIC);
        cell.setCellValue("STT");
        cell.setCellStyle(style);

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã dịch vụ");
        cell.setCellStyle(style);
        
        cell = row.createCell(2,CellType.STRING);
        cell.setCellValue("Tên dịch vụ");
        cell.setCellStyle(style);
        
        cell = row.createCell(3,CellType.NUMERIC);
        cell.setCellValue("Số Lượng");
        cell.setCellStyle(style);
        
        cell = row.createCell(4,CellType.NUMERIC);
        cell.setCellValue("Đơn giá");
        cell.setCellStyle(style);
        
         cell = row.createCell(5,CellType.NUMERIC);
        cell.setCellValue("Thành tiền");
        cell.setCellStyle(style);
        
        //DATA 
        for (int i = 0; i < list.size(); i++) {
    rownum++;
    row = sheet.createRow(rownum);

    cell = row.createCell(0, CellType.NUMERIC);
   cell.setCellValue(String.valueOf(tblDichVu.getValueAt(i, 0)));

    cell = row.createCell(1, CellType.STRING);
    cell.setCellValue(String.valueOf(tblDichVu.getValueAt(i, 1)));
    
    cell = row.createCell(2, CellType.STRING);
    cell.setCellValue(String.valueOf(tblDichVu.getValueAt(i, 2)));

    cell = row.createCell(3, CellType.NUMERIC);
    cell.setCellValue((Integer)tblDichVu.getValueAt(i, 3)); 

    cell = row.createCell(4, CellType.NUMERIC);
    cell.setCellValue((Double)tblDichVu.getValueAt(i, 4)); 

    cell = row.createCell(5, CellType.NUMERIC);
    cell.setCellValue((Double)tblDichVu.getValueAt(i, 5)); 
        } 
    return workbook;
}
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}

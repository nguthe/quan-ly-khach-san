package Hotel.dao;

import Hotel.entity.HoaDonChiTiet;
import Hotel.utils.JdbcHelPer;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HoaDonChiTietDAOTest {
    private HoaDonChiTietDAO dao;

    @Before
    public void setUp() {
        dao = new HoaDonChiTietDAO();
        // Tạo sẵn dữ liệu HoaDonThanhToan nếu chưa có (tránh lỗi khóa ngoại)
        JdbcHelPer.executeUpdate("INSERT INTO HoaDonThanhToan (MaHD, MaDP, MaNV, MaKH, NgayBD, ThanhTien, NgayLap) VALUES (?, ?, ?, ?, ?, ?, ?)",
                "HDTEST", 1, "NV01", "KH001", "2024-05-01", 500000.0, "2024-05-02"
        );
    }

    @After
    public void tearDown() {
        // Xóa dữ liệu test tránh trùng lặp
        dao.delete("HDTEST");
        JdbcHelPer.executeUpdate("DELETE FROM HoaDonThanhToan WHERE MaHD = ?", "HDTEST");
    }

    @Test
    public void testInsert_HoaDonChiTiet() {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setMaHD("HDTEST");
        hdct.setMaDV("DV01");
        hdct.setNgaythue("2024-05-01");
        hdct.setPhuThu(10000.0f);

        dao.insert(hdct);

        HoaDonChiTiet result = dao.findById("HDTEST");
        assertNotNull(result);
        assertEquals("DV01", result.getMaDV());
    }

    @Test
    public void testFindById_HoaDonChiTiet() {
        // chèn dữ liệu trước
        JdbcHelPer.executeUpdate("INSERT INTO HoaDonChiTiet (MaHD, MaDV, Ngaythue, PhuThu) VALUES (?, ?, ?, ?)",
                "HDTEST", "DV02", "2024-05-02", 20000.0f);

        HoaDonChiTiet result = dao.findById("HDTEST");
        assertNotNull(result);
        assertEquals("DV02", result.getMaDV());
    }

    @Test
    public void testDelete_HoaDonChiTiet() {
        JdbcHelPer.executeUpdate("INSERT INTO HoaDonChiTiet (MaHD, MaDV, Ngaythue, PhuThu) VALUES (?, ?, ?, ?)",
                "HDTEST", "DV03", "2024-05-03", 30000.0f);

        dao.delete("HDTEST");

        HoaDonChiTiet deleted = dao.findById("HDTEST");
        assertNull(deleted);
    }

    @Test
    public void testSelectAll_HoaDonChiTiet() {
        List<HoaDonChiTiet> list = dao.select();
        assertNotNull(list);
    }
}

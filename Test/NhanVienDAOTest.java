package Hotel.dao;

import Hotel.entity.NhanVien;
import Hotel.utils.JdbcHelPer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NhanVienDAOTest {

    private NhanVienDAO dao;

    @Before
    public void setUp() {
        dao = new NhanVienDAO();

        // Thêm nhân viên test để phục vụ các test sau
        JdbcHelPer.executeUpdate("INSERT INTO NhanVien (MaNV, TenNV, SoDT, ChucVu, MatKhau, Email, GioiTinh, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                "NVTEST", "Nguyen Van Test", "0909090909", true, "123456", "test@example.com", true, "test.jpg");
    }

    @After
    public void tearDown() {
        // Xóa nhân viên test tránh dữ liệu rác
        JdbcHelPer.executeUpdate("DELETE FROM NhanVien WHERE MaNV = ?", "NVTEST");
        JdbcHelPer.executeUpdate("DELETE FROM NhanVien WHERE MaNV = ?", "NVNEW");
    }

    @Test
    public void testInsert() {
        NhanVien nv = new NhanVien();
        nv.setMaNV("NVNEW");
        nv.setTenNV("Tran Thi New");
        nv.setSoDT("0912345678");
        nv.setChucVu(false);
        nv.setMatKhau("password");
        nv.setEmail("new@example.com");
        nv.setGioiTinh(false);
        nv.setImg("new.jpg");

        dao.insert(nv);

        NhanVien result = dao.findById("NVNEW");
        assertNotNull(result);
        assertEquals("Tran Thi New", result.getTenNV());
        assertEquals("0912345678", result.getSoDT());
        assertFalse(result.isChucVu());
        assertEquals("password", result.getMatKhau());
    }

    @Test
    public void testUpdatePassword() {
        NhanVien nv = dao.findById("NVTEST");
        assertNotNull(nv);

        nv.setMatKhau("newpassword123");
        dao.updatePassword(nv);

        NhanVien updated = dao.findById("NVTEST");
        assertEquals("newpassword123", updated.getMatKhau());
    }

    @Test
    public void testUpdate() {
        NhanVien nv = dao.findById("NVTEST");
        assertNotNull(nv);

        nv.setTenNV("Nguyen Van Updated");
        nv.setSoDT("0999888777");
        nv.setChucVu(false);
        nv.setMatKhau("updatedpass");
        nv.setEmail("updated@example.com");
        nv.setGioiTinh(false);
        nv.setImg("updated.jpg");

        dao.update(nv);

        NhanVien updated = dao.findById("NVTEST");
        assertEquals("Nguyen Van Updated", updated.getTenNV());
        assertEquals("0999888777", updated.getSoDT());
        assertFalse(updated.isChucVu());
        assertEquals("updatedpass", updated.getMatKhau());
        assertEquals("updated@example.com", updated.getEmail());
        assertFalse(updated.isGioiTinh());
        assertEquals("updated.jpg", updated.getImg());
    }

    @Test
    public void testDelete() {
        // Thêm nhân viên tạm để test xóa
        JdbcHelPer.executeUpdate("INSERT INTO NhanVien (MaNV, TenNV, SoDT, ChucVu, MatKhau, Email, GioiTinh, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                "NVDEL", "Delete Me", "0900000000", true, "delpass", "del@example.com", true, "del.jpg");

        dao.delete("NVDEL");
        NhanVien deleted = dao.findById("NVDEL");
        assertNull(deleted);
    }

    @Test
    public void testSelect() {
        List<NhanVien> list = dao.select();
        assertNotNull(list);
        assertTrue(list.size() > 0);
        boolean found = false;
        for (NhanVien nv : list) {
            if ("NVTEST".equals(nv.getMaNV())) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testFindById_Found() {
        NhanVien nv = dao.findById("NVTEST");
        assertNotNull(nv);
        assertEquals("Nguyen Van Test", nv.getTenNV());
    }

    @Test
    public void testFindById_NotFound() {
        NhanVien nv = dao.findById("NVNOTEXIST");
        assertNull(nv);
    }
}

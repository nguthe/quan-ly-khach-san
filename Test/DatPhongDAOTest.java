package Hotel.dao;

import Hotel.entity.DatPhong;
import Hotel.utils.JdbcHelPer;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class DatPhongDAOTest {

    private DatPhongDAO dao;
    private static final int TEST_MADP = 8888;
    private static final String TEST_MANV = "NV_TEST";
    private static final String TEST_MAKH = "KH_TEST";
    private static final String TEST_MAPHONG = "PT101";
    private static final String TEST_MAKIEUTHUE = "KT_TEST";
    private static final String TEST_NGAYDEN = "2025-05-21";
    private static final String TEST_NGAYDI = "2025-05-22";

    @Before
    public void setUp() {
        dao = new DatPhongDAO();
        // Xóa bản ghi (nếu có) với quyền IDENTITY_INSERT
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong ON");
        JdbcHelPer.executeUpdate("DELETE FROM DatPhong WHERE MaDP = ?", TEST_MADP);
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong OFF");
    }

    @After
    public void tearDown() {
        // Xóa bản ghi mẫu
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong ON");
        JdbcHelPer.executeUpdate("DELETE FROM DatPhong WHERE MaDP = ?", TEST_MADP);
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong OFF");
    }

    /** Giúp chèn với IDENTITY_INSERT ON/OFF */
    private void insertWithIdentity(DatPhong model) {
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong ON");
        dao.insert(model);
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong OFF");
    }

    @Test
    public void testInsertAndFindByMaDP() {
        DatPhong sample = new DatPhong();
        sample.setMaDP(TEST_MADP);
        sample.setNgayDen(TEST_NGAYDEN);
        sample.setNgayDi(TEST_NGAYDI);
        sample.setMaNV(TEST_MANV);
        sample.setMaKH(TEST_MAKH);
        sample.setMaPhong(TEST_MAPHONG);
        sample.setMaKieuThue(TEST_MAKIEUTHUE);

        insertWithIdentity(sample);

        DatPhong found = dao.findByMaDP(TEST_MADP);
        assertNotNull("findByMaDP phải trả về bản ghi vừa chèn", found);
        assertEquals(TEST_MADP, found.getMaDP());
        assertEquals(TEST_NGAYDEN, found.getNgayDen());
        assertEquals(TEST_NGAYDI, found.getNgayDi());
        assertEquals(TEST_MANV, found.getMaNV());
        assertEquals(TEST_MAKH, found.getMaKH());
        assertEquals(TEST_MAPHONG, found.getMaPhong());
        assertEquals(TEST_MAKIEUTHUE, found.getMaKieuThue());
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNullThrows() {
        // mặc dù chèn null, vẫn phải bật IDENTITY_INSERT
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong ON");
        dao.insert(null);
        JdbcHelPer.executeUpdate("SET IDENTITY_INSERT DatPhong OFF");
    }

    @Test
    public void testSelectContainsSample() {
        testInsertAndFindByMaDP();
        List<DatPhong> list = dao.select();
        assertNotNull("select() không được null", list);
        assertTrue("select() phải chứa MaDP vừa chèn",
                   list.stream().anyMatch(d -> d.getMaDP() == TEST_MADP));
    }

    @Test
    public void testSelectByKeywordMatch() {
        testInsertAndFindByMaDP();
        List<DatPhong> list = dao.selectByKeyword(TEST_MAKH);
        assertNotNull(list);
        assertFalse("selectByKeyword phải tìm ít nhất 1 phần tử", list.isEmpty());
        assertTrue("Mỗi phần tử phải có MaKH chứa keyword",
                   list.stream().allMatch(d -> d.getMaKH().contains(TEST_MAKH)));
    }

    @Test
    public void testSelectByKeywordNoMatch() {
        List<DatPhong> list = dao.selectByKeyword("NO_SUCH_KEYWORD");
        assertNotNull(list);
        assertTrue("Không có match thì trả về rỗng", list.isEmpty());
    }

    @Test
    public void testFindByIdInvalid() {
        assertNull("findById với MaKH không tồn tại phải trả null", dao.findById("NO_KH"));
    }

    @Test
    public void testFindByMaDPInvalid() {
        assertNull("findByMaDP(-1) phải trả null", dao.findByMaDP(-1));
    }

    @Test
    public void testFindByMaPhongValidAndInvalid() {
        testInsertAndFindByMaDP();
        List<DatPhong> ok = dao.findByMaPhong(TEST_MAPHONG);
        assertNotNull(ok);
        assertTrue("findByMaPhong phải có phần tử với MaPhong vừa chèn",
                   ok.stream().anyMatch(d -> d.getMaDP() == TEST_MADP));

        List<DatPhong> no = dao.findByMaPhong("NO_ROOM");
        assertNotNull(no);
        assertTrue("MaPhong không tồn tại thì trả rỗng", no.isEmpty());
    }

    @Test
    public void testSelectBadSqlThrows() {
        try {
            dao.selectSql("SELECT * FROM NonExistTable");
            fail("Phải ném RuntimeException khi SQL sai");
        } catch (RuntimeException ex) {
            assertTrue("Nguyên nhân phải là SQLException",
                       ex.getCause() instanceof java.sql.SQLException);
        }
    }
}

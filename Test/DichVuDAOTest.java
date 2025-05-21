package Hotel.dao;

import Hotel.entity.DichVu;
import Hotel.utils.JdbcHelPer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class DichVuDAOTest {

    private DichVuDAO dao;
    private static final String TEST_MADV   = "DV_TEST";
    private static final String TEST_TENDV  = "Dịch Vụ Test";
    private static final float  TEST_GIA    = 12345.67f;

    @Before
    public void setUp() {
        dao = new DichVuDAO();
        // Xóa nếu có sẵn
        JdbcHelPer.executeUpdate(
            "DELETE FROM DichVu WHERE MaDV = ?", TEST_MADV);
        // Chèn bản ghi mẫu
        JdbcHelPer.executeUpdate(
            "INSERT INTO DichVu(MaDV, TenDV, Gia) VALUES(?, ?, ?)",
            TEST_MADV, TEST_TENDV, TEST_GIA);
    }

    @After
    public void tearDown() {
        // Xóa bản ghi mẫu
        JdbcHelPer.executeUpdate(
            "DELETE FROM DichVu WHERE MaDV = ?", TEST_MADV);
    }

    @Test
    public void testSelectContainsSample() {
        List<DichVu> list = dao.select();
        assertNotNull("select() không được trả về null", list);
        assertTrue("select() phải chứa dịch vụ mẫu",
            list.stream().anyMatch(d -> TEST_MADV.equals(d.getMaDV())));
    }

    @Test
    public void testFindByIdValid() {
        DichVu dv = dao.findById(TEST_TENDV);
        assertNotNull("findById phải tìm được dịch vụ mẫu", dv);
        assertEquals("MaDV phải khớp", TEST_MADV, dv.getMaDV());
        assertEquals("TenDV phải khớp", TEST_TENDV, dv.getTenDV());
        assertEquals("DonGia phải khớp", TEST_GIA, dv.getDonGia(), 0.001);
    }

    @Test
    public void testFindByIdInvalid() {
        DichVu dv = dao.findById("KHONG_TON_TAI");
        assertNull("findById với TenDV không tồn tại phải trả null", dv);
    }

    @Test
    public void testFindByIdNull() {
        DichVu dv = dao.findById(null);
        assertNull("findById(null) phải trả null", dv);
    }

    @Test
    public void testSelectBadSqlThrows() throws Exception {
        // Dùng reflection để gọi private select(String,Object...)
        Method selectMethod = DichVuDAO.class
            .getDeclaredMethod("select", String.class, Object[].class);
        selectMethod.setAccessible(true);

        try {
            // invoke với SQL sai
            selectMethod.invoke(dao,
                "SELECT * FROM NonExistingTable", new Object[] { new Object[0] });
            fail("Phải ném RuntimeException khi SQL sai");
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            assertTrue("Phải là RuntimeException",
                       cause instanceof RuntimeException);
            assertTrue("Nguyên nhân phải là SQLException",
                       cause.getCause() instanceof SQLException);
        }
    }
}

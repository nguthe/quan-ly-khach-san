package Hotel.dao;

import Hotel.entity.DatPhong;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatPhongDAOTest {

    public DatPhongDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        // Thiết lập, ví dụ: kết nối đến database test nếu cần
    }

    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên, đóng kết nối nếu mở
    }

    @Before
    public void setUp() {
        // Thiết lập trước mỗi test
    }

    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần
    }

    @Test
    public void testInsert() {
        System.out.println("Testing insert method");
        DatPhong model = new DatPhong();
        // Cấu hình model nếu cần, ví dụ: model.setMaPhong("101");
        DatPhongDAO instance = new DatPhongDAO();
        try {
            instance.insert(model);
            // Có thể kiểm tra lại bằng cách gọi find để xác minh đối tượng đã được insert
        } catch(Exception e) {
            fail("Insert method threw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testSelect() {
        System.out.println("Testing select method");
        DatPhongDAO instance = new DatPhongDAO();
        List<DatPhong> result = instance.select();
        assertNotNull("Select should return a non-null list", result);
    }

    @Test
    public void testSelectByKeyword() {
        System.out.println("Testing selectByKeyword method");
        String keyword = "";
        DatPhongDAO instance = new DatPhongDAO();
        List<DatPhong> result = instance.selectByKeyword(keyword);
        assertNotNull("Select by keyword should return a non-null list", result);
    }

    @Test
    public void testFindById() {
        System.out.println("Testing findById method");
        String manv = "";
        DatPhongDAO instance = new DatPhongDAO();
        DatPhong result = instance.findById(manv);
        assertNull("findById should return null for an invalid id", result);
    }

    @Test
    public void testFindByMaDP() {
        System.out.println("Testing findByMaDP method");
        int maDP = 0;
        DatPhongDAO instance = new DatPhongDAO();
        DatPhong result = instance.findByMaDP(maDP);
        assertNull("findByMaDP should return null for non-existent id", result);
    }

    @Test
    public void testFindByMaPhong() {
        System.out.println("Testing findByMaPhong method");
        String maphong = "";
        DatPhongDAO instance = new DatPhongDAO();
        List<DatPhong> result = instance.findByMaPhong(maphong);
        assertNotNull("findByMaPhong should return a non-null list", result);
        assertTrue("Expected an empty list for an invalid room id", result.isEmpty());
    }
}
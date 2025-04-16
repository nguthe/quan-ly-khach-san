package Hotel.dao;

import Hotel.entity.DichVu;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases cho DichVuDAO.
 * 
 * Test case: testSelect()
 *  - Mô tả: Kiểm tra phương thức select() nhằm lấy danh sách các đối tượng DichVu.
 *  - Yêu cầu đầu ra: Phương thức trả về 1 List không bị null. (Danh sách có thể rỗng nếu chưa có dữ liệu)
 * 
 * Test case: testFindById()
 *  - Mô tả: Kiểm tra phương thức findById(String tenDV) với tham số không hợp lệ (ở đây dùng chuỗi rỗng).
 *  - Yêu cầu đầu ra: Phương thức trả về null khi không tìm thấy đối tượng DichVu tương ứng.
 */
public class DichVuDAOTest {

    public DichVuDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Thiết lập toàn cục, ví dụ: seed dữ liệu kiểm thử vào cơ sở dữ liệu nếu cần.
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên toàn cục nếu cần.
    }
    
    @Before
    public void setUp() {
        // Thiết lập trước mỗi test, ví dụ: reset dữ liệu kiểm thử.
    }
    
    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test.
    }
    
    /**
     * Test case: testSelect()
     * Mô tả: Kiểm tra rằng phương thức select() trả về danh sách các DichVu không bị null.
     * Yêu cầu đầu ra: Kết quả không null. (Nếu cần, có thể kiểm tra thêm kích thước của danh sách)
     */
    @Test
    public void testSelect() {
        System.out.println("Test case: testSelect() - Lấy danh sách DichVu");
        DichVuDAO instance = new DichVuDAO();
        List<DichVu> result = instance.select();
        assertNotNull("Kết quả của select() không được null", result);
        // Nếu bạn mong đợi danh sách có dữ liệu, có thể kiểm tra:
        // assertFalse("Danh sách DichVu không rỗng", result.isEmpty());
    }
    
    /**
     * Test case: testFindById()
     * Mô tả: Kiểm tra rằng phương thức findById() với tham số không hợp lệ (chuỗi rỗng) trả về null.
     * Yêu cầu đầu ra: Kết quả trả về là null.
     */
    @Test
    public void testFindById() {
        System.out.println("Test case: testFindById() - Lấy DichVu theo ID không hợp lệ");
        String tenDV = ""; // Sử dụng chuỗi rỗng để mô phỏng ID không hợp lệ.
        DichVuDAO instance = new DichVuDAO();
        DichVu result = instance.findById(tenDV);
        assertNull("Kết quả của findById() với id không hợp lệ phải là null", result);
    }
}
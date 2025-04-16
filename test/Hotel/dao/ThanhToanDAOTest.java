package Hotel.dao;

import Hotel.entity.HoaDonThanhToan;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ThanhToanDAOTest {
    
    public ThanhToanDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Thiết lập tài nguyên toàn cục nếu cần.
        // Ví dụ: seed dữ liệu vào cơ sở dữ liệu test.
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên toàn cục nếu cần.
    }
    
    @Before
    public void setUp() {
        // Thiết lập trước mỗi test (ví dụ: reset dữ liệu test).
    }
    
    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần.
    }
    
    /**
     * Test của phương thức selectYears của class ThanhToanDAO.
     */
    @Test
    public void testSelectYears() {
        System.out.println("Testing selectYears");
        ThanhToanDAO instance = new ThanhToanDAO();
        // Gọi phương thức selectYears để lấy danh sách các năm từ cơ sở dữ liệu.
        List<Integer> result = instance.selectYears();
        
        // Kiểm tra kết quả không được null.
        assertNotNull("Danh sách các năm không được null", result);
        
        // Nếu bạn mong đợi danh sách có kích thước cụ thể hoặc chứa những giá trị cố định,
        // bạn có thể thêm assertion như assertEquals hoặc assertTrue ở đây.
        // Ví dụ: assertTrue("Danh sách không được rỗng", !result.isEmpty());
    }
    
    /**
     * Test của phương thức selectHoaDons của class ThanhToanDAO.
     */
    @Test
    public void testSelectHoaDons() {
        System.out.println("Testing selectHoaDons");
        ThanhToanDAO instance = new ThanhToanDAO();
        // Gọi phương thức selectHoaDons để lấy danh sách các hóa đơn thanh toán.
        List<HoaDonThanhToan> result = instance.selectHoaDons();
        
        // Kiểm tra rằng kết quả trả về không phải là null.
        assertNotNull("Danh sách hóa đơn thanh toán không được null", result);
        
        // Nếu bạn biết rõ dữ liệu cụ thể, có thể kiểm tra kích thước hoặc nội dung của danh sách.
        // Ví dụ: assertTrue("Danh sách hóa đơn không được rỗng", result.size() > 0);
    }
}
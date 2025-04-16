package Hotel.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case cho lớp ThongKeDAO.
 *
 * Test Case 1: testGetDoanhThuPhong()
 *  - Mô tả: Kiểm tra gọi phương thức getDoanhThuPhong() với các tham số (thang = 0, nam = 0) để lấy danh sách doanh thu phòng.
 *  - Chức năng của hàm: Truy xuất danh sách doanh thu phòng từ cơ sở dữ liệu theo tháng và năm đã cho.
 *  - Yêu cầu đầu ra: Phương thức getDoanhThuPhong() trả về một List không null (có thể rỗng nếu không có dữ liệu).
 *
 * Test Case 2: testGetDoanhThuDV()
 *  - Mô tả: Kiểm tra gọi phương thức getDoanhThuDV() với các tham số (thang = 0, nam = 0) để lấy danh sách doanh thu dịch vụ.
 *  - Chức năng của hàm: Truy xuất danh sách doanh thu dịch vụ từ cơ sở dữ liệu theo tháng và năm đã cho.
 *  - Yêu cầu đầu ra: Phương thức getDoanhThuDV() trả về một List không null (có thể rỗng nếu không có dữ liệu).
 */
public class ThongKeDAOTest {

    public ThongKeDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        // Thiết lập tài nguyên toàn cục nếu cần,
        // ví dụ: seed dữ liệu vào cơ sở dữ liệu test.
    }

    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên toàn cục nếu cần.
    }

    @Before
    public void setUp() {
        // Thiết lập trước mỗi test, ví dụ: reset dữ liệu test.
    }

    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần.
    }

    /**
     * Test Case 1: testGetDoanhThuPhong()
     * Mô tả: Gọi getDoanhThuPhong() với thang = 0 và nam = 0 và kiểm tra rằng kết quả trả về không là null.
     * Yêu cầu đầu ra: Phương thức trả về một List không null (có thể empty nếu không có dữ liệu).
     */
    @Test
    public void testGetDoanhThuPhong() {
        System.out.println("Running testGetDoanhThuPhong()");
        int thang = 0;
        int nam = 0;
        ThongKeDAO instance = new ThongKeDAO();
        List result = instance.getDoanhThuPhong(thang, nam);
        assertNotNull("Danh sách doanh thu phòng không được null", result);
        // Nếu bạn mong đợi danh sách rỗng với các đầu vào không hợp lệ, có thể thêm:
        // assertTrue("Danh sách doanh thu phòng nên rỗng", result.isEmpty());
    }

    /**
     * Test Case 2: testGetDoanhThuDV()
     * Mô tả: Gọi getDoanhThuDV() với thang = 0 và nam = 0 và kiểm tra rằng kết quả trả về không là null.
     * Yêu cầu đầu ra: Phương thức trả về một List không null (có thể empty nếu không có dữ liệu).
     */
    @Test
    public void testGetDoanhThuDV() {
        System.out.println("Running testGetDoanhThuDV()");
        int thang = 0;
        int nam = 0;
        ThongKeDAO instance = new ThongKeDAO();
        List result = instance.getDoanhThuDV(thang, nam);
        assertNotNull("Danh sách doanh thu dịch vụ không được null", result);
        // Nếu bạn mong đợi danh sách trống với đầu vào không hợp lệ, có thể kiểm tra:
        // assertTrue("Danh sách doanh thu dịch vụ nên rỗng", result.isEmpty());
    }
}
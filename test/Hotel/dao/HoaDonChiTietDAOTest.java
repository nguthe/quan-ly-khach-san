package Hotel.dao;

import Hotel.entity.HoaDonChiTiet;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case cho lớp HoaDonChiTietDAO.
 *
 * Test Case 1: testInsert()
 *  - Mô tả: Kiểm tra việc chèn một record HoaDonChiTiet mới vào cơ sở dữ liệu.
 *  - Yêu cầu đầu ra: Phương thức insert() không phát sinh ngoại lệ và record được chèn thành công,
 *    tức là sau khi chèn, việc gọi findById() với ID vừa chèn sẽ trả về đối tượng không null.
 *
 * Test Case 2: testSelect()
 *  - Mô tả: Kiểm tra lấy danh sách các record HoaDonChiTiet.
 *  - Yêu cầu đầu ra: Phương thức select() trả về một List không null (có thể rỗng nếu chưa có dữ liệu).
 *
 * Test Case 3: testFindById()
 *  - Mô tả: Kiểm tra tìm kiếm một record HoaDonChiTiet theo ID được cung cấp.
 *  - Yêu cầu đầu ra: Với ID không tồn tại (ví dụ: chuỗi rỗng), kết quả trả về phải là null.
 *
 * Test Case 4: testDelete()
 *  - Mô tả: Kiểm tra việc xóa một record HoaDonChiTiet dựa trên ID.
 *  - Yêu cầu đầu ra: Sau khi xóa, việc gọi findById() với ID đó phải trả về null.
 */
public class HoaDonChiTietDAOTest {

    public HoaDonChiTietDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Nếu cần seed dữ liệu cho toàn bộ test, có thể thực hiện tại đây.
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên sau khi chạy hết các test.
    }
    
    @Before
    public void setUp() {
        // Thiết lập trạng thái cho mỗi test (ví dụ: reset dữ liệu, tạo kết nối test, …)
    }
    
    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần.
    }
    
    /**
     * Test Case 1: testInsert()
     * Mô tả: Kiểm tra việc chèn một HoaDonChiTiet mới.
     * Yêu cầu đầu ra: Phương thức insert() không phát sinh ngoại lệ. Sau khi chèn, record được insert 
     * có thể được tìm thấy qua phương thức findById().
     */
    @Test
    public void testInsert() {
        System.out.println("Test Case: testInsert() - Chèn HoaDonChiTiet mới");
        
        // Tạo đối tượng mẫu HoaDonChiTiet và thiết lập các thuộc tính cần thiết.
        HoaDonChiTiet model = new HoaDonChiTiet();
        // Ví dụ:
        // model.setMaHD("HD001");
        // model.setTenSP("Sản phẩm A");
        // ... thiết lập các thuộc tính khác nếu có.
        
        HoaDonChiTietDAO instance = new HoaDonChiTietDAO();
        try {
            instance.insert(model);
        } catch(Exception e) {
            fail("Phương thức insert() phát sinh ngoại lệ: " + e.getMessage());
        }
        
        // Sau khi chèn, kiểm tra record đã được insert thành công.
        HoaDonChiTiet inserted = instance.findById(model.getMaHD());
        assertNotNull("Sau khi chèn, record HoaDonChiTiet không được null", inserted);
    }
    
    /**
     * Test Case 2: testSelect()
     * Mô tả: Kiểm tra việc lấy danh sách các HoaDonChiTiet.
     * Yêu cầu đầu ra: Phương thức select() trả về danh sách không null.
     */
    @Test
    public void testSelect() {
        System.out.println("Test Case: testSelect() - Lấy danh sách HoaDonChiTiet");
        HoaDonChiTietDAO instance = new HoaDonChiTietDAO();
        List<HoaDonChiTiet> result = instance.select();
        assertNotNull("Danh sách HoaDonChiTiet trả về không được null", result);
        // Nếu bạn mong đợi danh sách có dữ liệu:
        // assertFalse("Danh sách HoaDonChiTiet không được rỗng", result.isEmpty());
    }
    
    /**
     * Test Case 3: testFindById()
     * Mô tả: Kiểm tra tìm kiếm HoaDonChiTiet theo ID.
     * Yêu cầu đầu ra: Với ID không hợp lệ (ví dụ: chuỗi rỗng), kết quả trả về phải là null.
     */
    @Test
    public void testFindById() {
        System.out.println("Test Case: testFindById() - Tìm kiếm HoaDonChiTiet theo ID không tồn tại");
        String maHD = ""; // Sử dụng chuỗi rỗng để kiểm tra trường hợp không tồn tại.
        HoaDonChiTietDAO instance = new HoaDonChiTietDAO();
        HoaDonChiTiet result = instance.findById(maHD);
        assertNull("Với ID không hợp lệ, kết quả tìm kiếm phải là null", result);
    }
    
    /**
     * Test Case 4: testDelete()
     * Mô tả: Kiểm tra xóa record HoaDonChiTiet theo ID.
     * Yêu cầu đầu ra: Sau khi gọi delete(), record được xóa sẽ không còn tồn tại (findById() trả về null).
     */
    @Test
    public void testDelete() {
        System.out.println("Test Case: testDelete() - Xóa HoaDonChiTiet");
        // Trước tiên, chèn một record để đảm bảo có dữ liệu để xóa.
        HoaDonChiTiet model = new HoaDonChiTiet();
        // Ví dụ:
        // model.setMaHD("HD002");
        // model.setTenSP("Sản phẩm B");
        
        HoaDonChiTietDAO instance = new HoaDonChiTietDAO();
        try {
            instance.insert(model);
        } catch(Exception e) {
            fail("Không thể chèn record để test delete(): " + e.getMessage());
        }
        
        // Thực hiện xóa record.
        try {
            instance.delete(model.getMaHD());
        } catch(Exception e) {
            fail("Phương thức delete() phát sinh ngoại lệ: " + e.getMessage());
        }
        
        // Kiểm tra record đã bị xóa.
        HoaDonChiTiet found = instance.findById(model.getMaHD());
        assertNull("Sau khi xóa, record không tồn tại", found);
    }
}
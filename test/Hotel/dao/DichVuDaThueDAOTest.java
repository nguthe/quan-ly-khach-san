package Hotel.dao;

import Hotel.entity.DichVuDaThue;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case cho lớp DichVuDaThueDAO.
 *
 * Test Case 1: testInsert()
 *  - Mô tả: Kiểm tra việc chèn một record DichVuDaThue mới vào cơ sở dữ liệu.
 *  - Yêu cầu đầu ra: Phương thức insert() không phát sinh ngoại lệ và record có thể được truy xuất lại bằng findById().
 *
 * Test Case 2: testFindById()
 *  - Mô tả: Kiểm tra tìm kiếm danh sách DichVuDaThue theo một mã đặt phòng (maDP).
 *  - Yêu cầu đầu ra: Nếu maDP không hợp lệ, kết quả trả về phải là danh sách rỗng.
 *
 * Test Case 3: testDelete()
 *  - Mô tả: Kiểm tra việc xóa tất cả DichVuDaThue có mã đặt phòng maDP.
 *  - Yêu cầu đầu ra: Sau khi xóa, danh sách dịch vụ đã thuê cho maDP đó phải rỗng.
 */
public class DichVuDaThueDAOTest {

    public DichVuDaThueDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Thiết lập tài nguyên nếu cần (ví dụ: seed dữ liệu test vào DB).
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên sau khi chạy tất cả các test.
    }
    
    @Before
    public void setUp() {
        // Thiết lập trạng thái trước mỗi test (ví dụ: tạo kết nối test).
    }
    
    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần.
    }
    
    /**
     * Test Case 1: testInsert()
     * Mô tả: Kiểm tra việc chèn một record DichVuDaThue mới.
     * Yêu cầu đầu ra: Phương thức insert() không phát sinh ngoại lệ và record có thể được truy xuất lại bằng findById().
     */
    @Test
    public void testInsert() {
        System.out.println("Test Case: testInsert() - Chèn DichVuDaThue mới");
        
        // Tạo đối tượng mẫu DichVuDaThue.
        DichVuDaThue model = new DichVuDaThue();
        // Ví dụ:
        // model.setMaDP(101);
        // model.setTenDV("Dịch vụ ăn uống");
        
        DichVuDaThueDAO instance = new DichVuDaThueDAO();
        try {
            instance.insert(model);
        } catch (Exception e) {
            fail("Phương thức insert() phát sinh ngoại lệ: " + e.getMessage());
        }
        
        // Kiểm tra record đã được chèn thành công.
        List<DichVuDaThue> insertedList = instance.findById(model.getMaDP(), null);
        assertNotNull("Sau khi chèn, danh sách DichVuDaThue không được null", insertedList);
        assertFalse("Danh sách DichVuDaThue không được rỗng", insertedList.isEmpty());
    }
    
    /**
     * Test Case 2: testFindById()
     * Mô tả: Kiểm tra tìm kiếm danh sách DichVuDaThue theo mã đặt phòng (maDP).
     * Yêu cầu đầu ra: Nếu maDP không hợp lệ, kết quả trả về phải là danh sách rỗng.
     */
    @Test
    public void testFindById() {
        System.out.println("Test Case: testFindById() - Tìm kiếm DichVuDaThue theo maDP không tồn tại");
        int maDP = 0; // Giả sử mã 0 không tồn tại.
        Object[] args = null;
        DichVuDaThueDAO instance = new DichVuDaThueDAO();
        List<DichVuDaThue> result = instance.findById(maDP, args);
        assertNotNull("Danh sách trả về không được null", result);
        assertTrue("Danh sách trả về phải rỗng khi maDP không hợp lệ", result.isEmpty());
    }
    
    /**
     * Test Case 3: testDelete()
     * Mô tả: Kiểm tra xóa tất cả DichVuDaThue có mã đặt phòng maDP.
     * Yêu cầu đầu ra: Sau khi xóa, danh sách dịch vụ đã thuê cho maDP đó phải rỗng.
     */
    @Test
    public void testDelete() {
        System.out.println("Test Case: testDelete() - Xóa DichVuDaThue theo maDP");
        
        // Trước tiên, chèn một record để đảm bảo có dữ liệu để xóa.
        DichVuDaThue model = new DichVuDaThue();
        // model.setMaDP(102);
        // model.setTenDV("Dịch vụ giặt là");
        
        DichVuDaThueDAO instance = new DichVuDaThueDAO();
        try {
            instance.insert(model);
        } catch (Exception e) {
            fail("Không thể chèn record để test delete(): " + e.getMessage());
        }
        
        // Thực hiện xóa dịch vụ đã thuê theo maDP.
        try {
            instance.delete(model.getMaDP());
        } catch (Exception e) {
            fail("Phương thức delete() phát sinh ngoại lệ: " + e.getMessage());
        }
        
        // Kiểm tra danh sách DichVuDaThue sau khi xóa.
        List<DichVuDaThue> afterDeleteList = instance.findById(model.getMaDP(), null);
        assertTrue("Danh sách DichVuDaThue phải rỗng sau khi xóa", afterDeleteList.isEmpty());
    }
}
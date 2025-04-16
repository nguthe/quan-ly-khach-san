package Hotel.dao;

import Hotel.entity.KhachHang;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test case cho lớp KhachHangDAO.
 *
 * Test Case 1: testInsert()
 *  - Chức năng của hàm insert: chèn một đối tượng KhachHang mới vào cơ sở dữ liệu.
 *  - Mô tả test case: Kiểm tra rằng phương thức insert() không phát sinh ngoại lệ khi chèn một đối tượng mới;
 *    sau đó, đối tượng được truy xuất qua findById() với mã mới chèn phải trả về không null.
 *  - Yêu cầu đầu ra: findById("KHTEST001") trả về đối tượng không null.
 *
 * Test Case 2: testSelectByKeyword()
 *  - Chức năng của hàm selectByKeyword: tìm kiếm danh sách KhachHang theo từ khóa được cung cấp.
 *  - Mô tả test case: Gọi selectByKeyword với một từ khóa (ví dụ là "Test"); danh sách trả về phải không null,
 *    và nếu có dữ liệu phù hợp, danh sách sẽ không rỗng.
 *  - Yêu cầu đầu ra: selectByKeyword("Test") trả về List không null.
 *
 * Test Case 3: testSelect()
 *  - Chức năng của hàm select: lấy tất cả đối tượng KhachHang từ cơ sở dữ liệu.
 *  - Mô tả test case: Kiểm tra rằng phương thức select() trả về danh sách không null.
 *  - Yêu cầu đầu ra: select() trả về List không null.
 *
 * Test Case 4: testFindById()
 *  - Chức năng của hàm findById: tìm kiếm một KhachHang theo ID.
 *  - Mô tả test case: Với ID không tồn tại (ví dụ: chuỗi rỗng), phương thức findById() trả về null.
 *  - Yêu cầu đầu ra: findById("") trả về null.
 */
public class KhachHangDAOTest {

    private KhachHangDAO dao;

    public KhachHangDAOTest() { }

    @BeforeClass
    public static void setUpClass() {
        // Khởi tạo tài nguyên toàn cục nếu cần, ví dụ seed dữ liệu vào cơ sở dữ liệu test.
    }

    @AfterClass
    public static void tearDownClass() {
        // Dọn dẹp tài nguyên sau tất cả các test.
    }

    @Before
    public void setUp() {
        // Khởi tạo đối tượng dao trước mỗi test.
        dao = new KhachHangDAO();
    }

    @After
    public void tearDown() {
        // Dọn dẹp sau mỗi test nếu cần.
    }

    /**
     * Test Case 1: testInsert()
     * Chức năng: chèn một đối tượng KhachHang mới vào cơ sở dữ liệu.
     * Mô tả test case: Kiểm tra rằng insert() không phát sinh ngoại lệ khi chèn, và sau đó,
     *    findById("KHTEST001") trả về đối tượng không null.
     * Yêu cầu đầu ra: Đối tượng được chèn phải không bị null.
     */
    @Test
    public void testInsert() {
        System.out.println("Running testInsert()");
        
        // Tạo đối tượng mẫu KhachHang
        KhachHang kh = new KhachHang();
        kh.setMaKH("KHTEST001");
        kh.setTenKH("Nguyen Van Test");
        // Set thêm các thuộc tính khác (nếu có) theo yêu cầu của ứng dụng
        
        // Gọi insert() và không mong đợi ngoại lệ.
        try {
            dao.insert(kh);
        } catch (Exception e) {
            fail("Insert() threw an exception: " + e.getMessage());
        }
        
        // Kiểm tra sau chèn: tìm đối tượng theo id
        KhachHang inserted = dao.findById("KHTEST001");
        assertNotNull("Record inserted must not be null", inserted);
        
        // Nếu có phương thức delete() ở DAO để làm sạch dữ liệu, bạn có thể gọi nó ở đây.
        // dao.delete("KHTEST001");
    }

    /**
     * Test Case 2: testSelectByKeyword()
     * Chức năng: truy xuất danh sách KhachHang theo từ khóa.
     * Mô tả test case: Gọi selectByKeyword với từ khóa "Test".
     * Yêu cầu đầu ra: Phương thức selectByKeyword() trả về List không null; nếu có dữ liệu,
     *    danh sách không rỗng.
     */
    @Test
    public void testSelectByKeyword() {
        System.out.println("Running testSelectByKeyword()");
        String keyword = "Test";  // Tùy thuộc vào dữ liệu trong cơ sở dữ liệu test
        List<KhachHang> list = dao.selectByKeyword(keyword);
        assertNotNull("selectByKeyword() should return a non-null list", list);
        // Nếu bạn biết chắc rằng dữ liệu tồn tại, bạn có thể kiểm tra:
        // assertFalse("List should not be empty", list.isEmpty());
    }

    /**
     * Test Case 3: testSelect()
     * Chức năng: lấy tất cả các khách hàng.
     * Mô tả test case: Kiểm tra rằng phương thức select() trả về danh sách khách hàng không null.
     * Yêu cầu đầu ra: select() trả về List không null.
     */
    @Test
    public void testSelect() {
        System.out.println("Running testSelect()");
        List<KhachHang> list = dao.select();
        assertNotNull("select() should return a non-null list", list);
    }

    /**
     * Test Case 4: testFindById()
     * Chức năng: tìm kiếm KhachHang theo ID.
     * Mô tả test case: Với ID không tồn tại (ví dụ: chuỗi rỗng), findById() nên trả về null.
     * Yêu cầu đầu ra: findById("") phải trả về null.
     */
    @Test
    public void testFindById() {
        System.out.println("Running testFindById()");
        KhachHang result = dao.findById("");
        assertNull("findById() should return null for non-existent ID", result);
    }
}
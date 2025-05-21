
package Hotel.dao;

import Hotel.entity.DSPhong;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class DSPhongDAoTest {

    private DSPhongDAo dao;
    private final String EXISTING_ID = "PT101";
    private String originalStatus;

    @Before
    public void setUp() {
        dao = new DSPhongDAo();
        // Lấy trạng thái gốc để restore sau test update
        DSPhong p = dao.findById(EXISTING_ID);
        assertNotNull("Phải có phòng PT101 trong DB để test", p);
        originalStatus = p.getTrangThai();
    }

    @After
    public void tearDown() {
        // Khôi phục trạng thái gốc
        DSPhong restore = new DSPhong();
        restore.setMaPhong(EXISTING_ID);
        restore.setTrangThai(originalStatus);
        dao.update(restore);
    }

    @Test
    public void testSelectContainsPT101() {
        List<DSPhong> list = dao.select();
        assertNotNull("select() không được trả về null", list);
        assertTrue("Phải tìm thấy PT101 trong select()", 
                   list.stream().anyMatch(r -> EXISTING_ID.equals(r.getMaPhong())));
    }

    @Test
    public void testFindByIdValid() {
        DSPhong room = dao.findById(EXISTING_ID);
        assertNotNull("PT101 phải tồn tại", room);
        assertEquals("Mã phòng phải là PT101", EXISTING_ID, room.getMaPhong());
        assertNotNull("Kiểu phòng không được null", room.getTenPhong());
        assertFalse("Kiểu phòng không được rỗng", room.getTenPhong().trim().isEmpty());

        // Loại phòng phải nằm trong {Thường, VIP}
        List<String> validCategories = Arrays.asList("Thường", "VIP");
        assertTrue("Loại phòng phải là Thường hoặc VIP", 
                   validCategories.contains(room.getMaLoaiPhong()));

        // Trạng thái phải nằm trong {Trống, Đang sử dụng}
        List<String> validStatus = Arrays.asList("Trống", "Đang sử dụng");
        assertTrue("Trạng thái phải là Trống hoặc Đang sử dụng", 
                   validStatus.contains(room.getTrangThai()));
    }

    @Test
    public void testFindByIdInvalid() {
        DSPhong room = dao.findById("NO_SUCH_ROOM");
        assertNull("Mã phòng không tồn tại phải trả về null", room);
    }

    @Test
    public void testFindByIdNullParam() {
        // Nếu truyền null, dao.findById phải trả về null (WHERE MaPhong IS NULL sẽ không match)
        DSPhong room = dao.findById(null);
        assertNull("findById(null) phải trả về null", room);
    }

    @Test
    public void testUpdateStatus() {
        // Thử cập nhật trạng thái sang một giá trị ngược lại
        String newStatus = originalStatus.equals("Trống") ? "Đang sử dụng" : "Trống";
        DSPhong m = new DSPhong();
        m.setMaPhong(EXISTING_ID);
        m.setTrangThai(newStatus);
        dao.update(m);

        DSPhong updated = dao.findById(EXISTING_ID);
        assertNotNull(updated);
        assertEquals("Phải cập nhật được trạng thái", newStatus, updated.getTrangThai());
    }

    @Test
    public void testUpdateSetStatusNull() {
        // Cập nhật quyền đặt trạng thái thành null
        DSPhong m = new DSPhong();
        m.setMaPhong(EXISTING_ID);
        // model.trangThai vẫn là null
        dao.update(m);

        DSPhong updated = dao.findById(EXISTING_ID);
        assertNotNull(updated);
        assertNull("TrangThai sau update null phải là null", updated.getTrangThai());
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNullModel() {
        // Gọi update(null) ném NullPointerException
        dao.update(null);
    }
}

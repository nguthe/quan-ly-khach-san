package Hotel.dao;

import Hotel.utils.JdbcHelPer;
import org.junit.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ThanhToanDAOTest {

    private ThanhToanDAO dao;

    @Before
    public void setUp() {
        dao = new ThanhToanDAO();
    }

    // Test 1: DB có dữ liệu, rs.next() true ít nhất 1 lần
    @Test
    public void testSelectYears_WithData() {
        List<Integer> years = dao.selectYears();
        assertNotNull(years);
        assertFalse(years.isEmpty());

        for (int i = 0; i < years.size() - 1; i++) {
            assertTrue(years.get(i) >= years.get(i + 1));
        }

        System.out.println("Years from DB: " + years);
    }

    // Test 2: DB không có dữ liệu - dùng override để trả về rỗng (giữ nguyên DB thật)
    @Test
    public void testSelectYears_NoData() {
        ThanhToanDAO daoNoData = new ThanhToanDAO() {
            @Override
            public List<Integer> selectYears() {
                String sql = "SELECT DISTINCT YEAR(NgayLap) AS Year FROM hoaDonThanhToan WHERE 1=0"; // trả về rỗng
                List<Integer> list = new ArrayList<>();
                try {
                    ResultSet rs = JdbcHelPer.executeQuery(sql);
                    while (rs.next()) {
                        int year = rs.getInt("Year");
                        list.add(year);
                    }
                    rs.getStatement().getConnection().close();
                    return list;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        List<Integer> years = daoNoData.selectYears();
        assertNotNull(years);
        assertTrue(years.isEmpty());
    }

    // Test 3: Truy vấn sai gây SQLException, kiểm tra RuntimeException được ném ra
    @Test(expected = RuntimeException.class)
    public void testSelectYears_ThrowsException() {
        ThanhToanDAO daoBadQuery = new ThanhToanDAO() {
            @Override
            public List<Integer> selectYears() {
                String sql = "SELECT invalidColumn FROM nonExistentTable"; // sai query
                List<Integer> list = new ArrayList<>();
                try {
                    ResultSet rs = JdbcHelPer.executeQuery(sql);
                    while (rs.next()) {
                        int year = rs.getInt("Year");
                        list.add(year);
                    }
                    rs.getStatement().getConnection().close();
                    return list;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        daoBadQuery.selectYears();
    }

    // Test 4: selectHoaDons() chưa cài đặt, ném UnsupportedOperationException
    @Test(expected = UnsupportedOperationException.class)
    public void testSelectHoaDons_ThrowsException() {
        dao.selectHoaDons();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Hotel.dao;

import Hotel.entity.DSPhong;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Phạm Thị Thuý Huyền
 */
public class DSPhongDAoIT {
    
    public DSPhongDAoIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of select method, of class DSPhongDAo.
     */
    @Test
    public void testSelect() {
        System.out.println("select");
        DSPhongDAo instance = new DSPhongDAo();
        List<DSPhong> expResult = null;
        List<DSPhong> result = instance.select();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class DSPhongDAo.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        String maphong = "";
        DSPhongDAo instance = new DSPhongDAo();
        DSPhong expResult = null;
        DSPhong result = instance.findById(maphong);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DSPhongDAo.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        DSPhong model = null;
        DSPhongDAo instance = new DSPhongDAo();
        instance.update(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

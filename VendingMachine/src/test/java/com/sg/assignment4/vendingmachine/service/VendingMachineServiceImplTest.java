package com.sg.assignment4.vendingmachine.service;

import com.sg.assignment4.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment4.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment4.vendingmachine.dto.Item;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendingMachineServiceImplTest {

    private VendingMachineService service;

    public VendingMachineServiceImplTest () {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//
//        service = new VendingMachineServiceImpl(dao, auditDao);

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("serviceLayer", VendingMachineServiceImpl.class);
    }

    @Test
    public void testGetAllItems() throws Exception {
        // ARRANGE
//        Item testClone = new Item("Fanta");
//        testClone.setItemCost(199);
//        testClone.setNumOfItemsInInventory(20);


        // ACT & ASSERT
        assertEquals( 1, service.getAllItemsFromService().size(),
                "Should only have one Item.");
//        assertTrue( service.getAllItemsFromService().contains(testClone),
//                "The one Item should be Fanta.");
    }




}

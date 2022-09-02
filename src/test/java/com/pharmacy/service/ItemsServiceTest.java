package com.pharmacy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepository;

class ItemsServiceTest {
     
	
	
	
	@Autowired
	private ItemsService itemService;
	
	@MockBean
	private ItemsRepository itemsRepo;
	
	
	/*
	 * @Test void testAddItem() {
	 * 
	 * ItemsBean item = new ItemsBean("Basic supplies","virtusa_dist"); when(
	 * itemsRepo.save(item)).thenReturn(item);
	 * assertEquals(item,itemService.addItem(item,distrr)); }
	 * 
	 * @Test void testInsertIntoItemTable() { fail("Not yet implemented"); }
	 * 
	 * @Test void testDeleteByDistributor() { fail("Not yet implemented"); }
	 * 
	 * @Test void testUpdateDistributorItem() { fail("Not yet implemented"); }
	 */

	@Test
	void testGetAllItems() {
		when(itemsRepo.findAll()).thenReturn(Stream.of(new ItemsBean("Basic supplies","virtusa_dist"),new  ItemsBean("Basic supplies","virtusa_dist")).collect(Collectors.toList()));
		
		assertEquals(2,itemService.getAllItems().size());
	}

}

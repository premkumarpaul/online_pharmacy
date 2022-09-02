package com.pharmacy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepository;
import com.pharmacy.model.*;

@SpringBootTest
class DistributorItemServiceTest {
	
	@Autowired
	private DistributorItemService dItemService;
	
	@MockBean
	private DistributorItemsRepo DItemRepository;
	
	@Autowired
	private ItemsService itemService;
	
	@MockBean
	private ItemsRepository itemsRepo;
	
	
	
	/** to add distributor**/
	@Test
	void testAddDisItem() {
		
		DistributorItemBean distributor = new DistributorItemBean(1,"Paracetamol",125,"fever",10);
		when( DItemRepository.save(distributor)).thenReturn(distributor);
		assertEquals(distributor,dItemService.addDisItem(distributor));
		
	}

	
	/** to delete distributor**/
	@Test
	void testDeleteItem() {

		DistributorItemBean distributor = new DistributorItemBean(1,"Paracetamol",125,"fever",10);
		dItemService.deleteItem(1);
		verify(DItemRepository,times(1)).deleteById(1);
	}
	
	/** to get All Items **/
	@Test
	void testGetAllItems() {
		
		 when(DItemRepository.findAll()).thenReturn(Stream.of(new DistributorItemBean(1,"Paracetamol",125,"fever",10),new  DistributorItemBean(1,"Paracetamol",125,"fever",10)).collect(Collectors.toList()));
			
			assertEquals(2,dItemService.getAllItems().size());

	}
	
	

	/** to get Items by ID**/
	@Test
	void testGetItemById() {
		DistributorItemBean distributor = new DistributorItemBean(1,"Paracetamol",125,"fever",10);
		when(DItemRepository.findById(1)).thenReturn(distributor);
		
		assertEquals(distributor,dItemService.getItemById(1));
	}
	
	
	/** to get All Items by ID**/
	@Test
	void testGetAllItemsByItemID() {
		 when(DItemRepository.findAll()).thenReturn(Stream.of(new DistributorItemBean(1,"Paracetamol",125,"fever",10),new  DistributorItemBean(1,"Paracetamol",125,"fever",10)).collect(Collectors.toList()));
			
			assertEquals(2,dItemService.getAllItems().size());
		
	}


}

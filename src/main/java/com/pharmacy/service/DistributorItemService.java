package com.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepository;

/** THIS CLASS DEFINES THE METHODS FOR DISTRIBUTOR ITEM * */
@Service
public class DistributorItemService {

	@Autowired
	private DistributorItemsRepo distributorItemRepo;

	@Autowired
	private ItemsRepository itemsRepo;
    
	/** CONSTRUCTORS* */
	public DistributorItemService() {
		super();
	}

	public DistributorItemService(DistributorItemsRepo distributorItemRepo) {
		super();
		this.distributorItemRepo = distributorItemRepo;
	}
     
	/** TO ADD DISTRIBUTOR ITEM * */
	public DistributorItemBean addDisItem(DistributorItemBean disItem) {
		return this.distributorItemRepo.save(disItem);
	}
	

	/** TO DELETE AN ITEM BY DISTRIBUTOR ITEM ID* */
	@Transactional
	public void deleteItem(int id) {
		distributorItemRepo.deleteDistributorItemBeanById(id);

	}
	
	/** TO GET ALL THE ITEMS * */
	public List<DistributorItemBean> getAllItems() {
		return this.distributorItemRepo.findAll();
	}
  
	/** TO GET AN ITEM BY DISTRIBUTOR ITEM ID * */
	public DistributorItemBean getItemById(int id) {
		return distributorItemRepo.findById(id);
	}
    
	/** TO GET ALL THE ITEMS BY ITEM ID* */
	public List<DistributorItemBean> getAllItemsByItemID(String distributor) {
		ItemsBean item = itemsRepo.findByDistributor(distributor);
		return distributorItemRepo.findByItemBean(item);
	}
    
	/** TO GET CATEGORY IITEMS* */
	public List<DistributorItemBean> getCategoryItems(String cat) {
		List<DistributorItemBean> disres = new ArrayList<>();
		List<ItemsBean> items = this.itemsRepo.findByCategory(cat);
		for (ItemsBean item : items) {
			disres.addAll(this.distributorItemRepo.findByItemBean(item));
		}
		return disres;
	}
	/** TO GET DISTRIBUTOR ITEMS BY ITEMBEAN * */
	public List<DistributorItemBean> getDistributorItems(List<ItemsBean> items) {
		List<DistributorItemBean> allresitems = new ArrayList<>();
		for (ItemsBean its : items) {
			List<DistributorItemBean> resitems = this.distributorItemRepo.findByItemBean(its);
			allresitems.addAll(resitems);
		}
		return allresitems;
	}
    
	/** TO GET THE PRODUCTS FROM CART * */
	public List<DistributorItemBean> getCartProducts(List<DistributorItemBean> items) {
		List<DistributorItemBean> products = new ArrayList<>();
		if (items.size() > 0) {
			for (DistributorItemBean item : items) {
				float price = distributorItemRepo.getPriceById(item.getId());
				item.setPrice(item.getQuantity() * price);
				products.add(item);
			}
		}

		return products;
	}
	
	/** TO UPDATE DIISTRIBUTOR ITEM BY ID * */
	@Transactional
	public DistributorItemBean updateDistributorItemById(DistributorItemBean disItem) {
		return this.distributorItemRepo.save(disItem);
	}
    
	/** TO UPDATE ITEM AFTER ORDER * */
	@Transactional
	public DistributorItemBean updateItemAfterOrder(int itemsId, String itemName, int quantity) {
		ItemsBean item = itemsRepo.findById(itemsId);

		DistributorItemBean it = this.distributorItemRepo.findByItemBeanAndItemName(item, itemName);
		int itemQuantity = it.getQuantity();
		quantity = itemQuantity - quantity;
		it.setItemBean(item);
		it.setQuantity(quantity);
		return this.distributorItemRepo.save(it);

	}

}

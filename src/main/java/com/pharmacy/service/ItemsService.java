package com.pharmacy.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepository;

/** THIS CLASS DEFINES THE METHOD FOR ITEMS* */
@Service
public class ItemsService{


	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private DistributorItemService distributorItemService;

	@Autowired
	private DistributorItemsRepo distributorRepo;
	
	/** CONSTRUCTOR* */
	public ItemsService() {
	}

	public ItemsService(ItemsRepository itemsRepo) {
		super();
		this.itemsRepository = itemsRepo;
	}
    
	/** TO ADD ITEMS * */
	public void addItem(ItemsBean itemBean, DistributorItemBean distributorItem) {
		ItemsBean resultItemBean = null;
		if (!itemsRepository.existsByDistributor(itemBean.getDistributor())) {
			itemsRepository.save(itemBean);
		}

		resultItemBean = itemsRepository.findByDistributor(itemBean.getDistributor());
		distributorItem.setItemBean(resultItemBean);
		distributorItemService.addDisItem(distributorItem);
	}
   
	/**  
	 * @author PREMKUMAR PAUL
	 * TO CHECK THE ITEM BY DISTRIBUTOR */
	public boolean checkItem(ItemsBean itemBean) {
		ItemsBean item = this.itemsRepository.findByDistributor(itemBean.getDistributor());
		if (item == null)
			return false;
		else
			return true;
	}
    
	/** 
	 * 
	 * TO SAVE OR INSERT AN ITEM * */
	public ItemsBean insertIntoItemTable(ItemsBean item) {
		return this.itemsRepository.save(item);
	}
    
	/**
	 * @author PREMKUMAR PAUL
	 *  TO DELETE AN ITEM BY DISTRIBUTOR* */
	@Transactional
	public int deleteByDistributor(String distributor) {
		return this.itemsRepository.deleteByDistributor(distributor);

	}
     

	/** 
	 * @author PREMKUMAR PAUL
	 * TO UPDATE AN ITEM BY DISTRIBUTOR ID * */
	@Transactional
	public void updateDistributorItem(String distributor,List<ParticularOrderBean> products) {
		int itemsId=this.getIdBydistributor(distributor);
		for(ParticularOrderBean product:products) {
			distributorItemService.updateItemAfterOrder(itemsId, product.getItemName(),product.getQuantity());
			}
	}
	
	/** TO GET ALL THE ITEMS * */
	public List<ItemsBean> getAllItems()

	{
		return this.itemsRepository.findAll();
	}
     
	/** 
	 * @author PREMKUMAR PAUL
	 * TO GET AN ITEM ID BY DISTRIBUTOR  * */
	public int getIdBydistributor(String distributor) {
		ItemsBean item = this.itemsRepository.findByDistributor(distributor);
		return item.getId();
	}
  
	/**
	 * @author PREMKUMAR PAUL
	 *  TO GET A DISTRIBUTOR NAME BY ITEM ID  * */
	public String getDistributorName(int id) {
		ItemsBean item = this.itemsRepository.findById(id);
		return item.getDistributor();
	}
    
	/** 
	 * @author PREMKUMAR PAUL
	 * TO GET AN ITEM BY ITEM ID * */
	public ItemsBean getItemById(int id) {
		return itemsRepository.findById(id);
	}
	/** 
	 * @author PREMKUMAR PAUL
	 * TO GET DISTRIBUTOR ITEMS BY DISTRIBUTOR NAME* */
	public List<DistributorItemBean> getDistributorItems(String distributor) {
		return distributorRepo.findByItemBean(itemsRepository.findByDistributor(distributor));
	}
	
	/** 
	 * @author PREMKUMAR PAUL
	 * TO DELETE ITEMS BY DISTEIBUTOR * */
	public void deleteItemsByDistributor(String distributor) {
		int id=itemsRepository.getIdByDistributor(distributor);
		distributorRepo.deleteByItemsId(id);
		itemsRepository.deleteByDistributor(distributor);
	}
}

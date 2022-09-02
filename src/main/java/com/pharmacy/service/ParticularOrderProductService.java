package com.pharmacy.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

/**
 * 
 * THIS CLASS IS MADE TO GET PARTICULAR ORDER PRODUCT SERVICE
 */
@Service
public class ParticularOrderProductService implements Serializable {


	private static final long serialVersionUID = 1L;

	@Autowired
	private transient ParticularProdRepo productRepo;

	@Autowired
	private transient OrdersRepo ordersRepo;

	public ParticularOrderProductService(ParticularProdRepo productRepo) {
		super();
		this.productRepo = productRepo;
	}
	
	/** TO ADD A PARTICULAR ORDER
	 * @param prod
	 *  
	 *  **/
	 
	
	public ParticularOrderBean addPartOrder(ParticularOrderBean prod) {
		return productRepo.save(prod);
	}
	
	/** TO DELETE A PARTICULAR ORDER
	 * @param int id
	 * @param boolean isMedicine
	 *  
	 *  **/
	 

	public void deleteInTable(int id, boolean isMedicine) {
		if (!isMedicine) {
			this.productRepo.deleteByOrderId(id);
		}
		this.ordersRepo.deleteById(id);
	}
	
	/** TO UPDATE A PARTICULAR ORDER BY USING ID 
	 *  @param prod
	 * 
	 * **/

	public ParticularOrderBean updateByIdAndOrderId(ParticularOrderBean prod) {
		return this.productRepo.save(prod);
	}
     
	/** TO GET A PARTICULAR ORDER BY USING PARTICULAR ORDER ID 
	 * @param int order_id
	 * 
	 * **/
	
	public List<ParticularOrderBean> getPartByOrderId(int order_id) {
		OrdersBean ord = this.ordersRepo.findById(order_id);
		return (this.productRepo).findByOrderBean(ord);
	}
    
	/** TO GET ALL THE PRODUCTS **/
	public List<ParticularOrderBean> getAllProds() {
		return this.productRepo.findAll();
	}

}

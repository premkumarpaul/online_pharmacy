package com.pharmacy.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.OrdersBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.OrdersRepo;
import com.pharmacy.repo.ParticularProdRepo;

/**
 * 
 *  THIS CLASS DEFINES THE METHODS FOR THE BEAN ORDER
 */
@Service
public class OrdersService {

	@Autowired
	private ParticularProdRepo productRepo;

	
	@Autowired
	private OrdersRepo ordersRepo;
	
     /**  PARAMETERIZED CONSTRUCTOR FOR ORDERSERVICE
      * @param productRepo
      * 
      * @param ordersRepo
      *   **/
	public OrdersService(ParticularProdRepo productRepo, OrdersRepo ordersRepo) {
		super();
		this.productRepo = productRepo;
		this.ordersRepo = ordersRepo;
	}
     
	/**TO GET A PARTICULAR ORDER BY ID 
	 * @param order
	 * 
	 * 
	 * **/
	public int getOrderId(OrdersBean order) {
		OrdersBean ord = this.ordersRepo.findByUsernameAndOrderDateAndTotalQuantity(order.getUsername(),
				order.getOrderDate(), order.getTotalQuantity());

		return ord.getOrderId();
	}
	
	
	/** TO ADD OR SAVE THE ORDERS
	 * @param order
	 *  
	 *  **/
	public String addOrder(OrdersBean order, List<ParticularOrderBean> prodBean) {
		OrdersBean orderBean = this.ordersRepo.save(order);
		for (ParticularOrderBean prod : prodBean) {
			prod.setOrderBean(orderBean);
			this.productRepo.save(prod);
		}
		if (orderBean != null)
			return "SUCCESS";
		else 
			return "not inserted";
	}
   
	/** TO ADD A FILE 
	 * @param order
	 * 
	 * 
	 * **/
	public String addFile(OrdersBean order) {
		OrdersBean orderBean = this.ordersRepo.save(order);

		
		  if (orderBean != null) return "SUCCESS"; 
		  else
		 
			return "not inserted";
	}
     
	/** TO GET ORDERS BY USING PARTICULAR DATE 
	 * 
	 * @param String date
	 * @param String role
	 * @param String username
	 * 
	 * **/
	public List<OrdersBean> getOrdersByDate(String date, String role, String username) {
		List<OrdersBean> orders=new ArrayList<>();
		if (role.equals("ADMIN")) {
			orders = this.ordersRepo.findByOrderDate(date);
		}
		else if(role.equals("DISTRIBUTOR")) {
			orders = this.ordersRepo.findByOrderDateAndDistributorName(date, username);
			
		}
		return orders;
	}
     
	/** TO GET ORDERS BY PARTICULAR DISTRIBUTOR NAME
	 * @param String distributorName
	 * 
	 * **/
	public List<OrdersBean> getOrdersByDistributor(String distributorName) {
		return ordersRepo.findByDistributorName(distributorName);
	}
     
	/** TO GET ALL THE ORDERS **/
	public List<OrdersBean> getAllOrders() {
		return this.ordersRepo.findAll();
	}
	
	/** TO GET ALL THE ORDERS BY USERNAME
	 * @param String username
	 *  **/
	public List<OrdersBean> getAllOrdersByUsername(String username) {
		return this.ordersRepo.findByUsername(username);
	}
  
	/** TO DELETE AN ORDER BY ID 
	 * @param int orderId
	 * 
	 * **/
	@Transactional
	public void deleteOrderById(int orderId) {
		this.ordersRepo.deleteById(orderId);
	}
     
	/** TO UPDATE THE STATUS OF AN ORDER BY ID 
	 *  @param int orderId
	 *  @param String status
	 *  @param String message
	 * 
	 * 
	 * **/
	@Transactional
	public OrdersBean updateOrderStatus(int orderId, String status, String message) {
		OrdersBean ord = this.ordersRepo.findById(orderId);
		ord.setStatus(status);
		ord.setMessage(message);
		return this.ordersRepo.save(ord);

	}
    
	/** TO GET ORDERS BY NAME AND ROLE 
	 * @param String name
	 * @param String role
	 *  
	 *  **/
	public List<OrdersBean> getOrdersByNameAndRole(String name, String role) {
		
		if (role.equals("USER")) {
			return this.ordersRepo.findByUsername(name);

		} else {
			return this.ordersRepo.findByDistributorName(name);
		}
	}
     
	/** TO GET ORDERS BY PARTICULAR ORDER ID 
	 * @param int orderId
	 * 
	 * **/
	public OrdersBean getOrderByOrdeId(int orderId) {
		return this.ordersRepo.findById(orderId);
	}
    
	/** TO UPDATE AN ORDER BY ORDER ID **/
	public void updateOrder(OrdersBean newOrder) {
		OrdersBean order = ordersRepo.findById(newOrder.getOrderId());
		order.setStatus(newOrder.getStatus());
		order.setMessage(newOrder.getMessage());
		order.setDistributorName(newOrder.getDistributorName());
		order.setTotalQuantity(newOrder.getTotalQuantity());
		order.setPrice(newOrder.getPrice());
		ordersRepo.save(order);
	}
}

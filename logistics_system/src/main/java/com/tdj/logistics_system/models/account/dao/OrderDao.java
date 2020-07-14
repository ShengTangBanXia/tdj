package com.tdj.logistics_system.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tdj.logistics_system.models.account.entity.Address;
import com.tdj.logistics_system.models.account.entity.Order;
import com.tdj.logistics_system.models.common.vo.SearchVo;

@Mapper
public interface OrderDao {

	@Select("<script>" + 
			"select * from logistics_order "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (order_num like binary '%${keyWord}%' or order_sender like binary '%${keyWord}%' "
			+ " or order_receiver like binary '%${keyWord}%' or order_status like binary '%${keyWord}%'"
			+ " or order_principal like binary '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by order_num desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List <Order> getOrdersBySearchVo (SearchVo searchVo);
	
	@Select("select * from address where pid = #{pid}")
	List <Address> getAddressByPid(int pid);
	
	@Insert("insert into logistics_order "
			+ "(order_num, order_sender, sender_address, sender_tel,"
			+ "order_receiver, receiver_address, receiver_tel, package_type, pay_method, input_time, order_status)"
			+ "values(#{orderNum}, #{orderSender}, #{senderAddress}, #{senderTel}, "
			+ "#{orderReceiver}, #{receiverAddress}, #{receiverTel}, #{packageType}, #{payMethod}, #{inputTime}, #{orderStatus})")
	void addOrder (Order order);
	
	@Delete("delete from logistics_order where order_num = #{orderNum}")
	void deleteOrder(String orderNum);
	
	@Select("select * from logistics_order where order_num = #{orderNum}")
	Order orderSlecet (String orderNum);
	
	@Update("update logistics_order set order_sender = #{orderSender}, sender_address = #{senderAddress}, "
			+ "sender_tel = #{senderTel}, order_receiver = #{orderReceiver}, receiver_address = #{receiverAddress}, "
			+ "receiver_tel = #{receiverTel} where order_num = #{orderNum}")
	void orderUpdate (Order order);
	
}

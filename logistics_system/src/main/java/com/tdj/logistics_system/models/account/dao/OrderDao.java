package com.tdj.logistics_system.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
	
}

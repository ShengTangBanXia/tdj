package com.tdj.logistics_system.models.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tdj.logistics_system.models.account.entity.Customer;
import com.tdj.logistics_system.models.common.vo.SearchVo;


@Mapper
public interface CustomerDao {
	@Select("<script>" + 
			"select * from customer"
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (customer_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by customer_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<Customer> getCustomerBySearchVo(SearchVo searchVo);
	
	@Insert("insert into customer(customer_name,customer_sex,customer_position,customer_telephone,customer_email,customer_address,customer_entrytime,customer_diploma,customer_departement,account_number,password,customer_birthday) "
			+ "values (#{customerName},#{customerSex},#{customerPosition},#{customerTelephone},#{customerEmail},#{customerAddress},#{customerEntrytime},#{customerDiploma},#{customerDepartement},#{accountNumber},#{password},#{customerBirthday})")
	@Options(useGeneratedKeys = true, keyColumn = "customer_id", keyProperty = "customerId")
	void insertCustomer(Customer customer);
	
	@Update("update customer set customer_name = #{customerName},customer_telephone = #{customerTelephone},customer_email = #{customerEmail},account_number = #{accountNumber},password = #{password} where customer_id = #{customerId}")
	void updateCustomer(Customer customer);
  
	@Select("select * from customer where customer_name = #{customerName}")
	Customer getCustomerByCustomerName(String customerName);
    
	@Select("select *  from customer where customer_id = #{customerId}")
	@Results(id = "customerResult", value = {
			@Result(column = "customer_id", property = "customerId"),
			@Result(column = "customer_id", property = "roles", 
					javaType = List.class, 
					many = @Many(select = "com.tdj.logistics_system.models.account.dao.RoleDao.getRolesByCustomerId"))
			})
	Customer getCustomerBycustomerId(int customerId);
    
	@Delete("delete from customer where customer_id = #{customerId}")
	void deleteCustomerBycustomerId(int customerId);
}

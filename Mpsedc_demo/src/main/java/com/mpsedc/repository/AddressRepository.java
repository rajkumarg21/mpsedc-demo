package com.mpsedc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mpsedc.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByUserId(Long userId);
	
	@Procedure(procedureName = "insert_address")
	void insertAddress(Long user_id, String state, String district, String city, String pin_code);

	@Procedure(procedureName = "update_address")
	void updateAddress(@Param("p_addressid") Long addressId, @Param("p_userid") Long userId,
			@Param("p_state") String state, @Param("p_district") String district, @Param("p_city") String city,
			@Param("p_pin_code") String pinCode);

	@Procedure(procedureName = "DeleteAddressById")
	void deleteAddressById(@Param("userId") Long userId);

}

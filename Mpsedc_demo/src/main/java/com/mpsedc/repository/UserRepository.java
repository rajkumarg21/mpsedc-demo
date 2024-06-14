package com.mpsedc.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mpsedc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	@Procedure(procedureName = "insert_user")
    Long insertUser(String first_name, String middle_name, String last_name, String mobile_no, Date date_of_birth,
                    String email, String cast_category, String specially_abled, String kind_disability);

	@Procedure(procedureName = "get_user_with_addresses")
    List<Object[]> getUserWithAddresses(@Param("userId") Long userId);
    
    @Procedure(procedureName = "update_user")
    void updateUser(
        @Param("p_userid") Long userId,
        @Param("p_first_name") String firstName,
        @Param("p_middle_name") String middleName,
        @Param("p_last_name") String lastName,
        @Param("p_mobile_no") String mobileNo,
        @Param("p_date_of_birth") Date dateOfBirth,
        @Param("p_email") String email,
        @Param("p_cast_catagory") String castCatagory,
        @Param("p_specially_abled") String speciallyAbled,
        @Param("p_kind_disability") String kindDisability
    );
    
    
    @Procedure(procedureName = "DeleteUserById")
    void deleteUserById(@Param("userId") Long userId);

    boolean existsById(Long id);

	boolean existsByEmail(String email);
	
	
	@Query("SELECT u FROM User u WHERE u.castCatagory = :castCatagory")
	List<User> findByCastCatagory(@Param("castCatagory") String castCatagory);

	@Query(value = "SELECT * FROM users WHERE date_of_birth BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<User> findUsersByDateOfBirthBetween(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

}

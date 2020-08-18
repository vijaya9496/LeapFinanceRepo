package com.leapfinance.assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leapfinance.assesment.model.LeapFinanceAsessment;

@Repository
public interface LeapFinanceRepository extends JpaRepository<LeapFinanceAsessment, Integer>{

	@Query(value="select l from LeapFinanceAsessment l where l.userId=:userId and l.password=:password ")
	LeapFinanceAsessment findLoginDetails(@Param("userId")String userId, @Param("password")String password);

}

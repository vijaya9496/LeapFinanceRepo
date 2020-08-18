package com.leapfinance.assesment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leapfinance.assesment.model.SaveUrlContent;

@Repository
public interface SaveUrlContentRepository extends JpaRepository<SaveUrlContent, Integer>{

	@Query(value="select s from SaveUrlContent s where s.leapFinanceAssesment.userId = :userId")
	List<SaveUrlContent> findByUserId(@Param("userId")String userId);

}

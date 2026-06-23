package com.cog.fundmatrix.repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.AuditLog;
import com.cog.fundmatrix.domain.enums.AuditAction;


@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {

	
	
//	public Page<AuditLog> findByUser_UserIdAndEntityTypeAndActionAndTimeStampBetween(UUID userId,String entityType,AuditAction action,LocalDate startDate,LocalDate endDate,Pageable pageble);
//	
//	public Page<AuditLog> findByAction(String action,Pageable pageble);
//	
//	public Page<AuditLog> findByEntityType(String entityType,Pageable pageble);
	
}

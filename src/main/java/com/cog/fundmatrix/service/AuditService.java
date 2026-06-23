package com.cog.fundmatrix.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cog.fundmatrix.domain.AuditLog;
import com.cog.fundmatrix.domain.User;
import com.cog.fundmatrix.domain.enums.AuditAction;
import com.cog.fundmatrix.repository.AuditLogRepository;
import com.cog.fundmatrix.repository.UserRepository;

@Service
public class AuditService {

	
	private AuditLogRepository auditRepo;
	
	public AuditService(AuditLogRepository auditRepo) {
		super();
		this.auditRepo = auditRepo;
	}
	
	public void saveLog(User user,AuditAction action,String entityType,String recordId)
	{
		AuditLog log=new AuditLog();
		
		log.setUser(user);
		log.setAction(action);
		log.setEntityType(entityType);
		log.setRecordId(recordId);
		log.setTimeStamp(LocalDateTime.now());
		
		auditRepo.save(log);
	}
	
	
//	Page<AuditLog> getAuditLogs(UUID userId,String entityType,AuditAction action,LocalDate startDate,LocalDate endDate)
//	{
////		return auditRepo.findByUser_UserIdAndEntityTypeAndActionAndTimeStampBetween(userId, entityType, action, startDate, endDate, PageRequest.of(0, 10))
//		return null;
//	}
//	
}

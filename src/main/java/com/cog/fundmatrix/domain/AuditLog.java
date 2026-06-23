package com.cog.fundmatrix.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import com.cog.fundmatrix.domain.enums.AuditAction;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "auditLog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID auditId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	@Enumerated(EnumType.STRING)
	
	private AuditAction action;
	
	private String entityType;
	
	
	private String recordId;
	
	private LocalDateTime timeStamp;
}

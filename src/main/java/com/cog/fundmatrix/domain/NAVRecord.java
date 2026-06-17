package com.cog.fundmatrix.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.cog.fundmatrix.domain.enums.NAVStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="nav_records")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NAVRecord{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "nav_record_id",updatable = false,nullable = false)
	private UUID navRecordId;
	
	@Column(name = "scheme_id", nullable = false)
    private String schemeId;

    @Column(name = "option_id", nullable = false)
    private String optionId;
	
	@Column(name = "nav_date",nullable = false)
	private LocalDate navDate;
	
	@Column(name = "nav_value",nullable = false)
	private BigDecimal navValue;
	
	@Column(name="total_aum")
	private BigDecimal totalAUM;
	
	@Column(name="total_units_outstanding")
	private BigDecimal totalUnitsOutstanding;
	
	@Column(name="pubblished_by_id")
	private String publishedByID;
	
	@Enumerated(EnumType.STRING)
	private NAVStatus status;
	
	
	 @CreationTimestamp
	 @Column(name = "created_at", updatable = false)
	 private LocalDateTime createdAt;

	 @UpdateTimestamp
	 @Column(name = "updated_at")
	 private LocalDateTime updatedAt;
	
}

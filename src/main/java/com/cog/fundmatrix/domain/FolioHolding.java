package com.cog.fundmatrix.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "folioHolding")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolioHolding {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID holdingId;
	
	@ManyToOne
	@JoinColumn(name = "folioId")
	private InvestorFolio folio;
	
	private BigDecimal units;
	
	private BigDecimal AverageCostNAV;
	
	private BigDecimal CurrentValue;
	private BigDecimal UnrealisedGainLoss;
	
	private LocalDateTime LastUpdated;
	
	
}

package com.cog.fundmatrix.domain;

import java.util.UUID;

import com.cog.fundmatrix.domain.enums.FolioStatus;
import com.cog.fundmatrix.domain.enums.ModeOfHolding;
import com.cog.fundmatrix.domain.enums.TaxStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


	@Getter
	@Setter
	@NoArgsConstructor
	@Entity
	@Table(name="investorFolio")
	public class InvestorFolio {

		@Id
		@GeneratedValue(strategy = GenerationType.UUID)
		private UUID folioId;
		
		
		private String investor;
		
		
		
		private String distibutor;
		
		@Enumerated(EnumType.STRING)
		private TaxStatus taxStatus;
		
		@Enumerated(EnumType.STRING)
		private ModeOfHolding modeOfHolding;
		
		private String bankAccountRef;
		
		@Enumerated(EnumType.STRING)
		private FolioStatus status;
		
		
	}



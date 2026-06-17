package com.cog.fundmatrix.domain;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.cog.fundmatrix.domain.enums.FolioStatus;
import com.cog.fundmatrix.domain.enums.ModeOfHolding;
import com.cog.fundmatrix.domain.enums.TaxStatus;
import com.cog.fundmatrix.dto.investorFolio.NomineeDetails;

import jakarta.persistence.Column;
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
		
		@ManyToOne
		@JoinColumn(name = "investorId")
		private User investor;
		
		
		@ManyToOne
		@JoinColumn(name = "distibutorId")
		private User distibutor;
		
		@Enumerated(EnumType.STRING)
		private TaxStatus taxStatus;
		
		@Enumerated(EnumType.STRING)
		private ModeOfHolding modeOfHolding;
		
		private String bankAccountRef;
		
		@Enumerated(EnumType.STRING)
		private FolioStatus status;
		
		
		@JdbcTypeCode(SqlTypes.JSON)
		@Column(columnDefinition = "json",name = "nomineeDetails")
		List<NomineeDetails> nomineeDetails;
		
		
	}



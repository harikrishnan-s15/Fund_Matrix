package com.cog.fundmatrix.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.KycStatus;
import com.cog.fundmatrix.domain.enums.KycType;

@Entity
@Table(name = "kyc_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class KycRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
   @Column(name = "investorId")
    private String investor;

    @Enumerated(EnumType.STRING)
    private KycType kycType;

   
    private String documentType;

    
    private String documentRef;

   
    private LocalDate verifiedDate;

    @Enumerated(EnumType.STRING)
    private KycStatus kycStatus;
}

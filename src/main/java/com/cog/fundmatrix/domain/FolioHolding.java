package com.cog.fundmatrix.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * The unit holding of a folio in a specific scheme option. Maintains units held,
 * average cost NAV and (NAV-derived) current value with unrealised gain/loss.
 */
@Entity
@Table(name = "folio_holdings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FolioHolding {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID holdingId;
	
    @ManyToOne
    @JoinColumn(name = "folioId")
    private InvestorFolio folio;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "scheme_id", nullable = false)
    private String scheme;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "option_id", nullable = false)
    private String option;

   
    private BigDecimal unitsHeld;

    
    private BigDecimal averageCostNav;

   
    private BigDecimal currentValue;

    
    private BigDecimal unrealisedGainLoss;

    @Column(name = "last_updated")
    private Instant lastUpdated;
}

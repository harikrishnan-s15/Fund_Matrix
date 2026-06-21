package com.cog.fundmatrix.dto.folioHolding;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PortfolioResponse(UUID investorId,BigDecimal totalValue,BigDecimal totalUnrelisedGainLoss,List<HoldingSummary> holdingSummaries) {

}

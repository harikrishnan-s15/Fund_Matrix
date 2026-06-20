package com.cog.fundmatrix.dto.folioHolding;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record FolioHoldingDto(
        UUID id,
        UUID folioId,
        String schemeId,
        String optionId,
        BigDecimal unitsHeld,
        BigDecimal averageCostNav,
        BigDecimal latestNav,
        BigDecimal currentValue,
        BigDecimal unrealisedGainLoss,
        LocalDateTime updated
) {
}

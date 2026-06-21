package com.cog.fundmatrix.dto.folioHolding;

import java.math.BigDecimal;
import java.util.UUID;

public record HoldingSummary(String schemeId,String schemeName,BigDecimal unitsheld,BigDecimal currentValue,BigDecimal unRelisedGainLoss) {

}

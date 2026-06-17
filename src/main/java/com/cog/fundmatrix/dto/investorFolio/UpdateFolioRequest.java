package com.cog.fundmatrix.dto.investorFolio;

import java.util.List;
import java.util.UUID;

import com.cog.fundmatrix.domain.enums.FolioStatus;
import com.cog.fundmatrix.domain.enums.ModeOfHolding;
import com.cog.fundmatrix.domain.enums.TaxStatus;

public record UpdateFolioRequest(
         TaxStatus taxStatus,
         ModeOfHolding modeOfHolding,
         FolioStatus status,
         List<NomineeDetails> nomineeDetails,
         String bankAccountRef
         ) {

}

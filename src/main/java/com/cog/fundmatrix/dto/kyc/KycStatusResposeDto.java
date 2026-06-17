package com.cog.fundmatrix.dto.kyc;

import java.util.UUID;

import com.cog.fundmatrix.domain.enums.KycStatus;

public record KycStatusResposeDto(UUID id,KycStatus status) {

}

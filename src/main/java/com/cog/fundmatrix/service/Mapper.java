package com.cog.fundmatrix.service;

import com.cog.fundmatrix.domain.*;
import com.cog.fundmatrix.dto.*;
import com.cog.fundmatrix.dto.folioHolding.FolioHoldingDto;
import com.cog.fundmatrix.dto.user.UserDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Mapper {

    public UserDto toUserDto(User u) {
        return new UserDto(u.getId(), u.getName(), u.getEmail(), u.getPhone(),
                u.getRole(), u.getStatus());
    }
    
    
    public FolioHoldingDto toFolioHolding(FolioHolding holding)
    {
    	return new FolioHoldingDto(holding.getHoldingId(),holding.getFolio().getFolioId(),holding.getSchemeId(),holding.getOptionId(),holding.getUnits(),holding.getAverageCostNAV(),holding.getLatestNavl(),holding.getCurrentValue(),holding.getUnrealisedGainLoss(),holding.getLastUpdated());
    }

}

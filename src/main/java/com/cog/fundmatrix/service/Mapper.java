package com.cog.fundmatrix.service;

import com.cog.fundmatrix.domain.*;
import com.cog.fundmatrix.dto.*;
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

}

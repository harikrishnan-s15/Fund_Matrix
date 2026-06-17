package com.cog.fundmatrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Controller
@RequestMapping("/api/kyc-record")
@SecurityRequirement(name = "bearerAuth")
public class KycController {

	
	
}

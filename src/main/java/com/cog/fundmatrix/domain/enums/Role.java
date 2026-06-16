package com.cog.fundmatrix.domain.enums;

/**
 * System roles driving role-based access control (RBAC).
 * Spring Security authorities are derived as {@code ROLE_<name>}.
 */
public enum Role {
    INVESTOR,
    DISTRIBUTOR,
    FUND_OPS,
    FUND_ACCOUNTANT,
    COMPLIANCE,
    ADMIN
}

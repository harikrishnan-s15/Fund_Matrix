package com.cog.fundmatrix.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.KycRecord;


@Repository
public interface KycRecordRepository extends JpaRepository<KycRecord, UUID>{
	public Optional<KycRecord> findByInvestor_UserId(UUID id);
}

package com.cog.fundmatrix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.FolioHolding;


@Repository
public interface FolioHoldingRepository extends JpaRepository<FolioHolding, UUID> {

	public List<FolioHolding> findAllByFolio_FolioId(UUID folioId);
	public List<FolioHolding> findAllByFolio_FolioIdIn(List<UUID> folioIds);
}

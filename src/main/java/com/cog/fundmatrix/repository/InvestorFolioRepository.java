package com.cog.fundmatrix.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.InvestorFolio;


@Repository
public interface InvestorFolioRepository extends JpaRepository<InvestorFolio, UUID> {

//    Optional<InvestorFolio> findByFolioNumber(String folioNumber);
//
//    boolean existsByFolioNumber(String folioNumber);
//
//    List<InvestorFolio> findByInvestor_Id(Long investorId);
//
//    List<InvestorFolio> findByDistributor_Id(Long distributorId);
}

package com.cog.fundmatrix.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cog.fundmatrix.domain.NAVRecord;
import com.cog.fundmatrix.domain.enums.NAVStatus;
import com.cog.fundmatrix.dto.NAVRecordDto.LatestNAVSummary;
import com.cog.fundmatrix.dto.NAVRecordDto.NAVResponse;

@Repository
public interface NAVRecordRepository extends JpaRepository<NAVRecord, UUID> {
	
	List<NAVRecord> findBySchemeIdOrderByNavDateDesc(String schemeId);
	
	boolean existsBySchemeIdAndOptionIdAndNavDate(
	        String schemeId, String optionId,LocalDate navDate
	    );

	@Query("""
		    SELECT new com.cog.fundmatrix.dto.NAVRecordDto$LatestNAVSummary(
		        n.schemeId, n.optionId, n.navDate,
		        n.navValue, n.totalAUM, n.status
		    )
		    FROM NAVRecord n
		    WHERE n.navDate = (
		        SELECT MAX(n2.navDate)
		        FROM NAVRecord n2
		        WHERE n2.schemeId = n.schemeId AND n2.optionId = n.optionId
		    )
		    AND n.status = :status
		    ORDER BY n.schemeId
		    """)
	List<LatestNAVSummary> findLatestPublishedNAVForAllSchemes(
	    @Param("status") NAVStatus status
	);
	
	
	boolean existsBySchemeIdAndOptionIdAndNavDateAndStatusNot(
		    String schemeId, String optionId, LocalDate navDate, NAVStatus status
	);
	
	Optional<NAVRecord> findBySchemeIdAndOptionIdAndNavDateAndStatusNot(
		    String schemeId, String optionId, LocalDate navDate, NAVStatus status
		);
}

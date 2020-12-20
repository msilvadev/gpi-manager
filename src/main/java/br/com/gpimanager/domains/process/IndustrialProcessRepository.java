package br.com.gpimanager.domains.process;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustrialProcessRepository extends JpaRepository<IndustrialProcess, Long> {

    @Query("SELECT COUNT(p) FROM IndustrialProcess p WHERE p.processType = :processType and p.processStatus = :processStatus")
    int countByProcessTypeAndProcessStatus(@Param("processType") ProcessType processType,
                                           @Param("processStatus") ProcessStatus processStatus);
}

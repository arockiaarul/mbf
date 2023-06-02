package com.youcloud.mbf.repository;

import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.entity.MerchantMasterEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbfDataRepository extends JpaRepository<MbfDataEntity, Long> {

    List<MbfDataEntity> findByStatus(Integer status);

    List<MerchantMasterEntity> findByGroupId(String groupId);

}

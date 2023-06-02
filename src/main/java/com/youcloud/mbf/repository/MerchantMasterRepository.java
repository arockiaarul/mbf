package com.youcloud.mbf.repository;

import com.youcloud.mbf.entity.MerchantMasterEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantMasterRepository extends JpaRepository<MerchantMasterEntity, Integer> {

    List<MerchantMasterEntity> findByGroupMid(String groupMid);

    List<MerchantMasterEntity> findByMid(String mid);

    Optional<MerchantMasterEntity> findFirstByMid(String mid);

    Optional<MerchantMasterEntity> findByCompanyId(Integer companyId);

    List<MerchantMasterEntity> findAllByMidAndMerchantToken(String mid, String merchantToken);
}

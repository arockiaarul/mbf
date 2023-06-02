package com.youcloud.mbf.service.impl;

import com.youcloud.mbf.entity.MerchantMasterEntity;
import com.youcloud.mbf.repository.MerchantMasterRepository;
import com.youcloud.mbf.service.MerchantMasterService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MerchantMasterServiceImpl implements MerchantMasterService {

    private MerchantMasterRepository merchantMasterRepository;


    @Override
    public List<MerchantMasterEntity> findByGroupMid(String groupMid) {
        var lstMerchantMaster = merchantMasterRepository.findByGroupMid(groupMid);
        log.debug("lstMerchantMaster: {}", lstMerchantMaster);
        return lstMerchantMaster;
    }

    public List<MerchantMasterEntity> validateSoftPOSMerchant(String mid, String merchantToken) {
        var lstMerchantMaster = merchantMasterRepository.findAllByMidAndMerchantToken(mid, merchantToken);
        log.debug("lstMerchantMaster: {}", lstMerchantMaster);
        return lstMerchantMaster;
    }

    @Override
    public MerchantMasterEntity getMerchantMaster(String companyId) {
        MerchantMasterEntity merchantMasterEntity = merchantMasterRepository.findByCompanyId(Integer.parseInt(companyId)).orElse(null);
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        return merchantMasterEntity;
    }

    @Override
    public MerchantMasterEntity saveMerchantMaster(MerchantMasterEntity merchantMasterEntity) {
        return merchantMasterRepository.saveAndFlush(merchantMasterEntity);
    }
}

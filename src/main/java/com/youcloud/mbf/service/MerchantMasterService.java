package com.youcloud.mbf.service;

import com.youcloud.mbf.entity.MerchantMasterEntity;
import java.util.List;

public interface MerchantMasterService {

    List<MerchantMasterEntity> findByGroupMid(String groupMid);

    List<MerchantMasterEntity> validateSoftPOSMerchant(String mid, String merchantToken);

    MerchantMasterEntity getMerchantMaster(String companyId);

    MerchantMasterEntity saveMerchantMaster(MerchantMasterEntity merchantMasterEntity);
}

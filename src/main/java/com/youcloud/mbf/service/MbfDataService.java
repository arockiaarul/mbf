package com.youcloud.mbf.service;

import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.entity.MerchantMasterEntity;
import java.util.List;

public interface MbfDataService {

    List<MbfDataEntity> getActiveStatusMbfDataEntityList();

    List<MerchantMasterEntity> findByGroupId(String groupId);

    List<MbfDataEntity> parseMdfDataAndSaveMerchantMaster();
}

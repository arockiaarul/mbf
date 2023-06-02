package com.youcloud.mbf.service.impl;

import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.entity.MerchantMasterEntity;
import com.youcloud.mbf.parser.MbfDataParser;
import com.youcloud.mbf.repository.MbfDataRepository;
import com.youcloud.mbf.repository.MerchantMasterRepository;
import com.youcloud.mbf.service.MbfDataService;
import com.youcloud.mbf.service.MerchantMasterService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class MbfDataServiceImpl implements MbfDataService {

    private MbfDataRepository mbfDataRepository;
    //private MerchantMasterRepository merchantMasterRepository;
    private MerchantMasterService merchantMasterService;

    @Override
    public List<MbfDataEntity> getActiveStatusMbfDataEntityList() {
        return mbfDataRepository.findByStatus(1);
    }

    public List<MbfDataEntity> updateMbfDataEntityList(List<MbfDataEntity> mbfDataEntityList) {
        return mbfDataRepository.saveAllAndFlush(mbfDataEntityList);
    }

    @Override
    public List<MerchantMasterEntity> findByGroupId(String groupId) {
        return null;
    }

    @Override
    public List<MbfDataEntity> parseMdfDataAndSaveMerchantMaster() {
        List<MbfDataEntity> mbfDataEntityList = getActiveStatusMbfDataEntityList();
        log.info("mbfDataEntityList: {}", mbfDataEntityList);
        mbfDataEntityList.forEach(mbfDataEntity -> {
            MbfDataParser mbfDataParser = new MbfDataParser(mbfDataEntity.getMbf());
            log.info("mbfDataParser: {}", mbfDataParser);
            List<FileDetail> fileDetailList = mbfDataParser.getDetailList();
            /*fileDetailList.stream().forEach(fileDetail ->  {
                MerchantMasterEntity merchantMasterEntity = getMerchantMasterEntity(fileDetail);
                merchantMasterService.saveMerchantMaster(merchantMasterEntity);
            });*/

            mbfDataEntity.setStatus(0);
        });
        mbfDataEntityList = updateMbfDataEntityList(mbfDataEntityList);
        log.info("mbfDataEntityList: {}", mbfDataEntityList);
        return mbfDataEntityList;
    }

    private MerchantMasterEntity getMerchantMasterEntity(FileDetail fileDetail) {
        MerchantMasterEntity merchantMasterEntity = MerchantMasterEntity.builder()
                .abn(String.valueOf(fileDetail.getAbn()))
                .acn(String.valueOf(fileDetail.getAcn()))
                /*.accountDtOpen()
                .accountNo()
                .accountType()
                .address()
                .addressRes()
                .allowedTotAmtPerDay()
                .amountPerTrans()
                .applicationId()
                .areaCode()
                .authBy()
                .authDt()
                .authorizedPerson()
                .authStatus()*/
                .averageBillAmount(fileDetail.getAverageTicketOrSalesAmount())
                /*.bankerBranchName()
                .bankName()
                .businessHrs()
                .businessIncome()
                .businessLine()
                .businessType()
                .cdrPrimary()
                .cityCode()
                //.companyId() // pk_id field
                .companyName()
                .countryCode()
                .createdBy()
                .createdDt()
                .dlFlag()
                .dmtAccountName()
                .dmtAccountNo()
                .dmtIfscCode()*/
                .emailId(fileDetail.getPosVendorEmail())
                /*.establishmentNo()
                .establishmentYrs()
                .expectedCardBusinessPerMonth()
                .faxNo()*/
                .firstName(fileDetail.getPosVendorName())
                /*.groupMid()
                .ifscCode()
                .ifscNo()
                .isMatmWallet()
                .lastName()
                .locationBound()
                .matmWallet()*/
                .mccCode(fileDetail.getMcc())
                /*.mdr()
                .mdrCommission()
                .mdrNonPrimary()
                .merchantFrequency()
                .merchantToken()
                .mobileNo()
                .modifyBy()
                .modifyDt()
                .noPosRequired()
                .microAtmStatus()
                .officeNo()
                .officePremisesStatus()
                .ownerName()*/
                .mid(String.valueOf(fileDetail.getMerchantNumber()))
                /*.panTan()
                .posMachineType()
                .pinCode()*/
                .printedName(fileDetail.getTradingBusinessName())
                /*.referenceId()
                .remark()
                .saleTaxNo()
                .status()
                .terminalIssue()
                .tinNo()
                .totAmtPerDay()
                .transDate()
                .transPerDay()*/
                .turnover1(fileDetail.getTotalAnnualCreditTurnover())
                /*.turnover2()
                .turnover3()
                .turnoverYear1()
                .turnoverYear2()
                .turnoverYear3()*/
                .typeEstablishment(fileDetail.getTypeOfBusiness())
                /*.virtualAccountno()
                .virtualIfsccode()
                .walletAccountNo()
                .walletBalance()
                .walletIfscCode()
                .yrsCurrentLocation()*/
                .build();
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        return merchantMasterEntity;
    }
}

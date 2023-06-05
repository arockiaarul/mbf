package com.youcloud.mbf.service.impl;

import com.youcloud.mbf.common.util.DateUtil;
import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.entity.MerchantMasterEntity;
import com.youcloud.mbf.parser.MbfDataParser;
import com.youcloud.mbf.repository.MbfDataRepository;
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
        try {
            mbfDataEntityList.forEach(mbfDataEntity -> {
                MbfDataParser mbfDataParser = new MbfDataParser(mbfDataEntity.getMbf());
                log.info("mbfDataParser: {}", mbfDataParser);
                List<FileDetail> fileDetailList = mbfDataParser.getDetailList();
                log.info("<----------------------------------- List<FileDetail>: {}", fileDetailList);
                fileDetailList.stream().forEach(fileDetail -> {
                    MerchantMasterEntity merchantMasterEntity = getMerchantMasterEntity(fileDetail);
                    merchantMasterService.saveMerchantMaster(merchantMasterEntity);
                });

                mbfDataEntity.setStatus(0);
            });
            mbfDataEntityList = updateMbfDataEntityList(mbfDataEntityList);
            log.info("mbfDataEntityList: {}", mbfDataEntityList);
        } catch (Exception e) {
            log.error("Exception:" + e.getMessage(), e);
        }
        return mbfDataEntityList;
    }

    private MerchantMasterEntity getMerchantMasterEntity(FileDetail fileDetail) {
        log.info("<-----------------------------------********* FileDetail: {}", fileDetail);
        MerchantMasterEntity merchantMasterEntity = MerchantMasterEntity.builder().build();
        merchantMasterEntity.setAbn(String.valueOf(fileDetail.getAbn()));
        log.debug("getAbn: {}", String.valueOf(fileDetail.getAbn()));
        merchantMasterEntity.setAcn(String.valueOf(fileDetail.getAcn()));
        log.debug("getAbn: {}", String.valueOf(fileDetail.getAcn()));
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        merchantMasterEntity.setAddress(new StringBuilder()
                        .append(fileDetail.getLegalAddressLine1()).append(" ")
                        .append(fileDetail.getLegalAddressLine2()).append(" ")
                        .append(fileDetail.getLegalAddressLine3()).append(" ")
                        .append(fileDetail.getLegalAddressLine4()).append(" ")
                        .append(fileDetail.getLegalAddressCity()).append(" ")
                        .append(fileDetail.getLegalAddressState()).append(" ")
                        .append(fileDetail.getLegalAddressPostcode()).append(" ")
                        .append(fileDetail.getLegalAddressCountry()).append(" ")
                        .toString());
        log.debug("setAddress: {}", new StringBuilder()
                .append(fileDetail.getLegalAddressLine1()).append(" ")
                .append(fileDetail.getLegalAddressLine2()).append(" ")
                .append(fileDetail.getLegalAddressLine3()).append(" ")
                .append(fileDetail.getLegalAddressLine4()).append(" ")
                .append(fileDetail.getLegalAddressCity()).append(" ")
                .append(fileDetail.getLegalAddressState()).append(" ")
                .append(fileDetail.getLegalAddressPostcode()).append(" ")
                .append(fileDetail.getLegalAddressCountry()).append(" ")
                .toString());
        merchantMasterEntity.setAddressRes(new StringBuilder()
                        .append(fileDetail.getTradingAddressContactName()).append(" ")
                        .append(fileDetail.getTradingAddressLine1()).append(" ")
                        .append(fileDetail.getTradingAddressLine2()).append(" ")
                        .append(fileDetail.getTradingAddressLine3()).append(" ")
                        .append(fileDetail.getTradingAddressLine4()).append(" ")
                        .append(fileDetail.getTradingAddressCity()).append(" ")
                        .append(fileDetail.getTradingAddressState()).append(" ")
                        .append(fileDetail.getTradingAddressCountry()).append(" ")
                        .toString());
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        merchantMasterEntity.setAuthorizedPerson(fileDetail.getLegalAddressContactName());
        log.debug("fileDetail.getLegalAddressContactName(): {}", fileDetail.getLegalAddressContactName());
        merchantMasterEntity.setAverageBillAmount(fileDetail.getAverageTicketOrSalesAmount());
        log.debug("fileDetail.getAverageTicketOrSalesAmount(): {}", fileDetail.getAverageTicketOrSalesAmount());
        merchantMasterEntity.setCompanyName(fileDetail.getTradingBusinessName());
        log.debug("fileDetail.getTradingBusinessName(): {}", fileDetail.getTradingBusinessName());
        merchantMasterEntity.setBusinessType(fileDetail.getPrincipal1Type());
        merchantMasterEntity.setEmailId(fileDetail.getPosVendorEmail());
        merchantMasterEntity.setExpectedCardBusinessPerMonth(fileDetail.getTotalAnnualCreditTurnover());
        log.debug("fileDetail.getPrincipal1Type(): {}", fileDetail.getPrincipal1Type());
        log.debug("fileDetail.getPosVendorEmail(): {}", fileDetail.getPosVendorEmail());
        log.debug("fileDetail.getTotalAnnualCreditTurnover(): {}", fileDetail.getTotalAnnualCreditTurnover());
        merchantMasterEntity.setFirstName(fileDetail.getPrincipal1FirstName());
        merchantMasterEntity.setGroupMid(fileDetail.getParentClient());
        merchantMasterEntity.setLastName(fileDetail.getPrincipal1MiddleInitial() + " " + fileDetail.getPrincipal1Surname());
        merchantMasterEntity.setMccCode(fileDetail.getMcc());
        log.debug("fileDetail.getMcc(): {}", fileDetail.getMcc());
        merchantMasterEntity.setOwnerName(fileDetail.getLegalName());
        merchantMasterEntity.setMid(String.valueOf(fileDetail.getMerchantNumber()));
        merchantMasterEntity.setPinCode(fileDetail.getTradingAddressPostcode());
        merchantMasterEntity.setPrintedName(fileDetail.getTradingBusinessName());
        merchantMasterEntity.setTerminalIssue(fileDetail.getTermQty());
        merchantMasterEntity.setTransDate(fileDetail.getSignedDate() != null ? DateUtil.getDateInDDMMYYYY(fileDetail.getSignedDate()) : null);
        merchantMasterEntity.setTurnover1(fileDetail.getTotalAnnualCashOrCreditDebitTurnover());
        merchantMasterEntity.setTypeEstablishment(fileDetail.getTypeOfBusiness());
        merchantMasterEntity.setWalletBalance(0d);
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        return merchantMasterEntity;
    }

    private MerchantMasterEntity getMerchantMasterEntityOld(FileDetail fileDetail) {
        log.info("<-----------------------------------********* FileDetail: {}", fileDetail);
        MerchantMasterEntity merchantMasterEntity = MerchantMasterEntity.builder()
                .abn(String.valueOf(fileDetail.getAbn()))
                .acn(String.valueOf(fileDetail.getAcn()))
                .address(new StringBuilder()
                        .append(fileDetail.getLegalAddressLine1()).append(" ")
                        .append(fileDetail.getLegalAddressLine2()).append(" ")
                        .append(fileDetail.getLegalAddressLine3()).append(" ")
                        .append(fileDetail.getLegalAddressLine4()).append(" ")
                        .append(fileDetail.getLegalAddressCity()).append(" ")
                        .append(fileDetail.getLegalAddressState()).append(" ")
                        .append(fileDetail.getLegalAddressPostcode()).append(" ")
                        .append(fileDetail.getLegalAddressCountry()).append(" ")
                        .toString())
                .addressRes(new StringBuilder()
                        .append(fileDetail.getTradingAddressContactName()).append(" ")
                        .append(fileDetail.getTradingAddressLine1()).append(" ")
                        .append(fileDetail.getTradingAddressLine2()).append(" ")
                        .append(fileDetail.getTradingAddressLine3()).append(" ")
                        .append(fileDetail.getTradingAddressLine4()).append(" ")
                        .append(fileDetail.getTradingAddressCity()).append(" ")
                        .append(fileDetail.getTradingAddressState()).append(" ")
                        .append(fileDetail.getTradingAddressCountry()).append(" ")
                        .toString())
                /*.accountDtOpen()
                .accountNo()
                .accountType()
                .allowedTotAmtPerDay()
                .amountPerTrans()
                .applicationId()
                .areaCode()
                .authBy()
                .authDt()
                .authStatus()*/
                .authorizedPerson(fileDetail.getLegalAddressContactName())
                .averageBillAmount(fileDetail.getAverageTicketOrSalesAmount())
                .companyName(fileDetail.getTradingBusinessName())
                /*.bankerBranchName()
                .bankName()
                .businessHrs()
                .businessIncome()
                .businessLine()*/
                .businessType(fileDetail.getPrincipal1Type())
                /*.cdrPrimary()
                .cityCode()
                //.companyId() // pk_id field
                .countryCode()
                .createdBy()
                .createdDt()
                .dlFlag()
                .dmtAccountName()
                .dmtAccountNo()
                .dmtIfscCode()*/
                .emailId(fileDetail.getPosVendorEmail())
                .expectedCardBusinessPerMonth(fileDetail.getTotalAnnualCreditTurnover())
                /*.establishmentNo()
                .establishmentYrs()
                .faxNo()*/
                .firstName(fileDetail.getPrincipal1FirstName()) // .firstName(fileDetail.getPosVendorName())
                .groupMid(fileDetail.getParentClient())
                /*.ifscCode()
                .ifscNo()
                .isMatmWallet()
                .locationBound()
                .matmWallet()*/
                .lastName(fileDetail.getPrincipal1MiddleInitial() + " " + fileDetail.getPrincipal1Surname())
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
                .officePremisesStatus()*/
                .ownerName(fileDetail.getLegalName())
                .mid(String.valueOf(fileDetail.getMerchantNumber()))
                /*.panTan()
                .posMachineType()*/
                .pinCode(fileDetail.getTradingAddressPostcode())
                .printedName(fileDetail.getTradingBusinessName())
                /*.referenceId()
                .remark()
                .saleTaxNo()
                .status()
                .tinNo()
                .totAmtPerDay()
                .transPerDay()*/
                .terminalIssue(fileDetail.getTermQty())
                .transDate(fileDetail.getSignedDate() != null ? DateUtil.getDateInDDMMYYYY(fileDetail.getSignedDate()) : null)
                .turnover1(fileDetail.getTotalAnnualCashOrCreditDebitTurnover())
                /*.turnover2()
                .turnover3()
                .turnoverYear1()
                .turnoverYear2()
                .turnoverYear3()*/
                .typeEstablishment(fileDetail.getTypeOfBusiness())
                /*.virtualAccountno()
                .virtualIfsccode()
                .walletAccountNo()
                .walletIfscCode()
                .yrsCurrentLocation()*/
                .walletBalance(0d)
                .build();
        log.debug("merchantMasterEntity: {}", merchantMasterEntity);
        return merchantMasterEntity;
    }
}

/*
* JOB ID for
MBF - TT4220MB
DE - TT422DE1
* */
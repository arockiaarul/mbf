package com.youcloud.mbf.parser;

import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.dto.FileHeader;
import com.youcloud.mbf.dto.FileTrailer;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import static com.youcloud.mbf.common.constant.MbfDataConstant.DATA_SEPARATOR;

@Slf4j
@Data
//@Builder
/*@AllArgsConstructor
@RequiredArgsConstructor*/
//@NoArgsConstructor
//@Component
public class MbfDataParser {

    private String data;

    /**
     * Start with a Descriptive / Header Record.
     */
    private FileHeader header;

    /**
     * Contain one or more Detail Record(s).
     */
    private List<FileDetail> detailList;

    /**
     * End with a Batch Control / File Trailer Record.
     */
    private FileTrailer trailer;

    public MbfDataParser(String data) {
        this.data = data;
        parseData();
    }

    public void parseData() {
        String[] dataArray = this.data.replace(" D~", "~D~").replace(" T~", "~T~").split(DATA_SEPARATOR); // String[] dataArray = mdfData.split(DATA_SEPARATOR);
        int dataArrayLength = dataArray.length;
        FileHeader fileHeader = validateAndGetFileHeader(dataArray, dataArrayLength);
        this.header = fileHeader;
        FileTrailer fileTrailer = validateAndGetFileTrailer(dataArray, dataArrayLength);
        this.trailer = fileTrailer;
        List<FileDetail> fileDetailList = validateAndGetFileDetail(dataArray, dataArrayLength, fileTrailer.getTotalMerchantRecords());
        this.detailList = fileDetailList;
        /*String[] detailArray = new String[dataArrayLength-9];
        if (dataArrayLength >249) {
            System.arraycopy(dataArray, 7, detailArray, 0, dataArrayLength-9);
        }
        String[] trailerArray = new String[2];
        if (dataArrayLength >251) {
            System.arraycopy(dataArray, detailArray.length-3, trailerArray, 0, 2);
        }
        return null;*/
    }

    private FileHeader validateAndGetFileHeader(String[] dataArray, int dataArrayLength) {
        FileHeader fileHeader = FileHeader.builder().build();
        if (dataArrayLength>6 && "H".equals(dataArray[0]) &&  dataArray[1].equals("MBF")) {
            fileHeader.setRecordType(dataArray[0]);
            fileHeader.setFileType(dataArray[1]);
            fileHeader.setInstitutionNumber(dataArray[2]);
            fileHeader.setInstitutionName(dataArray[3]);
            fileHeader.setThirdPartyProcessorName(dataArray[4]);
            fileHeader.setTppId(Long.valueOf(dataArray[5]));
            fileHeader.setTimeStamp(dataArray[6]);
            log.info(" fileHeader: " + fileHeader);
        }
        return  fileHeader;
    }


    private List<FileDetail> validateAndGetFileDetail(String[] dataArray, int dataArrayLength, Integer totalMerchantRecords) {
        log.info(" totalMerchantRecords: " + totalMerchantRecords);
        int detailRecords = (dataArrayLength - 9) / 243;
        log.info(" detailRecords: " + detailRecords);
        List<FileDetail> fileDetailList = new ArrayList<>(); //new ArrayList<>(detailRecords);
        String[] detailArray = new String[dataArrayLength-9];
        if (dataArrayLength >249) {
            System.arraycopy(dataArray, 7, detailArray, 0, dataArrayLength-9);
        }
        log.info(" detailArray.length: " + detailArray.length);
        if (dataArrayLength >249 && totalMerchantRecords != null && detailRecords == totalMerchantRecords) {
            for (int recordCounter = 0; recordCounter < totalMerchantRecords; recordCounter++) {
                int fieldCounter = 243 * recordCounter;
                log.debug("detailArray[fieldCounter +32]" + detailArray[fieldCounter +32]);
                FileDetail fileDetail = FileDetail.builder()
                        .recordType(detailArray[fieldCounter +0])
                        .merchantNumber(Long.valueOf(detailArray[fieldCounter +1]))
                        .merchantLevel(detailArray[fieldCounter +2])
                        .parentClient(detailArray[fieldCounter +3])
                        .tradingBusinessName(detailArray[fieldCounter +4])
                        .legalName(detailArray[fieldCounter +5])
                        .abn(Long.valueOf(detailArray[fieldCounter +6]))
                        .acn(Long.valueOf(detailArray[fieldCounter +7]))
                        .webAddress(detailArray[fieldCounter +8])
                        .dcc(detailArray[fieldCounter +9])
                        .typeOfBusiness(detailArray[fieldCounter +10])
                        .mcc(Integer.valueOf(detailArray[fieldCounter +11]))
                        .ecommerceEnabled(detailArray[fieldCounter +12])
                        .tradingAddressContactName(detailArray[fieldCounter +13])
                        .tradingAddressLine1(detailArray[fieldCounter +14])
                        .tradingAddressLine2(detailArray[fieldCounter +15])
                        .tradingAddressLine3(detailArray[fieldCounter +16])
                        .tradingAddressLine4(detailArray[fieldCounter +17])
                        .tradingAddressCity(detailArray[fieldCounter +18])
                        .tradingAddressState(detailArray[fieldCounter +19])
                        .tradingAddressPostcode(detailArray[fieldCounter +20])
                        .tradingAddressCountry(detailArray[fieldCounter +21])
                        .legalAddressContactName(detailArray[fieldCounter +22])
                        .legalAddressLine1(detailArray[fieldCounter +23])
                        .legalAddressLine2(detailArray[fieldCounter +24])
                        .legalAddressLine3(detailArray[fieldCounter +25])
                        .legalAddressLine4(detailArray[fieldCounter +26])
                        .legalAddressCity(detailArray[fieldCounter +27])
                        .legalAddressState(detailArray[fieldCounter +28])
                        .legalAddressPostcode(detailArray[fieldCounter +29])
                        .legalAddressCountry(detailArray[fieldCounter +30])
                        .signedDate(detailArray[fieldCounter +31])
                        .totalAnnualCashOrCreditDebitTurnover(Strings.isBlank(detailArray[fieldCounter +32]) ? null : Double.valueOf(detailArray[fieldCounter +32].trim()) )
                        .totalAnnualCreditTurnover(Strings.isBlank(detailArray[fieldCounter +33]) ? null : Double.valueOf(detailArray[fieldCounter +33].trim()))
                        .averageTicketOrSalesAmount(Strings.isBlank(detailArray[fieldCounter +34]) ? null : Double.valueOf(detailArray[fieldCounter +34].trim()))
                        .captureMethod(detailArray[fieldCounter +35])
                        .depositRequired(detailArray[fieldCounter +36])
                        .depositPercentage(detailArray[fieldCounter +37])
                        .averageDeliveryTimeframe(detailArray[fieldCounter +38])
                        .recurringTran(detailArray[fieldCounter +39])
                        .terminalType(detailArray[fieldCounter +40])
                        .configCode(detailArray[fieldCounter +41])
                        .termQty(Strings.isNotBlank(detailArray[fieldCounter +42]) ? Integer.valueOf(detailArray[fieldCounter +42].trim()) : null)
                        .acquiringAusdebit(detailArray[fieldCounter +43])
                        .acquiringVisaMasterCard(detailArray[fieldCounter +44])
                        .acquiringAmex(detailArray[fieldCounter +45])
                        .amexExisting(detailArray[fieldCounter +46])
                        .amexSENumber(detailArray[fieldCounter +47])
                        .amexNewRequired(detailArray[fieldCounter +48])
                        .acquiringDiners(detailArray[fieldCounter +49])
                        .dinersSENumber(detailArray[fieldCounter +50])
                        //todo
                        .principal3State(detailArray[fieldCounter+93])
                        .principal3Postcode(detailArray[fieldCounter+94])
                        .principal3Country(detailArray[fieldCounter+95])
                        .principal3PhoneNumber(detailArray[fieldCounter+96])
                        .principal3PassportNumber(detailArray[fieldCounter+97])
                        //todo
                        .placeholder242(detailArray[fieldCounter+241])
                        .placeholder243(detailArray[fieldCounter+242])
                        .build();
                log.info(" fileDetail: " + fileDetail);
                fileDetailList.add(fileDetail);
            }
        }
        log.info("<-------------------------- fileDetailList: " + fileDetailList);
        return fileDetailList;
    }

    private FileTrailer validateAndGetFileTrailer(String[] dataArray, int dataArrayLength) {
        FileTrailer fileTrailer = FileTrailer.builder().build();
        if (dataArrayLength >251) {
            String trailerRecordType = dataArray[dataArrayLength -2].trim();
            if ("T".equals(trailerRecordType)) {
                fileTrailer.setRecordType(trailerRecordType);
                fileTrailer.setTotalMerchantRecords(Integer.valueOf(dataArray[dataArrayLength -1]));
                log.info(" fileTrailer: " + fileTrailer);
            }
        }
        return fileTrailer;
    }

}

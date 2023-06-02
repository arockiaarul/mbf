package com.youcloud.mbf.parser;

import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.dto.FileHeader;
import com.youcloud.mbf.dto.FileTrailer;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
                FileDetail fileDetail = FileDetail.builder()
                        .recordType(detailArray[fieldCounter +0])
                        .merchantNumber(Long.valueOf(detailArray[fieldCounter +1]))
                        .merchantLevel(detailArray[fieldCounter +2])
                        .parentClient(detailArray[fieldCounter +3]) //todo

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

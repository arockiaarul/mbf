package com.youcloud.mbf.parser;

import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.dto.FileHeader;
import com.youcloud.mbf.dto.FileTrailer;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.youcloud.mbf.common.constant.MbfDataConstant.DATA_SEPARATOR;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
public class MbfDataParserBack {

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

    /*public MbfDataParser(String data) {
        this.data = data;
    }*/
    public String[] parseData() {
        String[] dataArray = this.data.replace(" D~", "~D~").replace(" T~", "~T~").split(DATA_SEPARATOR); // String[] dataArray = mdfData.split(DATA_SEPARATOR);
        int dataArrayLength = dataArray.length;
        FileHeader fileHeader = validateAndGetFileHeader(dataArray, dataArrayLength);
        String[] headerArray = new String[7];
        if (dataArrayLength >= 7) {
            System.arraycopy(dataArray, 0, headerArray, 0, 7);
        }
        validateAndGetFileTrailer(dataArray, dataArrayLength);
        String[] detailArray = new String[dataArrayLength-9];
        if (dataArrayLength >249) {
            System.arraycopy(dataArray, 7, detailArray, 0, dataArrayLength-9);
        }
        String[] trailerArray = new String[2];
        if (dataArrayLength >251) {
            System.arraycopy(dataArray, detailArray.length-3, trailerArray, 0, 2);
        }
        return null;
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

    private FileHeader validateAndGetFileHeader(String[] dataArray, int dataArrayLength) {
        /*if(headerArray[0].equals("H") &  headerArray[1].equals("MBF"))
        {
            log.info("Header Match");
            headermatch= true;

        } else
        {
            log.info("No Match");
        }*/
        FileHeader fileHeader = FileHeader.builder().build();
        if (dataArrayLength>6 && "H".equals(dataArray[0])) {
            fileHeader.setRecordType(dataArray[0]);
            fileHeader.setFileType(dataArray[1]);
            fileHeader.setInstitutionNumber(dataArray[2]);
            fileHeader.setInstitutionName(dataArray[3]);
            fileHeader.setThirdPartyProcessorName(dataArray[4]);
            fileHeader.setTppId(Long.valueOf(dataArray[5]));
            fileHeader.setTimeStamp(dataArray[6]);
        }
        return  fileHeader;
    }
}

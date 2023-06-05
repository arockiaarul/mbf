package com.youcloud.mbf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import static com.youcloud.mbf.common.constant.MbfDataConstant.DATA_SEPARATOR;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class FileHeader {

    /**
     * Record Type , M, H, 2, AN, Constant ‘H’ value
     */
    @Builder.Default
    private String recordType = "H";

    /**
     *
     * File Type, M, MBF, 20, AN, File Type for this extract
     */
    @Builder.Default
    private String fileType = "MBF";

    /**
     *
     * Institution Number, M, 00000021, 8, N, Eight digit Institution number – Always 00000021
     */
    @Builder.Default
    private String institutionNumber = "00000021";

    /**
     *
     * Institution Name, M, FDMSA, 20, AN, Institution Name – Always FDMSA
     */
    @Builder.Default
    private String institutionName = "FDMSA";

    /**
     *
     * Third-Party Processor Name, M, 40, AN, TPP name as registered with Fiserv
     */
    private String thirdPartyProcessorName;

    /**
     * TPP ID, M, 8, N, TPP ID assigned by Fiserv
     */
    private Long tppId;

    /**
     * Timestamp, M, 14, N, Header – System date on which the file extract was generated,  date is in format DDMMYYYYHHMMSS
     */
    private String timeStamp;

    /**
     *
     * @return String all values of FileBody by positioning.
     */
    @Override
    public String toString() {
        return recordType + DATA_SEPARATOR + fileType + DATA_SEPARATOR + institutionNumber + DATA_SEPARATOR +
                institutionName + DATA_SEPARATOR + thirdPartyProcessorName + DATA_SEPARATOR +
                tppId + DATA_SEPARATOR  + timeStamp
                ;
    }
}

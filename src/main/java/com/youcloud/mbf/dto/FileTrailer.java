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
public class FileTrailer {

    /**
     * Record Type, M, T, 2, AN, Constant ‘T’ value (251)
     */
    @Builder.Default
    private String recordType = "T";

    /**
     * Total Merchant Records, M, 5, N, Trailer – Total number of merchants (252)
     */
    private Integer totalMerchantRecords;

    @Override
    public String toString() {
        return recordType + DATA_SEPARATOR + totalMerchantRecords;
    }
}

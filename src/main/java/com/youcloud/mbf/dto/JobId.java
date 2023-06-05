package com.youcloud.mbf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import static com.youcloud.mbf.common.constant.MbfFileConstant.SENDER_CODE;
import static com.youcloud.mbf.common.constant.MbfFileConstant.TPP_IDENTIFIER;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class JobId { //TT4220MB

    /**
     * First 2 characters refer to the sender who sends the DE file,
     * it could be TPP or a 3rd party on behalf of TPP sending the file.
     * For instance if the sender is Fat Zebra, it will be identified as “FZ”.
     * Alternatively if TPP is sending it directly, then they will be assigned a mnemonic.
     * 2 Chars (Pos 1-2)
     */
    @Builder.Default
    private String senderCode = SENDER_CODE;

    /**
     * 3rd to 5th Characters refer to TPP ID
     */
    @Builder.Default
    private String tppId = TPP_IDENTIFIER;

    /**
     * 6th to 7th characters will be a constant value “DE” for DE files
     */
    @Builder.Default
    private String position6To7 = "DE";

    /**
     * 8th Character will be “1” always
     */
    @Builder.Default
    private Character position8 = '1';

    @Override
    public String toString() {
        return senderCode + tppId + position6To7 + position8;
    }
}

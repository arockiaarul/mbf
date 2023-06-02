package com.youcloud.mbf.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class MbfFileContent {

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

    private List<String> contentList;
}

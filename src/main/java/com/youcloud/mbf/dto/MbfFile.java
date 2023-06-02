package com.youcloud.mbf.dto;

import java.io.File;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@ToString(exclude = "inputFileContent")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MbfFile {

    MbfFileName inputFileName;
    String folderPath;
    MbfFileContent inputFileContent;
    String type;

    public String getAbsolutePath() {
        return folderPath.concat(File.separator).concat(inputFileName.toString());
    }
}

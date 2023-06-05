package com.youcloud.mbf.service.impl;

import com.youcloud.mbf.common.util.FileUtil;
import com.youcloud.mbf.dto.JobId;
import com.youcloud.mbf.dto.MbfFile;
import com.youcloud.mbf.dto.MbfFileContent;
import com.youcloud.mbf.dto.MbfFileName;
import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.service.MbfDataService;
import com.youcloud.mbf.service.WriteMdfFileService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class WriteMdfFileServiceImpl implements WriteMdfFileService {

    @Value("${de.filelib.inputFile.path:E:\\YouCloud\\Vivek\\mbf\\src\\test}")
    private String filePath;

    private MbfDataService mbfDataService;

    @Override
    public String generateMbfFile(List<MbfDataEntity> mbfDataEntityList) {
        final String status;
        log.info("************************** filePath *******************: {}", filePath);
        final JobId jobId = JobId.builder().build();
        log.debug("<===================jobId=============>: {}", jobId);
        MbfFileName mbfFileName = MbfFileName.builder()/*.jobId(jobId)*/.build();
        log.debug("<===================mbfFileName=============>: {}", mbfFileName);
        //Pair<MbfFileContent, List<SettlementTransaction>> pair = this.generateInputFileContent();
        final MbfFileContent mbfFileContent = this.generateInputFileContent(mbfDataEntityList);
        log.debug("<===================mbfFileContent=============>: {}", mbfFileContent);
        if (mbfFileContent != null && mbfFileContent.getContentList() != null && !mbfFileContent.getContentList().isEmpty()) {
            final MbfFile mbfFile = MbfFile.builder()
                    .folderPath((filePath == null || filePath.isEmpty()) ? "E:\\YouCloud\\Vivek\\mbf\\src\\test" : filePath)
                    .inputFileName(mbfFileName)
                    .inputFileContent(mbfFileContent)
                    .build();
            log.debug("<===================mbfFile=============>: {}", mbfFile);
            status = FileUtil.writeFile(mbfFile);
            log.info("<=================== WriteMdfFileServiceImpl.generateInputFile() status=============>: {}", status);

        } else {
            status = "Failure: Detail List is Empty !!!!";
        }
        return status;
    }

    private MbfFileContent generateInputFileContent(List<MbfDataEntity> mbfDataEntityList) {
        final List<String> contentList = new ArrayList<>();
        mbfDataEntityList.forEach(mbfDataEntity -> contentList.add(mbfDataEntity.getMbf()));
        log.debug("<===================contentList=============>: {}", contentList);
        final MbfFileContent mbfFileContent = MbfFileContent.builder()
                //.header(fileHeader)
                //.detailList(detailList)
                //.trailer(fileTrailer)
                .contentList(contentList)
                .build();
        log.debug("<===================mbfFileContent=============>: {}", mbfFileContent);

        return mbfFileContent;
    }


}

package com.youcloud.mbf.service;

import com.youcloud.mbf.entity.MbfDataEntity;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface WriteMdfFileService {

    String generateMbfFile(List<MbfDataEntity> mbfDataEntityList);
}

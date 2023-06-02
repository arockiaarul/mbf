package com.youcloud.mbf.scheduler;

import com.youcloud.mbf.entity.MbfDataEntity;
import com.youcloud.mbf.entity.MerchantMasterEntity;
import com.youcloud.mbf.service.MbfDataService;
import com.youcloud.mbf.service.MerchantMasterService;
import com.youcloud.mbf.service.WriteMdfFileService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@AllArgsConstructor
@Component
public class MerchantMasterScheduler {

    private MbfDataService mbfDataService;
    //private MerchantMasterService merchantMasterService;
    private WriteMdfFileService writeMdfFileService;

    @Async
    //@Scheduled(fixedRate = 120000)
    @Scheduled(cron = "${mbf.scheduler.merchant-master.scheduled-time:0 */10 * * * *}") //@Scheduled(cron = "0 30 21 * * *") // 9:30 PM every day
    public void populateMerchantMasterTaskAsync() throws InterruptedException {
        /*List<MerchantMasterEntity>  merchantMasterEntityList = merchantMasterService.saveMerchantMaster();
        log.info("merchantMasterEntityList==============> {}", merchantMasterEntityList);*/
        List<MbfDataEntity>  mbfDataEntityList = mbfDataService.parseMdfDataAndSaveMerchantMaster();
        String status = writeMdfFileService.generateMbfFile(mbfDataEntityList);
        log.info("status==============> {}", status);
        log.info("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }
}

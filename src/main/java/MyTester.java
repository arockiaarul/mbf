import com.youcloud.mbf.dto.FileDetail;
import com.youcloud.mbf.dto.FileHeader;
import com.youcloud.mbf.dto.FileTrailer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.youcloud.mbf.common.constant.MbfDataConstant.DATA_SEPARATOR;

public class MyTester {

    public static void main(String[] args) {
        String mdfData = "H~MBF~00000021~FDMSA~Touch2Pay~111~24052023110520 D~109~Member Level~132465789~I WET MY PLANTS GARDEN GIFTS PTY LTD~I WET MY PLANTS GARDEN GIFTS PTY LTD~39665978889~665978889~touch2pay.com.au~NO~corporation~5331~No~Nicholas Decker~32~Hope Street~~South Brisbane~QLD~4101~Australia~Nicholas Decker~32~Hope Street~South Brisbane~QLD~4101~Australia~23052023~No~No~~No~No~No~NoNo~NoRobertJohnson~Beneficial Owner~DDMMYYYY~~~AustraliaA111111~AbiJomba~Authorised Signer~01031974~36 King Street~Bowen Hills~QLD~4006~AustraliaA111111~Jacinda~K~Ardern~Beneficial Owner~26071980~~~AustraliaA111111~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ T~1";
        mdfData = "H~MBF~00000021~FDMSA~Touch2Pay~111~24052023110520 D~109~Member Level~132465789~I WET MY PLANTS GARDEN GIFTS PTY LTD~I WET MY PLANTS GARDEN GIFTS PTY LTD~39665978889~665978889~touch2pay.com.au~NO~corporation~5331~No~Nicholas Decker~32~Hope Street~~~South Brisbane~QLD~4101~Australia~Nicholas Decker~32~Hope Street~~~South Brisbane~QLD~4101~Australia~23052023~~~~~No~~~No~~~~No~No~No~No~~No~No~~Robert~~Johnson~Beneficial Owner~DDMMYYYY~~~~~~~Australia~~A111111~~~Abi~~Jomba~Authorised Signer~01031974~36 King Street~~~Bowen Hills~QLD~4006~Australia~~A111111~~~Jacinda~K~Ardern~Beneficial Owner~26071980~~~~~~~Australia~~A111111~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ D~444~Member Level~987654321~I WET MY PLANTS GARDEN GIFTS PTY LTD~I WET MY PLANTS GARDEN GIFTS PTY LTD~39665977778~665977778~touch2pay.com.au~NO~corporation~5331~No~Nicholas Decker~32~Hope Street~~~South Brisbane~QLD~4101~Australia~Nicholas Decker~32~Hope Street~~~South Brisbane~QLD~4101~Australia~23052023~~~~~No~~~No~~~~No~No~No~No~~No~No~~Robert~~Johnson~Beneficial Owner~DDMMYYYY~~~~~~~Australia~~A1222222~~~Abi~~Jomba~Authorised Signer~01031974~36 King Street~~~Bowen Hills~QLD~4006~Australia~~A222222~~~Jacinda~K~Ardern~Beneficial Owner~26071980~~~~~~~Australia~~A222222~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ T~0";
        String[] dataArray = mdfData.split(DATA_SEPARATOR);

        dataArray = mdfData.replace(" D~", "~D~").replace(" T~", "~T~").split(DATA_SEPARATOR); // String[] dataArray = mdfData.split(DATA_SEPARATOR);
        int dataArrayLength = dataArray.length;
        AtomicInteger counter = new AtomicInteger();

        /*for (String s : dataArray) {
            System.out.println(counter.getAndIncrement() + " record: " + s);
        }
        //FileHeader fileHeader = validateAndGetFileHeader(dataArray, dataArrayLength);
        String[] headerArray = new String[7];
        if (dataArray.length >= 7) {
            System.arraycopy(dataArray, 0, headerArray, 0, headerArray.length);
        }
        System.out.println("headerArray[0] : " + headerArray+",headerArray[6]" +headerArray[6]);
        System.out.println("////////////////////////////////////////////////////" + headerArray.length);
        String[] detailArray = new String[dataArrayLength-9];
        if (dataArrayLength >249) {
            System.arraycopy(dataArray, 7, detailArray, 0, dataArrayLength-9);
        }
        System.out.println("detailArray[0] : " + detailArray[0]+",detailArray["+(detailArray.length-1)+"] : " + detailArray[detailArray.length-1]);
        System.out.println("////////////////////////////////////////////////////" + detailArray.length);
        String[] trailerArray = new String[2];
        if (dataArrayLength >251) {
            System.arraycopy(dataArray, dataArrayLength-3, trailerArray, 0, 2);
        }
        System.out.println("trailerArray[0] : " + trailerArray[0]+",trailerArray[1] : " + trailerArray[1]);
        System.out.println("////////////////////////////////////////////////////" + trailerArray.length);*/
        FileTrailer fileTrailer = validateAndGetFileTrailer(dataArray, dataArrayLength);
        System.out.println("fileTrailer: " + fileTrailer);
        List<FileDetail> fileDetailList = validateAndGetFileDetail(dataArray, dataArrayLength, fileTrailer.getTotalMerchantRecords());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@fileDetailList: " + fileDetailList);
/*        dataArray = mdfData.split(DATA_SEPARATOR);
        System.out.println("dataArray length: " + dataArray.length);
        System.out.println("8th record: " + dataArray[7]);
        String[]  trailerSplitArray = mdfData.split(" T~");
        System.out.println(" trailerSplitArray[0]: " + trailerSplitArray[0]);
        System.out.println(" trailerSplitArray[1]: " + trailerSplitArray[1]);
        String[]  headerSplitArray = trailerSplitArray[0].split(" D~"); // mdfData.split(" D~");mdfData.split(" D~");
        System.out.println(" headerSplitArray[0]: " + headerSplitArray[0]);

        counter = new AtomicInteger();
        for (String s : headerSplitArray) {
            System.out.println(counter.getAndIncrement() + " record: " + s);
        }
        System.out.println("--------------------------------------------------------------");
        counter = new AtomicInteger();
         dataArrayLength = dataArray.length;
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
        fileTrailer = FileTrailer.builder().build();
        if (dataArrayLength>248) {
            String trailerRecordType = dataArray[dataArrayLength-2].trim();
            if ("T".equals(trailerRecordType)) {
                fileTrailer.setRecordType(trailerRecordType);
                fileTrailer.setTotalMerchantRecords(Integer.valueOf(dataArray[dataArrayLength-1]));
                System.out.println(" fileTrailer: " + fileTrailer);
            }
        }
        //if (dataArray[dataArray.length-2])
        for (String s : dataArray) {
            System.out.println(counter.getAndIncrement() + " record: " + s);
        }
        System.out.println("--------------------------------------------------------------");

        for (String s : mdfData.split(" D~")) {
            System.out.println(counter.getAndIncrement() + " record: " + s);
        }*/

    }

    private static List<FileDetail> validateAndGetFileDetail(String[] dataArray, int dataArrayLength, Integer totalMerchantRecords) {
        /*String[] trailerArray = new String[2];
        if (dataArrayLength >251) {
            System.arraycopy(dataArray, dataArrayLength-3, trailerArray, 0, 2);
        }*/
        System.out.println(" totalMerchantRecords: " + totalMerchantRecords);
        int detailRecords = (dataArrayLength - 9) / 243;
        System.out.println(" detailRecords: " + detailRecords);
        List<FileDetail> fileDetailList = new ArrayList<>(); //new ArrayList<>(detailRecords);
        String[] detailArray = new String[dataArrayLength-9];
        if (dataArrayLength >249) {
            System.arraycopy(dataArray, 7, detailArray, 0, dataArrayLength-9);
        }
        System.out.println(" detailArray.length: " + detailArray.length);
        /*int counter = 0;
        for (int fieldCounter = 0; fieldCounter < detailArray.length; fieldCounter++) {
            if (243 % counter == 0 ) {

            }

        }*/

        if (dataArrayLength >249 && detailRecords == totalMerchantRecords) {
            //int fieldCounter = 0;
            for (int recordCounter = 0; recordCounter < totalMerchantRecords; recordCounter++) {
                int fieldCounter = 243 * recordCounter;
                String recordType = detailArray[fieldCounter +0];
                System.out.println(" recordType: " + recordType);
                if ("D".equals(recordType)) {
                    FileDetail fileDetail = FileDetail.builder()
                            .recordType(detailArray[fieldCounter + 0])
                            .merchantNumber(Long.valueOf(detailArray[fieldCounter + 1]))
                            .merchantLevel(detailArray[fieldCounter + 2])
                            .parentClient(detailArray[fieldCounter + 3])

                            .principal3State(detailArray[fieldCounter + 93])
                            .principal3Postcode(detailArray[fieldCounter + 94])
                            .principal3Country(detailArray[fieldCounter + 95])
                            .principal3PhoneNumber(detailArray[fieldCounter + 96])
                            .principal3PassportNumber(detailArray[fieldCounter + 97])
                            .placeholder242(detailArray[fieldCounter + 241])
                            .placeholder243(detailArray[fieldCounter + 242])
                            .build();
                    System.out.println(" fileDetail: " + fileDetail);
                    fileDetailList.add(fileDetail);
                }
            }
        }
        System.out.println(" fileDetailList: " + fileDetailList);
        return fileDetailList;
    }

    private static FileTrailer validateAndGetFileTrailer(String[] dataArray, int dataArrayLength) {
        /*String[] trailerArray = new String[2];
        if (dataArrayLength >251) {
            System.arraycopy(dataArray, dataArrayLength-3, trailerArray, 0, 2);
        }*/
        FileTrailer fileTrailer = FileTrailer.builder().build();
        if (dataArrayLength >251) {
            String trailerRecordType = dataArray[dataArrayLength -2].trim();
            if ("T".equals(trailerRecordType)) {
                fileTrailer.setRecordType(trailerRecordType);
                fileTrailer.setTotalMerchantRecords(Integer.valueOf(dataArray[dataArrayLength -1]));
                System.out.println(" fileTrailer: " + fileTrailer);
            }
        }
        return fileTrailer;
    }


    private static FileHeader validateAndGetFileHeader(String[] dataArray, int dataArrayLength) {
        /*if(headerArray[0].equals("H") &  headerArray[1].equals("MBF"))
        {
            log.info("Header Match");
            headermatch= true;

        } else
        {
            log.info("No Match");
        }*/
        /*String[] headerArray = new String[7];
        if (dataArrayLength>6 && "H".equals(dataArray[0]) &&  dataArray[1].equals("MBF")) {
            System.arraycopy(dataArray, 0, headerArray, 0, headerArray.length);
        }*/
        FileHeader fileHeader = FileHeader.builder().build();
        if (dataArrayLength>6 && "H".equals(dataArray[0]) &&  dataArray[1].equals("MBF")) {
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

package com.youcloud.mbf.common.util;

import com.youcloud.mbf.dto.MbfFile;
import com.youcloud.mbf.dto.MbfFileContent;
import com.youcloud.mbf.exception.MbfDataLibException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileSystemUtils;

@Log4j2
public class FileUtil {

    private FileUtil() {
    }

    private static boolean checkIfDirectoryExist(final String folderPath) {
        if (Files.exists(Paths.get(folderPath))) {
            return true;
        }
        try {
            final Path path = Files.createDirectories(Paths.get(folderPath));
            return Files.exists(path);
        } catch (final IOException e) {
            log.error("couldn't able to create the file " + folderPath);
        }
        return false;
    }

    /**
     * Method which will create a single directory
     *
     * @param path : path name of the directory
     * @return
     */
    public static Path createDirectory(final String path) {
        try {
            deleteAll(path);
            return Files.createDirectory(Paths.get(path));
        } catch (final IOException e) {
            throw new MbfDataLibException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        }
    }

    /**
     * Method which will create the directories
     *
     * @param folderStructure : folder path structure.
     */
    public static void createDirectories(final List<String> folderStructure) {
        try {
            Files.createDirectories(Paths.get(String.join(File.separator, folderStructure)));
        } catch (final IOException e) {
            throw new MbfDataLibException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        }
    }

    /**
     * delete All files in the given path
     *
     * @param path : path of the file
     * @throws IOException : throws the IOException
     */
    public static void deleteAll(final String path) throws IOException {
        FileSystemUtils.deleteRecursively(Paths.get(path));
    }

    /**
     * Creates the File in the given path
     *
     * @param path     : path to the file
     * @param fileName : name of the file which needs to be created
     * @return : file which created.
     */
    public static File createFile(final String path, final String fileName) {
        try {
            final String filePath = path.concat("/").concat(fileName);
            deleteAll(filePath);
            Files.createFile(Paths.get(filePath));
            return new File(filePath);
        } catch (final IOException e) {
            throw new MbfDataLibException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        }
    }

    /**
     * Methods which packs the folder and all it's sub folder were packed as a zip
     * file
     *
     * @param folder      : folder path which needs to be packed
     * @param zipFilePath : destination path where the zip folder will be placed.
     */
    public static void pack(final Path folder, final Path zipFilePath) {
        try (final FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
             final ZipOutputStream zos = new ZipOutputStream(fos)) {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs)
                        throws IOException {
                    zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (final IOException ioException) {
            throw new MbfDataLibException(ioException.getMessage(), ioException, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        }
    }

    /**
     * Method which return the content of the file as a string
     * @param fileName : name of the file which needs to be read
     * @return : string content present in the file.
     */
    public static String readContentInFileAsString(final String fileName) {
        final byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(fileName));
            return new String(bytes, "UTF-8");
        } catch (final IOException e) {
            log.info("file not found ::." + fileName);
            throw new MbfDataLibException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        } finally {
            log.info("file read is successfully done.");
        }
    }

    /**
     * Method which writes the content of the file as a string
     * @param path : file path which needs to be written
     * @param fileName : name of the file which needs to be written
     * @param fileContent : content of the file which needs to be written
     * @return : Comparable<? extends Comparable<?>> .
     */
    public static Comparable<? extends Comparable<?>> writeContentInFileAsString(final String path, final String fileName, final String fileContent) {
        try {
             return Files.writeString(Paths.get(path), fileContent);
        } catch (final IOException e) {
            log.error("File Write failed with Exception: " + e.getMessage(), e);
            throw new MbfDataLibException(e.getMessage(), e, HttpStatus.INTERNAL_SERVER_ERROR, "500");
        } finally {
            log.info("file write is successfully done.");
        }
    }

    public static String writeFile(final MbfFile mbfFile) {
        AtomicReference<String> status = new AtomicReference<>("");
        final File file = new File(mbfFile.getAbsolutePath());
        try (final FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
             final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            final MbfFileContent mbfFileContent = mbfFile.getInputFileContent();
            if (mbfFileContent.getContentList() != null && !mbfFileContent.getContentList().isEmpty()) {
                mbfFileContent.getContentList().stream().forEach(content -> {
                    try {
                        bufferedWriter.write(content);
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        status.set(status.get() + "\n" + "File Write failed for content: " + content + " with IOException: "
                                + e.getMessage());
                        log.error(status.get(), e);
                    }
                });
            } else {
                bufferedWriter.write(mbfFileContent.getHeader().toString());
                bufferedWriter.newLine();
                mbfFileContent.getDetailList().stream().forEach(fileBody -> {
                    try {
                        bufferedWriter.write(fileBody.toString());
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        status.set(status.get() + "\n" + "File Write failed for fileBody: " + fileBody + " with IOException: "
                                + e.getMessage());
                        log.error(status.get(), e);
                    }
                });
                bufferedWriter.write(mbfFileContent.getTrailer().toString());
                bufferedWriter.newLine();
            }
            status.set("Success: Input File generated successfully !!!");
        } catch (IOException e) {
            log.error("File Write failed with Exception: " + e.getMessage(), e);
            status.set("Failure: File Write failed with Exception: " + e.getMessage());
        }
        return status.get();
    }

    public static List<File> getFileList(String folderPathName, String fileNamePrefix, String fileNameType) {
        //Creating a File object for directory
        File directoryPath = new File(folderPathName);
        File[] filesList = directoryPath.listFiles(
                (dir, name) -> name.startsWith(fileNamePrefix) && name.endsWith(fileNameType));
        log.debug("List of the files in the specified directory: {}", folderPathName);
        for(File file : filesList) {
            log.debug("File name: {}, File path: {}, Size : {} ", file.getName(), file.getAbsolutePath(), file.getTotalSpace());
            log.debug("fileName: {} length: {}", file.getName(), file.getName().length());
        }
        return Arrays.asList(filesList);
    }

    public static List<File> getFileList(String folderPathName, String fileNamePrefix, String fileNameType, Integer fileNameLength) {
        //Creating a File object for directory
        File directoryPath = new File(folderPathName);
        File[] filesArray = directoryPath.listFiles(
                (dir, name) -> name.startsWith(fileNamePrefix) && name.endsWith(fileNameType) && name.length() == fileNameLength);
        log.debug("List of the files in the specified directory: {}", folderPathName);
        List<File> fileList = new ArrayList<>();
        if (filesArray != null && filesArray.length > 0) {
            for (File file : filesArray) {
                log.debug("File name: {}, File path: {}, Size : {} ", file.getName(), file.getAbsolutePath(), file.getTotalSpace());
                log.debug("fileName: {} length: {}", file.getName(), file.getName().length());
            }
            fileList = Arrays.asList(filesArray);
        }
        return fileList;
    }

    public static boolean isFileExists(String folderPathName, String fileNamePrefix, String fileNameType) {
        //Creating a File object for directory
        File directoryPath = new File(folderPathName); //"D:\ExampleDirectory");
        //FileFilter textFilefilter = file -> file.isFile();
        //List of all the text files
        File filesList[] = directoryPath.listFiles(File::isFile);
        log.debug("List of the text files in the specified directory:");
        for(File file : filesList) {
            log.debug("File name: "+file.getName());
            log.debug("File path: "+file.getAbsolutePath());
            log.debug("Size :"+file.getTotalSpace());
            log.debug(" ");
        }
        // MD021555DE2301110DERP00012549.RPLY
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                //String lowercaseName = name.toLowerCase();
                //if (lowercaseName.endsWith(".txt")) {
                if (name.startsWith(fileNamePrefix) && name.endsWith(fileNameType)) { //".RPLY"
                    return true;
                } else {
                    return false;
                }
            }
        };
        File filesList1[] = directoryPath.listFiles(textFilefilter);
        File[] filesList2 = directoryPath.listFiles((dir, name) -> name.startsWith(fileNamePrefix) && name.endsWith(fileNameType));
        log.debug("List of the text files in the specified directory:");
        for(File file : filesList2) {
            log.debug("File name: "+file.getName());
            log.debug("File path: "+file.getAbsolutePath());
            log.debug("Size :"+file.getTotalSpace());
            log.debug(" ");
        }
        return true;
    }
}

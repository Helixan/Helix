package org.cultro.roulette.util;

import org.cultro.roulette.lang.Validate;

import java.io.*;
import java.nio.file.Files;


/**
 * The IOUtils class provides utility methods for performing various input/output operations.
 */
public class IOUtils {


    /**
     * Copies the contents of a directory to another directory.
     *
     * @param sourceDirectory      The source directory to copy.
     * @param destinationDirectory The destination directory where the contents will be copied.
     * @throws IOException           If an I/O error occurs during the copying process.
     * @throws IllegalArgumentException If either sourceDirectory or destinationDirectory is null.
     */
    public static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        Validate.notNull(sourceDirectory, "Cannot copy a null source directory into a destination directory");
        Validate.notNull(destinationDirectory, "Cannot copy a source directory into a null destination directory");
        if (sourceDirectory.isDirectory()) {
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdir();
            }

            String[] files = sourceDirectory.list();
            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(sourceDirectory, file);
                    File destFile = new File(destinationDirectory, file);
                    copyDirectory(srcFile, destFile);
                }
            }
        } else {
            copyFile(sourceDirectory, destinationDirectory);
        }
    }


    /**
     * Copies a file from the source to the destination.
     *
     * @param sourceFile      The source file to copy.
     * @param destinationFile The destination file where the source file will be copied.
     * @throws IOException           If an I/O error occurs during the copying process.
     * @throws IllegalArgumentException If either sourceFile or destinationFile is null.
     */
    public static void copyFile(File sourceFile, File destinationFile) throws IOException {
        Validate.notNull(sourceFile, "Cannot copy a null source file into a destination file");
        Validate.notNull(destinationFile, "Cannot copy a source file into a null destination file");
        try (InputStream inputStream = Files.newInputStream(sourceFile.toPath());
             OutputStream outputStream = Files.newOutputStream(destinationFile.toPath())) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }


    /**
     * Creates a backup file for the given file by appending a numeric suffix.
     *
     * @param fileToBackup The file to create a backup of.
     * @throws IOException           If an I/O error occurs while creating the backup file.
     * @throws IllegalArgumentException If fileToBackup is null.
     */
    public void createBackupFile(File fileToBackup) throws IOException {
        Validate.notNull(fileToBackup, "Cannot backup a null file");

        int backupNumber = 1;
        File backupFile;

        do {
            backupFile = new File(fileToBackup.getAbsoluteFile() + "-" + backupNumber + ".BACKUP");
            backupNumber++;
        } while (backupNumber > 0 && backupFile.exists());

        if (backupNumber <= 0 || !fileToBackup.renameTo(backupFile)) {
            throw new IOException("Failed to create a backup for " + fileToBackup.getAbsolutePath());
        }
    }


    /**
     * Creates a backup file for the given file by renaming it.
     *
     * @param fileToBackup The file to create a backup of.
     * @param backupFile   The backup file to be created.
     * @throws IOException           If an I/O error occurs while creating the backup file.
     * @throws IllegalArgumentException If either fileToBackup or backupFile is null.
     */
    public void createBackupFile(File fileToBackup, File backupFile) throws IOException {
        Validate.notNull(fileToBackup, "Cannot backup a null file");
        Validate.notNull(backupFile, "Cannot backup a file to a null file");

        if (backupFile.exists() || !fileToBackup.renameTo(backupFile)) {
            throw new IOException("Failed to create a backup for " + fileToBackup.getAbsolutePath());
        }
    }


    /**
     * Retrieves the last modified timestamp of a file.
     *
     * @param file The file to get the last modified timestamp for.
     * @return The last modified timestamp of the file.
     * @throws IllegalArgumentException If file is null.
     */
    public static long fileLastModified(File file) {
        Validate.notNull(file, "Cannot check when a null file was last modified");
        return file.lastModified();
    }


    /**
     * Retrieves the permissions (readable, writable, executable) of a file.
     *
     * @param file The file to check permissions for.
     * @return An array of boolean values representing permissions.
     * @throws IllegalArgumentException If file is null.
     */
    public static boolean[] filePermissions(File file) {
        Validate.notNull(file, "Cannot get permissions from a null file");
        boolean[] permissions = new boolean[3];
        permissions[0] = file.canRead();
        permissions[1] = file.canWrite();
        permissions[2] = file.canExecute();
        return permissions;
    }


    /**
     * Retrieves the size of a file in bytes.
     *
     * @param file The file to get the size of.
     * @return The size of the file in bytes.
     * @throws IllegalArgumentException If file is null.
     */
    public static long fileSize(File file) {
        Validate.notNull(file, "Cannot get the size of a null file");
        return file.length();
    }
}

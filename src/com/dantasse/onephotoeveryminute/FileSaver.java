package com.dantasse.onephotoeveryminute;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;


/**
 * Handles saving pictures to the SD card.
 */
public class FileSaver {

  /** The directory where all the files will be saved to */
  public File directory;

  private static FileSaver instance = null;
  public static FileSaver getInstance() {
    if (instance == null) {
      instance = new FileSaver(getPicturesDirectory(
          Environment.getExternalStorageState(),
          Environment.getExternalStorageDirectory()));
    }
    return instance;
  }

   
  // TODO (dantasse) this is I guess the least testable bit.
  /**
   * @param storageState whether there's an SD card mounted or not.  Typically
   *   found by calling Environment.getExternalStorageState.
   * @param root where the device wants you to put data.  Typically found by
   *   calling Environment.getExternalStorageDirectory.
   */
  public static File getPicturesDirectory(String storageState, File root) {
    if (Environment.MEDIA_MOUNTED.equals(storageState)) {
      // Save things in /sdcard/Pictures, as per 
      // http://developer.android.com/guide/topics/data/data-storage.html#filesExternal
      File picturesDir = new File(root, "Pictures");
      picturesDir.mkdirs();
      return picturesDir;
    } else {
      return null;
      // TODO(dantasse) handle if you can't write to the file.
    }
  }

  public FileSaver(File directory) {
    this.directory = directory;
  }

  /** Saves the data to the file at /sdcard/Pictures/(filename) */
  public void save(byte[] data, String filename) {

    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {

      File destFile = new File(directory.getAbsolutePath() + File.separator +
          filename);
      try {
        destFile.createNewFile();
        BufferedOutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(destFile));
        outputStream.write(data);
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
        //TODO(dantasse) handle this case too
      }

    } else {
      // TODO(dantasse) handle if you can't write to the file.
    }
  }
}

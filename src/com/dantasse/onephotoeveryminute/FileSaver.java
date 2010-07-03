package com.dantasse.onephotoeveryminute;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;


/**
 * Handles saving pictures to the SD card.
 */
public class FileSaver {

  /** Saves the data to the file at /sdcard/Pictures/(filename) */
  public void save(byte[] data, String filename) {

//    boolean mExternalStorageAvailable = false;
//    boolean mExternalStorageWriteable = false;
    String state = Environment.getExternalStorageState();

    if (Environment.MEDIA_MOUNTED.equals(state)) {
      // We can read and write the media
//      mExternalStorageAvailable = mExternalStorageWriteable = true;
      File root = Environment.getExternalStorageDirectory();
      File picturesDir = new File(root, "Pictures");
      
      // Save things in /sdcard/Pictures, as per 
      // http://developer.android.com/guide/topics/data/data-storage.html#filesExternal
      String outFileName = picturesDir.getAbsolutePath() + File.separator +
        filename;
      File destFile = new File(outFileName);
      
      try {
        picturesDir.mkdirs();
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
//      mExternalStorageAvailable = mExternalStorageWriteable = false;
    }
  }
}

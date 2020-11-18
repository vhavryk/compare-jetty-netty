package com.corundumstudio.socketio.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

public class DataFileLoader {

  public static final String DATA_FILE_NAME = "data-file-name";
  public static final String DEFAULT_FILE_NAME = "small.json";
  public static final String EMPTY_CONTENT = "";
  private static DataFileLoader instance = new DataFileLoader();
  private String data;

  private DataFileLoader() {
    this.data = EMPTY_CONTENT;
  }

  public static DataFileLoader getInstance() {
    return instance;
  }

  public String load() {
    String fileName = System.getProperty(DATA_FILE_NAME);
    if (fileName == null) {
      fileName = DEFAULT_FILE_NAME;
    }
    try {
      data = load(fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Loaded data from file : " + fileName + " " + data.length() + " bytes");
    return fileName;
  }

  private String load(String s) throws IOException {
    return IOUtils.resourceToString(s, Charset.defaultCharset(), this.getClass().getClassLoader());
  }

  public String getData() {
    return data;
  }
}

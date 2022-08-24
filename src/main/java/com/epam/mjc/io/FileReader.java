package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

//    public Profile getDataFromFile(File file) {
//
////        String absolutePath =
//
//        String filePath = file.getPath();
//        String fileName = file.getName();
//        String absolute = filePath + File.separator + fileName;
//
//        System.out.println(absolute);
//
//        FileReader inputStream = new FileReader(file.getPath());
//
//        return new Profile();
//    }

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        String profileInfo = "";
        try (java.io.FileReader inputStream = new java.io.FileReader(file);) {
            int ch;
            while ((ch = inputStream.read()) != -1) {
                profileInfo = profileInfo + (char) ch;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cleanString = profileInfo.replaceAll("[\\n\\r]", ":").replaceAll(" ", "").trim().replaceAll("::", ":");
        String[] strArr = cleanString.substring(0, cleanString.length() - 1).split(":");
        Map<String, String> profileFields = new HashMap<>();
        for (int i = 0; i < strArr.length - 1; i += 2) {
            profileFields.put(strArr[i], strArr[i + 1]);
        }
        for (Map.Entry<String, String> set :
                profileFields.entrySet()) {
            if (set.getKey().equals("Email")) {
                profile.setEmail(set.getValue());
            } else if (set.getKey().equals("Name")) {
                profile.setName(set.getValue());
            } else if (set.getKey().equals("Age")) {
                profile.setAge(Integer.valueOf(set.getValue()));
            } else if (set.getKey().equals("Phone")) {
                profile.setPhone(Long.valueOf(set.getValue()));
            }
        }
        return profile;
    }
}

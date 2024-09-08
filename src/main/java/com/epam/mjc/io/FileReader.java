package com.epam.mjc.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {

        List<String> byLines = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                byLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> mapList = new HashMap<>();
        for (String words : byLines) {
            String[] splitWords = words.split(":");
            mapList.put(splitWords[0].trim(), splitWords[1].trim());
        }

        Profile newProfile = new Profile();

        try {
            newProfile.setName(mapList.get("Name"));
            newProfile.setAge(Integer.parseInt(mapList.get("Age")));
            newProfile.setEmail(mapList.get("Email"));
            newProfile.setPhone(Long.parseLong(mapList.get("Phone")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return newProfile;
    }
}

package com.epam.mjc.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {

        List<Character> charList = new ArrayList<>();
        InputStream input = null;
        try {
            file = new File("Profile.txt");
            input = new FileInputStream(file);
            int result;
            while ((result = input.read()) != -1) {
                charList.add((char) result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        List<String> byLines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (char chars : charList) {
            if (chars == '\n') {
                byLines.add(line.toString());
                line = new StringBuilder();
            }
            line.append(chars);
        }

        Map<String, String> mapList = new HashMap<>();
        for (String words : byLines) {
            String[] splitWords = words.split(":");
            mapList.put(splitWords[0], splitWords[1]);
        }

        Profile newProfile = new Profile();
        newProfile.setName(mapList.get("Name"));
        newProfile.setAge(Integer.parseInt(mapList.get("Age")));
        newProfile.setEmail(mapList.get("Email"));
        newProfile.setPhone(Long.parseLong(mapList.get("Phone")));
        return newProfile;
    }
}

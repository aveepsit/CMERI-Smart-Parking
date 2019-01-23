/*
 *      Created By: Aveepsit Chowdhury
 *      File Name: WebsiteParser
 *      Project Name: CMERI-Smart-Parking
 *      Created on: 23-01-2019 09:41 PM
 *      Additional Notes:
 */

package com.cmeri.smartparking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


class WebsiteParser {
    private URL url;
    private InputStream is;

    WebsiteParser(String newUrl) throws IOException {
        this.url = new URL(newUrl);
    }

    String getNewState() throws IOException {
        is = this.url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder data = new StringBuilder();
        while ((line = br.readLine()) != null) {
            data.append(line).append("\n");
        }
        br.close();
        return data.toString();
    }
}


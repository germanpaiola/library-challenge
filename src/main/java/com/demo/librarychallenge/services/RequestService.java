package com.demo.librarychallenge.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    public RequestService(){};

    public List request(String title, BookService bookService) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:8081/api/film/get?title=" + title + "&recursive=true");
        try{
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return bookService.convertToList(EntityUtils.toString(entity));
            }else{
                return new ArrayList<>();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}

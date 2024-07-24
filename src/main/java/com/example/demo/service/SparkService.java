package com.example.demo.service;

import com.example.demo.spark.SparkJobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkService {

    @Autowired
    private SparkJobExecutor sparkJobExecutor;

    public void processDataset(String datasetPath) {
        sparkJobExecutor.processLargeDataset(datasetPath, "processedDataTopic");
    }

    public void tagUsers(String userDataPath, String tagCriteria) {
        sparkJobExecutor.tagUsers(userDataPath, tagCriteria);
    }
}
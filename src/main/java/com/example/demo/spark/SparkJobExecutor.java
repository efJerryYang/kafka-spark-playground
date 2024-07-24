package com.example.demo.spark;

import com.example.demo.service.KafkaProducerService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SparkJobExecutor {

    @Autowired
    private JavaSparkContext jsc;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public void processLargeDataset(String datasetPath, String outputTopic) {
        JavaRDD<String> dataset = jsc.textFile(datasetPath);

        // TODO
        JavaRDD<String> processedData = dataset.map(line -> {
            return "Processed: " + line;
        });

        List<String> results = processedData.collect();

        results.forEach(result -> kafkaProducerService.sendMessage(outputTopic, result));
    }

    public void tagUsers(String userDataPath, String tagCriteria) {
        JavaRDD<String> userData = jsc.textFile(userDataPath);

        JavaRDD<String> taggedUsers = userData.map(user -> {
            // TODO: Tagging logic
            if (user.contains(tagCriteria)) {
                return user + ",tagged";
            }
            return user;
        });

        List<String> results = taggedUsers.collect();

        results.forEach(result -> kafkaProducerService.sendMessage("userUpdateTopic", result));
    }
}
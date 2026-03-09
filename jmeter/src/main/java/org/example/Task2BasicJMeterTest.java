package org.example;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Task2BasicJMeterTest {

    public static void main(String[] args) throws Exception {

        var stats = testPlan(
            threadGroup("Thread Group - Basic Users", 1, 1,
                httpSampler("HTTP Request - Example Home Page", "https://example.com/")
            )
        ).run();

        testPlan(
            threadGroup("Thread Group - Basic Users", 1, 1,
                httpSampler("HTTP Request - Example Home Page", "https://example.com/")
            )
        ).saveAsJmx("task2-example-test-plan.jmx");

        System.out.println("Test executed successfully.");
        System.out.println("Samples: " + stats.overall().samplesCount());
        System.out.println("Errors: " + stats.overall().errorsCount());
        System.out.println("Average response time: " + stats.overall().sampleTime().mean() + " ms");
        System.out.println("Generated file: task2-example-test-plan.jmx");
    }
}
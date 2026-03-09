package org.example;

import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Task3ListenersAndRunTest {
    public static void main(String[] args) throws Exception {

        var plan = testPlan(
                threadGroup("Thread Group - Basic Users", 1, 1,
                        httpSampler("HTTP Request - Example Home Page", "https://example.com/")
                ),
                resultsTreeVisualizer(),              // View Results Tree (GUI listener)
                jtlWriter("task3-results.jtl")        // Saves raw results to file (replaces summaryReport)
        );

        // Save as JMX
        plan.saveAsJmx("task3-example-test-plan.jmx");

        // Run the test
        var stats = plan.run();

        // Summary output (this IS your summary report)
        System.out.println("Test executed successfully.");
        System.out.println("Total Samples:         " + stats.overall().samplesCount());
        System.out.println("Errors:                " + stats.overall().errorsCount());
        System.out.println("Average Response Time: " + stats.overall().sampleTime().mean() + " ms");
        System.out.println("Throughput: " + stats.overall().samplesCount() + " requests/second");
        System.out.println("Test plan saved as:    task3-example-test-plan.jmx");
    }
}
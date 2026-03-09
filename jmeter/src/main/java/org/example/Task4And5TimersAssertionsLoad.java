package org.example;

import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import java.time.Duration;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class Task4And5TimersAssertionsLoad {
    public static void main(String[] args) throws Exception {

        var plan = testPlan(
                threadGroup("Thread Group - Heavy Load", 50, 5,
                        constantTimer(Duration.ofMillis(1000)),
                        // No assertion needed — non-200 responses are captured as errors in stats
                        httpSampler("HTTP Request - Example Home Page", "https://example.com/")
                ),
                resultsTreeVisualizer(),
                jtlWriter("task5-results.jtl")
        );

        plan.saveAsJmx("task5-example-test-plan.jmx");

        TestPlanStats stats = plan.run();

        long totalSamples = stats.overall().samplesCount();
        long totalErrors  = stats.overall().errorsCount();
        double errorPct   = totalSamples > 0 ? (double) totalErrors / totalSamples * 100 : 0;
        double meanMs     = stats.overall().sampleTime().mean().toMillis();
        double throughput = meanMs > 0 ? 1000.0 / meanMs : 0;

        System.out.println("\n========== AGGREGATE REPORT ==========");
        System.out.println("Total Samples:         " + totalSamples);
        System.out.println("Errors:                " + totalErrors);
        System.out.println("Error %:               " + String.format("%.2f%%", errorPct));
        System.out.println("--------------------------------------");
        System.out.println("Average Response Time: " + meanMs + " ms");
        System.out.println("Min Response Time:     " + stats.overall().sampleTime().min().toMillis() + " ms");
        System.out.println("Max Response Time:     " + stats.overall().sampleTime().max().toMillis() + " ms");
        System.out.println("--------------------------------------");
        System.out.println("90th Percentile:       " + stats.overall().sampleTime().perc90().toMillis() + " ms");
        System.out.println("95th Percentile:       " + stats.overall().sampleTime().perc95().toMillis() + " ms");
        System.out.println("99th Percentile:       " + stats.overall().sampleTime().perc99().toMillis() + " ms");
        System.out.println("--------------------------------------");
        System.out.println("Throughput:            " + String.format("%.2f", throughput) + " req/sec");
        System.out.println("======================================");
        System.out.println("JMX saved as: task5-example-test-plan.jmx");
    }
}
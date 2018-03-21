/**
 * 
 */
package com.training.metrics;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;

/**
 * @author yangkai
 *
 */
public class MetricsTest {
    
    @Test
    public void test() {
        ScheduledReporter scheduledReporter
        
    }
    
    public static class QueueManager {
        private final Queue<String> queue;
        public QueueManager(MetricRegistry metrics, String name) {
            this.queue = new LinkedBlockingQueue<>();
            metrics.register(MetricRegistry.name(QueueManager.class, name, "size"),
                             new Gauge<Integer>() {
                                 @Override
                                 public Integer getValue() {
                                     return queue.size();
                                 }
                             });
        }
    } 

}

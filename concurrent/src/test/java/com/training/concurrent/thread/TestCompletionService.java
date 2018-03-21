package com.training.concurrent.thread;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompletionService {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private CompletionService<String> completionService = new ExecutorCompletionService<>(executor);



}

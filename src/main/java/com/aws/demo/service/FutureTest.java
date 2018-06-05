package com.aws.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class FutureTest {

	public static void main(String[] args) throws Exception {
		Consumer<Integer> intPrinter = (i) -> 	System.out.println(i);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Callable<List<Integer>> task1 = () -> {
			TimeUnit.SECONDS.sleep(2);
			List<Integer> numList1 = new ArrayList<>();
			for (int i = 0; i < 20000; i++) {
				numList1.add(i);
			}
			return numList1;
		};
		Callable<List<Integer>> task2 = () -> {
			List<Integer> numList1 = new ArrayList<>();
			for (int i = 20000; i < 40000; i++) {
				numList1.add(i);
			}
			return numList1;
		};
		long startTime = System.currentTimeMillis();
		/*
		 * Future<List<Integer>> future1 = executorService.submit(task1);
		 * Future<List<Integer>> future2 = executorService.submit(task2);
		 */
		CompletableFuture<List<Integer>> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(15);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Integer> numList1 = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				numList1.add(i);
			}
			return numList1;
		} , executorService);
		CompletableFuture<List<Integer>> future2 = CompletableFuture.supplyAsync(() -> {
			List<Integer> numList1 = new ArrayList<>();
			for (int i = 20; i < 30; i++) {
				numList1.add(i);
			}
			return numList1;
		} , executorService);
		future1.thenAccept(list -> {
			return list.forEach(intPrinter);
		});
		future2.thenAccept(list -> list.forEach(intPrinter));
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Elasped Time : " + (System.currentTimeMillis() - startTime) / 1000);
	}

}

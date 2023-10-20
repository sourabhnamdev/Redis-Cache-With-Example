package com.student.demo;

public class Multithreading {
	public static void main(String[] args) {

		synchronized (args) {
			Thread thread1 = new Thread(() -> {
				for (int i = 1; i <= 5; i++) {
					System.out.println("Thread 6: " + i);
				}
			});
			thread1.start();
		}
		// Task 1: Create a thread to print numbers from 1 to 5
		Thread thread1 = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				System.out.println("Thread 1: " + i);
			}
		});

		// Task 2: Create a thread to print letters from A to E
		Thread thread2 = new Thread(() -> {
			for (char ch = 'A'; ch <= 'E'; ch++) {
				System.out.println("Thread 2: " + ch);
			}
		});

		// Start the threads to run in parallel
		thread1.start();
		thread2.start();

		// Main thread can continue executing other tasks
		System.out.println("Main Thread: Task 3");
	}

	public synchronized void someSynchronizedMethod() {
		for (int i = 1; i <= 5; i++) {
			System.out.println("Thread 10: " + i);
		}
	}
}

package org.tobinok.mqdemo;

public class TestProducer {
	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.init();
		TestProducer testMq = new TestProducer();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Thread 1
		new Thread(testMq.new ProducerMq(producer)).start();
		// Thread 2
		new Thread(testMq.new ProducerMq(producer)).start();
		// Thread 3
		new Thread(testMq.new ProducerMq(producer)).start();
		// Thread 4
		new Thread(testMq.new ProducerMq(producer)).start();
		// Thread 5
		new Thread(testMq.new ProducerMq(producer)).start();
	}

	private class ProducerMq implements Runnable {
		Producer producer;

		public ProducerMq(Producer producter) {
			this.producer = producter;
		}

		@Override
		public void run() {
			while (true) {
				try {
					producer.sendMessage(MessageType.DEMO);
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
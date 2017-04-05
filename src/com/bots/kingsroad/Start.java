package com.bots.kingsroad;

import java.io.IOException;

public class Start{

	public static void main(String[] args) throws IOException {

		
		Configuration conf = new Configuration();
		
		if(conf.loadProperties()){
			Runner runner = new Runner();
			runner.start();
		}

		//thread.stopRunning();

	}
	
}
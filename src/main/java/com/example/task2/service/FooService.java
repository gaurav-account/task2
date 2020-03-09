package com.example.task2.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.task2.bean.Foo;
import reactor.core.publisher.Flux;

@Service
public class FooService  {

	private static Logger LOGGER = LoggerFactory.getLogger(FooService.class);
	private static DecimalFormat idformat = new DecimalFormat("0000");

	public FooService() {
	}

	public Flux<Foo> getFoos() {
		LocalDateTime startTime = LocalDateTime.now();

		return Flux.<Foo>create(fluxSink -> {
			boolean inFrameTime = true;
			
			while ( inFrameTime && !fluxSink.isCancelled() ) {
				fluxSink.next(getFoo());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					//e.printStackTrace();
				}
				long timenow = startTime.until(LocalDateTime.now(), 
						ChronoUnit.MILLIS);
				if (timenow > 60000) {
					LOGGER.info("FooService finish --> With timer");
					inFrameTime = false;
				}
			}
		}).share();
	}

		private static Foo getFoo() {
			RandomStringGenerator rndStringGen = new RandomStringGenerator.Builder().withinRange('A', 'Z').build();

			String name = new String(rndStringGen.generate(10));
		
			String id = idformat.format(ThreadLocalRandom.current().nextInt(0, 9999));
			
			return new Foo(id,name);
		}



}

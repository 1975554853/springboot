package com.example.springbootdemo.system.Elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

/**
 *
 * @author 19755
 */
@Slf4j
@Component
public class InitSystem {

	@PostConstruct
	public void init() throws FileNotFoundException {
		System.setProperty("es.set.netty.runtime.available.processors", "false");

	}
}

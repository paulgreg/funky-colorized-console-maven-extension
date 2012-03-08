package org.apache.maven.cli;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class FunkyColorizedPrintStreamLoggerTest {

	PrintStream stream;
	private FunkyColorizedPrintStreamLogger funkyLogger;

	@Before
	public void init() {
		stream = spy( new PrintStream( System.out ) );
		funkyLogger = new FunkyColorizedPrintStreamLogger(stream);
	}

	@Test
	public void should_print_hello() {
		funkyLogger.info( "hello in normal font" );

		verify( stream ).println( "hello in normal font" );
	}

	@Test
	public void should_print_hello_in_red_qui_pete() {
		funkyLogger.error( "hello in RED" );

		verify( stream ).print( (char)27 + "[1m" + (char)27 + "[31m" + "[ERROR] " );
		verify( stream ).println( "hello in RED" + (char)27 + "[0m" );
	}
	
	@Test
	public void should_print_BUILD_SUCCESS_in_green_qui_pete() {
		funkyLogger.info( "BUILD SUCCESS" );

		verify( stream ).print( (char)27 + "[1m" + (char)27 + "[32m" + "[INFO] " );
		verify( stream ).println( "BUILD SUCCESS" + (char)27 + "[0m" );
	}

}

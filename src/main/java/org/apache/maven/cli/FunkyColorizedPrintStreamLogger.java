package org.apache.maven.cli;

import org.apache.maven.Maven;
import org.codehaus.plexus.logging.AbstractLogger;
import org.codehaus.plexus.logging.Logger;

import java.io.PrintStream;

public class FunkyColorizedPrintStreamLogger extends AbstractLogger {
	public static final char ASCII_ESCAPE = ( char ) 27;
	public static final String ASCII_GREEN = ASCII_ESCAPE + "[32m";
	public static final String ASCII_RED =  ASCII_ESCAPE + "[31m";
	public static final String ASCII_FLASHY = ASCII_ESCAPE + "[1m";
	public static final String ASCII_CLOSED = ( char ) 27 + "[0m";
	public static final String ASCII_ORANGE = ASCII_ESCAPE + "[33m";
	private PrintStream stream;

	private String before = "";
	
	static interface Provider
	{
		PrintStream getStream();
	}

	private Provider provider;

	private static final String FATAL_ERROR = "[FATAL] ";

	private static final String ERROR = "[ERROR] ";

	private static final String WARNING = "[WARNING] ";

	private static final String INFO = "[INFO] ";

	private static final String DEBUG = "[DEBUG] ";

	public FunkyColorizedPrintStreamLogger( Provider provider )
	{
		super( Logger.LEVEL_INFO, Maven.class.getName() );

		if ( provider == null )
		{
			throw new IllegalArgumentException( "output stream provider missing" );
		}
		this.provider = provider;
	}

	public FunkyColorizedPrintStreamLogger( PrintStream out )
	{
		super( Logger.LEVEL_INFO, Maven.class.getName() );

		setStream( out );
	}
	
	public void setStream( final PrintStream out )
	{
		if ( out == null )
		{
			throw new IllegalArgumentException( "output stream missing" );
		}

		this.provider = new Provider()
		{
			public PrintStream getStream()
			{
				return out;
			}
		};
	}

	public void debug( String message, Throwable throwable )
	{
		if ( isDebugEnabled() )
		{
			PrintStream out = provider.getStream();

			out.print( DEBUG );
			out.println( message );

			if ( null != throwable )
			{
				throwable.printStackTrace( out );
			}
		}
	}

	public void info( String message, Throwable throwable )
	{
		if ( isInfoEnabled() )
		{
			PrintStream out = provider.getStream();

			if( message.equals( "------------------------------------------------------------------------" ) ) {

			}
			
			if( message.equals( "BUILD SUCCESS" ) ) {
				out.print( ASCII_FLASHY + ASCII_GREEN + INFO );
				out.println( message + ASCII_CLOSED );
			} else {
				out.print( INFO );
				out.println( message );
			}

			if ( null != throwable )
			{
				throwable.printStackTrace( out );
			}
		}
	}

	public void warn( String message, Throwable throwable )
	{
		if ( isWarnEnabled() )
		{
			PrintStream out = provider.getStream();

			out.print( ASCII_FLASHY + ASCII_ORANGE + WARNING );
			out.println( message + ASCII_CLOSED );

			if ( null != throwable )
			{
				throwable.printStackTrace( out );
			}
		}
	}

	public void error( String message, Throwable throwable )
	{
		if ( isErrorEnabled() )
		{
			PrintStream out = provider.getStream();

			out.print( ASCII_FLASHY + ASCII_RED + ERROR );
			out.println( message + ASCII_CLOSED );

			if ( null != throwable )
			{
				throwable.printStackTrace( out );
			}
		}
	}

	public void fatalError( String message, Throwable throwable )
	{
		if ( isFatalErrorEnabled() )
		{
			PrintStream out = provider.getStream();

			out.print( FATAL_ERROR );
			out.println( message );

			if ( null != throwable )
			{
				throwable.printStackTrace( out );
			}
		}
	}

	public void close()
	{
		PrintStream out = provider.getStream();

		if ( out == System.out || out == System.err )
		{
			out.flush();
		}
		else
		{
			out.close();
		}
	}

	public Logger getChildLogger( String arg0 )
	{
		return this;
	}
}
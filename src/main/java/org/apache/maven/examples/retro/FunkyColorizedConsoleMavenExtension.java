package org.apache.maven.examples.retro;

import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.cli.FunkyColorizedPrintStreamLogger;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.execution.RuntimeInformation;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;

@Component(role = AbstractMavenLifecycleParticipant.class, hint = "retro")
public class FunkyColorizedConsoleMavenExtension extends
		AbstractMavenLifecycleParticipant {

	@Requirement
	private Logger logger;

	@Requirement
	RuntimeInformation runtime;
	
	@Override
	public void afterSessionStart(MavenSession session)
			throws MavenExecutionException {
	}
	
	@Override
	public void afterProjectsRead(MavenSession session) {
        // TODO We would like to set the following FunkyColorizedPrintStreamLogger instead of built-in maven one (see PrintStreamLogger).
		logger = new FunkyColorizedPrintStreamLogger(System.out);
	}

}

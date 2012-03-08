Funky Colorized Console Maven Extension
=======================================

Here’s an experiment to colorize maven output.

It’s work in progress for now.

That project contains a new PrintStreamLogger called FunkyColorizedPrintStreamLogger based on Maven’s default PrintStreamLogger.
It also contains a skeleton extension, FunkyColorizedConsoleMavenExtension.java, wich should load that class instead of PrintStreamLogger’s one from Maven.
For now, I haven’t found a way to make that happen.

Any suggestions ?

Ugo & Grégory

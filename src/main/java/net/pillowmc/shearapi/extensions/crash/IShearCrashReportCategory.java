package net.pillowmc.shearapi.extensions.crash;

public interface IShearCrashReportCategory {
	void applyStackTrace(Throwable t);
}

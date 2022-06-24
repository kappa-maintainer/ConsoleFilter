package com.chaosthedude.consolefilter.filter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.LogRecord;

import com.chaosthedude.consolefilter.config.ConfigHandler;

public class SystemFilter extends PrintStream {

	public SystemFilter(OutputStream out) {
		super(out, true);
	}
	
	@Override
	public void println(String s) {
		if (!shouldFilter(s)) {
			super.println(s);
		}
	}
	
	private boolean shouldFilter(String s) {
		for (String filter : ConfigHandler.getMessagesToFilter()) {
			try {
				if (s.contains(filter)) {
					return true;
				}
			}catch (Exception ignored) {}
		}
		StackTraceElement[] elems = Thread.currentThread().getStackTrace();
		int BASE_DEPTH = 3;
		StackTraceElement elem = elems[BASE_DEPTH]; // The caller is always at BASE_DEPTH, including this call.
		if (elem.getClassName().startsWith("kotlin.io.")) {
			elem = elems[BASE_DEPTH + 2]; // Kotlins IoPackage masks origins 2 deeper in the stack.
		} else if (elem.getClassName().startsWith("java.lang.Throwable")) {
			elem = elems[BASE_DEPTH + 4];
		}
		String name = elem.getClassName();
		for (String cfilter : ConfigHandler.getClassesToFilter()) {
			if (name.contains(cfilter)) {
				return true;
			}
		}

		return false;
	}
	
	public static void applyFilter() {
		System.setOut(new SystemFilter(System.out));
	}

}

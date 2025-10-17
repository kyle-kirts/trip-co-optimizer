package performance;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

class Benchmark {
	public static void main(String[] args) {

		try (
			PrintStream log = new PrintStream("dynmemuse4.txt");
		) {
			BenchmarkRunner.showHeadings(log);
			for (int n = 128; n <= 513; n += 128) {
				runBenchmarks(n, log);
			}
		} catch (FileNotFoundException e) {};
    }

    private static void runBenchmarks(int n, PrintStream log) {
		System.out.println(n);
		new ArrLstDefN().time(n, log);
		new ArrLstSet().time(n, log);
		new ArrLst1().time(n, log);
		new ArrLstN().time(n, log);
		new ArrLong1().time(n, log);
		new ArrLongN().time(n, log);
		new Arr1().time(n, log);
		new ArrN().time(n, log);
		log.flush();
	}

    abstract static class BenchmarkRunner {

	public abstract String name();
	public abstract void init(int n);
	public abstract void create(int n);
	public abstract void add(int value);

	static final double milliseconds = 1.0e3;
	static final String format = "%3d N, %5.1f secs, %,4d gcs, %,16d bytes, %s\n";

	public static void showHeadings(PrintStream log) {
		final String maxMemoryFormat = "max heap memory %,16d\n";
		final String totMemoryFormat = "tot heap memory %,16d\n";
		final String freMemoryFormat = "fre heap memory %,16d\n";

		System.out.printf(maxMemoryFormat, Runtime.getRuntime().maxMemory());
		System.out.printf(totMemoryFormat, Runtime.getRuntime().totalMemory());
		System.out.printf(freMemoryFormat, Runtime.getRuntime().freeMemory());
		log.printf(totMemoryFormat, Runtime.getRuntime().totalMemory());
		log.printf(maxMemoryFormat, Runtime.getRuntime().maxMemory());
		log.printf(freMemoryFormat, Runtime.getRuntime().freeMemory());
	}

	public Long time(int n, PrintStream log) {
		long time = - System.currentTimeMillis();
		long bytes = 0;
		long gcs = 0;
		DynamicMemoryUsage.reset();
		benchmark(n);
		time += System.currentTimeMillis();
		bytes = DynamicMemoryUsage.bytesUsed();
		gcs = DynamicMemoryUsage.gcs();
		log.printf(format, n, time/milliseconds, gcs, bytes, name());
		log.flush();
		return time;
	}

	private void benchmark(int n) {
		init(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					create(n);
					DynamicMemoryUsage.update(); // capture after each new object is created in dynamic memory.
					for (int f = 0; f < n; f++) {
						add(f);
					}
					DynamicMemoryUsage.update(); // capture capture updates done in dynamic memory.
				}
			}
		}

	}
}

    class ArrLstDefN extends BenchmarkRunner {

	    ArrayList<Long> array;

	    public String name() {
		    return "new ArrayList<Long>() create/add";
	    }

	    public void init(int n) { }

	    public void create(int n) {
		    array = new ArrayList<>();
	    }

	    public void add(int value) {
		    array.add((long) value);
	    }
    }

}
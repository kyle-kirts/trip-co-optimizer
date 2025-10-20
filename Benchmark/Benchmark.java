package performance;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
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

	class ArrLstDefN extends BenchmarkRunner {

		long[] array;
		int size;

		public char name() {
			return 'P';
		}

		public void init(int n) { }

		public void create(int n) {
			array = new long[n];
			size = n;
		}

		public void add(int value) {
			if (size == array.length) {
				long[] newArray = new long[size * 2];
				System.arraycopy(array, 0, newArray, 0, size);
				array = newArray;
			}
			array[size++] = value;
		}
	}
}
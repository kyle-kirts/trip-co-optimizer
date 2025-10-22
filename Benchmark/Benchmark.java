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
		NearestNeighbor NN = new NearestNeighbor();
		NN.time(n, log);
		log.flush();
	}
}
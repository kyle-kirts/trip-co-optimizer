class NearestNeighbor extends BenchmarkRunner {

	long[] array;

	@Override
	public String name() {
		return "NearestNeighbor";
	}

	public void init(int n) {
		array = new long[n];
	}

	public void create(int n) { }

	public void add(int value) {
		array[value] = (long) value;
		//TODO this needs to implement the nearest neighbor alg.
	}
}
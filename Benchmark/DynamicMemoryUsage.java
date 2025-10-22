class DynamicMemoryUsage {

	private static Runtime rt = Runtime.getRuntime();
	private static long cumulativeBytes;
	private static long lastFreeMemory;
	private static long gcs;


	public DynamicMemoryUsage() {
		reset();
	}


	public static void reset() {
		// resets the values for a new start.
		cumulativeBytes = 0;
		gcs = 0;
		lastFreeMemory = rt.freeMemory();
	}


	public static long bytesUsed() {
		// returns current cumulative memory use
		update();
		return cumulativeBytes;
	}


	public static long gcs() {
		return gcs;
	}


	public static void update() {
		/* updates the cumulative memory usage,
		 * must be called frequently to track usage between garbage collections.
		 */
		long newFreeMemory = rt.freeMemory();
		if (newFreeMemory == lastFreeMemory) {
			// no change
		}
		else if (newFreeMemory < lastFreeMemory) {
			cumulativeBytes += lastFreeMemory - newFreeMemory;
			lastFreeMemory = newFreeMemory;
		} else if (newFreeMemory > lastFreeMemory) {
			//cumulativeBytes - gc occurred so no idea
			gcs += 1;
			lastFreeMemory = newFreeMemory;
		}
	}

}
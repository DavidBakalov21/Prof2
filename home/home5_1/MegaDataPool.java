import java.util.ArrayList;
import java.util.Optional;

class MegaData {
    public static final int smallArraySize = 1024;
    public static final int bigArraySize = 1024 * 1024;

    public float[] smallArray = new float[smallArraySize];
    public double[] bigArray = new double[bigArraySize];

    public MegaData() {
        fillData();
    }

    public void fillData() {
        java.util.Arrays.fill(smallArray, 42.0f);
        java.util.Arrays.fill(bigArray, 42.0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }
}

public class MegaDataPool {
    private  ArrayList<MegaData> pool;
    private  ArrayList<Boolean> availableObjects;

    public MegaDataPool(int size) {
        pool = new ArrayList<>(size);
        availableObjects = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            pool.add(new MegaData());
            availableObjects.add(true);
        }
    }

    public int size() {
        return pool.size();
    }

    public int usedSize() {
        int count = 0;
        for (boolean available : availableObjects) {
            if (!available) {
                count++;
            }
        }
        return count;
    }

    public Optional<MegaData> acquire() {
        for (int i = 0; i < pool.size(); i++) {
            if (availableObjects.get(i)) {
                availableObjects.set(i, false);
                return Optional.of(pool.get(i));
            }
        }
        return Optional.empty();
    }

    public boolean release(MegaData object) {
        for (int i = 0; i < pool.size(); i++) {
            if (pool.get(i).equals(object)) {
                object.fillData();
                availableObjects.set(i, true);
                return true;
            }
        }
        return false;
    }
}
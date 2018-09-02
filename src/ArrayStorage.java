import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    static int count;
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
        count = 0;
    }

    void save(Resume r) {
        storage[count] = r;
        count++;
    }

    Resume get(String uuid) {
        for (Resume r : Arrays.copyOfRange(storage, 0, count)){
            if (r.uuid.equals(uuid)){
                return r;
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        for(int i = 0; i < storage.length; i++){
            if(storage[i].uuid.equals(uuid)){
                --count;
                System.arraycopy(storage, count, storage, i, 1);
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }

    int size() {
        return count;
    }
}

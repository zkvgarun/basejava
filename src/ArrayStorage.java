import java.util.Arrays;
import java.util.Comparator;

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
        for(int i = 0; i < storage.length; i++){
            if(storage[i] == null){
                storage[i] = r;
                count++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume r : getAll()){
            if (r.uuid.equals(uuid)){
                return r;
            }
        }
        return new Resume();
    }

    void delete(String uuid) {
        for(int i = 0; i < storage.length; i++){
            if(storage[i].uuid.equals(uuid)){
                storage[i] = null;
                count--;
                getAll();
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Arrays.sort(storage, new Comparator<Resume>() {
            @Override
            public int compare(Resume r1, Resume r2) {
                if(r1 == null && r2 == null){
                    return 0;
                }
                if(r1 == null){
                    return 1;
                }
                if(r2 == null){
                    return -1;
                }
                return r1.uuid.compareTo(r2.uuid);
            }
        });
        return Arrays.copyOfRange(storage, 0, count);
    }

    int size() {
        return count;
    }
}

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for(int i = 0; i < storage.length; i++){
            if(storage[i] == null){
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (Resume r : this.getAll()){
            if (r.uuid.equals(uuid)) return r;
        }
        return new Resume();
    }

    void delete(String uuid) {
        for(int i = 0; i < storage.length; i++){
            if(storage[i].uuid.equals(uuid)){
                storage[i] = null;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
        return this.getAll().length;
    }
}

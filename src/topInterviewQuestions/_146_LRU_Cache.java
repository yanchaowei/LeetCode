import java.util.*;

public class _146_LRU_Cache {
    private int capacity;
    private int size;
    Map<Integer, Entry> map;
    ArrayList<Entry> list = new ArrayList<>();
    public _146_LRU_Cache(int capacity) {
        this.capacity = capacity;
        size = 0;
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            list.remove(map.get(key));
            list.add(map.get(key));
            return map.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.get(key).val = value;
            list.remove(map.get(key));
            list.add(map.get(key));
            return;
        }
        if(size == capacity) {
            map.remove(list.get(0).key);
            list.remove(0);
        }
        else size++;
        Entry entry = new Entry(key, value);
        map.put(key, entry);
        list.add(entry);
    }
    class Entry {
        int key;
        int val;
        public Entry(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

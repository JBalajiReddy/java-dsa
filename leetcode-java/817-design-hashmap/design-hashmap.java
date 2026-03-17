class MyHashMap {
    Integer[] mp;
    public MyHashMap() {
        mp = new Integer[1000001];
        Arrays.fill(mp, null);

    }
    
    public void put(int key, int value) {
        mp[key] = value;
    }
    
    public int get(int key) {
        return mp[key] == null ? -1 : mp[key];
    }
    
    public void remove(int key) {
        mp[key] = null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
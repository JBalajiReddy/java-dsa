class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        // If key doesn't exist, create a new TreeMap for it
        map.putIfAbsent(key, new TreeMap<>());
        
        // Add the value at the specific timestamp for this key
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        // If the key strictly doesn't exist, return ""
        if (!map.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> timeMap = map.get(key);
        
        // 2. Efficiently find the largest timestamp <= requested timestamp
        // floorKey returns the greatest key less than or equal to the given key, or null if there is no such key.
        Integer prevTime = timeMap.floorKey(timestamp);
        
        if (prevTime == null) {
            return "";
        }
        
        return timeMap.get(prevTime);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
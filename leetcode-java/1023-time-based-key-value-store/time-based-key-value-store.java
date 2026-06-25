class TimeMap {
    class Data {
        int timestamp;
        String value;

        Data(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private Map<String, List<Data>> mp;

    public TimeMap() {
        mp = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        mp.putIfAbsent(key, new ArrayList<>());
        mp.get(key).add(new Data(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Data> ls = mp.get(key);
        if (ls == null) {
            return "";
        }
        int l = 0, r = ls.size() - 1;
        String res = "";
        while (l <= r) {
            int m = l + (r - l) / 2;
            int timestamp_prev = ls.get(m).timestamp;
            if (timestamp_prev <= timestamp) {
                res = ls.get(m).value;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
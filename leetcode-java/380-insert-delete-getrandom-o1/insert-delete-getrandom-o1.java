class RandomizedSet {

    List<Integer> ls;
    Map<Integer, Integer> map;

    public RandomizedSet() {
        ls = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        ls.add(val);
        map.put(val, ls.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;

        int idx = map.get(val);
        ls.set(idx, ls.get(ls.size() - 1));
        map.put(ls.get(idx), idx);
        ls.remove(ls.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        Random rand = new Random();
        return ls.get(rand.nextInt(ls.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
class Solution {
    public int[] countMentions(int n, List<List<String>> events) {
        int[] res = new int[n];
        Map<Integer, Integer> mp = new HashMap<>(); //[id, TS] -> offline

        events.sort(
                (a, b) -> {
                    int t1 = Integer.parseInt(a.get(1));
                    int t2 = Integer.parseInt(b.get(1));
                    if (t1 != t2) {
                        return Integer.compare(t1, t2);
                    }

                    if (a.get(0).equals("OFFLINE") && b.get(0).equals("MESSAGE"))
                        return -1;
                    if (a.get(0).equals("MESSAGE") && b.get(0).equals("OFFLINE"))
                        return 1;

                    return 0;
                });

        for (int i = 0; i < events.size(); i++) {
            List<String> event = events.get(i);

            if (event.get(0).equals("MESSAGE")) {
                String s = event.get(2);
                if (s.indexOf("id") != -1) {
                    String[] parts = s.split(" ");
                    for (String part : parts) {
                        int id = Integer.parseInt(part.substring(2));
                        res[id]++;
                    }
                } else if (s.equals("ALL")) {
                    for (int j = 0; j < n; j++) {
                        res[j]++;
                    }
                } else if (s.equals("HERE")) {
                    for (int j = 0; j < n; j++) {
                        if (!mp.containsKey(j)) {
                            res[j]++;
                            continue;
                        }

                        int time = mp.get(j);
                        int timeStamp = Integer.parseInt(event.get(1));
                        if (timeStamp - time >= 60) {
                            res[j]++;
                        }
                    }
                }

            } else if (event.get(0).equals("OFFLINE")) {
                int id = Integer.parseInt(event.get(2));
                int time = Integer.parseInt(event.get(1));
                mp.put(id, time);
            } 
        }

        return res;
    }
}
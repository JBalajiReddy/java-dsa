public class Twitter {

    // Global timestamp counter to ensure total ordering across all tweets
    private static int timestamp = 0;

    // Helper class to represent a Node in the Tweet linked list
    private static class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timestamp++; // Newer tweets have LARGER timestamps
            this.next = null;
        }
    }

    // Maps userId -> head of their Tweet linked list (newest first)
    private Map<Integer, Tweet> tweetMap;
    // Maps followerId -> Set of followeeIds
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
        tweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    /** Post a new tweet. O(1) time complexity. */
    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId);
        // Insert new tweet at the head of the user's tweet list
        newTweet.next = tweetMap.get(userId);
        tweetMap.put(userId, newTweet);
    }

    /**
     * Retrieve the 10 most recent tweet IDs in the user's news feed.
     * Merge K sorted lists using a Max-Heap.
     * Time Complexity: O(K log K + 10 log K) where K is number of followees.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        
        // Ensure user follows themselves so their own tweets show up in their feed
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        Set<Integer> followees = followMap.get(userId);

        // Max-Heap ordered by newest tweet first (largest timestamp first)
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

        // Step 1: Push the head (most recent tweet) of each followee's list into the Heap
        for (int followeeId : followees) {
            Tweet head = tweetMap.get(followeeId);
            if (head != null) {
                maxHeap.offer(head);
            }
        }

        // Step 2: Extract up to 10 most recent tweets
        while (!maxHeap.isEmpty() && res.size() < 10) {
            Tweet top = maxHeap.poll();
            res.add(top.id);

            // If this followee has more tweets, offer their next tweet to the heap
            if (top.next != null) {
                maxHeap.offer(top.next);
            }
        }

        return res;
    }

    /** Follow a user. O(1) time complexity. */
    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    /** Unfollow a user. O(1) time complexity. */
    public void unfollow(int followerId, int followeeId) {
        // A user cannot unfollow themselves
        if (followMap.containsKey(followerId) && followerId != followeeId) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}



class Twitter_Using_PriorityQueue {
    private int count;
    private Map<Integer, List<int[]>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;

    public Twitter_Using_PriorityQueue() {
        this.count = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[] {count, tweetId});
        if (tweetMap.get(userId).size() > 10) {
            tweetMap.get(userId).remove(0);
        }
        count--;
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        if (followMap.get(userId).size() >= 10) {
            PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
            for (int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId))
                    continue;
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                maxHeap.offer(new int[] {-tweet[0], tweet[1], followeeId, index - 1});
                if (maxHeap.size() > 10) {
                    maxHeap.poll();
                }
            }
            while (!maxHeap.isEmpty()) {
                int[] top = maxHeap.poll();
                minHeap.offer(new int[] {-top[0], top[1], top[2], top[3]});
            }
        } else {
            for (int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId))
                    continue;
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                minHeap.offer(new int[] {tweet[0], tweet[1], followeeId, index - 1});
            }
        }

        while (!minHeap.isEmpty() && res.size() < 10) {
            int[] top = minHeap.poll();
            res.add(top[1]);
            int nextIndex = top[3];
            if (nextIndex >= 0) {
                List<int[]> tweets = tweetMap.get(top[2]);
                int[] nextTweet = tweets.get(nextIndex);
                minHeap.offer(new int[] {nextTweet[0], nextTweet[1], top[2], nextIndex - 1});
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}


/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
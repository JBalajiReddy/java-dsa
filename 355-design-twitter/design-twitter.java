class Twitter {

    private static int timeStamp = 0;

    private class User {
        int id;
        Set<Integer> followed;
        Tweet tweetHead;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id); //self follow (useful for feed design)
            tweetHead = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            if (this.id != id)
                followed.remove(id);
        }

        public void post(int id) {
            Tweet newTweet = new Tweet(id);
            newTweet.next = tweetHead;
            tweetHead = newTweet;
        }
    }

    private class Tweet {
        int id;
        int time;
        Tweet next;

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }

    private Map<Integer, User> userMap;

    public Twitter() {
        userMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));

        userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new LinkedList<>();
        if (!userMap.containsKey(userId))
            return newsFeed;

        Set<Integer> followedUsers = userMap.get(userId).followed;
        //max heap
        PriorityQueue<Tweet> tweetHeap = new PriorityQueue<>(followedUsers.size(), (a, b) -> b.time - a.time); 

        for (int user : followedUsers) {
            Tweet tweet = userMap.get(user).tweetHead;
            if (tweet != null)
                tweetHeap.offer(tweet);
        }

        int cnt = 0;
        while (!tweetHeap.isEmpty() && cnt < 10) {
            Tweet tweet = tweetHeap.poll();
            newsFeed.add(tweet.id);
            cnt++;
            if (tweet.next != null)
                tweetHeap.offer(tweet.next);
        }

        return newsFeed;
    }

    //follower follows followee
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId))
            userMap.put(followerId, new User(followerId));
        if (!userMap.containsKey(followeeId))
            userMap.put(followeeId, new User(followeeId));

        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId) && followerId != followeeId) {
            userMap.get(followerId).unfollow(followeeId);
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
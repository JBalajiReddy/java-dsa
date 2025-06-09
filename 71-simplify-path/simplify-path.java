class Solution {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            if (!s.equals("") && !s.equals(".") && !s.equals(".."))
                stack.push(s);
            else if (!stack.isEmpty() && s.equals(".."))
                stack.pop();
        }

        StringBuilder res = new StringBuilder();
        for (String s : stack) {
            res.append("/");
            res.append(s);
        }
        return res.length() == 0 ? "/" : res.toString();
    }
}
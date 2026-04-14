// class Solution {
//     public String simplifyPath(String path) {
//         String[] parts = path.split("/");
//         Stack<String> st = new Stack<>();
//         for (String p : parts) {
//             if (!p.equals("") && !p.equals(".") && !p.equals("..")) {
//                 st.push(p);
//             } else if (!st.isEmpty() && p.equals("..")) {
//                 st.pop();
//             }
//         }
//         StringBuilder sb = new StringBuilder();
//         for (String s : st) {
//             sb.append("/");
//             sb.append(s);
//         }
//         return sb.length() == 0 ? "/" : sb.toString();
//     }
// }


public class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");

        for (String cur : paths) {
            if (cur.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!cur.equals("") && !cur.equals(".")) {
                stack.push(cur);
            }
        }

        return "/" + String.join("/", stack);
    }
}
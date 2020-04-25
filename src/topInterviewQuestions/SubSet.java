package test;

import java.util.*;

/**
 * @author ycw
 *
 * T 78. Subsets
 * 90. Subsets II
 * 46. Permutations
 * 47. Permutations II
 * 784. Letter Case Permutation
 */
public class SubSet {

    public static void main(String[] args) {


    }
}

class Solution78 {
    /**
     * LeetCode 78. Subsets
     * 参数数组中无重复数字
     * 结果集中要求无重复子集
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null) return ret;
        ArrayList<Integer> list = new ArrayList<>();
        dfs(nums, 0, list);
        return ret;
    }

    public void dfs(int[] nums, int offset, ArrayList<Integer> list){
        if(offset == nums.length){
            ret.add(new ArrayList<>(list));
            return;
        }
        dfs(nums, offset+1, list);
        list.add(nums[offset]);
        dfs(nums, offset+1, list);
        list.remove(list.size() - 1);
    }
}

class Solution78_1 {
    /**
     * 另一种风格的dfs+回溯
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null) return ret;
        ArrayList<Integer> list = new ArrayList<>();
        dfs(nums, 0, list);
        return ret;
    }
    public void dfs(int[] nums, int start, ArrayList<Integer> list){
        ret.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            dfs(nums, i+1, list);
            list.remove(list.size() - 1);
        }
    }
}
class Solution78_2 {
    /**
     * 递归也可以做（也叫BFS？）。
     * 思路：
     * 当我们遍历数组中的每一个元素，我们可以选择也可以不选择：
     * 1. 如果我们不选择，那么就保持结果集中不变。
     * 2. 如果我们选择，那么就出现已有结果集中的所有子集分别加上这个元素，就生成了包含这个元素的新的子集；
     * 将以上两种情况加起来就是对当前元素处理过的新的结果集。
     * 对了，因为空集 [], 也是符合的，并且我们要使用它对结果集初始化。{[]}
     * 比如对于数组{1， 2， 3}，遍历到第一个元素后结果集为 {[], [1]}, 遍历到第二元素结果集变为：{[], [1], [2], [1, 2]}, 以此类推。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<Integer>());
        for(int i = 0; i < nums.length; i++){
            int size = ret.size();
            for(int j = 0; j < size; j++){
                ArrayList<Integer> temList = new ArrayList<>(ret.get(j));
                temList.add(nums[i]);
                ret.add(temList);
            }
        }
        return ret;
    }
}

class Solution90{
    /**
     * LeetCode 78. Subsets
     * 参数数组中可能含有重复数字（这是与上一题中不一样的地方）：
     * 1. 子集中可以有重复数字，但返回的结果中不能含有相同的子集
     * 所以我们用直接用链表作为结果集就有可能使前后两个相同的子集先后放入，导致出现重复子集，因此这里我们使用set作为结果集，以免相同子集加入，返回时转化为链表
     * 2. 此外，由于参数提供的数组并不是有序的，这就导致出现很多含有相同数据集但顺序不同的子集，所以，我们可以在dfs前对数组进行一个排序，
     * 这样可以保证只要两个子集包含相同的数字，那么他们的顺序也会相同，故不可以连续加入结果集中
     *
     */
    Set<List<Integer>> ret = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums == null) return new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, list);
        return new ArrayList<>(ret);
    }

    public void dfs(int[] nums, int offset, ArrayList<Integer> list){
        if(offset == nums.length){
            // Collections.sort(list);
            ret.add(new ArrayList<>(list));
            return;
        }
        dfs(nums, offset+1, list);
        list.add(nums[offset]);
        dfs(nums, offset+1, list);
        list.remove(list.size() - 1);
    }
}
class Solution90_1 {
    ArrayList<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums == null) return new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, list);
        return ret;
    }

    public void dfs(int[] nums, int start, ArrayList<Integer> list){
        ret.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++){
            if(i != start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(nums, i+1, list);
            list.remove(list.size() - 1);
        }
    }
}
class Solution90_2 {
    /**
     * 我们再用递归的方式做一下，因为数组中包含重复数字，所以我们遍历时，如果第i个元素和第i+1个元素相同，就会出现重复。
     * 比如数组{1， 2， 2}
     * 初始     ：{[]}
     * 第1个元素：{[], [1]}
     * 第2个元素：{[], [1], [2], [1, 2]}
     * 第3个元素：{[], [1], [2], [1, 2]，[2], [1, 2], [2, 2], [1, 2, 2]}  //出现重复
     * 那么规避的方式也很简单：当我们判断出当前元素和上一个元素相同时，我们仅仅将其插入到上一步生成的元素。
     * 对应上面的例子就是仅仅将其插入或不插入这些子集：{[2], [1, 2]}
     * 那么遍历完第3个元素后的正确结果应该是：{[], [1], [2], [1, 2], [2, 2], [1, 2, 2]}
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<Integer>());
        int preIndex = 1;
        for(int i = 0; i < nums.length; i++){
            int size = ret.size();
            int j = 0;
            if(i != 0 && nums[i] == nums[i - 1]) j = preIndex;
            for(; j < size; j++){
                ArrayList<Integer> temList = new ArrayList<>(ret.get(j));
                temList.add(nums[i]);
                ret.add(temList);
            }
            preIndex = size;
        }
        return ret;
    }
}

class Solution901{
    /**
     * LeetCode 78. Subsets
     * 参数数组中可能含有重复数字（这是与上一题中不一样的地方）：
     * 1. 子集中可以有重复数字，但返回的结果中不能含有相同的子集
     * 所以我们用直接用链表作为结果集就有可能使前后两个相同的子集先后放入，导致出现重复子集，因此这里我们使用set作为结果集，以免相同子集加入，返回时转化为链表
     * 2. 此外，由于参数提供的数组并不是有序的，这就导致出现很多含有相同数据集但顺序不同的子集，所以，我们可以在dfs前对数组进行一个排序，
     * 这样可以保证只要两个子集包含相同的数字，那么他们的顺序也会相同，故不可以连续加入结果集中
     *
     */
    ArrayList<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums == null) return new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, list);
        return ret;
    }

    public void dfs(int[] nums, int offset, ArrayList<Integer> list){
        if(offset == nums.length){
            // Collections.sort(list);
            ret.add(new ArrayList<>(list));
            return;
        }
        dfs(nums, offset+1, list);
        list.add(nums[offset]);
        dfs(nums, offset+1, list);
        list.remove(list.size() - 1);
    }
}

class Solution46 {
    /**
     * LeetCode 46. Permutations
     * 题目要求：给定一个不包含重复数字的数据集，要求返回这个数据集的全排列。
     * 思路：
     * 1. 整体还是一个 dfs+回溯 的思路
     * 2. 用一个链表存储新加入的数字，当链表长度等于数组长度，表明生成一个新的排列
     * 3. 由于原数据集(数组）中无重复数字)，那么我们在判断一个数字是否已经加入时就可以直接判断链表中是否已经包含这个数字，若不包含，则证明还没加入；
     *    但是，当遇到数据集中含有重复数字的时候，就不可以这样判断了，因为一个数字可能有两次或多次，当我们判断链表中已经有了这个数字，那这个数字第二次出现就五分啊加入，
     *    这时候我们可以用一个标记数组(boolean类型的数组)标记每个数字是否已经加入。
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null) return ret;
        dfs(nums, new ArrayList<Integer>());
        return ret;
    }

    public void dfs(int[] nums, ArrayList<Integer> list){
        if(list.size() == nums.length){
            ret.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(list.contains(nums[i])) continue;
            list.add(nums[i]);
            dfs(nums, list);
            list.remove(list.size() - 1);
        }
    }
}

class Solution47 {
    /**
     * LeetCode 47. Permutations II
     * 题目要求：给定一个可能含重复数字的数据集，要求返回这个数据集的全排列。
     * 思路：
     * 1. 整体还是一个 dfs+回溯 的思路
     * 2. 用一个链表存储新加入的数字，当链表长度等于数组长度，表明生成一个新的排列
     * 3. 遇到数据集中含有重复数字的时候，就不可以这样判断了，因为一个数字可能有两次或多次，当我们判断链表中已经有了这个数字，那这个数字第二次出现就五分啊加入，
     *    这时候我们可以用一个标记数组(boolean类型的数组)标记每个数字是否已经加入。
     * 4. 两外需要注意的是，当遇到两个重复的数据，先加入a后加入b和先加入b后加入a会出现相同的排列，所以需要使用集合类型保存结果集，防止重复排列加入。
     */
    Set<List<Integer>> ret = new HashSet<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null) return new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), visited);
        return new ArrayList<>(ret);
    }

    public void dfs(int[] nums, ArrayList<Integer> list, boolean[] visited){
        if(list.size() == nums.length){
            ret.add(new ArrayList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            list.add(nums[i]);
            visited[i] = true;
            dfs(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
class Solution47_1 {
    /**
     * 另一种方式的dfs+回溯
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), visited);
        return ret;
    }
    public void dfs(int[] nums, ArrayList<Integer> list, boolean[] visited){
        if(list.size() == nums.length){
            ret.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            if(i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])continue;
            list.add(nums[i]);
            visited[i] = true;
            dfs(nums, list, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}

class Solution784 {
    /**
     * LeetCode 784. Letter Case Permutation
     * 题目要求：给定一个字符串，将其中的字母变为大写或者小写可以变成另一个字符串，要求返回所有可能的字符串的集合
     * 思路：典型的dfs+回溯，只是在每次处理时需要判断此处的字符是字母还是数字，数字直接追加，字母则可以分为大写和小写
     */
    List<String> ret = new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        if(S == null) return ret;
        String str = "";
        dfs(S, 0, str);
        return ret;
    }

    public void dfs(String S, int offset, String str){
        if(offset == S.length()){
            ret.add(str);
            return;
        }
        char c = S.charAt(offset);
        if(c >= '0' && c <= '9') {
            dfs(S, offset + 1, str + c);
        }
        if(c >= 'a' && c <= 'z'){
            dfs(S, offset + 1, str + c);
            dfs(S, offset + 1, str + (char)(c - 32));
        }
        if(c >= 'A' && c <= 'Z'){
            dfs(S, offset + 1, str + (char)(c + 32));
            dfs(S, offset + 1, str + c);
        }
        return;
    }
}

class Solution39 {
    /**
     * LeetCode 39. Combination Sum
     * 题目要求：给一个无重复数字的候选数据集和一个目标数字，要求从候选数据集中选出相加的和等于目标数字的数据集的所有情况，候选数据集中的数字可以被重复使用。
     * 思路：
     * 我们依然考虑dfs+回溯的思想，但是本题中每个数字可以被重复使用，dfs考虑起来不是那么方便。所以我们换一种方式。
     * 采用迭代+dfs+回溯的方式。
     * dfs每次需要一个起使偏移量，那么我们每次递归的时候将起使偏移量设置为原值就实现了数字的重复使用。
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return ret;
    }
    public void backtrack(int[] candidates, int target, int start, ArrayList<Integer> list){
        if(target < 0) return;
        if(target == 0){
            ret.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, list); // 不用start+1, 因为每个数可以重复使用
            list.remove(list.size() - 1);
        }
    }
}

class Solution40 {
    /**
     * LeetCode 40. Combination Sum II
     * 本题和 41 题有两点不同：
     * 1. 提供的候选数可能重复；
     * 2. 每个数字不可重复使用。
     * 因此做出相应修改就可以。
     */
    Set<List<Integer>> ret = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return new ArrayList<>(ret);
    }
    public void backtrack(int[] candidates, int target, int start, ArrayList<Integer> list){
        if(target < 0) return;
        if(target == 0){
            ret.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, list); // 不用start+1, 因为每个数可以重复使用
            list.remove(list.size() - 1);
        }
    }
}

class Solution401 {
    /**
     * LeetCode 40. Combination Sum II
     * 本题和 41 题有两点不同：
     * 1. 提供的候选数可能重复；
     * 2. 每个数字不可重复使用。
     *  每个数字不可重复修改还可以通过另一种方式：
     *  for 循环的目的是: 每次循环找出以第i个数字开始的序列，所以当第i个数字和第i-1个数字相同时，由于后续的回溯处理是一样的，就会生成一系列重复的序列
     *  因此，这种情况下可以直接跳过：
     */
    List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return ret;
    }
    public void backtrack(int[] candidates, int target, int start, ArrayList<Integer> list){
        if(target < 0) return;
        if(target == 0){
            ret.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            if(i > start && candidates[i] == candidates[i-1]) continue;
            list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, list); // 不用start+1, 因为每个数可以重复使用
            list.remove(list.size() - 1);
        }
    }
}

class Solution402 {
    /**
     * LeetCode 40. Combination Sum II
     * 当然这种数字不重复使用的，也可以用我们笨笨的dfs+回溯，那就要考set去重了
     */
    Set<List<Integer>> ret = new HashSet<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return new ArrayList<>(ret);
    }
    public void backtrack(int[] candidates, int target, int offset, ArrayList<Integer> list){
        if(target == 0){
            ret.add(new ArrayList<>(list));
            return;
        }
        if(target < 0 || offset >= candidates.length) return;
        backtrack(candidates, target, offset + 1, list);
        list.add(candidates[offset]);
        backtrack(candidates, target - candidates[offset], offset + 1, list);
        list.remove(list.size() - 1);
    }
}

class Solution131 {
    /**
     * LeetCode 131. Palindrome Partitioning
     * 题目要求：将一个给定的字符串，分割成若干个子字符串，使得每一个字符串都为回文字符串，返回所有的分割情况。
     */
    List<List<String>> ret = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backtrack(s, 0, new ArrayList<String>());
        return ret;
    }
    public void backtrack(String s, int start, ArrayList<String> list)
    {
        if(start == s.length()){
            ret.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < s.length(); i++){
            if(isPalindrome(s, start, i)){
                list.add(s.substring(start, i + 1));
                backtrack(s, i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int l, int h){
        while(l < h){
            if(s.charAt(l) != s.charAt(h)) return false;
            l++;
            h--;
        }
        return true;
    }
}

class Solution79 {
    boolean[][] visited;
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0) return false;
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for(int i =0 ; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(board, word, "", i, j)) return true;
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, String str, int i, int j){
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] == true) return false;
        if(str.length() == word.length()) return true;
        if(word.charAt(str.length()) != board[i][j]) return false;
        visited[i][j] = true;
        if(dfs(board, word, str + board[i][j], i + 1, j)) return true;
        if(dfs(board, word, str + board[i][j], i - 1, j)) return true;
        if(dfs(board, word, str + board[i][j], i, j + 1)) return true;
        if(dfs(board, word, str + board[i][j], i, j - 1)) return true;
        visited[i][j] = false;
        return false;
    }
}
class Solution79_1 {
    /**
     * 这里的思路依然是 dfs+递归；只是为了减少空间复杂度，使用位屏蔽来标记已经访问过的位置。
     * 解释这里使用的位屏蔽：
     * ASCII 的范围是： 0000 0000 ~ 0111 1110 也就是 0 ~ 126
     * 对每个位置的字符和255进行异或运算：board[i][j] ^= 255;
     * 即： (0000 0000 ~ 0111 1110) ^ (1111 1111) -> 1xxx xxxx 总比原字符大128甚至更多，所以一定不是一个有效的字符，以此表示该字符已经被访问；
     * 在递归返回时，又可再次 board[i][j] ^= 255，将其恢复成原来的字符。
     */
    int m;
    int n;
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0) return false;
        m = board.length;
        n = board[0].length;
        for(int i =0 ; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(board, word, "", i, j)) return true;
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, String str, int i, int j){
        if(str.length() == word.length()) return true;
        if(i < 0 || i >= m || j < 0 || j >= n) return false;
        if(word.charAt(str.length()) != board[i][j]) return false;
        board[i][j] ^= 255;
        if(dfs(board, word, str + board[i][j], i + 1, j)) return true;
        if(dfs(board, word, str + board[i][j], i - 1, j)) return true;
        if(dfs(board, word, str + board[i][j], i, j + 1)) return true;
        if(dfs(board, word, str + board[i][j], i, j - 1)) return true;
        board[i][j] ^= 255;
        return false;
    }
}

class Solution212 {
    ArrayList<String> ret = new ArrayList<>();
    int m, n;
    public List<String> findWords(char[][] board, String[] words) {
        if(board == null || board.length == 0) return ret;
        m = board.length;
        n = board[0].length;
        for(String word : words){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    backTrack(board, i, j, word, "");
                }
            }
        }
        return ret;
    }
    public boolean backTrack(char[][] board, int i, int j, String word, String str){
        if(str.length() == word.length()){
            ret.add(str);
            return true;
        }
        if(i < 0 || i >= m || j < 0 || j >= n) return false;
        if(board[i][j] != word.charAt(str.length())) return false;
        board[i][j] ^= 255;
        if(backTrack(board, i + 1, j, word, str + board[i][j])) return true;
        if(backTrack(board, i - 1, j, word, str + board[i][j])) return true;
        if(backTrack(board, i, j + 1, word, str + board[i][j])) return true;
        if(backTrack(board, i, j - 1, word, str + board[i][j])) return true;
        board[i][j] ^= 255;
        return false;
    }
}

/**
 * 回溯思想解法一般可以归纳为以下思路：
 * int start = x;   // 选择一个合适的起点
 * while(问题未被解决/所获临时结果未达到有效)
 *      for(从start端可以进行的所有路径){
 *          判断进行这条路径是否合法;
 *          合法就做相应处理；
 *          递归调用处理剩下的部分；
 *          恢复到处理前的状态；
 *      }
 * 返回结果集；
 */



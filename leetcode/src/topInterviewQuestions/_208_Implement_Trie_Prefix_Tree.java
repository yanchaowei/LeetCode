package topInterviewQuestions;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.HashMap;
import java.util.Map;

public class _208_Implement_Trie_Prefix_Tree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}

class Trie {

    class TrieNode {
        boolean isWord = false;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode current = root;
        for(char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        current.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode current = root;
        for(char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if(current == null) return false;
        }
        return current.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for(char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if(current == null) return false;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

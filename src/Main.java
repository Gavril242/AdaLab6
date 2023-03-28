import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class TrieNode {
    private final TrieNode[] children;
    private boolean isWord;

    public TrieNode(int size) {
        children = new TrieNode[size];
        isWord = false;
    }

    public boolean hasChild(char c) {
        int index = c - 'a';
        return index >= 0 && index < children.length && children[index] != null;
    }

    public TrieNode getChild(char c) {
        return children[c - 'a'];
    }

    public void setChild(char c, TrieNode child) throws IllegalArgumentException {
        if (c < 'a' || c > 'z') {
            throw new IllegalArgumentException("Invalid character: " + c);
        }

        children[c - 'a'] = child;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }
}

class Trie {
    private final TrieNode root;
    private final int size;

    public Trie(int size) {
        root = new TrieNode(size);
        this.size = size;
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (!current.hasChild(c)) {
                current.setChild(c, new TrieNode(size));
            }
            current = current.getChild(c);
        }
        current.setWord(true);
    }

    private List<String> getWords(TrieNode node, String prefix) {
        List<String> words = new ArrayList<>();
        if (node.isWord()) {
            words.add(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            TrieNode child = node.getChild(c);
            if (child != null) {
                words.addAll(getWords(child, prefix + c));
            }
        }
        return words;
    }

    public void printWords() {
        List<String> words = getWords(root, "");
        Collections.sort(words);
        for (String word : words) {
            System.out.println(word);
        }
    }
}

public class Main{
    public static void main(String[] args) {
        // Create a new Trie with a size of 26 (for the 26 letters of the alphabet)
        Trie trie = new Trie(26);

        // Define an array of smartphone names to choose from
        String[] smartphones = {"iphone", "samsung", "samsang", "oneplus", "xiaomi", "huawei", "sony", "soney", "motorola", "moto"};

        // Add 10 smartphone names to the Trie
        for (int i = 0; i < 10; i++) {
            // Add the smartphone name to the Trie
            trie.addWord(smartphones[i]);
        }

        // Print all the smartphone names in alphabetical order
        trie.printWords();

    }
}

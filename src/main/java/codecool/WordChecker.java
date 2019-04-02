package codecool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordChecker {

    private CustomFileReader customFileReader;
    private Set<String> suggesstionsList;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public WordChecker() {
        customFileReader = new CustomFileReader();
        suggesstionsList = new HashSet<>();
    }

    public boolean searchWordInWordlist(String searchedWord) {
        List<String> wordlist =  customFileReader.readWordList("wordlist.txt");

        for(String element: wordlist) {
            if(element.equalsIgnoreCase(searchedWord)) {
                return true;
            }
        }
        return false;
    }

    public void swappingCharacters(String word) {
        for(int i=0; i<word.length() - 1; i++) {
            StringBuilder builder = new StringBuilder();

            for(int j=0; j<word.length(); j++) {
                if(i + 1 == j) {
                    continue;
                }
                if(i == j) {
                    builder.append(word.charAt(i+1));
                    builder.append(word.charAt(i));
                    continue;
                }
                builder.append(word.charAt(j));
            }

            if(searchWordInWordlist(builder.toString())) {
                suggesstionsList.add(builder.toString());
            }
        }
    }

    public void insertLettersBetweenAdjacentLetters(String word) {
        for(int i=0; i<alphabet.length(); i++) {
            StringBuilder builder = new StringBuilder();

            for(int j=0; j<word.length(); j=j+2) {

                if(j % 2 == 0) {
                    builder.append(alphabet.charAt(i));
                }
                builder.append(word.charAt(j));

                if(word.length() % 2 == 1) {
                    if(j + 1 == word.length()) {
                        builder.append(alphabet.charAt(i));

                        if(searchWordInWordlist(builder.toString())) {
                            suggesstionsList.add(builder.toString());
                        }
                        break;
                    }
                }
                builder.append(word.charAt(j+1));

                if(word.length() % 2 == 0) {
                    if(j + 1== word.length() - 1) {
                        builder.append(alphabet.charAt(i));

                        if(searchWordInWordlist(builder.toString())) {
                            suggesstionsList.add(builder.toString());
                        }
                        break;
                    }
                }
            }
        }
    }

    public void deleteEachCharacter(String word) {
        StringBuilder builder = new StringBuilder(word);

        for(int i = builder.toString().length() - 1; i >= 0; i--) {
            builder.deleteCharAt(i);

            if(searchWordInWordlist(builder.toString())) {
                suggesstionsList.add(builder.toString());
            }
        }
    }

    public void replaceEachCharacterOthers(String word) {
        StringBuilder builder = new StringBuilder(word);

        for(int i=0; i<builder.toString().length(); i++) {
            for(int j=0; j<alphabet.length(); j++) {
                builder.setCharAt(i, alphabet.charAt(j));

                if(searchWordInWordlist(builder.toString())) {
                    suggesstionsList.add(builder.toString());
                }

                if(j == alphabet.length() - 1) {
                    builder.setCharAt(i, word.charAt(i));
                }
            }
        }
    }

    


    public static void main(String[] args) {
        WordChecker wordChecker = new WordChecker();
//        boolean result = wordChecker.searchWordInWordlist("makexxxxxxxxx");
//
//        if(result) {
//            System.out.println("The word has been found");
//        } else {
//            System.out.println("Not found");
//        }
        wordChecker.swappingCharacters("make");

        wordChecker.insertLettersBetweenAdjacentLetters("MAKE");

        wordChecker.deleteEachCharacter("make");

        wordChecker.replaceEachCharacterOthers("make");

    }
}

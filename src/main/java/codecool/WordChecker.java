package codecool;

import java.util.*;

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

    public void splitWordInsertingSpaceBetweenCharacters(String word) {
        StringBuilder builder = insertSpaceBetweenParCharacters(word);
        String[] splicedWord = builder.toString().split(" ");
        List<String> wordsList = new ArrayList<>();
        List<String> result = new ArrayList<>();

        for(String element: splicedWord) {
            if(element.length() > 1) {
                wordsList.add(element);
            }
        }

        for(String element: wordsList) {
            if(searchWordInWordlist(element)) {
                result.add(element);
            }
        }

        if(result.size() > 1) {
            for(String element: result) {
                replaceEachCharacterOthers(element);
            }
        }
    }

    public void spellchecking(String word) {
        String[] splicedInputText = word.split(" ");

        for(String element: splicedInputText) {
            if(!searchWordInWordlist(element)) {
                System.out.println("word not found: " + element.toUpperCase() + "\n");

                this.suggesstionsList = new HashSet<>();

                swappingCharacters(element);
                insertLettersBetweenAdjacentLetters(element);
                deleteEachCharacter(element);
                replaceEachCharacterOthers(element);
                splitWordInsertingSpaceBetweenCharacters(element);

                for(String suggestedWord: this.suggesstionsList) {
                    System.out.println(suggestedWord);
                }
                System.out.println();
            }
        }
    }

    private StringBuilder insertSpaceBetweenParCharacters(String word) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < word.length(); i += 2) {
            if(i == word.length() - 1) {
                builder.append(word.substring(i, i + 1));
                break;
            }
            builder.append(word.substring(i, i + 2));
            builder.append(" ");
        }
        return builder;
    }
}

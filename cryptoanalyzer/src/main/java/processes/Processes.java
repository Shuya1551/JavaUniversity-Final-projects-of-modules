package processes;

import valid.Validator;

import java.util.HashMap;

import static lists.Alphabet.RUSSIAN_ALPHABET;

public class Processes {

    Validator validator = new Validator();
    FileReaderWriter fileRW = new FileReaderWriter();

    public void encrypt(String firstFile, String secondFile, int key) {
        String text = fileRW.fileReader(firstFile);

        char[] symbols = text.toLowerCase()
                             .toCharArray();
        validator.validateSymbol(symbols, RUSSIAN_ALPHABET);
        for (int i = 0; i < symbols.length; i++) {
            boolean check = true;
            int j = 0;
            while (check) {
                if (symbols[i] == RUSSIAN_ALPHABET[j]) {
                    if ((j + key) >= RUSSIAN_ALPHABET.length) {
                        int altKey = (j + key) % RUSSIAN_ALPHABET.length;
                        symbols[i] = RUSSIAN_ALPHABET[altKey];
                    } else {
                        symbols[i] = RUSSIAN_ALPHABET[j + key];
                    }
                    check = false;

                }
                if (j == RUSSIAN_ALPHABET.length - 1) {
                    check = false;
                } else j++;
            }
        }
        fileRW.fileWriter(secondFile, String.valueOf(symbols));
    }

    public void decrypt(String firstFile, String secondFile, int code) {

        String text = fileRW.fileReader(firstFile);

        char[] symbols = text.toLowerCase()
                             .toCharArray();
        validator.validateSymbol(symbols, RUSSIAN_ALPHABET);
        for (int i = 0; i < symbols.length; i++) {
            boolean check = true;
            int j = 0;
            while (check) {
                int key = code % RUSSIAN_ALPHABET.length;
                if (symbols[i] == RUSSIAN_ALPHABET[j]) {
                    if ((j - key) < 0) {
                        int keyVal = RUSSIAN_ALPHABET.length - Math.abs(j - key);
                        symbols[i] = RUSSIAN_ALPHABET[keyVal];
                    } else {
                        symbols[i] = RUSSIAN_ALPHABET[j - key];
                    }
                    check = false;
                }
                if (j == RUSSIAN_ALPHABET.length - 1) {
                    check = false;
                } else j++;

            }
        }
        fileRW.fileWriter(secondFile, String.valueOf(symbols));
    }

    public void decryptByBruteForce(String firstFile, String secondFile, String[] verificationDictionary) {
        int mostCorrect = 0;
        int key = 0;
        for (int i = 0; i <= 45; i++) {
            int counter = 0;
            decrypt(firstFile, secondFile, i);
            for (String verifyWord :
                    verificationDictionary) {
                if ((fileRW.fileReader(secondFile)
                           .toLowerCase()).contains(verifyWord)) {
                    counter++;
                }
            }
            if (counter > mostCorrect) {
                mostCorrect = counter;
                key = i;
            }
        }
        decrypt(firstFile, secondFile, key);
    }

    public void findMostUsableSymbol(String firstFile, String secondFile) {

        String text = fileRW.fileReader(firstFile);
        int mostUsableValue = 0;
        char mostUsableKey = ' ';
        HashMap<Character, Integer> checkedSymbols = new HashMap<>();
        char[] symbols = text.toLowerCase()
                             .toCharArray();
        validator.validateSymbol(symbols, RUSSIAN_ALPHABET);
        for (int i = 0; i < symbols.length - 1; i++) {
            int counter = 0;
            int j = i;
            boolean check = true;
            if (checkedSymbols.containsKey(symbols[i])) {
                check = false;
            }
            while (check) {
                if (symbols[i] == symbols[j + 1]) {
                    counter++;
                }
                if (j == symbols.length - 2) {
                    check = false;
                } else {
                    j++;
                }
            }
            if (! checkedSymbols.containsKey(symbols[i])) {
                checkedSymbols.put(symbols[i], counter + 1);
                if (mostUsableValue < counter + 1) {
                    mostUsableValue = counter + 1;
                    mostUsableKey = symbols[i];

                }
            }
        }
        decrypt(firstFile, secondFile, getKeyBetweenSymbols(mostUsableKey));
    }

    public int getKeyBetweenSymbols(char symbol) {
        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        int num = 1;
        for (char s :
                RUSSIAN_ALPHABET) {
            alphabetMap.put(s, num);
            num++;
        }
        int firstSymbol = 41; // пробел - ' '
        int secondSymbol = alphabetMap.get(symbol);
        int result;
        if (secondSymbol > firstSymbol) {
            result = secondSymbol - firstSymbol;
        } else {
            result = 46 + secondSymbol - firstSymbol;
        }

        return result;
    }
}

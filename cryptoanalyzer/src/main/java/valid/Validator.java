package valid;


import valid.exceptions.newFileException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class Validator {
    private final static List<String> unusableFilename = List.of(".bash_history", ".bash_profile", "etc", "proc");

    private static char result;


    public void validateReadingFile(String filePath){
        Path path = Path.of(filePath);
        for (int i = 0; i < path.getNameCount(); i++) {
            for (int j = 0; j < unusableFilename.size(); j++) {
                if ((path.getName(i)
                         .toString()).equalsIgnoreCase(unusableFilename.get(j))) {
                    throw new newFileException("Путь содержит недопустимое наименование: " + unusableFilename.get(j));
                }
            }
        }
        if (Files.notExists(path)) {
            throw new newFileException("Файла " + path.getFileName() + " не существует.");
        }
        if (Files.isDirectory(path)) {
            throw new newFileException("Данный файл - это директория: " + path);
        }
        if (! Files.isReadable(path)) {
            throw new newFileException("Отсутствует доступ к данному файлу: " + path);
        }


    }

    public void validateWritingFile(String filePath){
        Path path = Path.of(filePath);

        for (int i = 0; i < path.getNameCount(); i++) {
            for (int j = 0; j < unusableFilename.size(); j++) {
                if ((path.getName(i)
                         .toString()).equalsIgnoreCase(unusableFilename.get(j))) {
                    throw new newFileException("Путь содержит недопустимое наименование: " + unusableFilename.get(j));
                }
            }
        }

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new newFileException("Данный файл - это директория: " + path);
            }
            if (! Files.isWritable(path)) {
                throw new newFileException("Отсутствует доступ к данному файлу: " + path);
            }
        }
        /*else {
            throw new newFileException("Файла " + path.getFileName() + " не существует.");
        } */
    }

    public void validateKey(int code){
        if (code < 0) {
            throw new newFileException("Ключ не может быть меньше 0");
        }

    }

    public void validateSymbol(char[] text, char[] alphabet){
        if (!charContains(text, alphabet)) {
            throw new newFileException("Текст содержит недопустимый символ: " + result);
        }

    }
    public static boolean charContains(char[] text, char[] alphabet) {
        for (int i = 0; i < text.length; i++) {
            boolean containsChar = false;
            for (int j = 0; j < alphabet.length; j++) {
                if (text[i] == alphabet[j]) {
                    containsChar = true;
                }
            }
            if (!containsChar) {
                result = text[i];
                return false;
            }
        }
        return true;
    }

}

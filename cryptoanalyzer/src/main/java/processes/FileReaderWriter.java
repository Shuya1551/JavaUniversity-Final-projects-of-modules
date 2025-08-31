package processes;

import valid.exceptions.newFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class FileReaderWriter {

    public String fileReader(String file) {
        try {
            Path path = Path.of(file);
            return Files.readString(path);
        } catch (IOException | InvalidPathException e) {
            throw new newFileException(e.getMessage(), e);
        }

    }

    public Path fileWriter(String file, String symbols) {
        try {
            Path path = Path.of(file);
            if (Files.exists(path)) {
                Files.writeString(path, String.valueOf(symbols));
            } else {
                Files.createFile(path);
            }
            return Files.writeString(path, String.valueOf(symbols));
        } catch (IOException e) {
            throw new newFileException(e.getMessage(), e);
        }
    }
}

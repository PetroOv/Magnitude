package utils.data;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class CsvReader {
    public List<String[]> readAll(String fileName) {
        List<String[]> myEntries = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            myEntries = Objects.requireNonNull(reader).readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myEntries;
    }
}

package service;

import model.*;
import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class NoteService {
    Map<Integer, Note> notes = new HashMap<>();
    String filePath = "storage/notes.txt";

    public NoteService() {
        loadNotesFromFile();
    }

    public boolean addNote(Note note) {
        boolean result = true;
        if (!notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
            saveNotesToFile();
        } else {
            result = false;
        }
        return result;
    }

    public Map<Integer, Note> getAllNotes() {
        return notes;
    }

    public boolean deleteNote(int id) {
        boolean result = true;
        if (notes.containsKey(id)) {
            notes.remove(id);
            saveNotesToFile();
        } else {
            result = false;
        }
        return result;
    }

    public void loadNotesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String note[] = line.split(" \\| ");
                Note noteObj = new Note(Integer.parseInt(note[0]), note[1]);
                notes.put(noteObj.getId(), noteObj);
            }
        } catch (FileNotFoundException e) {
            // NORMAL on first run
            // File doesn't exist yet, so we start with empty notes
            // Do NOTHING here
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

    }

    public void saveNotesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Note note : notes.values()) {
                int id = note.getId();
                String content = note.getContent();
                System.out.println("Writing to: " + new File(filePath).getAbsolutePath());
                bw.write(id + " | " + content);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }
}

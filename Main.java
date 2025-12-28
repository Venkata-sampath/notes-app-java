import java.util.*;
import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NoteService noteService = new NoteService();

        loop: while (true) {
            System.out.println("===== Notes App =====");
            System.out.println("1. Add Note\n2. View All Notes\n3. Delete Note\n4. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Note ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Note Content: ");
                    String content = sc.nextLine();

                    Note newNote = new Note(id, content);

                    if (noteService.addNote(newNote)) {
                        System.out.println("Note Added Successfully.");
                    } else {
                        System.out.println("Duplicate ID!");
                    }

                    break;
                case 2:
                    Map<Integer, Note> allNotes = noteService.getAllNotes();

                    if (allNotes.isEmpty()) {
                        System.out.println("No Notes Available!");
                    } else {
                        for (Note note : allNotes.values()) {
                            System.out.println(note.getId() + " | " + note.getContent());
                        }
                    }

                    break;
                case 3:
                    System.out.print("Enter Note ID to Delete: ");
                    int did = sc.nextInt();
                    sc.nextLine();

                    if (noteService.deleteNote(did)) {
                        System.out.println("Note Deleted Successfully with ID: " + did);
                    } else {
                        System.out.println("Note with ID: " + did + " Not Found!");
                    }

                    break;
                case 4:
                    System.out.println("Exited System Successfully.");
                    sc.close();
                    break loop;

                default:
                    break;
            }
        }
    }
}

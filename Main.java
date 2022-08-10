
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(12, 100, 4, 102.8);
        GameProgress game2 = new GameProgress(25, 400, 7, 302.64);
        GameProgress game3 = new GameProgress(90, 700, 10, 848.59);
        saveGame("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game1.dat", game1);
        saveGame("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game2.dat", game2);
        saveGame("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game1.dat");
        arrayList.add("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game2.dat");
        arrayList.add("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game3.dat");
        zipFiles("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/zip.zip", arrayList);
        File game1Dat = new File("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game1.dat");
        File game2Dat = new File("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game2.dat");
        File game3Dat = new File("/home/aleksey/Рабочий стол/Java курс/Java core/Files/task 1/src/Games/savegames/game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fileOutput = new FileOutputStream(path);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fileInput = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zipOutput.putNextEntry(entry);
                    while (fileInput.available() > 0) {
                        zipOutput.write(fileInput.read());
                    }
                    zipOutput.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
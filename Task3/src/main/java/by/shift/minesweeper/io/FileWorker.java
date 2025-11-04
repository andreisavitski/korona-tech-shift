package by.shift.minesweeper.io;

import by.shift.minesweeper.exception.ApplicationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class FileWorker {

    private static final String FAILED_TO_SERIALIZE = "Не удалось сериализовать объект в файл: ";

    public void serializeObject(final Object object, final String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(object);
        } catch (IOException e) {
            throw new ApplicationException(FAILED_TO_SERIALIZE + path, e);
        }
    }

    public Object deserializeObject(final String path) {
        final Object o;
        try (final ObjectInputStream ois = new ObjectInputStream((new FileInputStream(path)))) {
            o = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Object();
        }
        return o;
    }
}

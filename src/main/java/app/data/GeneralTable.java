package app.data;

import app.domain.Entity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface GeneralTable<T extends Entity> {

    T get(@NotNull T val);
    T getById(@NotNull UUID uuid);
    T add(@NotNull T val);
    T change(@NotNull T val);
    void remove(@NotNull T val);
    List<T> getAll();
    List<T> searchEntries(String inText);
}

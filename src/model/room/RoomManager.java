package model.room;

import model.common.Direction;
import model.common.IdIterator;

public interface RoomManager {
    Room getCurrentRoom();

    void update(double elapsed);

    IdIterator getIdIterator();

    void changeRoom(Direction direction);
}

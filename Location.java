/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    //Метод сравнения объектов
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
//instanceof - Оператор instanceof нужен, чтобы проверить, был ли объект, на который ссылается переменная X, создан на основе какого-либо класса Y.
        if (obj instanceof Location)
        {
            // Приведение другого объекта к типу Location, а затем сравнение
            Location other = (Location) obj;

            if (xCoord == other.xCoord &&
                    yCoord == other.yCoord)
            {
                return true;
            }
        }

        return false;
    }
// Метод hashCode возвращает числовое значение фиксированной длины для любого объекта. В Java вовзращает 32-битное число типа int
    public int hashCode()
    {
        int result = 22; // некоторое значение

        // Использование другого значения для определения
        result = 14 * result + (xCoord * 33);
        result = 88 * result + (yCoord * 66);

        return result;
    }
}
import java.util.HashMap;


/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    /*Хэширование открытых Waypoint*/
    private HashMap<Location, Waypoint> openWaypoints;

    /*  Хэширование закрытых Waypoint */
    private HashMap<Location, Waypoint> closedWaypoints;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     */
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;

        openWaypoints = new HashMap<Location, Waypoint>();
        closedWaypoints = new HashMap<Location, Waypoint>();
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    //Функция проверяет все точки в хеше открытых точек и возвращает ссылку на точку с наименьшей общей стоимостью
    public Waypoint getMinOpenWaypoint()
    {
        Waypoint sol = null;

        float min = Float.POSITIVE_INFINITY;
        float totalCost = 0;

        for(Waypoint p : openWaypoints.values())
        {
            totalCost = p.getTotalCost();

            if(min > totalCost) //
            {
                min = totalCost;
                sol = p;
            }
        }

        return sol;
    }

    /**
     -Если в настоящее время нет точек для этого места в "открытых точках"
     хеша, то просто добавить новую точку.
     -Если точка уже в этом месте в "открытой точке" хеша, то потом
     добавить новый пункт, только если "старое значение" за новую точку
     меньше "старого значения" за текущую точку. (Проверка, что используется
     прежнее значение, а не общее значение.) Проще говоря, если
     новая точка представляет собой более короткий путь к этому месту,
     чем текущий маршрут, заменить текущую точку на новую.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint other = openWaypoints.get(newWP.getLocation());

        if(other == null || newWP.getPreviousCost() < other.getPreviousCost())
        {
            openWaypoints.put(newWP.getLocation(), newWP);
            return true;
        }

        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
 Этот метод перемещает точку из открытого листа в закрытый
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint point = openWaypoints.remove(loc);

        if(point != null)
        {
            closedWaypoints.put(loc, point);
        }
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closedWaypoints.containsKey(loc);
    }
}

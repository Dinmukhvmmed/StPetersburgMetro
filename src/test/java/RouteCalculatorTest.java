import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    //Инициализируем пути
    List<Station> route = new ArrayList<>();
    List<Station> route1 = new ArrayList<>();
    List<Station> route2 = new ArrayList<>();

    //Инициализируем пересадки
    List<Station> connection1 = new ArrayList<>();
    List<Station> connection2 = new ArrayList<>();

    //Инициализируем линии
    Line line1 = new Line(1, "First");
    Line line2 = new Line(2, "Second");
    Line line3 = new Line(3, "Third");

    //Инициализируем станции
    Station station1_1 = new Station("Апельсиновая", line1);
    Station station1_2 = new Station("Арбузная", line1);
    Station station2_1 = new Station("Виноградная", line2);
    Station station2_2 = new Station("Вишневая", line2);
    Station station3_1 = new Station("Гранатовая", line3);
    Station station3_2 = new Station("Грушевая", line3);

    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);


    @Override
    protected void setUp() throws Exception {
        //Добавляем станции на соответствующие пути
        route.add(station1_1);
        route.add(station1_2);

        route1.add(station1_1);
        route1.add(station1_2);
        route1.add(station2_1);
        route1.add(station2_2);

        route2.add(station1_1);
        route2.add(station1_2);
        route2.add(station2_1);
        route2.add(station2_2);
        route2.add(station3_1);
        route2.add(station3_2);

        //Добавляем станции на соответствующие линии
        line1.addStation(station1_1);
        line1.addStation(station1_2);

        line2.addStation(station2_1);
        line2.addStation(station2_2);

        line3.addStation(station3_1);
        line3.addStation(station3_2);

        //Добавляем станции для пересадок
        connection1.add(station1_2);
        connection1.add(station2_1);

        connection2.add(station2_2);
        connection2.add(station3_1);

        //Добавляем пересадки в индекс
        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
    }

    //Расчет времени без пересадок
    @Test
    public void testCalculateDuration0() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected0 = 2.5;
        assertEquals(expected0, actual);
    }

    //Расчет времени с одной пересадкой
    @Test
    public void testCalculateDuration1() {

        double actual = RouteCalculator.calculateDuration(route1);
        double expected1 = 8.5;
        assertEquals(expected1, actual);
    }

    //Расчет времени с двумя пересадками
    @Test
    public void testCalculateDuration2() {
        double actual = RouteCalculator.calculateDuration(route2);
        double expected2 = 14.5;
        assertEquals(expected2, actual);
    }

    //Расчет маршрута без пересадок
    @Test
    public void testGetRouteOnTheLine() {
        List<Station> shortestRoute = routeCalculator.getShortestRoute(station1_1, station1_2);
        assertEquals(route, shortestRoute);
    }

    //Расчет маршрута с одной пересадкой
    @Test
    public void testGetRouteWithOneConnection() {
        List<Station> shortestRoute1 = routeCalculator.getShortestRoute(station1_1, station2_2);
        assertEquals(route1, shortestRoute1);
    }

    //Расчет маршрута с двумя пересадками
    @Test
    public void testGetRouteWithTwoConnections() {
        List<Station> shortestRoute2 = routeCalculator.getShortestRoute(station1_1, station3_2);
        assertEquals(route2, shortestRoute2);
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
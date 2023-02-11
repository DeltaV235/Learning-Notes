package com.deltav;

import com.deltav.entity.Bike;
import com.deltav.entity.Path;
import com.deltav.entity.Station;
import com.deltav.entity.Truck;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.deltav.constant.ManagerConstant.*;

/**
 * @author DeltaV235
 * @version 1.0
 */
public class Manager {

    private final List<Station> stationList;
    private final List<Bike> usingBikeList;
    private final List<Truck> truckList;

    private Manager() {
        usingBikeList = new ArrayList<>(100);
        Path a2B1 = new Path("A", "B", 14);
        Path a2B2 = new Path("A", "B", 14);
        Path b2C1 = new Path("B", "C", 9);
        Path b2C2 = new Path("B", "C", 19);
        Path c2A1 = new Path("C", "A", 5);
        Path c2A2 = new Path("C", "A", 21);

        Map<String, List<Path>> a2AnyDistanceMap = new HashMap<>();
        a2AnyDistanceMap.put("B", Arrays.asList(a2B1, a2B2));
        a2AnyDistanceMap.put("C", Arrays.asList(c2A1, c2A2));

        Map<String, List<Path>> b2AnyDistanceMap = new HashMap<>();
        b2AnyDistanceMap.put("C", Arrays.asList(b2C1, b2C2));
        b2AnyDistanceMap.put("A", Arrays.asList(a2B1, a2B2));

        Map<String, List<Path>> c2AnyDistanceMap = new HashMap<>();
        c2AnyDistanceMap.put("A", Arrays.asList(c2A1, c2A2));
        c2AnyDistanceMap.put("B", Arrays.asList(b2C1, b2C2));

        Station stationA = new Station("A", 30, 1, a2AnyDistanceMap, usingBikeList);
        Station stationB = new Station("B", 30, 0, b2AnyDistanceMap, usingBikeList);
        Station stationC = new Station("C", 40, 0, c2AnyDistanceMap, usingBikeList);

        stationList = Arrays.asList(stationA, stationB, stationC);
        truckList = Arrays.asList(new Truck(TRUCK_SPEED, stationList), new Truck(TRUCK_SPEED, stationList));
    }

    public void start() {
        int round = 0;
        while (round <= SIMULATE_SECOND) {
            round++;
            // 创建小黄车
            Station sourceStation = selectRandomElementInList(stationList);
            List<Station> avaliDestStation = new ArrayList<>(stationList);
            avaliDestStation.remove(sourceStation);
            Station destinationStation = selectRandomElementInList(avaliDestStation);
            List<Path> paths = sourceStation.getStationPathMap().get(destinationStation.getName());

            Bike bike = new Bike(BIKE_SPEED, destinationStation, selectRandomElementInList(paths).getDistance());
            usingBikeList.add(bike);

            showInfo(round, usingBikeList, stationList);
            // 所有使用中的小黄车移动一次
            usingBikeList.forEach(Bike::move);
            try {
                TimeUnit.SECONDS.sleep(1 / SIMULATE_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T selectRandomElementInList(List<T> inputList) {
        int selectedIndex = new Random().nextInt(100000) % inputList.size();
        return inputList.get(selectedIndex);
    }

    private void showInfo(int currentTime, List<Bike> usingBikeList, List<Station> stationList) {
        StringBuilder outputMessage = new StringBuilder();
        outputMessage.append(currentTime).append("\t");
        int usingBikeSize = usingBikeList.size();
        outputMessage.append("路上车 ").append(usingBikeSize).append("\t");
        stationList.stream()
                .sorted(Comparator.comparing(Station::getName))
                .forEach(station -> outputMessage.append(station.getName()).append("车站 ").append(station.getRemainingBike()).append("\n"));
        System.out.println(outputMessage);
    }

}

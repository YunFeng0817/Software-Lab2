package P3;

import java.util.*;

class store {
    Map<Stop, Map<String, List<Integer>>> data = new HashMap<>();

    void setData(Stop stop, String route, int time) {
        Map<String, List<Integer>> busBuffer;
        List<Integer> timeBuffer;
        if ((busBuffer = data.get(stop)) != null && (timeBuffer = busBuffer.get(route)) != null) {
            timeBuffer.add(time);
        } else if (busBuffer == null) {
            Map<String, List<Integer>> temp = new HashMap<>();
            temp.put(route, new ArrayList<>(Arrays.asList(time)));
            data.put(stop, temp);
        } else busBuffer.computeIfAbsent(route, k -> new ArrayList<>(Collections.singletonList(time)));
    }

    Map<String, List<Integer>> getBuses(Stop stop) {
        return data.get(stop);
    }
}

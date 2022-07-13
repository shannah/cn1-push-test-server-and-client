package ca.weblite.pushtest.collections;

import ca.weblite.pushtest.valueobjects.Device;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Devices implements Iterable<Device> {
    private Set<Device> devices = new HashSet<>();
    @Override
    public Iterator<Device> iterator() {
        return devices.iterator();
    }

    public static Devices of(Device... devices) {
        Devices out = new Devices();
        out.devices.addAll(List.of(devices));
        return out;
    }

    public static Devices withIds(String... deviceIds) {
        return Devices.of(
                List.of(deviceIds)
                        .stream()
                        .map(deviceId -> new Device(deviceId))
                        .toArray(Device[]::new)
        );
    }
}

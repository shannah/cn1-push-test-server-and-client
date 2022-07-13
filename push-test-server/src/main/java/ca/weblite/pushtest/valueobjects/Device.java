package ca.weblite.pushtest.valueobjects;

import java.util.Objects;

public class Device {
    private final String id;
    public Device(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Device) {
            return Objects.equals(((Device) obj).id, id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

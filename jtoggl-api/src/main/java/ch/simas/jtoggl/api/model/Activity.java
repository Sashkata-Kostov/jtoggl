package ch.simas.jtoggl.api.model;

import java.util.Objects;

public class Activity {

    private String id;
    private String name;

    public Activity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Activity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return getId().equals(activity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}

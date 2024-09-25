package hashcode;

import java.util.Objects;

public class Issue {
    private final int id;
    private final String description;
    private final Priority priority;

    public Issue(int id, String description, Priority priority) {
        if (description == null) {
            throw new NullPointerException();
        }
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Issue other
               && id == other.id
               && description.equals(other.description)
               && priority == other.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority);
    }
}

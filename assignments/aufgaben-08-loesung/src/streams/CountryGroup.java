package streams;

import java.util.ArrayList;
import java.util.List;

public class CountryGroup {
    private final String name;
    private final List<Country> members = new ArrayList<>();

    public CountryGroup(String name, List<Country> members) {
        this.name = name;
        this.members.addAll(members);
    }

    public String getName() {
        return name;
    }

    public List<Country> getMembers() {
        return members;
    }
}

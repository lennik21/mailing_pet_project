package main;

import java.util.*;
public class CompositeSpamFilter implements SpamFilter {
    private final List<SpamFilter> filters = new ArrayList<>();

    public void addFilter(SpamFilter filter){
        filters.add(filter);
    }
    @Override
    public boolean isSpam(Message message){
        return filters.stream().anyMatch(filter -> filter.isSpam(message));
    }
}

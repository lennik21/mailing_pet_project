package main;

import java.util.*;

public class KeywordsSpamFilter implements SpamFilter {
    private final List<String> keywords;

    public KeywordsSpamFilter(String keywords){
        this.keywords = Arrays.asList(keywords.toLowerCase().split(" "));
    }
    @Override
    public boolean isSpam(Message message){
        List<String> caption = Arrays.asList(message.getCaption().toLowerCase().split("[^\\p{L}\\p{N}]+"));
        List<String> text = Arrays.asList(message.getText().toLowerCase().split("[^\\p{L}\\p{N}]+"));
        for (String key : keywords){
            if (caption.contains(key.trim()) || text.contains(key.trim())){
                return true;
            }
        }
        return false;
    }
}

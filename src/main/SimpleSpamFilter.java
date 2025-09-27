package main;

public class SimpleSpamFilter implements SpamFilter {
    @Override
    public boolean isSpam(Message message){
        String[] caption = message.getCaption().toLowerCase().split("[^\\p{L}\\p{N}]+");
        String[] text = message.getText().toLowerCase().split("[^\\p{L}\\p{N}]+");;
        for (String cap : caption){
            if (cap.equals("spam")){
                return true;
            }
        }
        for (String tex : text){
            if (tex.equals("spam")){
                return true;
            }
        }
        return false;
    }
}

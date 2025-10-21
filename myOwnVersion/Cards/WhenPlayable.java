package myOwnVersion.Cards;

public class WhenPlayable {
    private final String playingString;
    //TODO
    public WhenPlayable(String playingString) {
        this.playingString = playingString;
    }

    public boolean isPlayable(String context) {
        return context.contains(playingString);
    }
    

}

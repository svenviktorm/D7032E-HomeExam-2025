package myOwnVersion.Cards.CardLoader;

import java.util.List;

import myOwnVersion.Cards.Card;

public interface PostCardLoader {
    List<Card> process(List<Card> cards);
}

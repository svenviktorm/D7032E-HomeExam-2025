package myOwnVersion.Cards.CardBuilder;

import com.google.gson.JsonObject;
import myOwnVersion.Cards.Card;

public interface CardBuilder {

    /**
     * Builds a Card from a JSON object
     * @param jsonObject The JSON object containing card data
     * @return A new Card instance with components added based on the JSON data
     */
    Card buildCard(JsonObject jsonObject);
}

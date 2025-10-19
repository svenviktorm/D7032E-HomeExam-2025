package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.CardSymbolType;
import myOwnVersion.Cards.CardSymbol;
import java.util.ArrayList;
import java.util.List;

/**
 * A card component that holds a resource type and can be rotated to show different quantities of that resource.
 * differentiated from ResourceCardCardComponent for potential future extensions and the goldcache card.
 */
public class ResourceHoldingCardComponent extends RotatableCardComponent {
    public final CardSymbolType resourceType;

    public ResourceHoldingCardComponent(CardSymbolType resourceType) {
        super(generateResourceFaces(resourceType));
        this.resourceType = resourceType;
    }

    /**
     * Generates the faces for the resource holding card component.
     * the faces represent 0, 1, 2, and 3 units of the specified resource.
     * @param resourceType the type of resource
     * @return the list of faces with resource rewards
     */
    private static List<List<CardSymbol>> generateResourceFaces(CardSymbolType resourceType) {
        List<List<CardSymbol>> resources = new ArrayList<>();

        //first face
        List<CardSymbol> firstFace = new ArrayList<>();
        resources.add(firstFace);

        //second face
        List<CardSymbol> secondFace = new ArrayList<>();
        secondFace.add(new CardSymbol(resourceType, 1));
        resources.add(secondFace);

        //third face
        List<CardSymbol> thirdFace = new ArrayList<>();
        thirdFace.add(new CardSymbol(resourceType, 2));
        resources.add(thirdFace);

        //fourth face
        List<CardSymbol> fourthFace = new ArrayList<>();
        fourthFace.add(new CardSymbol(resourceType, 3));
        resources.add(fourthFace);

        return resources;
    }

}
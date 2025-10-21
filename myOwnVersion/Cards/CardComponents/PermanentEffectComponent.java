package myOwnVersion.Cards.CardComponents;

import myOwnVersion.Cards.PermamentEffect.PermanentEffect;

public class PermanentEffectComponent implements CardComponent {
    public final PermanentEffect permanentEffect;

    public PermanentEffectComponent(PermanentEffect permanentEffect) {
        this.permanentEffect = permanentEffect;
    }

}

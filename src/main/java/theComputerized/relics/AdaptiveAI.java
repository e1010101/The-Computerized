package theComputerized.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import theComputerized.TheComputerized;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

public class AdaptiveAI extends AbstractCustomRelic {
    public static final String ID = makeID("AdaptiveAI");

    public AdaptiveAI() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT,
              TheComputerized.Enums.COMPUTERIZED_COLOR);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractCard c = getRandomItem(getAllSpareParts());
        makeInHand(c);
    }
}

package theComputerized.relics;

import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theComputerized.TheComputerized;
import theComputerized.cards.tokens.PocketMissiles;
import theComputerized.util.Wiz;

import java.util.ArrayList;

import static theComputerized.Main.makeID;

public class AdaptiveAI extends AbstractCustomRelic {
    public static final String ID = makeID("AdaptiveAI");
    private static final ArrayList<AbstractCard> allJunk;

    static {
        allJunk = new ArrayList<>();
        allJunk.add(new PocketMissiles());
    }

    public AdaptiveAI() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT,
              TheComputerized.Enums.COMPUTERIZED_COLOR);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractCard c = Wiz.getRandomItem(allJunk);
        Wiz.makeInHand(c);
    }
}

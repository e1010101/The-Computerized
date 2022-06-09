package theComputerized.cards.tokens;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.cards.AbstractCustomCard;

import java.util.ArrayList;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

@AutoAdd.Ignore
public class IntelligenceProject extends AbstractCustomCard {

    public final static String ID = makeID("IntelligenceProject");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int SECOND_MAGIC = 1;

    public IntelligenceProject() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> ls =
            getCardsMatchingPredicateInHand(c -> c.type == CardType.ATTACK);
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard top = p.drawPile.getTopCard();
            if (top.type == CardType.SKILL) {
                AbstractCard c = getRandomItem(ls);
                c.cost = Math.max(c.cost - secondMagic, 0);
                c.costForTurn = c.cost;
                c.isCostModified = true;
            }
            drawCards(1);
        }
    }

    @Override
    public void upp() {

    }
}
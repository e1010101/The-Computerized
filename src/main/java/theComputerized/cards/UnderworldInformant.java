package theComputerized.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.actions.ModalChoiceAction;
import theComputerized.cards.abstracts.ModalChoiceCard;

import java.util.ArrayList;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

public class UnderworldInformant extends AbstractCustomCard {

    public final static String ID = makeID("UnderworldInformant");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 3;
    private static final int MAGIC = 20;
    private static final int SECOND_MAGIC = 20;
    private static final int UPGRADE_MAGIC = -10;
    private static final int UPGRADE_SECOND_MAGIC = 5;

    public UnderworldInformant() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardList = new ArrayList<>();
        cardList.add(new ModalChoiceCard("Accept Proposal",
                                         "Lose " + magicNumber + " " +
                                         "Gold. Shuffle an " +
                                         "*Intelligence *Project into your " +
                                         "draw pile.", () -> {
            p.loseGold(magicNumber);
            shuffleIn(new IntelligenceProject());
        }));
        cardList.add(new ModalChoiceCard("Reject Proposal",
                                         "Restore " + secondMagic + " " +
                                         "Health. Draw a card.", () -> {
            p.heal(secondMagic);
            drawCards(1);
        }));
        atb(new ModalChoiceAction(cardList));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND_MAGIC);
    }
}
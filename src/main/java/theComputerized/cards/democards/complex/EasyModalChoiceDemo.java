package theComputerized.cards.democards.complex;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theComputerized.actions.ModalChoiceAction;
import theComputerized.cards.AbstractCustomCard;
import theComputerized.cards.abstracts.ModalChoiceCard;

import java.util.ArrayList;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

public class EasyModalChoiceDemo extends AbstractCustomCard {
    public final static String ID = makeID("EasyModalChoiceDemo");
    // intellij stuff skill, self, uncommon, , , , , , 

    public EasyModalChoiceDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new ModalChoiceCard("Draw", "Draw " + magicNumber + " cards.", () -> att(new DrawCardAction(magicNumber))));
        easyCardList.add(new ModalChoiceCard("Strength", "Gain " + secondMagic + " Strength.", () -> applyToSelfTop(new StrengthPower(p, secondMagic))));
        atb(new ModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}
package theComputerized.cards;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.tempCards.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.Main.SpecialCardTags;
import theComputerized.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

public class Toolbox extends AbstractCustomCard {

    public final static String ID = makeID("Toolbox");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    @Override
    protected float getRotationTimeNeeded() {
        return 0.33F;
    }

    public Toolbox() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        cardToPreview = getAllSpareParts();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            makeInHand(getRandomItem(getAllSpareParts()));
        }
    }

    public void upp() {
        upgradeCardToPreview();
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
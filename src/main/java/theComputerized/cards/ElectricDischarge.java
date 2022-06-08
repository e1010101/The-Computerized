package theComputerized.cards;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.Main.SpecialCardTags;
import theComputerized.util.Wiz;

import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;

public class ElectricDischarge extends AbstractCustomCard {

    public final static String ID = makeID("ElectricDischarge");
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 2;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#c46f0e]Function[]",
                        "Reduce the cost of a random [#c46f0e]Function[] in " +
                        "your hand by 1.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public ElectricDischarge() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        tags.add(SpecialCardTags.FUNCTION);
        customTooltips = tooltips;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);

        // FUNCTION EFFECT
        AbstractCard tmp = Wiz.getRandomItem(
            Wiz.getCardsMatchingPredicateInHand(
                c -> c.hasTag(SpecialCardTags.FUNCTION)));
        tmp.costForTurn = Math.max(0, tmp.costForTurn - 1);
        tmp.flash(Color.GOLD.cpy());
    }

    public void upp() {
        this.selfRetain = true;
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
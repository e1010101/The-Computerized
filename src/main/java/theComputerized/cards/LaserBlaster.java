package theComputerized.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theComputerized.Main.SpecialCardTags;

import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.getRandomMonster;
import static theComputerized.util.Wiz.vfx;

public class LaserBlaster extends AbstractCustomCard {

    public final static String ID = makeID("LaserBlaster");
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 3;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#005c0f]Military Might[]",
                        "Look at the top card of your draw pile. If it is an " +
                        "attack, deal this card's damage to a random enemy.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public LaserBlaster() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(SpecialCardTags.MILITARY);
        customTooltips = tooltips;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);

        // MILITARY EFFECT
        AbstractCard tmp = p.drawPile.getTopCard().makeStatEquivalentCopy();
        vfx(new ShowCardBrieflyEffect(tmp));
        if (tmp.type == CardType.ATTACK) {
            dmg(getRandomMonster(), AbstractGameAction.AttackEffect.FIRE);
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
package theComputerized.cards.tokens;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import theComputerized.Main.SpecialCardTags;
import theComputerized.cards.AbstractCustomCard;
import theComputerized.powers.PocketMissilesPower;

import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.*;

@AutoAdd.Ignore
public class PocketMissiles extends AbstractCustomCard {

    public final static String ID = makeID("PocketMissiles");
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 0;
    private static final int MAGIC = 1;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#176e10]Spare Part[]",
                        "At the end of your turn, exhaust this and gain 5 " +
                        "Gold.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public PocketMissiles() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        this.purgeOnUse = true;
        tags.add(SpecialCardTags.SPARE_PART);
        customTooltips = tooltips;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PocketMissilesPower(p, magicNumber));
    }

    @Override
    public void upp() {

    }
}
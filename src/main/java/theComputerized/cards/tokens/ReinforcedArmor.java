package theComputerized.cards.tokens;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.Main.SpecialCardTags;
import theComputerized.cards.AbstractCustomCard;
import theComputerized.powers.EnergyShieldPower;

import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.applyToSelf;

@AutoAdd.Ignore
public class ReinforcedArmor extends AbstractCustomCard {

    public final static String ID = makeID("ReinforcedArmor");
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 0;
    private static final int MAGIC = 10;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#176e10]Spare[] [#176e10]Part[]",
                        "At the end of your turn, exhaust this and gain 5 " +
                        "Gold.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public ReinforcedArmor() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        this.purgeOnUse = true;
        tags.add(SpecialCardTags.SPARE_PART);
        customTooltips = tooltips;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnergyShieldPower(p, magicNumber));
    }

    @Override
    public void upp() {

    }
}
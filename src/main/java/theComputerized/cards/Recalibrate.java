package theComputerized.cards;

import basemod.AutoAdd;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.cards.abstracts.AbstractSwappableCard;

import java.util.Collections;
import java.util.List;

import static com.brashmonkey.spriter.Spriter.draw;
import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.drawCards;

@AutoAdd.Ignore
public class Recalibrate extends AbstractSwappableCard {
    public final static String ID = makeID("DeployPlating");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#100573]Tactical[]",
                        "Right Click to swap the effect of this card.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public Recalibrate(AbstractSwappableCard linkedCard) {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = MAGIC;
        customTooltips = tooltips;
        if (linkedCard == null) {
            setLinkedCard(new DeployPlating(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        drawCards(magicNumber);
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}
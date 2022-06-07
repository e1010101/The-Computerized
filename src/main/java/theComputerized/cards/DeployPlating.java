package theComputerized.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.cards.abstracts.AbstractCustomCard;
import theComputerized.cards.abstracts.AbstractSwappableCard;

import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;

public class DeployPlating extends AbstractSwappableCard {
    public final static String ID = makeID("DeployPlating");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#100573]Tactical[]",
                        "Right Click to swap the effect of this card.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public DeployPlating(AbstractSwappableCard linkedCard) {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        tags.add(CardTags.STARTER_DEFEND);
        customTooltips = tooltips;
        if (linkedCard == null) {
            setLinkedCard(new Recalibrate(this));
        } else {
            setLinkedCard(linkedCard);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }



    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}
package theComputerized.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.Main.SpecialCardTags;
import theComputerized.actions.ModalChoiceAction;
import theComputerized.cards.abstracts.ModalChoiceCard;
import theComputerized.cards.tokens.IntelligenceProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.atb;
import static theComputerized.util.Wiz.att;

public class UnderworldInformant extends AbstractCustomCard {

    public final static String ID = makeID("UnderworldInformant");
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = 3;
    private static final int MAGIC = 20;
    private static final int SECOND_MAGIC = 15;
    private static final int UPGRADE_MAGIC = -10;
    private static final int UPGRADE_SECOND_MAGIC = 5;
    private static final TooltipInfo toolTipInfo =
        new TooltipInfo("[#112485]Government [#112485]Grant",
                        "Choose 1: *Accept *Proposal OR *Reject *Proposal. NL" +
                        " NL *Accept *Proposal: May require sacrifices of " +
                        "Gold and/or Health in exchange for powerful tools. NL NL " +
                        "*Reject *Proposal: No downsides, but a far less " +
                        "powerful effect.");
    private static final List<TooltipInfo> tooltips =
        Collections.singletonList(toolTipInfo);

    public UnderworldInformant() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        customTooltips = tooltips;
        tags.add(SpecialCardTags.GOVERNMENT_GRANT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardList = new ArrayList<>();
        cardList.add(new ModalChoiceCard("Accept Proposal",
                                         "Lose " + magicNumber + " " +
                                         "Gold. Shuffle an " +
                                         "*Intelligence *Project into your " +
                                         "draw pile.", () -> {
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!this.isDone) {
                        p.loseGold(magicNumber);
                    }
                    this.isDone = true;
                }
            });
            att(new MakeTempCardInDrawPileAction(new IntelligenceProject(), 1,
                                                 true, true));
        }));
        cardList.add(new ModalChoiceCard("Reject Proposal",
                                         "Restore " + secondMagic + " " +
                                         "Health. Draw a card.", () -> {
            att(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!this.isDone) {
                        p.heal(secondMagic);
                    }
                    this.isDone = true;
                }
            });
            att(new DrawCardAction(1));
        }));
        atb(new ModalChoiceAction(cardList));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND_MAGIC);
    }
}
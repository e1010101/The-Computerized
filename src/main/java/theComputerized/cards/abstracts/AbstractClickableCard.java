package theComputerized.cards.abstracts;

import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theComputerized.cards.AbstractCustomCard;

import static theComputerized.util.Wiz.adp;

public abstract class AbstractClickableCard extends AbstractCustomCard {

    public AbstractClickableCard(final String id, final int cost,
                                 final AbstractCard.CardType type,
                                 final AbstractCard.CardRarity rarity,
                                 final AbstractCard.CardTarget target) {

        super(id, cost, type, rarity, target);

    }

    @Override
    public void update() {
        super.update();
        if (adp() != null) {
            clickUpdate();
        }
    }

    public void clickUpdate() {
        if (!AbstractDungeon.isScreenUp &&
            HitboxRightClick.rightClicked.get(this.hb) &&
            !AbstractDungeon.actionManager.turnHasEnded) {
            onRightClick();
        }
    }

    public abstract void onRightClick();
}
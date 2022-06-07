package theComputerized.cards.abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theComputerized.actions.SwapCardsAction;

import static theComputerized.util.Wiz.att;

public abstract class AbstractSwappableCard extends AbstractClickableCard {
    private AbstractGameAction action;
    private static boolean looping;

    public AbstractSwappableCard(String id, int cost, CardType type,
                                 CardRarity rarity, CardTarget target) {
        super(id, cost, type, rarity, target);
    }

    protected void setLinkedCard(AbstractSwappableCard linkedCard) {
        if (linkedCard != null) {
            this.cardsToPreview = linkedCard;
            this.cardsToPreview.cardsToPreview = this;
        }
    }

    @Override
    public void upgrade() {
        if (cardsToPreview != null && !looping) {
            looping = true;
            cardsToPreview.upgrade();
            looping = false;
        }
    }

    @Override
    public void onRightClick() {
        if (canSwap() && action == null && cardsToPreview != null) {
            CardCrawlGame.sound.play("CARD_SELECT", 0.1F);
            action = new SwapCardsAction(this, cardsToPreview);
            att(action);
        }
    }

    @Override
    public void update() {
        super.update();
        if (action != null && action.isDone) {
            action = null;
        }
    }

    public boolean canSwap() {
        return true;
    }

    public void onSwapOut() {
    }

    public void onSwapIn() {
    }
}

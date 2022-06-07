package theComputerized.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theComputerized.util.Wiz.adp;

public class UpdateAfterTransformAction extends AbstractGameAction {
    private final AbstractCard cardToUpdate;

    public UpdateAfterTransformAction(AbstractCard cardToUpdate) {
        this.cardToUpdate = cardToUpdate;
    }

    @Override
    public void update() {
        adp().hand.applyPowers();
        adp().hand.glowCheck();
        cardToUpdate.superFlash();
        cardToUpdate.initializeDescription();
        this.isDone = true;
    }
}
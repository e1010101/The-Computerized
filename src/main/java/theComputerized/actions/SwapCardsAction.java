package theComputerized.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theComputerized.cards.abstracts.AbstractSwappableCard;

import static theComputerized.util.Wiz.adp;
import static theComputerized.util.Wiz.att;

public class SwapCardsAction extends AbstractGameAction {
    private final AbstractCard toReplace;
    private final AbstractCard newCard;

    public SwapCardsAction(AbstractCard toReplace, AbstractCard newCard) {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
        this.toReplace = toReplace;
        this.newCard = newCard;
    }

    @Override
    public void update() {
        AbstractPlayer p = adp();
        int index = 0;
        boolean found = false;
        for (AbstractCard card : p.hand.group) {
            if (card == toReplace) {
                found = true;
                break;
            }
            index++;
        }
        if (found && toReplace != null) {
            if (toReplace instanceof AbstractSwappableCard &&
                newCard instanceof AbstractSwappableCard) {
                ((AbstractSwappableCard) toReplace).onSwapOut();
                ((AbstractSwappableCard) newCard).onSwapIn();
                //CardModifierManager.removeAllModifiers(toReplace, true);
                //for (AbstractCardModifier mod : CardModifierManager.modifiers(toReplace)) {
                //    CardModifierManager.addModifier(newCard, mod.makeCopy());
                //    BoosterFieldPatch.equipBooster(newCard);
                //}
            }
            newCard.cardsToPreview = toReplace.makeStatEquivalentCopy();
            newCard.applyPowers();
            newCard.cardsToPreview.applyPowers();
            if (p.hoveredCard == toReplace) {
                p.releaseCard();
            }
            AbstractDungeon.actionManager.cardQueue.removeIf(
                q -> q.card == toReplace);
            att(new UpdateAfterTransformAction(newCard));
            att(new TransformCardInHandAction(index, newCard));
            //p.hand.group.remove(index);
            //p.hand.group.add(index, newCard);
        }
        this.isDone = true;
    }
}
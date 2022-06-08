package theComputerized.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.atb;

public class PocketMissilesPower extends AbstractCustomPower
    implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("PocketMissiles");
    private static final PowerStrings powerStrings =
        CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final PowerType TYPE = PowerType.BUFF;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PocketMissilesPower(final AbstractCreature owner,
                               final int amount) {
        super(NAME, TYPE, false, owner, amount);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description =
                DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + DESCRIPTIONS[2];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + amount +
                          DESCRIPTIONS[4];
        }
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for (int i = 0; i < amount; i++) {
            atb(new DamageRandomEnemyAction(
                new DamageInfo(owner, 1, DamageType.NORMAL),
                AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new PocketMissilesPower(owner, amount);
    }
}

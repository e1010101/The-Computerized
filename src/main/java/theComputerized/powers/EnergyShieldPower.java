package theComputerized.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theComputerized.Main.makeID;
import static theComputerized.util.Wiz.att;

public class EnergyShieldPower extends AbstractCustomPower
    implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("EnergyShield");
    private static final PowerStrings powerStrings =
        CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final PowerType TYPE = PowerType.BUFF;

    public EnergyShieldPower(final AbstractCreature owner, final int amount) {
        super(NAME, TYPE, false, owner, amount);
        updateDescription();
    }

    @Override
    public int onLoseHp(int damageAmount) {
        this.flash();
        double blocked_damage = Math.min(damageAmount * 0.75, amount);
        double taken_damage = damageAmount * 0.25;
        if (blocked_damage < amount) {
            amount -= blocked_damage;
        } else {
            taken_damage = damageAmount - blocked_damage;
            att(new RemoveSpecificPowerAction(owner, owner, this));
        }
        return (int) Math.ceil(taken_damage);
    }

    @Override
    public AbstractPower makeCopy() {
        return new EnergyShieldPower(owner, amount);
    }
}

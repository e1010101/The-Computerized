package theComputerized.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

public abstract class LambdaPower extends AbstractCustomPower {
    public LambdaPower(String name, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(name, powerType, isTurnBased, owner, amount);
    }

    public abstract void updateDescription();
}

package theComputerized.relics;

import theComputerized.TheComputerized;

import static theComputerized.Main.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT,
              TheComputerized.Enums.COMPUTERIZED_COLOR);
    }
}

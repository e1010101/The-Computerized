package theComputerized.cards.abstracts;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theComputerized.cards.AbstractCustomCard;

import static theComputerized.Main.makeID;

@AutoAdd.Ignore
public class ModalChoiceCard extends AbstractCustomCard {

    private final Runnable onUseOrChosen;
    private final String passedName;
    private final String passedDesc;

    public ModalChoiceCard(String name, String description, Runnable onUseOrChosen) {
        super(makeID(name.replaceAll(" ", "")), -2, CardType.SKILL,
              CardRarity.SPECIAL,
              CardTarget.NONE, CardColor.COLORLESS);
        this.name = this.originalName = passedName = name;
        this.rawDescription = passedDesc = description;
        this.onUseOrChosen = onUseOrChosen;
        initializeTitle();
        initializeDescription();
    }

    @Override
    public void onChoseThisOption() {
        onUseOrChosen.run();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUseOrChosen.run();
    }

    @Override
    public void upp() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new ModalChoiceCard(passedName, passedDesc, onUseOrChosen);
    }
}

package theComputerized;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theComputerized.cards.DeployPlating;
import theComputerized.cards.LaserBlaster;
import theComputerized.relics.AdaptiveAI;

import java.util.ArrayList;

import static theComputerized.Main.*;
import static theComputerized.TheComputerized.Enums.COMPUTERIZED_COLOR;

public class TheComputerized extends CustomPlayer {
    private static final String[] orbTextures =
        {modID + "Resources/images/char/mainChar/orb/layer1.png",
         modID + "Resources/images/char/mainChar/orb/layer2.png",
         modID + "Resources/images/char/mainChar/orb/layer3.png",
         modID + "Resources/images/char/mainChar/orb/layer4.png",
         modID + "Resources/images/char/mainChar/orb/layer5.png",
         modID + "Resources/images/char/mainChar/orb/layer6.png",
         modID + "Resources/images/char/mainChar/orb/layer1d.png",
         modID + "Resources/images/char/mainChar/orb/layer2d.png",
         modID + "Resources/images/char/mainChar/orb/layer3d.png",
         modID + "Resources/images/char/mainChar/orb/layer4d.png",
         modID + "Resources/images/char/mainChar/orb/layer5d.png",};
    static final String ID = makeID("TheComputerized");
    static final CharacterStrings characterStrings =
        CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;

    public TheComputerized(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID +
                                                               "Resources/images/char/mainChar/orb/vfx.png",
                                                  null), new SpriterAnimation(
            modID + "Resources/images/char/mainChar/static.scml"));
        initializeClass(null, SHOULDER1, SHOULDER2, CORPSE, getLoadout(), 20.0F,
                        -10.0F, 166.0F, 327.0F, new EnergyManager(3));

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], 80, 80, 0, 99, 5, this,
                                  getStartingRelics(), getStartingDeck(),
                                  false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(LaserBlaster.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(DeployPlating.ID);
        }
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(AdaptiveAI.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW,
                                        ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 8;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return COMPUTERIZED_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new LaserBlaster();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheComputerized(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
            AbstractGameAction.AttackEffect.FIRE,
            AbstractGameAction.AttackEffect.BLUNT_HEAVY,
            AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {

        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_COMPUTERIZED;

        @SpireEnum(name = "COMPUTERIZED_COLOR")
        public static AbstractCard.CardColor COMPUTERIZED_COLOR;

        @SpireEnum(name = "COMPUTERIZED_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType COMPUTERIZED_LIBRARY_COLOR;
    }
}

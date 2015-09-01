package tehnut.goodybags.enums;

import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.util.Locale;

public enum BagType {
    PRIZE(new Color(0, 251, 255), EnumChatFormatting.AQUA),
    LOOT(new Color(255, 223, 0), EnumChatFormatting.YELLOW),
    SPAWN(new Color(0, 255, 22), EnumChatFormatting.GREEN),
    MOB(new Color(255, 142, 82), EnumChatFormatting.GOLD);

    private final Color bagColor;
    private final EnumChatFormatting textColor;

    BagType(Color bagColor, EnumChatFormatting textColor) {
        this.bagColor = bagColor;
        this.textColor = textColor;
    }

    public Color getBagColor() {
        return bagColor;
    }

    public EnumChatFormatting getTextColor() {
        return textColor;
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ENGLISH);
    }
}

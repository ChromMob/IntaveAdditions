package me.chrommob.intaveadditions.module.falseFlags;

public class FalseFlagConfig {
    private boolean notVanillaEnchantments;

    public void setNotVanillaEnchantments(final boolean notVanillaEnchantments) {
        this.notVanillaEnchantments = notVanillaEnchantments;
    }

    public boolean notVanillaEnchantments() {
        return notVanillaEnchantments;
    }
}

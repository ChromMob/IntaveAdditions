package me.chrommob.intaveadditions.module.falseFlags;

import de.jpx3.intave.access.check.event.IntaveViolationEvent;
import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import me.chrommob.intaveadditions.event.Subscriber;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class NonVanillaSubscriber implements Subscriber {

    private final IntaveAdditionsPlugin plugin;

    public NonVanillaSubscriber(IntaveAdditionsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPhysics(IntaveViolationEvent event) {
        Player player = event.player();
        Set<ItemStack> items = new HashSet<>();
        items.add(player.getInventory().getItemInHand());
        // Add off-hand item which should be item with index 45
        items.add(player.getInventory().getItem(45));
        for (ItemStack item : items) {
            if (isOverAllowedLevel(item)) {
                event.suggestReaction(IntaveViolationEvent.Reaction.IGNORE);
                plugin.getLogger().info("Player " + player.getName() + " has a non-vanilla enchantment on their item. Ignoring violation.");
                return;
            }
        }
    }

    private boolean isOverAllowedLevel(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getEnchantments().isEmpty()) {
            return false;
        }
        if (itemStack.getType().equals(Material.AIR)) {
            return false;
        }
        if (!itemStack.getType().toString().contains("trident")) {
            return false;
        }
        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            int level = itemStack.getEnchantments().get(enchantment);
            if (level > enchantment.getMaxLevel()) {
                return true;
            }
        }
        return false;
    }
}

package dev.j3fftw.litexpansion.nms.v1_20_R3;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CustomEnchantment extends Enchantment {

  private final Set<String> ids = new HashSet<>();

  public CustomEnchantment(String[] ids) {
    super(Rarity.COMMON, EnchantmentCategory.WEAPON, EquipmentSlot.values());
    this.ids.addAll(Arrays.asList(ids));
  }

  @Override
  public int getMinCost(int level) {
    return 0;
  }

  @Override
  public boolean isTradeable() {
    return false;
  }

  @Override
  public boolean isDiscoverable() {
    return false;
  }

  @Override
  public int getMaxCost(int level) {
    return 0;
  }

  @Override
  protected boolean checkCompatibility(@NotNull Enchantment other) {
    return false;
  }

  @Override
  public boolean canEnchant(@NotNull ItemStack stack) {
    org.bukkit.inventory.ItemStack item = CraftItemStack.asBukkitCopy(stack);
    if (!item.hasItemMeta()) {
      return false;
    }

    final ItemMeta itemMeta = item.getItemMeta();
    final Optional<String> id = Slimefun.getItemDataService().getItemData(itemMeta);

    return id.filter(ids::contains).isPresent();
  }
}

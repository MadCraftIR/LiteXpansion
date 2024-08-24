package dev.j3fftw.litexpansion.nms.v1_20_R3;

import dev.j3fftw.litexpansion.LiteXpansion;
import dev.j3fftw.litexpansion.utils.Reflex;
import java.util.IdentityHashMap;
import lombok.Getter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R3.enchantments.CraftEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnchantNMS {

  @Getter private static @Nullable Enchantment glowEnchantment = null;
  @Getter final @NotNull NamespacedKey glowKey;

  public EnchantNMS(@NotNull LiteXpansion plugin) {
    glowKey = new NamespacedKey(plugin, "glow_enchant");
  }

  public void registerEnchantments() {
    unfreezeRegistry();
    CustomEnchantment enchantments =
        new CustomEnchantment(
            new String[] {
              "ADVANCED_CIRCUIT",
              "NANO_BLADE",
              "GLASS_CUTTER",
              "LAPOTRON_CRYSTAL",
              "ADVANCEDLX_SOLAR_HELMET",
              "HYBRID_SOLAR_HELMET",
              "ULTIMATE_SOLAR_HELMET",
              "DIAMOND_DRILL"
            });
    Registry.register(BuiltInRegistries.ENCHANTMENT, glowKey.getKey(), enchantments);

    freezeRegistry();
    glowEnchantment = CraftEnchantment.minecraftToBukkit(enchantments);
  }

  public void unfreezeRegistry() {
    Reflex.setFieldValue(BuiltInRegistries.ENCHANTMENT, "l", false); // MappedRegistry#frozen
    Reflex.setFieldValue(
        BuiltInRegistries.ENCHANTMENT,
        "m",
        new IdentityHashMap<>()); // MappedRegistry#unregisteredIntrusiveHolders
  }

  public void freezeRegistry() {
    BuiltInRegistries.ENCHANTMENT.freeze();
  }
}

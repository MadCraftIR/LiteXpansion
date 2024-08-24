package dev.j3fftw.litexpansion.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public final class Utils {

  private Utils() {}

  public static void send(@NotNull Player player, @NotNull String message) {
    player.sendMessage(ChatColor.GRAY + "[LiteXpansion] " + ChatColors.color(message));
  }

  public static @NotNull Optional<Boolean> getOptionalBoolean(
      @Nonnull ItemMeta meta, @Nonnull NamespacedKey key) {
    final Byte b = meta.getPersistentDataContainer().get(key, PersistentDataType.BYTE);

    if (b == null) {
      return Optional.empty();
    } else {
      return Optional.of(b == 1);
    }
  }
}

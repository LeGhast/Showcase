package de.leghast.showcase.ui;

import de.leghast.showcase.constant.Colors;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.InterfaceItem;
import de.leghast.showcase.ui.Page;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.List;

public class FrequentItems {

    public static void addGlint(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
    }

    public static void addPageSwitchItems(ItemStack[] content, Page page){
        content[0] = new InterfaceItem(
                Material.MAGENTA_GLAZED_TERRACOTTA,
                Component.text("Position", Colors.ACCENT),
                () -> page == Page.POSITION,
                List.of(
                        Component.text("Adjust the position", NamedTextColor.GRAY),
                        Component.text("of the item display", NamedTextColor.GRAY)
                )
        );

        content[9] = new InterfaceItem(
                Material.PUFFERFISH,
                Component.text("Size", Colors.ACCENT),
                () -> page == Page.SIZE,
                List.of(
                        Component.text("Adjust the size", NamedTextColor.GRAY),
                        Component.text("of the item display", NamedTextColor.GRAY)
                )
        );

        content[18] = new InterfaceItem(
                Material.ITEM_FRAME,
                Component.text("Rotation", Colors.ACCENT),
                () -> page == Page.ROTATION,
                List.of(
                        Component.text("Adjust the rotation", NamedTextColor.GRAY),
                        Component.text("of the item display", NamedTextColor.GRAY)
                )
        );

        content[27] = new InterfaceItem(
                Material.BAMBOO_HANGING_SIGN,
                Component.text("Transform", Colors.ACCENT),
                () -> page == Page.TRANSFORM,
                List.of(
                        Component.text("Set the transform", NamedTextColor.GRAY),
                        Component.text("of the item display", NamedTextColor.GRAY)
                )
        );

    }

    public static void addGeneralItems(ItemStack[] content, boolean enabled){

        content[8] = new InterfaceItem(
                ConfigManager.TOOL,
                Component.text("Showcase tool", NamedTextColor.GOLD),
                List.of(
                        Component.text("The selected adjusting", NamedTextColor.GRAY),
                        Component.text("settings are bound to this item", NamedTextColor.GRAY)
                )
        );

        content[26] = new InterfaceItem(
                Material.STRUCTURE_VOID,
                Component.text("Deselect Item Display", Colors.ERROR)
        );

        content[35] = new InterfaceItem(
                (enabled) ? Material.RED_STAINED_GLASS_PANE : Material.LIME_STAINED_GLASS_PANE,
                (enabled) ? Component.text("Click to disable item spawning", Colors.ERROR) : Component.text("Click to enable item spawning", Colors.SUCCESS)
        );

        content[44] = new InterfaceItem(
                Material.BARRIER,
                Component.text("Delete Item Display", Colors.ERROR)
        );

        ItemStack filler = new InterfaceItem(
                Material.GRAY_STAINED_GLASS_PANE,
                Component.empty()
        );

        for(int i = 0; i < content.length; i++){
            if(content[i] == null){
                content[i] = filler;
            }
        }
    }

    public static void addAxisItems(ItemStack[] content, Axis axis) {
        content[30] = new InterfaceItem(
                Material.RED_STAINED_GLASS_PANE,
                Component.text("X-Axis", NamedTextColor.RED),
                () -> axis == Axis.X
        );

        content[31] = new InterfaceItem(
                Material.LIME_STAINED_GLASS_PANE,
                Component.text("Y-Axis", NamedTextColor.GREEN),
                () -> axis == Axis.Y
        );

        content[32] = new InterfaceItem(
                Material.BLUE_STAINED_GLASS_PANE,
                Component.text("Z-Axis", NamedTextColor.BLUE),
                () -> axis == Axis.Z
        );
    }

    public static List<ItemStack> getValueItems(double factor){
        return List.of(
                new InterfaceItem(
                        Material.COAL,
                        Component.text("0.25 blocks", NamedTextColor.GRAY),
                        () -> factor == 0.25
                ),
                new InterfaceItem(
                        Material.IRON_INGOT,
                        Component.text("0.5 blocks", NamedTextColor.GRAY),
                        () -> factor == 0.5
                ),
                new InterfaceItem(
                        Material.DIAMOND,
                        Component.text("1 block", NamedTextColor.GRAY),
                        () -> factor == 1
                ),
                new InterfaceItem(
                        Material.GRASS_BLOCK,
                        Component.text("10 blocks", NamedTextColor.GRAY),
                        () -> factor == 10
                ),
                new InterfaceItem(
                        Material.PAPER,
                        Component.text("Custom factor ", NamedTextColor.GRAY)
                                .append(Component.text("(" + factor + " block" + (factor == 1 ? "" : "s") + ")", Colors.ACCENT)),
                        () -> factor != 0.25 && factor != 0.5 && factor != 1
                )
        );
    }

}
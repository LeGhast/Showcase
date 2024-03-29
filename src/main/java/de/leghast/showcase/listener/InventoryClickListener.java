package de.leghast.showcase.listener;

import de.leghast.showcase.Showcase;
import de.leghast.showcase.instance.DisplayWrapper;
import de.leghast.showcase.instance.settings.AdjusterSettings;
import de.leghast.showcase.instance.settings.DimensionSettings;
import de.leghast.showcase.instance.settings.FactorSettings;
import de.leghast.showcase.manager.ConfigManager;
import de.leghast.showcase.ui.Page;
import de.leghast.showcase.ui.UserInterface;
import de.leghast.showcase.util.Util;
import org.bukkit.Axis;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private Showcase main;

    public InventoryClickListener(Showcase main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String title = e.getView().getTitle();
        if (title.contains(Page.POSITION.getTitle())) {
            handlePositionInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.SIZE.getTitle())) {
            handleSizeInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.ROTATION.getTitle())) {
            handleRotationInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        } else if (title.contains(Page.TRANSFORM.getTitle())) {
            handleTransformInteractions(e.getRawSlot(), player);
            e.setCancelled(true);
        }
    }

    private void handlePositionInteractions(int slot, Player player) {
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings positionSettings = settings.getPositionSettings();

        switch (slot) {
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getToolMaterial()));
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.TRANSFORM);
            case 11 -> positionSettings.setFactor(0.25);
            case 12 -> positionSettings.setFactor(0.5);
            case 13 -> positionSettings.setFactor(1);
            case 14 -> positionSettings.setFactor(10);
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> positionSettings.setAxis(Axis.X);
            case 31 -> positionSettings.setAxis(Axis.Y);
            case 32 -> positionSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getClipboard(player.getUniqueId()).remove();
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleSizeInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        FactorSettings sizeSettings = settings.getSizeSettings();

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 18 -> settings.setPage(Page.ROTATION);
            case 27 -> settings.setPage(Page.TRANSFORM);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getToolMaterial()));
            case 20 -> sizeSettings.setFactor(0.25);
            case 21 -> sizeSettings.setFactor(0.5);
            case 22 -> sizeSettings.setFactor(1);
            case 23 -> sizeSettings.setFactor(5);
            case 24 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 26 -> {
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getClipboard(player.getUniqueId()).remove();
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleRotationInteractions(int slot, Player player) {
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DimensionSettings rotationSettings = settings.getRotationSettings();

        switch (slot) {
            case 0 -> settings.setPage(Page.POSITION);
            case 9 -> settings.setPage(Page.SIZE);
            case 27 -> settings.setPage(Page.TRANSFORM);
            case 8 -> player.getInventory().addItem(new ItemStack(ConfigManager.getToolMaterial()));
            case 11 -> rotationSettings.setFactor(22.5);
            case 12 -> rotationSettings.setFactor(45);
            case 13 -> rotationSettings.setFactor(90);
            case 14 -> rotationSettings.setFactor(180);
            case 15 -> Util.setCustomNumberInput(main, player, settings.getPage());
            case 30 -> rotationSettings.setAxis(Axis.X);
            case 31 -> rotationSettings.setAxis(Axis.Y);
            case 32 -> rotationSettings.setAxis(Axis.Z);
            case 26 -> {
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getClipboard(player.getUniqueId()).remove();
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }

        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }

    private void handleTransformInteractions(int slot, Player player){
        AdjusterSettings settings = main.getSettingsManager().getAdjusterSettings(player.getUniqueId());
        DisplayWrapper wrapper = main.getClipboardManager().getClipboard(player.getUniqueId());

        switch (slot){
            case 0 -> settings.setPage(Page.POSITION);
            case 9 -> settings.setPage(Page.SIZE);
            case 18 -> settings.setPage(Page.ROTATION);
            case 20 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.FIRSTPERSON_RIGHTHAND);
            case 21 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.FIXED);
            case 22 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.GUI);
            case 23 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.HEAD);
            case 24 -> wrapper.setTransform(ItemDisplay.ItemDisplayTransform.THIRDPERSON_RIGHTHAND);
            case 26 -> {
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
            case 44 -> {
                main.getClipboardManager().getClipboard(player.getUniqueId()).remove();
                main.getClipboardManager().removeClipboard(player.getUniqueId());
                player.closeInventory();
            }
        }
        if(slot != 26 && slot != 44){
            new UserInterface(main, player, main.getSettingsManager().getAdjusterSettings(player.getUniqueId()).getPage());
        }
    }
}



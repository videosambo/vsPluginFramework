package fi.videosambo.pluginFramework.spigot.gui;

import fi.videosambo.pluginFramework.spigot.VSItemStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Class for creating easy to use clickable guis for players.
 */
public class Gui implements Listener {

    InventoryType type;
    String title;
    int size;
    Inventory inventory;
    ArrayList<GuiSlot> buttons;

    /**
     * @param type  Inventory type, specifies size that is going to be used
     * @param title Title that you can see after opening inventory
     */
    public Gui(InventoryType type, String title) {
        this.type = type;
        this.title = title;
        this.size = type.getDefaultSize();
        buttons = new ArrayList<>();
        inventory = Bukkit.createInventory(null, type, title);
    }

    /**
     * @param size  Inventory size, specifies size that is going to be used
     * @param title Title that you can see after opening inventory
     */
    public Gui(int size, String title) {
        this.size = size;
        this.title = title;
        inventory = Bukkit.createInventory(null, size, title);
    }

    /**
     * Method to add guislot for gui
     * @param index         Place in inventory
     * @param displayName   Item name
     * @param material      Item material
     * @param event         Event for click
     */
    public void addGuiSlot(int index, String displayName, Material material, GuiClickEventListener event) {
        inventory.setItem(index, new VSItemStack(material, displayName));
        buttons.add(new GuiSlot(displayName,material,event));
    }

    /**
     * Method to add guislot for gui
     * @param index     Place in inventory
     * @param itemStack Item name
     * @param event     Event for click
     */
    public void addGuiSlot(int index, ItemStack itemStack, GuiClickEventListener event) {
        inventory.setItem(index, itemStack);
        buttons.add(new GuiSlot(itemStack, event));
    }

    /**
     * Method to add guislot for gui
     * @param index     Place in inventory
     * @param guiSlot   Guislot object
     */
    public void addGuiSlot(int index, GuiSlot guiSlot) {
        inventory.setItem(index, guiSlot.getItem());
        buttons.add(guiSlot);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        for (GuiSlot slot : buttons) {
            if (e.getCurrentItem().equals(slot))
                slot.click(new GuiClickEvent(slot, (Player) e.getWhoClicked()));
        }
        e.setCancelled(true);
    }

    /**
     * Open gui for player
     * @param player    player
     */
    public void openForPlayer(Player player) {
        player.openInventory(this.inventory);
    }

    /**
     * Close inventory for all players that are viewing (this) gui
     */
    public void closeInventory() {
        for (HumanEntity humanEntity : inventory.getViewers()) {
            humanEntity.closeInventory();
        }
    }

    /**
     * Close inventory for one player
     * @param player    player
     */
    public void closeInventory(Player player) {
        player.closeInventory();
    }

    public InventoryType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<GuiSlot> getButtons() {
        return buttons;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

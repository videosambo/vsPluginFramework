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

import java.util.ArrayList;

public class Gui implements Listener {

    InventoryType type;
    String title;
    int size;
    Inventory inventory;
    ArrayList<GuiSlot> buttons;

    public Gui(InventoryType type, String title) {
        this.type = type;
        this.title = title;
        this.size = type.getDefaultSize();
        buttons = new ArrayList<>();
        inventory = Bukkit.createInventory(null, type, title);
    }

    public Gui(int size, String title) {
        this.size = size;
        this.title = title;
        inventory = Bukkit.createInventory(null, size, title);
    }

    public void addGuiSlot(int index, String displayName, Material material, GuiClickEventListener event) {
        inventory.setItem(index, new VSItemStack(material, displayName));
        buttons.add(new GuiSlot(displayName,material,event));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        for (GuiSlot slot : buttons) {
            if (e.getCurrentItem().equals(slot))
                slot.click(new GuiClickEvent(slot, (Player) e.getWhoClicked()));
        }
    }

    public void openForPlayer(Player player) {
        player.openInventory(this.inventory);
    }

    public void closeInventory() {
        for (HumanEntity humanEntity : inventory.getViewers()) {
            humanEntity.closeInventory();
        }
    }

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

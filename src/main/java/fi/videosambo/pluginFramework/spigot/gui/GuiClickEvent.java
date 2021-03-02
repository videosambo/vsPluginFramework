package fi.videosambo.pluginFramework.spigot.gui;

import fi.videosambo.pluginFramework.spigot.VSItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.EventObject;

public class GuiClickEvent extends EventObject {

    private Player clicker;
    private GuiSlot slot;

    public GuiClickEvent(GuiSlot slot, Player player) {
        super(slot);
        this.slot = slot;
        this.clicker = player;
    }

    public void setClicker(Player clicker) {
        this.clicker = clicker;
    }

    public Player getClicker() {
        return clicker;
    }

    public Gui getGui() {
        return slot.getGui();
    }

    public VSItemStack getItem() {
        return slot.getItem();
    }

    public String getDisplayName() {
        return slot.getDisplayName();
    }

    public Material getMaterial() {
        return slot.getMaterial();
    }
}

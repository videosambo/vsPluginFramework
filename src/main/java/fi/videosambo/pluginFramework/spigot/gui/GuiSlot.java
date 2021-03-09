package fi.videosambo.pluginFramework.spigot.gui;

import fi.videosambo.pluginFramework.spigot.VSItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GuiSlot extends VSItemStack{

    private static ArrayList<GuiClickEventListener> listeners = new ArrayList<>();

    private GuiClickEventListener event;

    private Gui gui;
    private VSItemStack item;
    private String displayName;
    private Material material;

    public GuiSlot(String displayName, Material material, GuiClickEventListener event) {
        super(material);
        this.displayName = displayName;
        super.setDisplayName(displayName);
        this.material = material;
        super.setType(material);
        this.event = event;
        listeners.add(event);
    }
    public GuiSlot(ItemStack item, GuiClickEventListener event) {
        super(item.getType());
        this.displayName = item.getItemMeta().getDisplayName();
        super.setDisplayName(displayName);
        this.material = item.getType();
        super.setType(material);
        this.event = event;
        listeners.add(event);
    }

    public void click(GuiClickEvent e) {
        for(GuiClickEventListener event : listeners) {
            if (event == this.event) {
                event.onClick(e);
            }
        }
    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public VSItemStack getItem() {
        return item;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Material getMaterial() {
        return material;
    }
}

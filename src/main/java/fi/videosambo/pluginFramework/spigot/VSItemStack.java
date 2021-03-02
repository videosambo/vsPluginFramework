package fi.videosambo.pluginFramework.spigot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class VSItemStack extends ItemStack {

    public VSItemStack(Material material) {
        super(material);
    }

    public VSItemStack(Material type, int amount) {
        super(type, amount);
    }

    public VSItemStack(ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    public VSItemStack(Material material, String displayName) {
        super(material);
        setDisplayName(displayName);
    }

    public void setLore(List<String> list) {
        ItemMeta meta = super.getItemMeta();
        meta.setLore(list);
        super.setItemMeta(meta);
    }

    public void setLore(String... list) {
        ItemMeta meta = super.getItemMeta();
        meta.setLore(Arrays.asList(list));
        super.setItemMeta(meta);
    }

    public void addLore(String lore) {
        ItemMeta meta = super.getItemMeta();
        List<String> list = meta.getLore();
        list.add(lore);
        meta.setLore(list);
        super.setItemMeta(meta);
    }

    public void removeLore(String lore) throws NoSuchFieldException {
        ItemMeta meta = super.getItemMeta();
        List<String> list = meta.getLore();
        for (String s : list) {
            if (lore.equals(s)){
                list.remove(s);
                meta.setLore(list);
                super.setItemMeta(meta);
            }
        }
        throw new NoSuchFieldException("Lore didn't contain String");
    }

    public void addEnchantment(Enchantment enchantment, int level, boolean unnatural){
        ItemMeta meta = super.getItemMeta();
        meta.addEnchant(enchantment, level,unnatural);
        super.setItemMeta(meta);
    }

    public void setDisplayName(String name) {
        ItemMeta meta = super.getItemMeta();
        meta.setDisplayName(name);
        super.setItemMeta(meta);
    }

    public void setUnbreakable(boolean isUnbreakable) {
        ItemMeta meta = super.getItemMeta();
        meta.setUnbreakable(isUnbreakable);
        super.setItemMeta(meta);
    }

    public void setCustomModelData(int modelData) {
        ItemMeta meta = super.getItemMeta();
        meta.setCustomModelData(modelData);
        super.setItemMeta(meta);
    }
}

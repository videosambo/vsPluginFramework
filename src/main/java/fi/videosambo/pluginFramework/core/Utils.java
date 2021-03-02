package fi.videosambo.pluginFramework.core;

import fi.videosambo.pluginFramework.core.database.DBVar;
import fi.videosambo.pluginFramework.core.exceptions.DBUndentifiedValueException;
import fi.videosambo.pluginFramework.spigot.gui.Gui;
import fi.videosambo.pluginFramework.spigot.gui.GuiClickEvent;
import fi.videosambo.pluginFramework.spigot.gui.GuiClickEventListener;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;

public class Utils {

    /**
     * Method to calculate inventory index out of x y coordinates
     * @param x     Slot in horizontal scale
     * @param y     Slot in vertical scale
     * @param type  Inventory type
     * @return      Index of inventory
     */
    public static int getInvIndex(int x, int y, InventoryType type) {
        int slot = y*9 + x;
        return slot > type.getDefaultSize() ? null : slot;
    }

    /**
     * Method to calculate inventory index out of x y coordinates
     * @param x     Slot in horizontal scale
     * @param y     Slot in vertical scale
     * @param inv   Inventory
     * @return      Index of inventory
     */
    public static int getInvIndex(int x, int y, Inventory inv) {
        int slot = y*9 + x;
        return slot > inv.getSize() ? null : slot;
    }

    public static void prepareStatement(PreparedStatement statement, DBVar... args) throws SQLException, DBUndentifiedValueException {
        for (int i = 0; i < args.length; i++) {
            if (args[i].getVal() instanceof String) {
                statement.setString(i + 1, (String) args[i].getVal());
            } else if (args[i].getVal() instanceof Integer) {
                statement.setInt(i + 1, (Integer) args[i].getVal());
            } else if (args[i].getVal() instanceof Boolean) {
                statement.setBoolean(i + 1, (Boolean) args[i].getVal());
            } else if (args[i].getVal() instanceof Blob) {
                statement.setBlob(i + 1, (Blob) args[i].getVal());
            } else if (args[i].getVal() instanceof Array) {
                statement.setArray(i + 1, (Array) args[i].getVal());
            } else if (args[i].getVal() instanceof InputStream) {
                statement.setAsciiStream(i + 1, (InputStream) args[i].getVal());
            } else if (args[i].getVal() instanceof BigDecimal) {
                statement.setBigDecimal(i + 1, (BigDecimal) args[i].getVal());
            } else if (args[i].getVal() instanceof Time) {
                statement.setTime(i + 1, (Time) args[i].getVal());
            } else if (args[i].getVal() instanceof Byte) {
                statement.setByte(i + 1, (Byte) args[i].getVal());
            } else if (args[i].getVal() instanceof Timestamp) {
                statement.setTimestamp(i + 1, (Timestamp) args[i].getVal());
            } else throw new DBUndentifiedValueException("Tried to prepare statement with variable what doesn't exist: " + args[i].getVal().toString());
        }
    }
}

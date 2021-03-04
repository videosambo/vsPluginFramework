package fi.videosambo.pluginFramework.core;

import fi.videosambo.pluginFramework.core.database.DBVar;
import fi.videosambo.pluginFramework.core.exceptions.DBUndentifiedValueException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

    /**
     * This method is for DB classes to insert arquments into prepared statement.
     * @param statement                     Prepared statement variable
     * @param args                          List of DBVar arguments that have variable and type class
     * @throws SQLException                 If sql statement fails, throws SQLException
     * @throws DBUndentifiedValueException  If class does not support value, throws SQLException
     */
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
    private static final String LOC_SPLIT_CHAR = "_";

    /**
     * This method converts Location to simple String so it can be easily saved
     * @param loc   Location object that is going to be converted to String
     * @return      Returns a String that can be converted back to Location
     */
    public static String convertLocationToString(Location loc) {
        return loc.getWorld().getName() + LOC_SPLIT_CHAR + loc.getBlockY() + LOC_SPLIT_CHAR + loc.getBlockY() + LOC_SPLIT_CHAR + loc.getBlockZ();
    }

    /**
     * This method converts String to Location
     * @param loc   String that represents location
     * @return      Returns a Location
     */
    public static Location convertStringToLocation(String loc) {
        try {
            return new Location(Bukkit.getWorld(loc.split(LOC_SPLIT_CHAR)[0]), Integer.parseInt(loc.split(LOC_SPLIT_CHAR)[1]), Integer.parseInt(loc.split(LOC_SPLIT_CHAR)[2]), Integer.parseInt(loc.split(LOC_SPLIT_CHAR)[3]));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}

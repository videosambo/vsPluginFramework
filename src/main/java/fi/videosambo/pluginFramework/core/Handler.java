package fi.videosambo.pluginFramework.core;

import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.spigot.gui.GuiClickEventListener;

import java.util.ArrayList;

public class Handler {

    private static ArrayList<DatabaseHandler> dbHandlers = new ArrayList<>();
    private static ArrayList<GuiClickEventListener> listeners = new ArrayList<>();;

    private static Object handler;

    public static void setHandler(Object object) {
        handler = object;
    }

    public static Object getHandler() {
        return handler;
    }

    public static ArrayList<DatabaseHandler> getDbHandlers() {
        return dbHandlers;
    }

    public static ArrayList<GuiClickEventListener> getListeners() {
        return listeners;
    }
}

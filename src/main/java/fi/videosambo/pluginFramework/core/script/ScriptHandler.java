package fi.videosambo.pluginFramework.core.script;

import org.bukkit.plugin.java.JavaPlugin;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScriptHandler {

    private ScriptEngine engine;

    public ScriptHandler() {
        engine = new ScriptEngineManager().getEngineByName("JavaScript");
    }

    public void runFile(File file) throws FileNotFoundException, ScriptException {
        engine.eval(new FileReader(file));
    }

    public void define(String variable, Object obj) {
        engine.put(variable,obj);
    }

    private ScriptEngine getEngine() {
        return engine;
    }
}

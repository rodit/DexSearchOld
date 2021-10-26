package xyz.rodit.dexsearch;

import org.jf.dexlib2.DexFileFactory;
import org.jf.dexlib2.Opcodes;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.DexFile;
import xyz.rodit.dexsearch.cached.CachedClassDef;

import java.io.File;
import java.util.*;

public class DexBase {

    private final Map<String, ClassDef> classes = new HashMap<>();

    public Collection<ClassDef> getClasses() {
        return classes.values();
    }

    public ClassDef getClass(String name) {
        return classes.get(name);
    }

    public boolean hasClass(String name) {
        return classes.containsKey(name);
    }

    public int loadClasses(File file) throws Exception {
        DexFile dex = DexFileFactory.loadDexFile(file, Opcodes.getDefault());
        for (ClassDef cls : dex.getClasses()) {
            classes.put(cls.getType(), new CachedClassDef(cls));
        }
        return dex.getClasses().size();
    }
}

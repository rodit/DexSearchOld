package xyz.rodit.dexsearch;

import com.google.common.collect.Lists;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.jf.dexlib2.iface.ClassDef;
import org.jf.dexlib2.iface.value.IntEncodedValue;
import xyz.rodit.dexsearch.schema2.resolver.Resolver;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.addRequiredOption("i", "input", true, "The input apk file containing DEX binaries.")
                .addRequiredOption("o", "output", true, "The output directory to write the schema mapping and Java classes.")
                .addRequiredOption("s", "schema", true, "The schema file.")
                .addOption("a", "app", true, "The application id (package).")
                .addOption("b", "build", true, "The build number of the apk.")
                .addRequiredOption("p", "package", true, "The package to place the output wrapper classes in.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cli = parser.parse(options, args);

        File input = new File(cli.getOptionValue("i"));
        File output = new File(cli.getOptionValue("o"));
        File schema = new File(cli.getOptionValue("s"));
        String appId = cli.getOptionValue("a", "");
        String packageName = cli.getOptionValue("p");
        String buildNumber = cli.getOptionValue("b");

        if (!input.exists()) {
            Logger.error("Input file not found.");
            System.exit(1);
        } else if (output.isFile()) {
            Logger.error("Output path must be a directory.");
            System.exit(1);
        } else if (!schema.exists()) {
            Logger.error("Schema file not found.");
            System.exit(1);
        }

        ZipFile apk = new ZipFile(input);
        List<File> dexFiles = new ArrayList<>();
        Enumeration<? extends ZipEntry> entries = apk.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.getName().endsWith(".dex")) {
                Logger.info("Found dex file %s.", entry.getName());
                File tmp = File.createTempFile("tmp", "dex");
                tmp.deleteOnExit();
                Files.copy(apk.getInputStream(entry), Paths.get(tmp.getPath()), StandardCopyOption.REPLACE_EXISTING);
                dexFiles.add(tmp);
            }
        }
        apk.close();

        DexBase base = new DexBase();
        for (File dexFile : dexFiles) {
            int loaded = base.loadClasses(dexFile);
            Logger.info("Loaded %d classes from %s.", loaded, dexFile.getName());
        }

        int buildCode;
        ClassDef buildCls = base.getClass("L" + appId.replace(".", "/") + "/BuildConfig;");
        if (buildCls != null) {
            buildCode = ((IntEncodedValue) Lists.newArrayList(buildCls.getStaticFields().iterator()).stream().filter(f -> f.getName().equals("VERSION_CODE")).findFirst().get().getInitialValue()).getValue();
        } else {
            buildCode = Integer.parseInt(buildNumber);
        }

        long start = System.nanoTime();

        Resolver resolver = Resolver.create(base, schema);
        resolver.resolveAll();

        Logger.info("Generating binding classes...");
        resolver.generateBindings(output, packageName, buildCode, true);

        long diff = System.nanoTime() - start;
        double duration = (double) diff / 1000000d;

        Logger.info("Done. Resolution took %fms.", duration);
    }
}

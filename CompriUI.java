import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;

/**
 * Beschreiben Sie hier die Klasse CompriUI.
 *
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class CompriUI {
    File[] files;
    File out;
    int scale, quality;

    public CompriUI() {
        scale = 50;
        quality = 80;

        JFrame frame = new JFrame("Comprimizer");
        frame.setLayout(new BoxLayout(frame.getContentPane(), 1));
        frame.setSize(800, 600);
        frame.setVisible(true);

        JButton buttoni = new JButton("OpenImages");
        buttoni.addActionListener(e -> openImages());
        frame.add(buttoni);

        JButton buttono = new JButton("OpenOutputFolder");
        buttono.addActionListener(e -> openOutput());
        frame.add(buttono);

        JButton buttonc = new JButton("Convert");
        buttonc.addActionListener(e -> convert());
        frame.add(buttonc);
    }

    public void openImages() {
        JFileChooser chooser = new JFileChooser("Open");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        files = chooser.getSelectedFiles();
        for (File f : files) System.out.println(f);
    }

    public void openOutput() {
        JFileChooser chooser = new JFileChooser("Open");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        out = chooser.getSelectedFile();
    }

    public void convert() {
        for (File f : files) convert(f);
    }

    public void convert(File in) {
        System.out.println("\n\nConverting: " + in.getAbsolutePath() + "\nto: " + getOutName(in));
        try {
            exec("/usr/bin/convert", in.getAbsolutePath(), "-size", scale + "%", "-quality", quality + "", getOutName(in));
        } catch (Exception e) {e.printStackTrace();}
    }

    public String getOutName(File in) {
        String out = in.getName();
        out = out.substring(0, out.lastIndexOf(".")) + ".jpg";
        out = this.out.getAbsolutePath() + "/" + out;
        return out;
    }

    public static String[] exec(String... command) throws Exception {
        System.out.println(String.join(" ", command));
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        ArrayList<String> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.add(line);
                System.out.println(line);
            }
        }
        process.waitFor();
        return out.toArray(String[]::new);
    }
}

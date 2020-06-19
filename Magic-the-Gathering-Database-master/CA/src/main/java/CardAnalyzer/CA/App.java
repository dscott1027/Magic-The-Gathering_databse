//Rashid Taleb, 2019
package CardAnalyzer.CA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FilenameUtils;

import java.time.Instant;

public class App {
	
	public static void main(String[] args) {
		try {
			App meow = new App();
			meow.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<myCard> run() throws IOException {
		List<myCard> entries = new ArrayList<myCard>();
		
		// filechooser
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// components for selecting from and presenting notifications
		JFrame notification = new JFrame("status of analysis");
		File dir1;
		File[] content = {};
		String from;

		// selecting the from folder
		while (content.length == 0) {
			j.setDialogTitle("Select folder of unlogged cards");
			j.showSaveDialog(null);
			from = j.getSelectedFile().getAbsolutePath();
			dir1 = new File(from);
			content = dir1.listFiles();
			// informs user if from folder is empty
			if (content.length == 0) {
				JOptionPane.showMessageDialog(notification, "Choose a non-empty directory", "Empty directory",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		// selecting the to folder
		j.setDialogTitle("Select folder of logged cards");
		j.showSaveDialog(null);
		String to = j.getSelectedFile().getAbsolutePath();
		String dest;
		long time;

		// loop to process card entries
		for (int i = 0; i < content.length; i++) {
			
			// opens an analyzer window for the entry
			try {
				time = Instant.now().getEpochSecond();
				dest = to + "\\" + time + "." + FilenameUtils.getExtension(content[i].getName());
				//move file
				Files.move(Paths.get(content[i].getCanonicalPath()), Paths.get(dest));
				analyzerFrame window = new analyzerFrame();
				window.open(dest, entries);
				//this ensures that you cannot have two files renamed in the same second
				java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1001);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		return entries;
	}
}
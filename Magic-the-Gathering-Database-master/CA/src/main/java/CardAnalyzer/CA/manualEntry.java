package CardAnalyzer.CA;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class manualEntry extends Dialog {

	protected Object result;
	protected Shell shlManualEntry;
	String imgPath;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public manualEntry(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String s) {
		this.imgPath = s;
		createContents();
		shlManualEntry.open();
		shlManualEntry.layout();
		Display display = getParent().getDisplay();
		while (!shlManualEntry.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlManualEntry = new Shell(getParent(), getStyle());
		shlManualEntry.setSize(450, 153);
		shlManualEntry.setText("Manual Entry");
		
		Button btnCopytoClipboard = new Button(shlManualEntry, SWT.NONE);
		btnCopytoClipboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				StringSelection stringSelection = new StringSelection(imgPath);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopytoClipboard.setBounds(111, 87, 107, 25);
		btnCopytoClipboard.setText("CopyTo Clipboard");
		
		Button btnCloseDialog = new Button(shlManualEntry, SWT.NONE);
		btnCloseDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shlManualEntry.close();
			}
		});
		btnCloseDialog.setBounds(236, 87, 78, 25);
		btnCloseDialog.setText("Close Dialog");
		
		Label lblYouHaveOpted = new Label(shlManualEntry, SWT.CENTER);
		lblYouHaveOpted.setBounds(10, 10, 424, 25);
		lblYouHaveOpted.setText("You have opted to enter this card manually, but the image will still be moved.");
		
		Label lblPleaseCopyThe = new Label(shlManualEntry, SWT.CENTER);
		lblPleaseCopyThe.setBounds(10, 35, 424, 25);
		lblPleaseCopyThe.setText("Please copy the filepath to your clipboard so you can reference it later.");
		
		Label fileName = new Label(shlManualEntry, SWT.CENTER);
		fileName.setBounds(10, 60, 424, 25);
		fileName.setText(imgPath);
	}
}


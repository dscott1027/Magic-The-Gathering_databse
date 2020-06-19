//TODO: change text items to labels
package CardAnalyzer.CA;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class AnalyzerBox extends Composite {
	private Text txtCardName;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AnalyzerBox(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(10, 10, 334, 314);
		
		Button btnConfirm = new Button(this, SWT.CENTER);
		btnConfirm.setBounds(269, 330, 75, 25);
		btnConfirm.setText("Confirm");
		
		Combo combo_1 = new Combo(this, SWT.NONE);
		combo_1.setBounds(75, 330, 66, 23);
		
		Button btnManual = new Button(this, SWT.NONE);
		btnManual.setBounds(269, 361, 75, 25);
		btnManual.setText("Manual");
		
		Button btnFoil = new Button(this, SWT.CHECK);
		btnFoil.setBounds(223, 344, 40, 25);
		btnFoil.setText("Foil");
		
		txtCardName = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		txtCardName.setText("Card Name");
		txtCardName.setBounds(10, 392, 91, 21);
		
		Combo combo_2 = new Combo(this, SWT.NONE);
		combo_2.setBounds(119, 392, 225, 23);
		
		Label lblCondition = new Label(this, SWT.CENTER);
		lblCondition.setBounds(10, 330, 59, 23);
		lblCondition.setText("Condition");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

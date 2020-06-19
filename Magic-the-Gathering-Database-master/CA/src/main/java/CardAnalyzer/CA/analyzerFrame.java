/*Rashid Taleb, 2019
 *TODO:pass image name to open to create
 */
package CardAnalyzer.CA;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.exception.HttpRequestFailedException;
import io.magicthegathering.javasdk.resource.Card;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;

public class analyzerFrame {

	List<Card> cards;
	List<myCard> entries;
	protected Shell shell;
	private Text parameter;
	public String pic;
	Display display;
	boolean f;

//	public static void main(String[] args) {
//		try {
//			analyzerFrame window = new analyzerFrame();
//			window.open("D:\\Profiles\\Rashid\\programming\\CA\\458.png", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	// Open the window
	public List<myCard> open(String img, List<myCard> entries) {
		pic = img;
		this.entries = entries;
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return this.entries;
	}

	// Create contents of the window
	protected void createContents() {
		shell = new Shell();
		shell.setSize(662, 493);
		shell.setText("SWT Application");

		// image display location
		Label imageBox = new Label(shell, SWT.NONE);
		imageBox.setBounds(0, 0, 640, 360);
		imageBox.setImage(new Image(display, pic));

		// entry status label
		final Label lblSearchStatus = new Label(shell, SWT.CENTER);
		lblSearchStatus.setBounds(140, 422, 415, 25);
		lblSearchStatus.setText("No Search Yet...");

		// card search parameter text box
		parameter = new Text(shell, SWT.BORDER);
		parameter.setBounds(11, 366, 330, 21);

		// card selection dropbox
		final Combo cardSelection = new Combo(shell, SWT.NONE);
		cardSelection.setBounds(83, 393, 553, 23);

		// name search button
		Button search = new Button(shell, SWT.NONE);
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblSearchStatus.setText("Searching, please be patient.");
				try {
					cards = CardAPI.getAllCards(Collections.singletonList("name=" + parameter.getText()));
				} catch (HttpRequestFailedException err) {
					lblSearchStatus.setText("Request timed out; enter your card manually");
					shell.close();
				}
				//set message based on search results
				if (cards == null || cards.size() == 0) {
					lblSearchStatus.setText("No cards found");
				} else if (cards.size() >= 100) {
					lblSearchStatus.setText("If you don't see your card, narrow your search");
				} else {
					lblSearchStatus.setText("Select card above");
				}
				//updates selections for the card dropbox
				for (int i = 0; i < cards.size(); i++) {
					cardSelection.add(cards.get(i).getName() + " - " + cards.get(i).getSet());
				}
			}
		});
		search.setBounds(347, 366, 82, 21);
		search.setText("Search Name");

		// card select dropbox label
		Label lblSelectCard = new Label(shell, SWT.NONE);
		lblSelectCard.setBounds(10, 395, 67, 21);
		lblSelectCard.setText("Select Card:");

		// foil selector
		Button isFoil = new Button(shell, SWT.CHECK);
		isFoil.addSelectionListener(new SelectionAdapter() {

	        @Override
	        public void widgetSelected(SelectionEvent event) {
	            Button btn = (Button) event.getSource();
	            f = btn.getSelection();
	        }
	    });
		isFoil.setBounds(590, 368, 46, 16);
		isFoil.setText("Foil");

		// condition dropbox
		final Combo condition = new Combo(shell, SWT.NONE);
		condition.setItems(new String[] { "M", "NM", "LP", "MP", "HP", "DMG" });
		condition.setBounds(496, 366, 74, 23);

		// condition dropbox label
		final Label lblCondition = new Label(shell, SWT.NONE);
		lblCondition.setBounds(435, 369, 55, 15);
		lblCondition.setText("Condition:");

		// accept button
		Button btnAccept = new Button(shell, SWT.NONE);
		// accept selected card
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (condition.getText().equals("")) {
					lblSearchStatus.setText("Please Select Condition");
				} else if (cardSelection.getText().equals("")) {
					lblSearchStatus.setText("Please Select Card");
				} else {
					myCard m = new myCard();
					m.setName(cards.get(cardSelection.getSelectionIndex()).getName());
					m.setTypes(merge(cards.get(cardSelection.getSelectionIndex()).getSupertypes(), cards.get(cardSelection.getSelectionIndex()).getTypes()),
							cards.get(cardSelection.getSelectionIndex()).getSubtypes());
					m.setSet(cards.get(cardSelection.getSelectionIndex()).getSet());
					m.setCost(cards.get(cardSelection.getSelectionIndex()).getManaCost());
					double b = cards.get(cardSelection.getSelectionIndex()).getCmc();
					int c = (int) Math.round(b);
					m.setCMC(c);
					m.setColor(cards.get(cardSelection.getSelectionIndex()).getColors());
					List<String> l1 = Arrays.asList(cards.get(cardSelection.getSelectionIndex()).getTypes());
					if (l1.contains("Creature")) {
						m.setPower(Integer.parseInt(cards.get(cardSelection.getSelectionIndex()).getPower()));
						m.setToughness(Integer.parseInt(cards.get(cardSelection.getSelectionIndex()).getToughness()));
					} else if (l1.contains("Planeswalker")) {
						m.setLoyalty(Integer.parseInt(cards.get(cardSelection.getSelectionIndex()).getLoyalty()));
					}
					m.setCondition(condition.getText());
					m.setFoil(f);
					m.setPath(pic);
					entries.add(m);
					shell.close();
				}
			}
		});
		btnAccept.setBounds(561, 422, 75, 25);
		btnAccept.setText("Accept");

		// manual entry button
		Button btnSkipenterManually = new Button(shell, SWT.NONE);
		btnSkipenterManually.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					manualEntry w = new manualEntry(shell, 0);
					w.open(pic);
					shell.close();
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnSkipenterManually.setBounds(10, 422, 124, 25);
		btnSkipenterManually.setText("Skip (Enter Manually)");
	}
	
	private String[] merge(String[] a, String[] b) {
		int aLen = a.length;
        int bLen = b.length;
        String[] c = new String[aLen + bLen];
		
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        
		return c;
	}
}

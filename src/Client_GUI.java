import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StyledText;


public class Client_GUI extends Client {
	private static final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private static Text msg;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		msg = new Text(shell, SWT.BORDER);
		msg.setText("Enter Text..");
		msg.setBounds(10, 193, 334, 58);
		formToolkit.adapt(msg, true, true);
		
		Button Send = new Button(shell, SWT.NONE);
		Send.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		Send.setBounds(349, 207, 75, 25);
		formToolkit.adapt(Send, true, true);
		Send.setText("Send");
		
		StyledText window = new StyledText(shell, SWT.BORDER);
		window.setAlignment(SWT.CENTER);
		window.setBounds(10, 10, 414, 177);
		formToolkit.adapt(window);
		formToolkit.paintBordersFor(window);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}

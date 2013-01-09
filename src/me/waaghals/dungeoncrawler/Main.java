package me.waaghals.dungeoncrawler;

import java.util.logging.Logger;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;


import edu.uci.ics.jung.graph.Graph;

import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * @author Patrick Berenschot
 * 
 */
public class Main {

	public static final int NORTH 	= 2;
	public static final int EAST 	= 4;
	public static final int SOUTH 	= 8;
	public static final int WEST 	= 16;
	
	/**
	 * @param args
	 */
	
	public static void main (String [] args) {
		Display display = new Display ();
		final Shell shell = new Shell (display);
		shell.setText("Shell");
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginWidth = 10;
		fillLayout.marginHeight = 10;
		shell.setLayout(fillLayout);

		Button open = new Button (shell, SWT.PUSH);
		open.setText ("Prompt for a String");
		open.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				final Shell dialog = new Shell (shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				dialog.setText("Dialog Shell");
				FormLayout formLayout = new FormLayout ();
				formLayout.marginWidth = 10;
				formLayout.marginHeight = 10;
				formLayout.spacing = 10;
				dialog.setLayout (formLayout);

				Label label = new Label (dialog, SWT.NONE);
				label.setText ("Type a String:");
				FormData data = new FormData ();
				label.setLayoutData (data);

				Button cancel = new Button (dialog, SWT.PUSH);
				cancel.setText ("Cancel");
				data = new FormData ();
				data.width = 60;
				data.right = new FormAttachment (100, 0);
				data.bottom = new FormAttachment (100, 0);
				cancel.setLayoutData (data);
				cancel.addSelectionListener (new SelectionAdapter () {
					public void widgetSelected (SelectionEvent e) {
						System.out.println("User cancelled dialog");
						dialog.close ();
					}
				});

				final Text text = new Text (dialog, SWT.BORDER);
				data = new FormData ();
				data.width = 200;
				data.left = new FormAttachment (label, 0, SWT.DEFAULT);
				data.right = new FormAttachment (100, 0);
				data.top = new FormAttachment (label, 0, SWT.CENTER);
				data.bottom = new FormAttachment (cancel, 0, SWT.DEFAULT);
				text.setLayoutData (data);

				Button ok = new Button (dialog, SWT.PUSH);
				ok.setText ("OK");
				data = new FormData ();
				data.width = 60;
				data.right = new FormAttachment (cancel, 0, SWT.DEFAULT);
				data.bottom = new FormAttachment (100, 0);
				ok.setLayoutData (data);
				ok.addSelectionListener (new SelectionAdapter () {
					public void widgetSelected (SelectionEvent e) {
						System.out.println ("User typed: " + text.getText ());
						dialog.close ();
					}
				});

				dialog.setDefaultButton (ok);
				dialog.pack ();
				dialog.open ();
			}
		});
		shell.pack ();
		shell.open ();
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	public static void mai
	(String[] args) {
		// TODO Auto-generated method stub
		// Graph<V, E> where V is the type of the vertices
		// and E is the type of the edges
		
		//Undirected want; geen richting gevoeligheid in de edges, je kan van kamer A naar B en van B naar A
		//Multi, an room can have multiple exits to the same room, East may have a path to room B but so might exit West.
		Graph<Integer, Room> g = new UndirectedSparseMultigraph<Integer, Room>();
		// Add some vertices. From above we defined these to be type Integer.
		g.addVertex((Integer) 1);
		g.addVertex((Integer) 2);
		g.addVertex((Integer) 3);
		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.
		g.addEdge(new Room(), 1, 2); // Note that Java 1.5 auto-boxes primitives
		g.addEdge(new Room(), 2, 3);
		// Let's see what we have. Note the nice output from the
		// SparseMultigraph<V,E> toString() method
		System.out.println("The graph g = " + g.toString());
		
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout(new FillLayout());
		shell.setText("ExpandBar Example");
		ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
		Image image = display.getSystemImage(SWT.ICON_QUESTION);
		
		// First item
		Composite composite = new Composite (bar, SWT.NONE);
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		Button button = new Button (composite, SWT.PUSH);
		button.setText("SWT.PUSH");
		button = new Button (composite, SWT.RADIO);
		button.setText("SWT.RADIO");
		button = new Button (composite, SWT.CHECK);
		button.setText("SWT.CHECK");
		button = new Button (composite, SWT.TOGGLE);
		button.setText("SWT.TOGGLE");
		ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
		item0.setText("What is your favorite button");
		item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item0.setControl(composite);
		item0.setImage(image);
		
		// Second item
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (2, false);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);	
		Label label = new Label (composite, SWT.NONE);
		label.setImage(display.getSystemImage(SWT.ICON_ERROR));
		label = new Label (composite, SWT.NONE);
		label.setText("SWT.ICON_ERROR");
		label = new Label (composite, SWT.NONE);
		label.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
		label = new Label (composite, SWT.NONE);
		label.setText("SWT.ICON_INFORMATION");
		label = new Label (composite, SWT.NONE);
		label.setImage(display.getSystemImage(SWT.ICON_WARNING));
		label = new Label (composite, SWT.NONE);
		label.setText("SWT.ICON_WARNING");
		label = new Label (composite, SWT.NONE);
		label.setImage(display.getSystemImage(SWT.ICON_QUESTION));
		label = new Label (composite, SWT.NONE);
		label.setText("SWT.ICON_QUESTION");
		ExpandItem item1 = new ExpandItem (bar, SWT.NONE, 1);
		item1.setText("What is your favorite icon");
		item1.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item1.setControl(composite);
		item1.setImage(image);
		
		// Third item
		composite = new Composite (bar, SWT.NONE);
		layout = new GridLayout (2, true);
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		label = new Label (composite, SWT.NONE);
		label.setText("Scale");	
		new Scale (composite, SWT.NONE);
		label = new Label (composite, SWT.NONE);
		label.setText("Spinner");	
		new Spinner (composite, SWT.BORDER);
		label = new Label (composite, SWT.NONE);
		label.setText("Slider");	
		new Slider (composite, SWT.NONE);
		ExpandItem item2 = new ExpandItem (bar, SWT.NONE, 2);
		item2.setText("What is your favorite range widget");
		item2.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite);
		item2.setImage(image);
		
		item1.setExpanded(true);
		bar.setSpacing(8);
		shell.setSize(400, 350);
		shell.open();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) {
				display.sleep ();
			}
		}
		image.dispose();
		display.dispose();
		
		/*Speak blaat = new Speak();
		try {
			blaat.call("\"The quick brown fox jumps over the lazy dog\" is an English-language pangram – a phrase that contains all of the letters of the alphabet. It has been used to test typewriters and computer keyboards, and in other applications involving all of the letters in the English alphabet. Owing to its brevity and coherence, it has become widely known.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}






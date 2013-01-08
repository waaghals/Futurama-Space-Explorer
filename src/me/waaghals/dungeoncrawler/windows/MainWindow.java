package me.waaghals.dungeoncrawler.windows;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainWindow extends Shell {
    public static void main(String[] args) {
        try {
            Display display = Display.getDefault();
            MainWindow shell = new MainWindow(display, SWT.SHELL_TRIM);
            shell.open();
            shell.layout();
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainWindow(Display display, int style) {
        super(display, style);
        createContents();
    }

    protected void createContents() {
        setText("Dungeon Crawler");
        setSize(450, 300);
    }

    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }
}

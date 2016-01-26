package com.jetbrains.test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class Main extends JFrame {
    private final JTextArea textArea = new JTextArea();

    private Main() {
        super("Event test");

        JPanel settingsPanel = new JPanel();
        final JCheckBox keyEventsCheckBox = new JCheckBox("Keyboard events", true);
        settingsPanel.add(keyEventsCheckBox);
        final JCheckBox inputMethodEventsCheckBox = new JCheckBox("Input method events", true);
        settingsPanel.add(inputMethodEventsCheckBox);
        final JCheckBox mouseEventsCheckBox = new JCheckBox("Mouse events");
        settingsPanel.add(mouseEventsCheckBox);
        final JCheckBox mouseMovesCheckBox = new JCheckBox("Mouse moves");
        settingsPanel.add(mouseMovesCheckBox);
        final JCheckBox mouseWheelCheckBox = new JCheckBox("Mouse wheel");
        settingsPanel.add(mouseWheelCheckBox);

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logEvent(e, mouseEventsCheckBox);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                logEvent(e, mouseEventsCheckBox);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                logEvent(e, mouseEventsCheckBox);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logEvent(e, mouseEventsCheckBox);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logEvent(e, mouseEventsCheckBox);
            }
        });
        textArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                logEvent(e, mouseMovesCheckBox);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                logEvent(e, mouseMovesCheckBox);
            }
        });
        textArea.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                logEvent(e, mouseWheelCheckBox);
            }
        });
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                logEvent(e, keyEventsCheckBox);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                logEvent(e, keyEventsCheckBox);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                logEvent(e, keyEventsCheckBox);
            }
        });
        textArea.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent e) {
                logEvent(e, inputMethodEventsCheckBox);
            }

            @Override
            public void caretPositionChanged(InputMethodEvent e) {
                logEvent(e, inputMethodEventsCheckBox);
            }
        });
        textArea.setEditable(false);
        textArea.enableInputMethods(true);
        textArea.append("Java " + System.getProperty("java.version") + " " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + "\n");

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        settingsPanel.add(clearButton);
        JButton copyButton = new JButton("Copy to clipboard");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.getToolkit().getSystemClipboard().setContents(new StringSelection(textArea.getText()), null);
            }
        });
        settingsPanel.add(copyButton);

        add(new JScrollPane(textArea));
        add(settingsPanel, BorderLayout.NORTH);
    }

    private void logEvent(AWTEvent event, JCheckBox checkBox) {
        if (checkBox.isSelected()) {
            textArea.append(event.getClass().getName() + "[" + event.paramString() + "]\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setSize(1500, 600);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }
}

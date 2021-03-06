package courseschedule.gui;

import courseschedule.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TableButton extends JButton implements MouseListener {

	private String[] label;
	private Dimension dimension = new Dimension(1170, 40);
	OffsetFinder of;
	private CustomFont font = new CustomFont();
	private Color bg = getBackground();

	/*
	Constructors
	 */
	public TableButton(String[] label, OffsetFinder of) {
		this.label = label;
		this.of = of;
		enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder());
		setFont(font.getFontAbel(15));
		setForeground(CustomColour.getNightBlue());
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(this);
	}

	/*
	paints the TableButton
	 */
	public void paintComponent(Graphics g) {
		int newWidth = getPreferredSize().width;
		int newHeight = getPreferredSize().height;
		g.setColor(bg);
		g.drawRect(0, 0, newWidth, newHeight);
		g.fillRect(0, 0, newWidth, newHeight);
		// draw the label according to design in the button
		g.setColor(getForeground());
		Font f = getFont();
		if (f != null) {
			FontMetrics fm = getFontMetrics(getFont());
			Graphics2D g2d = (Graphics2D) g;

			for (int i = 0; i < label.length; i++) {
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
				g2d.drawString(label[i], of.getOffset(label[i], i, this), (getHeight() / 2) + 5);
			}
		}
	}

	public void setColor(Color newColor) {
		bg = newColor;
	}

	public void setOffset(OffsetFinder of){
		this.of = of;
	}

	public void mouseEntered(MouseEvent event) {
		setForeground(CustomColour.getLighterBlue());
	}

	public void mouseExited(MouseEvent event) {
		setForeground(CustomColour.getNightBlue());
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseClicked(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}

	public void setPreferredSize(Dimension dimension) {
		this.dimension = dimension;
	}

	/*
	Getter methods
	*/
	public Dimension getPreferredSize() {
		return dimension;
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
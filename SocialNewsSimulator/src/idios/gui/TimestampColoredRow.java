package idios.gui;

import idios.Item;
import idios.Simulation;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
public class TimestampColoredRow extends JLabel implements ListCellRenderer<Item> {
	private static final double MAX_AGE = 10.0 * 60.0;
	Simulation sim;
    public TimestampColoredRow(Simulation sim) {
        setOpaque(true);
        this.sim = sim;
    }
    
	@Override
	public Component getListCellRendererComponent(final JList<? extends Item> list, Item value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(sim.getRankingStrategy().renderItem(value));
		double age = Math.min(MAX_AGE, (float)(Simulation.getWorldTime() - value.timestamp)/60.0);
		age = Math.max(0, age);
		double hue = (MAX_AGE-age)/MAX_AGE*(117.0/360.0);
        setBackground(Color.getHSBColor((float)hue, 1.0f, 1.0f));
        SwingWorker worker = new SwingWorker() {
            @Override
            public Object doInBackground() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) { /*Who cares*/ }
                return null;
            }
            @Override
            public void done() {
                list.repaint();
            }
        };
        worker.execute();
        return this;
	}
}
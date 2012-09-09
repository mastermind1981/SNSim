package idios.gui;

import idios.Item;
import idios.RedditRanking;
import idios.Simulation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ApplicationView {

    private JFrame frame;
    private JTable frontPageLinks;
    private JTextField textInitialUsers;
    private JTextField textNewUsers;
    
    private Simulation sim;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ApplicationView window = new ApplicationView();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ApplicationView() {
        initSimulation();
        initialize();
    }

    private void initSimulation() {
        sim = new Simulation(new RedditRanking());
        sim.setup();
        sim.run(60*60*24);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 866, 593);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel frontPagePanel = new JPanel();
        frontPagePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        JPanel userListPanel = new JPanel();
        userListPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        JPanel simulationSettingsPanel = new JPanel();
        simulationSettingsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(frontPagePanel, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(simulationSettingsPanel, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                        .addComponent(userListPanel, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(userListPanel, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(simulationSettingsPanel, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                        .addComponent(frontPagePanel, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
                    .addContainerGap())
        );
        
        final JSpinner simDurationSpinner = new JSpinner();
        simDurationSpinner.setModel(new SpinnerNumberModel(new Integer(24), new Integer(0), null, new Integer(1)));
        
        JButton btnRun = new JButton("Run");

        
        JPanel simSettingsPanel = new JPanel();
        
        JButton btnInitialize = new JButton("Initialize");
        
        
        GroupLayout gl_simulationSettingsPanel = new GroupLayout(simulationSettingsPanel);
        gl_simulationSettingsPanel.setHorizontalGroup(
            gl_simulationSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_simulationSettingsPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_simulationSettingsPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_simulationSettingsPanel.createSequentialGroup()
                            .addComponent(btnInitialize)
                            .addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                            .addComponent(simDurationSpinner, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnRun))
                        .addComponent(simSettingsPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_simulationSettingsPanel.setVerticalGroup(
            gl_simulationSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_simulationSettingsPanel.createSequentialGroup()
                    .addGap(8)
                    .addComponent(simSettingsPanel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGap(18)
                    .addGroup(gl_simulationSettingsPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnRun)
                        .addComponent(btnInitialize)
                        .addComponent(simDurationSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        
        JLabel lblNumberOfInitial = new JLabel("Number of initial users");
        
        textInitialUsers = new JTextField();
        lblNumberOfInitial.setLabelFor(textInitialUsers);
        textInitialUsers.setColumns(10);
        
        JLabel lblNumberOfNew = new JLabel("Number of new users");
        
        textNewUsers = new JTextField();
        lblNumberOfNew.setLabelFor(textNewUsers);
        textNewUsers.setColumns(10);
        GroupLayout gl_simSettingsPanel = new GroupLayout(simSettingsPanel);
        gl_simSettingsPanel.setHorizontalGroup(
            gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_simSettingsPanel.createSequentialGroup()
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNumberOfInitial)
                        .addComponent(lblNumberOfNew))
                    .addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(textNewUsers)
                        .addComponent(textInitialUsers, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
        );
        gl_simSettingsPanel.setVerticalGroup(
            gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_simSettingsPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberOfInitial)
                        .addComponent(textInitialUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberOfNew)
                        .addComponent(textNewUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(139, Short.MAX_VALUE))
        );
        simSettingsPanel.setLayout(gl_simSettingsPanel);
        simulationSettingsPanel.setLayout(gl_simulationSettingsPanel);
        userListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JList userList = new JList();
        userListPanel.add(userList);
        frontPagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        final JList<String> itemList = new JList<>();
        itemList.setVisibleRowCount(80);
//        itemList.setBounds(228, 7, 0, 0);
        itemList.setModel(getKingsFrontPageModel());
        frontPagePanel.add(itemList);
        groupLayout.setAutoCreateGaps(true);
        frame.getContentPane().setLayout(groupLayout);
        
        btnRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int dur = (Integer) simDurationSpinner.getValue();
                sim.run(dur);
                itemList.setModel(getKingsFrontPageModel());
            }
        });
    }

    private ItemListModel getKingsFrontPageModel() {
        return new ItemListModel(sim, "kings");
    }
}

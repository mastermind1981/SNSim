package idios.gui;

import idios.RedditRanking;
import idios.Simulation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ApplicationView {

    private JFrame frame;
    private JTable frontPageLinks;
    private JSpinner spnInitialUsers;
    private JSpinner spnNewUsers;
    
    private Simulation sim;
    private JTextField txtSatisfaction;

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
        initSimulation(10, 0);
        initialize();
    }

    private void initSimulation(int numInitialUsers, int numNewUsers) {
        sim = new Simulation(new RedditRanking(), numInitialUsers, numNewUsers);
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
        			.addComponent(frontPagePanel, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(simulationSettingsPanel, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        				.addComponent(userListPanel, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
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
        					.addComponent(simulationSettingsPanel, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        				.addComponent(frontPagePanel, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
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
        
        spnInitialUsers = new JSpinner();
        spnInitialUsers.setModel(new SpinnerNumberModel(500, 0, 20000, 1));
        lblNumberOfInitial.setLabelFor(spnInitialUsers);
        
        JLabel lblNumberOfNew = new JLabel("Number of new users");
        
        spnNewUsers = new JSpinner();
        spnNewUsers.setModel(new SpinnerNumberModel(1000, 0, 100000, 1));
        lblNumberOfNew.setLabelFor(spnNewUsers);
        GroupLayout gl_simSettingsPanel = new GroupLayout(simSettingsPanel);
        gl_simSettingsPanel.setHorizontalGroup(
            gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, gl_simSettingsPanel.createSequentialGroup()
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNumberOfInitial)
                        .addComponent(lblNumberOfNew))
                    .addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(spnNewUsers)
                        .addComponent(spnInitialUsers, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
        );
        gl_simSettingsPanel.setVerticalGroup(
            gl_simSettingsPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_simSettingsPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberOfInitial)
                        .addComponent(spnInitialUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_simSettingsPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNumberOfNew)
                        .addComponent(spnNewUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(139, Short.MAX_VALUE))
        );
        simSettingsPanel.setLayout(gl_simSettingsPanel);
        simulationSettingsPanel.setLayout(gl_simulationSettingsPanel);
        
        JList userList = new JList();
        
        JLabel lblInitialUserSatisfaction = new JLabel("Initial user satisfaction");
        
        txtSatisfaction = new JTextField();
        txtSatisfaction.setEditable(false);
        txtSatisfaction.setColumns(10);
        GroupLayout gl_userListPanel = new GroupLayout(userListPanel);
        gl_userListPanel.setHorizontalGroup(
        	gl_userListPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_userListPanel.createSequentialGroup()
        			.addGroup(gl_userListPanel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_userListPanel.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblInitialUserSatisfaction))
        				.addGroup(gl_userListPanel.createSequentialGroup()
        					.addGap(186)
        					.addGroup(gl_userListPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtSatisfaction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(userList))))
        			.addContainerGap(53, Short.MAX_VALUE))
        );
        gl_userListPanel.setVerticalGroup(
        	gl_userListPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_userListPanel.createSequentialGroup()
        			.addGap(5)
        			.addComponent(userList)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_userListPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblInitialUserSatisfaction)
        				.addComponent(txtSatisfaction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(236, Short.MAX_VALUE))
        );
        userListPanel.setLayout(gl_userListPanel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        final JList<String> itemList = new JList<>();
        scrollPane.setViewportView(itemList);
        itemList.setVisibleRowCount(80);
//        itemList.setBounds(228, 7, 0, 0);
        itemList.setModel(getKingsFrontPageModel());
        GroupLayout gl_frontPagePanel = new GroupLayout(frontPagePanel);
        gl_frontPagePanel.setHorizontalGroup(
        	gl_frontPagePanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_frontPagePanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_frontPagePanel.setVerticalGroup(
        	gl_frontPagePanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_frontPagePanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        			.addContainerGap())
        );
        frontPagePanel.setLayout(gl_frontPagePanel);
        groupLayout.setAutoCreateGaps(true);
        frame.getContentPane().setLayout(groupLayout);
        
        btnRun.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dur = (Integer) simDurationSpinner.getValue();
                sim.run(dur);
                itemList.setModel(getKingsFrontPageModel());
                double satisfaction = 0;
                double[] fitness = sim.judgeFitnessOfFrontPage("kings");
                for (int i = 0; i < fitness.length; i++) {
                	satisfaction += fitness[i];
                }
                satisfaction /= fitness.length;
                txtSatisfaction.setText(String.format("%0$.2f", satisfaction));
            }
        });
        
        btnInitialize.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		int numInitialUsers = (Integer) spnInitialUsers.getValue();
        		int numNewUsers = (Integer) spnNewUsers.getValue();
        		initSimulation(numInitialUsers, numNewUsers);
        	}
		});
    }

    private ItemListModel getKingsFrontPageModel() {
        return new ItemListModel(sim, "kings");
    }
}

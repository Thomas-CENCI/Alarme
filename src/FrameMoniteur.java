import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameMoniteur extends JFrame {
    private Moniteur moniteurA;
    private Moniteur moniteurB;

    public FrameMoniteur(Moniteur moniteurA, Moniteur moniteurB) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moniteurA = moniteurA;
        this.moniteurB = moniteurB;
    }

    public void refresh() {
        FrameMoniteur.this.getContentPane().removeAll();
        FrameMoniteur.this.repaint();

        FrameMoniteur.this.display_moniteur(FrameMoniteur.this.moniteurA, FrameMoniteur.this.moniteurB);
    }

public void display_moniteur(Moniteur moniteurA, Moniteur moniteurB){

        Font police = new Font("Tahoma", Font.BOLD, 18);

        this.setTitle("Moniteurs");

        JOptionPane detail = new JOptionPane();
        JOptionPane confirmation = new JOptionPane();

        JSplitPane content = new JSplitPane(JSplitPane.VERTICAL_SPLIT){
            private final int location = 400;
            {
                setDividerLocation( location );
            }
            @Override
            public int getDividerLocation() {
                return location ;
            }
            @Override
            public int getLastDividerLocation() {
                return location ;
            }
        };
        content.setResizeWeight(0.5);

        JSplitPane splitContentG = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
            private final int location = 775;
            {
                setDividerLocation( location );
            }
            @Override
            public int getDividerLocation() {
                return location ;
            }
            @Override
            public int getLastDividerLocation() {
                return location ;
            }
        };
        splitContentG.setResizeWeight(0.43);
        content.setLeftComponent(splitContentG);

        JPanel left_panel_A = new JPanel();
        JPanel right_panel_A = new JPanel();
        JPanel left_panel_B = new JPanel();
        JPanel right_panel_B = new JPanel();
        JPanel button_panel = new JPanel();

        ////*******LEFT TOP PANEL********
        left_panel_A.setBackground(Color.DARK_GRAY);
        left_panel_A.setLayout(new GridBagLayout());

        GridBagConstraints left_gbc_A = new GridBagConstraints();
        left_gbc_A.anchor = GridBagConstraints.NORTH;
        left_gbc_A.weightx = 0.1;
        left_gbc_A.weighty = 0.1;
        left_gbc_A.gridx = 0;
        left_gbc_A.gridy = 0;

        JLabel left_title_A = new JLabel("Alarmes reçues et non traitées (A)");
        left_title_A.setFont(police);
        left_title_A.setForeground(Color.WHITE);
        left_panel_A.add(left_title_A, left_gbc_A);

        String [] left_header_A = {"Date", "Localisation", "Type", "Détail"};
        String [][] left_data_A = {};

        DefaultTableModel left_model_A = new DefaultTableModel(left_data_A,left_header_A);
        JTable left_table_A = new JTable(left_model_A);

        left_table_A.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if(!event.getValueIsAdjusting()) {
                    JFrame frame_detail = new JFrame();

                    frame_detail.setSize(500, 250);
                    frame_detail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame_detail.setLocationRelativeTo(null);
                    frame_detail.setTitle("Détail alarme");
                    frame_detail.setLayout(new BorderLayout());

                    String detail_date = left_table_A.getValueAt(left_table_A.getSelectedRow(), 0).toString();
                    String detail_location = left_table_A.getValueAt(left_table_A.getSelectedRow(), 1).toString();
                    String detail_type = left_table_A.getValueAt(left_table_A.getSelectedRow(), 2).toString();
                    String detail_detail = left_table_A.getValueAt(left_table_A.getSelectedRow(), 3).toString();

                    Alarme selected_alarme = moniteurA.getSelectedAlarme(detail_date, detail_location, detail_type, detail_detail);

                    JPanel detail_panel = new JPanel();
                    detail_panel.setBackground(Color.DARK_GRAY);

                    String html_label = "<html> <br>Date : " + selected_alarme.getDate() + "<br>Type : " + selected_alarme.getType() + "<br>Lieu : " + selected_alarme.getLocation() + "<br>Niveau d'importance : " + selected_alarme.getDefcon() + "<br>Détail : " + selected_alarme.getDetail();
                    JLabel detail_label = new JLabel(html_label);
                    detail_label.setFont(police);
                    detail_label.setForeground(Color.WHITE);

                    detail_panel.add(detail_label);

                    JPanel panel_detail_button = new JPanel();
                    panel_detail_button.setBackground(Color.DARK_GRAY);
                    JButton traiter_button = new JButton("Traiter");
                    traiter_button.setSize(20, 10);

                    traiter_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selected_alarme.setStatus(true);
                            FrameMoniteur.this.refresh();
                            frame_detail.setVisible(false);
                            frame_detail.dispose();
                        }
                    });

                    panel_detail_button.add(traiter_button);

                    frame_detail.getContentPane().add(detail_panel, BorderLayout.NORTH);
                    frame_detail.getContentPane().add(panel_detail_button, BorderLayout.CENTER);
                    frame_detail.setVisible(true);
                    frame_detail.toFront();
                }
            }
        });

        left_table_A.setPreferredScrollableViewportSize(new Dimension(450,63));
        left_table_A.setFillsViewportHeight(true);

        JScrollPane left_js_A = new JScrollPane(left_table_A);
        left_js_A.setVisible(true);

        for (Alarme alarme : moniteurA.getAlarmesRecues()){
            left_model_A.addRow(new Object[]{alarme.getDate(), alarme.getLocation(), alarme.getType(), alarme.getDetail()} );
        }

        left_gbc_A.gridy = 1;
        left_panel_A.add(left_js_A, left_gbc_A);

        splitContentG.setLeftComponent(left_panel_A);

        //*********RIGHT TOP PANEL**********
        right_panel_A.setBackground(Color.DARK_GRAY);
        right_panel_A.setLayout(new GridBagLayout());

        GridBagConstraints right_gbc_A = new GridBagConstraints();
        right_gbc_A.anchor = GridBagConstraints.NORTH;
        right_gbc_A.weightx = 0.1;
        right_gbc_A.weighty = 0.1;
        right_gbc_A.gridx = 0;
        right_gbc_A.gridy = 0;

        JLabel right_title_A = new JLabel("Alarmes traitées (A)");
        right_title_A.setForeground(Color.WHITE);
        right_title_A.setFont(police);
        right_panel_A.add(right_title_A, right_gbc_A);

        String [] right_header_A = {"Date", "Localisation", "Type", "Détail"};
        String [][] right_data_A = {};

        DefaultTableModel right_model_A = new DefaultTableModel(right_data_A,right_header_A);
        JTable right_table_A = new JTable(right_model_A);
        right_table_A.setPreferredScrollableViewportSize(new Dimension(450,63));
        right_table_A.setFillsViewportHeight(true);

        JScrollPane right_js_A = new JScrollPane(right_table_A);
        right_js_A.setVisible(true);

        for (Alarme alarme : moniteurA.getAlarmesTraitees()){
            right_model_A.addRow(new Object[]{alarme.getDate(), alarme.getLocation(), alarme.getType(), alarme.getDetail()} );
        }

        right_gbc_A.gridy = 1;
        right_panel_A.add(right_js_A, right_gbc_A);

        splitContentG.setRightComponent(right_panel_A);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JSplitPane splitContentD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
            private final int location = 775;
            {
                setDividerLocation( location );
            }
            @Override
            public int getDividerLocation() {
                return location ;
            }
            @Override
            public int getLastDividerLocation() {
                return location ;
            }
        };
        splitContentD.setResizeWeight(0.43);
        content.setRightComponent(splitContentD);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //********LEFT BOTTOM PANEL*********
        left_panel_B.setBackground(Color.DARK_GRAY);
        left_panel_B.setLayout(new GridBagLayout());

        JLabel left_title_B = new JLabel("Alarmes reçues et non traitées (B)");
        left_title_B.setForeground(Color.WHITE);
        left_title_B.setFont(police);

        GridBagConstraints left_gbc_B = new GridBagConstraints();
        left_gbc_B.anchor = GridBagConstraints.NORTH;
        left_gbc_B.weightx = 0.1;
        left_gbc_B.weighty = 0.1;
        left_gbc_B.gridx = 0;
        left_gbc_B.gridy = 0;

        left_panel_B.add(left_title_B, left_gbc_B);

        String [] left_header_B = {"Date", "Localisation", "Type", "Détail"};
        String [][] left_data_B = {};

        DefaultTableModel left_model_B = new DefaultTableModel(left_data_B,left_header_B);
        JTable left_table_B = new JTable(left_model_B);

        left_panel_B.add(left_title_B, left_gbc_B);

        left_table_B.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if(!event.getValueIsAdjusting()) {

                    JFrame frame_detail = new JFrame();

                    frame_detail.setSize(500, 250);
                    frame_detail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame_detail.setLocationRelativeTo(null);
                    frame_detail.setTitle("Détail alarme");
                    frame_detail.setLayout(new BorderLayout());

                    String detail_date = left_table_B.getValueAt(left_table_B.getSelectedRow(), 0).toString();
                    String detail_location = left_table_B.getValueAt(left_table_B.getSelectedRow(), 1).toString();
                    String detail_type = left_table_B.getValueAt(left_table_B.getSelectedRow(), 2).toString();
                    String detail_detail = left_table_B.getValueAt(left_table_B.getSelectedRow(), 3).toString();

                    Alarme selected_alarme = moniteurB.getSelectedAlarme(detail_date, detail_location, detail_type, detail_detail);

                    JPanel detail_panel = new JPanel();
                    detail_panel.setBackground(Color.DARK_GRAY);

                    String html_label = "<html> <br>Date : " + selected_alarme.getDate() + "<br>Type : " + selected_alarme.getType() + "<br>Lieu : " + selected_alarme.getLocation() + "<br>Niveau d'importance : " + selected_alarme.getDefcon() + "<br>Détail : " + selected_alarme.getDetail();
                    JLabel detail_label = new JLabel(html_label);
                    detail_label.setFont(police);
                    detail_label.setForeground(Color.WHITE);

                    detail_panel.add(detail_label);

                    JPanel panel_detail_button = new JPanel();
                    panel_detail_button.setBackground(Color.DARK_GRAY);
                    JButton traiter_button = new JButton("Traiter");
                    traiter_button.setSize(20, 10);

                    traiter_button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selected_alarme.setStatus(true);
                            FrameMoniteur.this.refresh();
                            frame_detail.setVisible(false);
                            frame_detail.dispose();
                        }
                    });

                    panel_detail_button.add(traiter_button);

                    frame_detail.getContentPane().add(detail_panel, BorderLayout.NORTH);
                    frame_detail.getContentPane().add(panel_detail_button, BorderLayout.CENTER);
                    frame_detail.setVisible(true);
                    frame_detail.toFront();
                }
            }
        });

        left_table_B.setPreferredScrollableViewportSize(new Dimension(450,63));
        left_table_B.setFillsViewportHeight(true);

        JScrollPane left_js_B = new JScrollPane(left_table_B);
        left_js_B.setVisible(true);

        for (Alarme alarme : moniteurB.getAlarmesRecues()){
            left_model_B.addRow(new Object[]{alarme.getDate(), alarme.getLocation(), alarme.getType(), alarme.getDetail()} );
        }

        left_gbc_B.gridy = 1;
        left_panel_B.add(left_js_B, left_gbc_B);

        splitContentD.setLeftComponent(left_panel_B);


        //**********RIGHT BOTTOM PANEL**********
        right_panel_B.setBackground(Color.DARK_GRAY);
        right_panel_B.setLayout(new GridBagLayout());

        JLabel right_title_B = new JLabel("Alarmes traitées (B)");
        right_title_B.setForeground(Color.WHITE);
        right_title_B.setFont(police);

        GridBagConstraints right_gbc_B = new GridBagConstraints();
        right_gbc_B.anchor = GridBagConstraints.NORTH;
        right_gbc_B.weightx = 0.1;
        right_gbc_B.weighty = 0.1;
        right_gbc_B.gridx = 0;
        right_gbc_B.gridy = 0;

        right_panel_B.add(right_title_B, right_gbc_B);

        String [] right_header_B = {"Date", "Localisation", "Type", "Détail"};
        String [][] right_data_B = {};

        DefaultTableModel right_model_B = new DefaultTableModel(right_data_B,right_header_B);
        JTable right_table_B = new JTable(right_model_B);
        right_table_B.setPreferredScrollableViewportSize(new Dimension(450,63));
        right_table_B.setFillsViewportHeight(true);

        JScrollPane right_js_B = new JScrollPane(right_table_B);
        right_js_B.setVisible(true);

        for (Alarme alarme : moniteurB.getAlarmesTraitees()){
            right_model_B.addRow(new Object[]{alarme.getDate(), alarme.getLocation(), alarme.getType(), alarme.getDetail()} );
        }

        right_gbc_B.gridy = 1;
        right_panel_B.add(right_js_B, right_gbc_B);

        splitContentD.setRightComponent(right_panel_B);


        //*****BUTTON PANEL*****
        button_panel.setBackground(Color.DARK_GRAY);

        JButton close_button = new JButton("Fermer");
        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm;
                confirm = confirmation.showConfirmDialog(null, "Fermer la fenêtre des moniteurs", "Confirmation de fermeture", JOptionPane.YES_NO_OPTION);
                if(confirm == 0) {
                    dispose();
                }
            }
        });

        button_panel.add(close_button);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}

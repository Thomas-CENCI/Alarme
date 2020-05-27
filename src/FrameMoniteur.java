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
    JPanel left_panel_A = new JPanel();
    JPanel right_panel_A = new JPanel();
    JPanel left_panel_B = new JPanel();
    JPanel right_panel_B = new JPanel();
    JPanel button_panel = new JPanel();

    public FrameMoniteur(Moniteur moniteurA, Moniteur moniteurB) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moniteurA = moniteurA;
        this.moniteurB = moniteurB;
    }

    public void refresh() {
        left_panel_A.removeAll();
        right_panel_A.removeAll();
        left_panel_B.removeAll();
        right_panel_B.removeAll();
        button_panel.removeAll();

        left_panel_A = new JPanel();
        right_panel_A = new JPanel();
        left_panel_B = new JPanel();
        right_panel_B = new JPanel();
        button_panel = new JPanel();

        FrameMoniteur.this.display_moniteur(FrameMoniteur.this.moniteurA, FrameMoniteur.this.moniteurB);
    }


public void display_moniteur(Moniteur moniteurA, Moniteur moniteurB){

        Font police = new Font("Tahoma", Font.BOLD, 18);

        this.setTitle("Moniteurs");

        JOptionPane detail = new JOptionPane();
        JOptionPane confirmation = new JOptionPane();

        JSplitPane content = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        content.setResizeWeight(0.5);

        JSplitPane splitContentG = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitContentG.setResizeWeight(0.43);
        content.setLeftComponent(splitContentG);


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

        JSplitPane splitContentD = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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

        JButton traitee_button = new JButton("Traiter");
        traitee_button.setVisible(false);
        traitee_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Mettre en place le déplacement de la zone "Anomalies reçues" vers "Anomalies traitées".
                 * Il suffit de supprimer de la liste anomalies_recues et de compléter anomalies_traitees.
                 */
                int confirm;
                confirm = confirmation.showConfirmDialog(null, "L'alarme est-elle traitée ?", "Confirmation de traitement", JOptionPane.YES_NO_OPTION);
                if(confirm == 0){
                    traitee_button.setVisible(false);
                }
            }
        });

        JButton detail_button = new JButton("Détail");
        detail_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Il faut mettre en place la fonction qui affiche tous les détails de l'élément sélectionné. Voir
                 * Frame.java dans lequel j'ai mis en place une fonction qui parcourt les éléments et qui détecte
                 * s'ils sont sélectionnés. A voir si on peut s'en inspirer.
                 */
                detail.showMessageDialog(null, "Détail de l'élément sélectionné", "Détail", JOptionPane.INFORMATION_MESSAGE);
                traitee_button.setVisible(true);
            }
        });

        button_panel.add(detail_button);
        button_panel.add(close_button);
        button_panel.add(traitee_button);

        this.getContentPane().add(content, BorderLayout.CENTER);
        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}

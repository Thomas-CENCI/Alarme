import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.*;

public class Frame extends JFrame {

    CardLayout cardLayout_content = new CardLayout();
    CardLayout cardLayout_detail = new CardLayout();
    JPanel content = new JPanel();
    JLabel label = new JLabel("Bâtiment");
    String[] listContent = {"Type d'alarme", "Niveau d'importance", "Bâtiment"};
    ArrayList<Moniteur> moniteurs = new ArrayList<Moniteur>();
    int indice = -1;
    FrameMoniteur frame_moniteur;
    ManageAlarme manage_alarme;

    public Frame(ArrayList<Moniteur> moniteurs, FrameMoniteur frame_moniteur) {
        this.setTitle("Alarme");
        this.setSize(600, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.moniteurs = moniteurs;
        this.frame_moniteur = frame_moniteur;
        this.manage_alarme = new ManageAlarme(moniteurs);
    }

    public HashMap<String, String> generate() {
        JPanel title_panel = new JPanel();
        title_panel.setBackground(Color.ORANGE);

        JPanel batiment_panel = new JPanel();
        batiment_panel.setBackground(Color.DARK_GRAY);
        JPanel main_type_panel = new JPanel();
        main_type_panel.setBackground(Color.DARK_GRAY);
        JPanel type_panel = new JPanel();
        type_panel.setBackground(Color.DARK_GRAY);
        JPanel type_detail_panel = new JPanel();
        type_detail_panel.setBackground(Color.DARK_GRAY);
        type_detail_panel.setVisible(false);
        JLabel type_label = new JLabel("Merci de préciser :");
        type_label.setForeground(Color.WHITE);
        type_detail_panel.add(type_label);
        JPanel niv_panel = new JPanel();
        niv_panel.setBackground(Color.DARK_GRAY);

        BoxLayout boxLayout = new BoxLayout(main_type_panel, BoxLayout.Y_AXIS);
        main_type_panel.setLayout(boxLayout);

        main_type_panel.add(type_panel);
        main_type_panel.add(type_detail_panel);

        JPanel button_panel = new JPanel();
        button_panel.setBackground(Color.DARK_GRAY);

        JRadioButton bat1 = new JRadioButton("Bâtiment1");
        bat1.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment1'.\n");
        });

        JRadioButton bat2 = new JRadioButton("Bâtiment2");
        bat2.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment2'.\n");
        });

        JRadioButton bat3 = new JRadioButton("Bâtiment3");
        bat3.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment3'.\n");
        });

        JRadioButton bat4 = new JRadioButton("Bâtiment4");
        bat4.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment4'.\n");
        });


        ButtonGroup group_batiment = new ButtonGroup();
        group_batiment.add(bat1);
        group_batiment.add(bat2);
        group_batiment.add(bat3);
        group_batiment.add(bat4);

        ArrayList<AbstractButton> batButtons = Collections.list(group_batiment.getElements());

        batiment_panel.add(bat1, BorderLayout.CENTER);
        batiment_panel.add(bat2, BorderLayout.CENTER);
        batiment_panel.add(bat3, BorderLayout.CENTER);
        batiment_panel.add(bat4, BorderLayout.CENTER);

        JTextField type_detail = new JTextField("Type de gaz émis");
        type_detail.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                type_detail.setText("");
            }

            public void focusLost(FocusEvent e) {
            }
        });
        type_detail.setVisible(false);
        type_detail_panel.add(type_detail, BorderLayout.SOUTH);

        JRadioButton type1 = new JRadioButton("Incendie");
        type1.addActionListener(e -> {
            label.setText("Your current choice is 'Incendie'.\n");
            type_detail_panel.setVisible(false);
            type_detail.setVisible(false);
        });

        JRadioButton type2 = new JRadioButton("Gaz");
        type2.addActionListener(e -> {
            label.setText("Your current choice is 'Gaz'.\n");
            type_detail_panel.setVisible(true);
            type_detail.setText("Type de gaz émis");
            type_label.setVisible(true);
            type_detail.setVisible(true);
        });

        JRadioButton type3 = new JRadioButton("Radiation");
        type3.addActionListener(e -> {
            label.setText("Your current choice is 'Radiation'.\n");
            type_detail_panel.setVisible(true);
            type_detail.setText("Niveau de radiation (1 - 100)");
            type_label.setVisible(true);
            type_detail.setVisible(true);
        });

        ButtonGroup group_type = new ButtonGroup();
        group_type.add(type1);
        group_type.add(type2);
        group_type.add(type3);

        ArrayList<AbstractButton> typeButtons = Collections.list(group_type.getElements());

        type_panel.add(type1, BorderLayout.CENTER);
        type_panel.add(type2, BorderLayout.CENTER);
        type_panel.add(type3, BorderLayout.CENTER);

        JRadioButton niv1 = new JRadioButton("1");
        niv1.addActionListener(e -> {
            label.setText("Your current choice is '1'.\n");
        });

        JRadioButton niv2 = new JRadioButton("2");
        niv2.addActionListener(e -> {
            label.setText("Your current choice is '2'.\n");
        });

        JRadioButton niv3 = new JRadioButton("3");
        niv3.addActionListener(e -> {
            label.setText("Your current choice is '3'.\n");
        });

        ButtonGroup group_niv = new ButtonGroup();
        group_niv.add(niv1);
        group_niv.add(niv2);
        group_niv.add(niv3);

        ArrayList<AbstractButton> nivButtons = Collections.list(group_niv.getElements());

        niv_panel.add(niv1, BorderLayout.CENTER);
        niv_panel.add(niv2, BorderLayout.CENTER);
        niv_panel.add(niv3, BorderLayout.CENTER);

        Font police = new Font("Tahoma", Font.BOLD, 16);
        label.setFont(police);
        label.setForeground(Color.DARK_GRAY);

        JButton button = new JButton("Suivant");
        JOptionPane lackOfDetail = new JOptionPane();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((indice + 1) % 3 == 1) && atLeastOneIsSelected(typeButtons) && !(valueOfSelectedElement(typeButtons).equals("Incendie")) && !(isDetailedType(type_detail))) {
                    lackOfDetail.showMessageDialog(null, "Merci de renseigner les détails à propos du type d'anomalie", "Attention", JOptionPane.WARNING_MESSAGE);
                } else {
                    cardLayout_content.next(content);
                    label.setText(listContent[(indice + 1) % 3]);
                    indice += 1;
                }

            }
        });

        JButton button2 = new JButton("Valider");
        JOptionPane lackOfValues = new JOptionPane();
        HashMap<String, String> selectedValues = new HashMap<String, String>();
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (atLeastOneIsSelected(batButtons) && atLeastOneIsSelected(typeButtons) && atLeastOneIsSelected(nivButtons)) {
                    selectedValues.put("batiment", valueOfSelectedElement(batButtons).toUpperCase());
                    selectedValues.put("type_alarme", valueOfSelectedElement(typeButtons).toUpperCase());
                    selectedValues.put("niveau_importance", valueOfSelectedElement(nivButtons).toUpperCase());
                    if (isDetailedType(type_detail)) {
                        selectedValues.put("detail_type", type_detail.getText().toUpperCase());
                    }
                    else if (selectedValues.get("type_alarme").equals("INCENDIE")){
                        selectedValues.put("detail_type", "Pas de détails disponibles");
                    }
                    dispose();
                    manage_alarme.generateAlarme(selectedValues.get("batiment"), selectedValues.get("type_alarme"), Integer.parseInt(selectedValues.get("niveau_importance")), selectedValues.get("detail_type"));
                    frame_moniteur.refresh();
                } else {
                    lackOfValues.showMessageDialog(null, "Il manque des éléments dans la selection des valeurs", "Information", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton button3 = new JButton("Détails");
        JOptionPane det = new JOptionPane();
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((indice + 1) % 3 == 0) {
                    det.showMessageDialog(null, "Merci de choisir un bâtiment", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

                if ((indice + 1) % 3 == 1) {
                    det.showMessageDialog(null, "Merci de choisir le type d'alarme parmi les suivants :\n- Incendie\n- Gaz(Hydrogène, Hélium, CO2)\n- Radiation(1 -> 100)", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

                if ((indice + 1) % 3 == 2) {
                    det.showMessageDialog(null, "Merci de renseigner le niveau d'importance (de 1 à 3)", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton button4 = new JButton("Fermer");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        title_panel.add(label, BorderLayout.NORTH);
        button_panel.add(button, BorderLayout.CENTER);
        button_panel.add(button2, BorderLayout.CENTER);
        button_panel.add(button3, BorderLayout.CENTER);
        button_panel.add(button4, BorderLayout.CENTER);

        content.setLayout(cardLayout_content);

        content.add(batiment_panel, listContent[0]);
        content.add(main_type_panel, listContent[1]);
        content.add(niv_panel, listContent[2]);

        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.getContentPane().add(title_panel, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);

        return(selectedValues);
    }

    private boolean atLeastOneIsSelected(ArrayList<AbstractButton> buttonList){
        for(AbstractButton button : buttonList) {
            if (button.isSelected()){
                return true;
            }
        }
        return false;
    }

    private String valueOfSelectedElement(ArrayList<AbstractButton> buttonList){
        for(AbstractButton button : buttonList) {
            if (button.isSelected()){
                return button.getText();
            }
        }
        return null;
    }

    private boolean isDetailedType(JTextField textField){
        if(textField.getText().equals("") || textField.getText().equals("Type de gaz émis") || textField.getText().equals("Niveau de radiation (1 - 100)")){
            return false;
        }
    return true;
    }
}
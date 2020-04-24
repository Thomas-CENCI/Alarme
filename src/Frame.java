import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.ImageIcon;

public class Frame extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel content = new JPanel();
    JLabel label = new JLabel("Bâtiment");
    String[] listContent = {"Type d'alarme", "Niveau d'importance", "Bâtiment"};
    int indice = -1;

    public Frame() {
        this.setTitle("Alarme");
        this.setSize(600, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel title_panel = new JPanel();
        title_panel.setBackground(Color.ORANGE);

        JPanel batiment_panel = new JPanel();
        batiment_panel.setBackground(Color.DARK_GRAY);
        JPanel type_panel = new JPanel();
        type_panel.setBackground(Color.DARK_GRAY);
        JPanel niv_panel = new JPanel();
        niv_panel.setBackground(Color.DARK_GRAY);

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

        JRadioButton type1 = new JRadioButton("Incendie");
        type1.addActionListener(e -> {
            label.setText("Your current choice is 'Incendie'.\n");
        });

        JRadioButton type2 = new JRadioButton("Gaz");
        type2.addActionListener(e -> {
            label.setText("Your current choice is 'Gaz'.\n");
        });

        JRadioButton type3 = new JRadioButton("Radiation");
        type3.addActionListener(e -> {
            label.setText("Your current choice is 'Radiation'.\n");
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(content);
                label.setText(listContent[(indice + 1) % 3]);
                indice += 1;

            }
        });

        JButton button2 = new JButton("Valider");
        ArrayList<String> selectedValues = new ArrayList<String>();
        JOptionPane lackOfValues = new JOptionPane();
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(atLeastOneIsSelected(batButtons) && atLeastOneIsSelected(typeButtons) && atLeastOneIsSelected(nivButtons)) {
                    selectedValues.add(valueOfSelectedElement(batButtons).toUpperCase());
                    selectedValues.add(valueOfSelectedElement(typeButtons).toUpperCase());
                    selectedValues.add(valueOfSelectedElement(nivButtons).toUpperCase());
                    dispose();
                    System.out.println(selectedValues); /** Au lieu de print les valeurs il faudrait appeler une fonction qui génère l'anomalie
                                                            avec autant de détail que ce qui est sélectionné (Voir Moniteur.generateAnomalie). */
                }
                else{
                    lackOfValues.showMessageDialog(null, "Il manque des éléments dans la séléction des valeurs", "Information", JOptionPane.ERROR_MESSAGE);
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
                    det.showMessageDialog(null, "Merci de choisir le type d'alarme parmi les suivants :\n- Incendie\n- Gaz\n- Radiation", "Information", JOptionPane.INFORMATION_MESSAGE);
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


        content.setLayout(cardLayout);

        content.add(batiment_panel, listContent[0]);
        content.add(type_panel, listContent[1]);
        content.add(niv_panel, listContent[2]);


        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.getContentPane().add(title_panel, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);
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
}
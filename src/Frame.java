import java.awt.*;
import java.awt.event.*;
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
        this.setSize(1200, 700);
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

        JRadioButton bat1 = new JRadioButton("Bâtiment 1");
        bat1.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment 1'.\n");
        });

        JRadioButton bat2 = new JRadioButton("Bâtiment 2");
        bat2.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment 2'.\n");
        });

        JRadioButton bat3 = new JRadioButton("Bâtiment 3");
        bat3.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment 3'.\n");
        });

        JRadioButton bat4 = new JRadioButton("Bâtiment 4");
        bat4.addActionListener(e -> {
            label.setText("Your current choice is 'Bâtiment 4'.\n");
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Bâtiment");
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(bat1);
        group.add(bat2);
        group.add(bat3);
        group.add(bat4);

        batiment_panel.add(bat1, BorderLayout.CENTER);
        batiment_panel.add(bat2, BorderLayout.CENTER);
        batiment_panel.add(bat3, BorderLayout.CENTER);
        batiment_panel.add(bat4, BorderLayout.CENTER);
        batiment_panel.add(reset, BorderLayout.SOUTH);

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
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Il faut pouvoir valider les choix réalisés et lancer le programme
                dispose();
            }
        });

        JButton button3 = new JButton("Détails");
        JOptionPane jop1 = new JOptionPane();
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((indice + 1) % 3 == 0) {
                    jop1.showMessageDialog(null, "Merci de choisir un bâtiment", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

                if ((indice + 1) % 3 == 1) {
                    jop1.showMessageDialog(null, "Merci de choisir le type d'alarme parmi les suivants :\n- Incendie\n- Gaz\n- Radiation", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

                if ((indice + 1) % 3 == 2) {
                    jop1.showMessageDialog(null, "Merci de renseigner le niveau d'importance (de 1 à 3)", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        title_panel.add(label, BorderLayout.NORTH);
        button_panel.add(button, BorderLayout.CENTER);
        button_panel.add(button2, BorderLayout.CENTER);
        button_panel.add(button3, BorderLayout.CENTER);

        content.setLayout(cardLayout);

        content.add(batiment_panel, listContent[0]);
        content.add(type_panel, listContent[1]);
        content.add(niv_panel, listContent[2]);


        this.getContentPane().add(button_panel, BorderLayout.SOUTH);
        this.getContentPane().add(title_panel, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
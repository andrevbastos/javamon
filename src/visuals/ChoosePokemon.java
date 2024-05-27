package visuals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChoosePokemon extends JPanel {

    private JButton charmanderButton;
    private JButton squirtleButton;
    private JButton bulbasaurButton;
    private String selectedPokemon;

    public ChoosePokemon() {
        setLayout(new GridLayout(1, 2));

        try {
            charmanderButton = new JButton(new ImageIcon("res/pokemon/charizard_front.png"));
            squirtleButton = new JButton(new ImageIcon("res/pokemon/charizard_front.png"));
            bulbasaurButton = new JButton(new ImageIcon("res/pokemon/charizard_front.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        charmanderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon = "CHARMANDER";
            }
        });

        squirtleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon = "SQUIRTLE";
            }
        });

        bulbasaurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPokemon = "BULBASAUR";
            }
        });

        add(charmanderButton);
        add(squirtleButton);
        add(bulbasaurButton);
        
    }

    public String getSelectedSkin() {
        return selectedPokemon;
    }

}

package visuals.screens;

import visuals.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Start {

    private GamePanel gp;
    private JButton okButton;
    private JTextField repetitionsField;
    private JToggleButton charmanderButton;
    private JToggleButton squirtleButton;
    private JToggleButton bulbasaurButton;
    private ArrayList<String> selectedPokemons;

    public Start(GamePanel gp) {
        
        this.gp = gp;
        this.selectedPokemons = new ArrayList<String>();

        createButtons();        
        
    }

    public void createButtons() {

        try {
            charmanderButton = new JToggleButton(new ImageIcon("res/pokemon/charmander.png"));
            charmanderButton.setName("CHARMANDER");
            squirtleButton = new JToggleButton(new ImageIcon("res/pokemon/squirtle.png"));
            squirtleButton.setName("SQUIRTLE");
            bulbasaurButton = new JToggleButton(new ImageIcon("res/pokemon/bulbasaur.png"));
            bulbasaurButton.setName("BULBASAUR");
            okButton = new JButton("OK");
        } catch (Exception e) {
            e.printStackTrace();
        }

        addSelectionAction(charmanderButton);
        addSelectionAction(squirtleButton);
        addSelectionAction(bulbasaurButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

    }

    public void addSelectionAction(JToggleButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedPokemons.contains(b.getName())) {
                    selectedPokemons.remove(b.getName());
                } else {
                    selectedPokemons.add(b.getName());
                }
            }
        });
    }

    public void runStartSequence() {
        gp.add(charmanderButton);
        gp.add(squirtleButton);
        gp.add(bulbasaurButton);
    }

}
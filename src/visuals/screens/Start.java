package visuals.screens;

import visuals.*;

import java.awt.event.*;
import javax.swing.*;

public class Start {

    private GamePanel gp;
    private JButton charmanderButton;
    private JButton squirtleButton;
    private JButton bulbasaurButton;
    private int buttonWidth = 150;
    private int buttonHeight = 150;


    public Start(GamePanel gp) {
        
        this.gp = gp;
        int numButtons = 3; // Número de botões
        int buttonSpacing = 20; // Espaçamento entre os botões
        int totalButtonWidth = numButtons * buttonWidth + (numButtons - 1) * buttonSpacing;
        int buttonX = (gp.getPrefWidth() - totalButtonWidth) / 2;
        int buttonY = (gp.getPrefHeight() - 200) / 2;
        
        try {
            charmanderButton = new JButton(new ImageIcon("res/pokemon/charmander.png"));
            squirtleButton = new JButton(new ImageIcon("res/pokemon/squirtle.png"));
            bulbasaurButton = new JButton(new ImageIcon("res/pokemon/bulbasaur.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        charmanderButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        charmanderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        squirtleButton.setBounds(buttonX + buttonWidth + buttonSpacing, buttonY, buttonWidth, buttonHeight);
        squirtleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        bulbasaurButton.setBounds(buttonX + 2 * (buttonWidth + buttonSpacing), buttonY, buttonWidth, buttonHeight);
        bulbasaurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
    }

    public void runStartSequence() {
        gp.add(charmanderButton);
        gp.add(squirtleButton);
        gp.add(bulbasaurButton);
    }

}
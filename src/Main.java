/**
 * @file Main.java
 * @brief Application entry point
 * 
 * @see Javamon
 * 
 *  * @mainpage Javamon Pokémon Battle Simulator
 * 
 * @section introduction Introduction
 * Javamon is an interactive Pokémon battle simulator featuring:
 * - All Eevee evolutions with game accurate stats
 * - Turn-based battle system
 * - Detailed battle logging
 * 
 * @section features Features
 * - Graphical interface for Pokémon selection
 * - Tournament-style battle system
 * - Statistical tracking
 * - Battle replay functionality
 * 
 * @section design_patterns Design Patterns
 * | Pattern        | Application Area              |
 * |----------------|-------------------------------|
 * | Singleton      | Unique application instance   |
 * | Factory        | Pokémon/Move/Ability creation |
 * | Strategy       | Ability implementations       |
 * | Observer       | Battle event handling         |
 * | Composite      | UI flow control               |
 * | State          | UI flow control               |
 * 
 * @section installation Installation
 *   1. Ensure Java 11+ JDK is installed on your system.
 *   2. Clone repository:
 *      git clone https://github.com/zezevitor/javamon.git
 *   3. Compile and run:
 *      cd javamon &&
 *      mkdir bin &&
 *      javac -d bin --source-path src $(find src -name "*.java") &&
 *      java -cp bin Main
 * 
 * @section controls Controls
 * - [1-9] Select Pokémon
 * - [ENTER] Start battle
 * - [R] Reset simulation
 * - [ESC] Exit application
 * 
 * @author André Vitor Bastos de Macêdo
 * @contact
 * GitHub: [andrevbastos](https://github.com/andrevbastos)
 * GitLab: [andrevbastos](https://gitlab.com/andrevbastos)
 * 
 * @version 2.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Javamon.init();
    }
}
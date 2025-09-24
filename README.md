# Javamon

Also check out dev branch! Transitioning to use Spring de PostgreSQL!

Javamon is an interactive Pokémon battle simulator featuring:
 - All Eevee evolutions with game accurate stats, moves and abilities
 - Turn-based battle system
 - Detailed battle logging

## Installation

1. Ensure Java 11+ JDK is installed
2. Clone repository:
```bash
git clone https://github.com/zezevitor/javamon.git
```
3. Compile and run:
```bash
cd javamon &&
mkdir bin &&
javac -d bin --source-path src $(find src -name "*.java") &&
java -cp bin Main
```
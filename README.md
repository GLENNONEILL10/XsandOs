# XsandOs
X and O (Tic Tac Toe) Game
A Java implementation of the classic Tic Tac Toe game, featuring both two-player mode and single-player mode against an AI opponent.
Originally developed as a learning project to practice Java fundamentals, object-oriented programming, and basic AI logic.

🎮 Features
Two-player mode – Play locally with a friend in the console or GUI.

Single-player mode with AI:

AI selects moves based on available spaces.

Demonstrates basic game decision-making.

Console-based gameplay:

Numbered grid for selecting moves.

Clear board updates after each turn.

(Optional) GUI mode using Java Swing:

Clickable buttons for moves.

Visual board updates.

Simple and responsive interface.

🛠️ Technologies Used
Java (JDK 8+)

Object-Oriented Programming (OOP) principles

Java Swing (for GUI mode)

Git & GitHub for version control

📂 Project Structure
bash
Copy
Edit
src/
 ├── Board.java       # Handles the game board and move logic
 ├── AI.java          # Basic AI for single-player mode
 ├── Game.java        # Main game loop (console mode)
 ├── Menu.java        # Handles menu prompts
 ├── TicTacToeGUI.java# Swing-based GUI version
 └── ...
▶️ How to Run
Console Mode
Clone the repository:

bash
Copy
Edit
git clone https://github.com/YourUsername/XsandOs.git
cd XsandOs
Compile and run:

bash
Copy
Edit
javac src/*.java
java src.Game
GUI Mode
Compile and run:

bash
Copy
Edit
javac src/*.java
java src.TicTacToeGUI
🧠 AI Logic (Basic)
AI randomly selects from available spaces on the board.

Valid move checking ensures no overwriting existing moves.

Can be extended for:

Defensive play (blocking opponent wins)

Offensive play (winning when possible)

Minimax algorithm for unbeatable AI.

📸 Screenshots
Console Mode Example:

markdown
Copy
Edit
1  | 2  | 3
------------
4  | 5  | 6
------------
7 | 8  | 9

X  | O  | X
------------
O  | X  |  
------------
     |    | O

   
GUI Mode Example:







<img width="549" height="647" alt="Screenshot 2025-08-13 003423" src="https://github.com/user-attachments/assets/b27e84ce-c5b7-4550-8198-9c25e29d7944" />



<img width="530" height="630" alt="Screenshot 2025-08-13 003446" src="https://github.com/user-attachments/assets/86148a88-f57b-4f03-b28a-22c135ed51b0" />


🚀 Future Improvements
Advanced AI (Minimax algorithm with difficulty levels)

Score tracking for multiple rounds

Online multiplayer via sockets

Custom board sizes (e.g., 4x4, 5x5)

📜 License
This project is licensed under the MIT License – feel free to use and modify it for learning purposes.

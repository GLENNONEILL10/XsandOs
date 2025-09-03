XsandOs ❌⭕

A Java implementation of the classic Tic Tac Toe game, featuring both two-player mode and single-player mode against a simple AI opponent.

Originally developed as a learning project to practice Java fundamentals, object-oriented programming, and basic AI logic.






✨ Features

Two-player mode – Play locally with a friend in console or GUI.

Single-player AI mode – AI selects moves from available spaces, demonstrating simple decision-making.

Console mode – Numbered grid input, with board updates after each turn.

GUI mode (Swing) – Clickable buttons, visual board updates, simple and responsive design.

📦 Technologies

Java (JDK 8+)

Object-Oriented Programming (OOP) principles

Java Swing (for GUI mode)

Git & GitHub for version control

📂 Project Structure
src/
 ├── Board.java        # Handles game board state and moves
 ├── AI.java           # Basic AI for single-player mode
 ├── Game.java         # Console mode main loop
 ├── Menu.java         # Menu prompts
 ├── TicTacToeGUI.java # Swing-based GUI version
 └── ...

🚀 How to Run
1) Clone the repo
git clone https://github.com/GLENNONEILL10/XsandOs.git
cd XsandOs

2) Compile
javac -d out src/*.java

3) Run

Console mode (main class: Game):

java -cp out Game


GUI mode (main class: TicTacToeGUI):

java -cp out TicTacToeGUI

🧠 AI Logic (Basic)

Randomly selects from available spaces on the board.

Valid move checking ensures no overwriting existing moves.

Can be extended to:

Block opponent wins (defensive)

Prioritize winning moves (offensive)

Use the Minimax algorithm for an unbeatable AI.

📸 Screenshots

Console Mode Example:

1  | 2  | 3
------------
4  | 5  | 6
------------
7  | 8  | 9

X  | O  | X
------------
O  | X  |  
------------
   |    | O


GUI Mode Example:

<img width="549" height="647" alt="Screenshot 2025-08-13 003423" src="https://github.com/user-attachments/assets/b27e84ce-c5b7-4550-8198-9c25e29d7944" /> <img width="530" height="630" alt="Screenshot 2025-08-13 003446" src="https://github.com/user-attachments/assets/86148a88-f57b-4f03-b28a-22c135ed51b0" />



🧾 What I Learned

Practiced object-oriented design (Board, AI, Game separation).

Implemented a simple AI opponent and understood how to extend it.

Built a GUI with Swing and event-driven logic.

Learned how to structure and document a small Java project for clarity.

🛣️ Future Improvements

Smarter AI (Minimax algorithm with difficulty levels).

Score tracking across multiple rounds.

Online multiplayer via sockets.

Custom board sizes (e.g., 4×4, 5×5).

📄 License

This project is licensed under the MIT License
.

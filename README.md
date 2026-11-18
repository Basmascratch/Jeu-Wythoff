# Wythoff Game 🎮

University project carried out as part of a **SAÉ (Situation d'Apprentissage et d'Évaluation) in 1st year of BUT Informatique** (French Bachelor of Technology in Computer Science).

## 📌 Project Description
This project is a complete implementation in **Java** of the combinatorial strategy game known as **Wythoff's game**. 
The game takes place on a square grid of customizable size (between 4 and 16). A pawn is placed randomly on the board. Players take turns moving the pawn downwards, to the left, or diagonally. The player who successfully moves the pawn to the final winning position `(0,0)` wins the game.

## ✨ Key Features
* **Two Game Modes:** 
  * **Duo Mode** (Player vs Player).
  * **Solo Mode** (Player vs Computer).
* **Built-in Artificial Intelligence:** In solo mode, the computer calculates winning positions (using a symmetry-based algorithm) to make optimal strategic moves, or plays randomly if no direct winning move is available.
* **Dynamic Console Display:** Visual representation of the board with the pawn (`o`) and, in solo mode, optional display of winning strategic positions (`x`).
* **Integrated Unit Tests:** Automatic validation of movements via built-in test functions.

## 🛠️ Technologies Used
* **Language:** Java

## 🚀 How to Run the Project

1. Make sure you have installed the **Java JDK** and the `SimpleInput` class required by your project on your machine.
2. Download the file `Wythoff_SAHRAOUI.java`.
3. Open a terminal in the folder containing the file and compile it:
   ```bash
   javac Wythoff_SAHRAOUI.java

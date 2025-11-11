import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q1 extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton a, b, c, d;
    JButton submitBtn, nextBtn;
    ButtonGroup group;

    int index = 0;
    int score = 0; // ✅ count correct answers

    // QUESTIONS, OPTIONS & ANSWERS
    String[][] data = {
            {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "C"},
            {"Which planet is known as the Red Planet?", "Venus", "Jupiter", "Mars", "Saturn", "C"},
            {"Who wrote the national anthem of India?", "Mahatma Gandhi", "Rabindranath Tagore", "Bankim Chandra Chatterjee", "Subhas Chandra Bose", "B"},
            {"What is the largest ocean in the world?", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", "D"},
            {"Who is known as the 'Father of the Nation' in India?", "Jawaharlal Nehru", "Sardar Patel", "Mahatma Gandhi", "B. R. Ambedkar", "C"},
            {"How many continents are there on Earth?", "5", "6", "7", "8", "C"},
            {"Which gas do plants absorb during photosynthesis?", "Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen", "C"},
            {"What is the national currency of Japan?", "Yen", "Won", "Dollar", "Peso", "A"},
            {"Who was the first person to step on the Moon?", "Yuri Gagarin", "Buzz Aldrin", "Neil Armstrong", "Michael Collins", "C"},
            {"Which is the smallest planet in our solar system?", "Mercury", "Mars", "Venus", "Earth", "A"}
    };

    Q1() {
        setTitle("GK Quiz");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        a = new JRadioButton();
        b = new JRadioButton();
        c = new JRadioButton();
        d = new JRadioButton();

        group = new ButtonGroup();
        group.add(a);
        group.add(b);
        group.add(c);
        group.add(d);

        submitBtn = new JButton("Submit");
        nextBtn = new JButton("Next Question");

        submitBtn.addActionListener(this);
        nextBtn.addActionListener(e -> loadNext());

        nextBtn.setVisible(false);

        add(questionLabel);
        add(a);
        add(b);
        add(c);
        add(d);
        add(submitBtn);
        add(nextBtn);

        loadQuestion();
    }

    void loadQuestion() {
        questionLabel.setText((index + 1) + ". " + data[index][0]);
        a.setText("A) " + data[index][1]);
        b.setText("B) " + data[index][2]);
        c.setText("C) " + data[index][3]);
        d.setText("D) " + data[index][4]);

        group.clearSelection();
        submitBtn.setEnabled(true);
        nextBtn.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        String selected = "";
        if (a.isSelected()) selected = "A";
        else if (b.isSelected()) selected = "B";
        else if (c.isSelected()) selected = "C";
        else if (d.isSelected()) selected = "D";

        if (selected.equals("")) {
            JOptionPane.showMessageDialog(this, "Please select an answer!");
            return;
        }

        if (selected.equals(data[index][5])) {
            score++; // ✅ increase score when correct
            JOptionPane.showMessageDialog(this, "✅ Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Incorrect! Correct answer: " + data[index][5]);
        }

        submitBtn.setEnabled(false);
        nextBtn.setVisible(true);
    }

    void loadNext() {
        index++;
        if (index == data.length) {
            JOptionPane.showMessageDialog(this,
                    "🎉 Quiz Completed!\n✅ You scored: " + score + " out of " + data.length);
            System.exit(0);
        } else {
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        new Q1().setVisible(true);
    }
}

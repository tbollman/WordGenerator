import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class App{
    private static Activity activity = new Activity();
    private static JFrame frame;
    private static Character c = null;

    public static void main(String[] args) {
        new App();
    }

    public static List<String> getAdjectives() {
        List<String> results = new LinkedList<>();
        if (c == ' ') {
            results = activity.handleRequest('/');
        } else {
            results = activity.handleRequest(c);
        }
        return results;
    }

    public static String getRandom() {
        c = ' ';
        List<String> adjectives = getAdjectives();
        Random r = new Random();
        Integer result = r.nextInt(adjectives.size());
        return adjectives.get(result);
    }

    public App() {
        frame = new JFrame();

        JTextField textField = new JTextField();
        textField.setSize(300, 50);

        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel2.setLayout(new GridLayout(0,1));
        panel2.add(scroll);

        JButton button = new JButton("Get Adjectives Starting With Given Letter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String text = "";
                if (textField.getText().isEmpty()) {
                    c = ' ';
                } else {
                    c = textField.getText().charAt(0);
                }
                List<String> results = getAdjectives();
                for (String s : results) {
                    text += s;
                    text += "\n";
                }
                textArea.setText(text);
                textArea.updateUI();
            }
        });

        JButton randomButton = new JButton("Get Random");
        randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                textArea.setText(getRandom());
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(textField);
        panel.add(button);
        panel.add(randomButton);

        frame.add(panel, BorderLayout.BEFORE_FIRST_LINE);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Adjective Generator");
        frame.pack();
        frame.setSize(1024,960);
        frame.setVisible(true);
    }
}

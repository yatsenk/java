package Java2L4;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
	new Chat();
    }
}


class Chat extends JFrame{
    JTextArea history; // окно истории чата
    JTextField inputField; // поле ввода
    JTextField info; // поле информации о чате

    String emptyName = "Ваш никнейм не введен"; // пустое имя клиента
    String name = emptyName; // имя клиента
    String title = "Just chat"; // название чата
    String historyTitle = "История чата \n";
    String initName = "Введите свое имя";
    String invitation = "Введите сообщение";

    public Chat() throws HeadlessException {

        setTitle(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500,200,400,600);

        //главная панель чата (похожедля верстки не нужна, но для логики пусть будет)
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        //информация вверху главной панели
        info = new JTextField(name);
        info.setEditable(false);
        info.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(info, BorderLayout.NORTH);

        //история по центру главной панели
        history = new JTextArea(historyTitle);
        history.setEditable(false);
        history.setLineWrap(true);
        //панель прокрутки для истории (экспериментально и так работает)
        JScrollPane textPane = new JScrollPane(history);
        mainPanel.add(textPane, BorderLayout.CENTER);

        //нижняя панель поверх главной панели
        JPanel bottomPanel = new JPanel(new BorderLayout());
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);

        //поле ввода по центру нижней панели
        inputField = new JTextField(initName);
        bottomPanel.add(inputField, BorderLayout.CENTER);

        //кнопка отправки
        JButton inputButton = new JButton("Отправить");
        bottomPanel.add(inputButton, BorderLayout.EAST);

        //обработчик кнопки отправки
        inputButton.addActionListener(e -> sendMessage());

        //обработчик enter в поле отправки
        inputField.addActionListener(e -> sendMessage());

        //поле ввода - в фокусе
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals(invitation) || inputField.getText().equals(initName))
                    inputField.setText("");
            }
        });

        //поле ввода - не в фокусе
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().equals("") && !name.equals(emptyName)) inputField.setText(invitation);
                else if (inputField.getText().equals("") && name.equals(emptyName)) inputField.setText(initName);
            }
        });


        //рисуем чат
        setVisible(true);
    }

    private void sendMessage(){
        String message = inputField.getText();
        if (!message.isEmpty() && !message.equals(initName) && !message.equals(invitation))
            if (name.equals(emptyName)){
                name = message;
                info.setText(name);
                history.setText(history.getText() + name + " вошел в чат." + "\n");
            } else {
                history.setText(history.getText() + name + ": " + message + "\n");
            }
        inputField.setText("");
        inputField.grabFocus();
    }
}

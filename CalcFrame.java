import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CalcFrame extends JFrame implements ActionListener {
    JLabel label;
    JLabel history;
    boolean state = false;
    double num1, num2;
    double result;
    String func="";
    String nlnput="";

    String[] btn = {
            "%","CE","C","<-",
            "1/x","x^2","√","/",
            "7","8","9","X",
            "4","5","6","-",
            "1","2","3","+",
            "+/-","0",".","="
    };

    public CalcFrame(){
        setBounds(100,100,500,600);
        setLayout(new BorderLayout());
        setTitle("Calculator");     //Title 지정
        setResizable(false);

        //GUI icon 변경
        ImageIcon icon = new ImageIcon("D:\\Java\\GuiCalProject\\Image\\calculator.png");
        setIconImage(icon.getImage());
        setVisible(true);

        //결과 창
        label = new JLabel("0", JLabel.RIGHT);

        JPanel mainView = new JPanel();
        label.setFont(new Font("궁서체",Font.BOLD,50));
        label.setBackground(Color.WHITE);
        label.setOpaque(true);              //배경색 적용 불투명 설정

        //Button 창
        JPanel btnView = new JPanel();
        btnView.setLayout(new GridLayout(6, 4, 5, 5));
        JButton[] button = new JButton[btn.length];

        //history 창
        history = new JLabel("--");
        this.add(history,BorderLayout.SOUTH);

        for(int i=0;i<btn.length;i++){
            button[i] = new JButton(btn[i]);
            button[i].setFont(new Font("궁서체",Font.BOLD,25));
            button[i].addActionListener(this);

            if(i==23)
                button[i].setForeground(Color.BLUE);

            btnView.add(button[i]);
        }

        mainView.setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(btnView,BorderLayout.CENTER);
        add(history,BorderLayout.SOUTH);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        switch (input) {
            case "+":
                num1=num2;
                func = "+";
                nlnput = "";
                break;
            case "0":
                if (label.getText().equals("0"))
                    label.setText(e.getActionCommand());
                else label.setText(label.getText() + e.getActionCommand());
                break;
            case "-":
                num1 = num2;
                func = "-";
                nlnput = "";
                break;
            case "X":
                num1 = num2;
                func = "*";
                nlnput = "";
                break;
            case "/":
                num1 = num2;
                func = "/";
                nlnput = "";
                break;
            case "%":
                num1 = num2;
                func = "%";
                nlnput = "";
                result = num1 / 100;
                label.setText(String.valueOf(result));
                break;
            case "√":
                num1 = num2;
                func = "√";
                nlnput = "";
                result = Math.sqrt(num1);
                label.setText(String.valueOf(result));
                break;
            case "x^2":
                num1 = num2;
                func = "x^2";
                nlnput = "";
                result = num1 * num1;
                label.setText(String.valueOf(result));
                break;
            case "1/x":
                num1 = num2;
                func = "1/x";
                nlnput = "";
                result = 1 / num1;
                label.setText(String.valueOf(result));
                break;
            case "+/-":
                num1 = num2;
                func = "+/-";
                if (!label.getText().equals("0")) {
                    if (!label.getText().contains("-"))
                        label.setText("-" + label.getText());
                    else label.setText(label.getText().substring(1));
                }
                break;
            case "C":
                nlnput = "";
                num2 = 0;
                num1 = 0;
                label.setText("0");
                break;
            case"CE":
                nlnput="";
                label.setText("0");
            case "<--":
                if(getBackSpace().length()<1){
                    nlnput="";
                    num2=0;
                    num1=0;
                    label.setText("0");
                }
                else
                    setBackSpace(getBackSpace().substring(0,getBackSpace().length()-1));
                break;
            case ".":
                if (!label.getText().contains("."))
                    label.setText(label.getText() + ".");
                break;
            case "=":
                switch (func) {
                    case "+":
                        result = num1 + num2;
                        label.setText(String.valueOf(result)); //결과값을 문자열로 반환하여 결과창에 출력

                        state = true; // 결과 값이 나온 후 다음 입력이 들어왔을 때 화면에 표시된 결과 값을 지운다.


                        break;
                    case "-":
                        result = num1 - num2;
                        label.setText(String.valueOf(result));
                        state = true;

                        break;
                    case "*":
                        result = num1 * num2;
                        label.setText(String.valueOf(result));
                        state = true;

                        break;
                    case "/":
                        result = num1 / num2;
                        label.setText(String.valueOf(result));
                        state = true;

                        break;
                }
                break;
            default:
                if (state) {
                    label.setText("0");
                    state = false;
                    num1 = 0;
                    num2 = 0;
                    nlnput = "";
                }
                nlnput += e.getActionCommand();
                label.setText(nlnput);
                num2 = Double.parseDouble(nlnput);//문자열을 double로 변환

                break;
        }
    }

    private void setBackSpace(String bs) {
        label.setText(bs);
    }
    private String getBackSpace() {
        return label.getText();
    }


    public static void main(String[] args){
        new CalcFrame();
    }
}

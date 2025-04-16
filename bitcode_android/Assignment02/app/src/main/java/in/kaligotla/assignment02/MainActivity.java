package in.kaligotla.assignment02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private long lastClearClickTime = 0;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSubtract, btnMultiply, btnDivide, btnClear, btnEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        initViews();
        initListeners();
    }

    public void initViews() {
        display = findViewById(R.id.display);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnClear = findViewById(R.id.btnClear);
        btnEquals = findViewById(R.id.btnEquals);
    }

    private void initListeners() {
        ButtonClickListener btnClickListener = new ButtonClickListener();
        btn0.setOnClickListener(btnClickListener);
        btn1.setOnClickListener(btnClickListener);
        btn2.setOnClickListener(btnClickListener);
        btn3.setOnClickListener(btnClickListener);
        btn4.setOnClickListener(btnClickListener);
        btn5.setOnClickListener(btnClickListener);
        btn6.setOnClickListener(btnClickListener);
        btn7.setOnClickListener(btnClickListener);
        btn8.setOnClickListener(btnClickListener);
        btn9.setOnClickListener(btnClickListener);
        btnAdd.setOnClickListener(btnClickListener);
        btnSubtract.setOnClickListener(btnClickListener);
        btnMultiply.setOnClickListener(btnClickListener);
        btnDivide.setOnClickListener(btnClickListener);
        btnClear.setOnClickListener(btnClickListener);
        btnEquals.setOnClickListener(btnClickListener);
    }

    private void mathOperation() {
        String expression = display.getText().toString();
        if (expression.isEmpty()) return;
        try {
            int result = evaluateExpression(expression);
            display.append(" = "+result);
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    private int evaluateExpression(String expression) {
        int result = 0;
        char operator = ' ';
        String[] tokens = expression.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        if (tokens.length < 3) return Integer.parseInt(tokens[0]);
        result = Integer.parseInt(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            operator = tokens[i].charAt(0);
            double num = Double.parseDouble(tokens[i + 1]);
            switch (operator) {
                case '+': result += num; break;
                case '-': result -= num; break;
                case '*': result *= num; break;
                case '/':
                    if (num == 0) throw new ArithmeticException("Division by zero");
                    result /= num;
                    break;
            }
        }
        return result;
    }

    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn0) {
                display.setText("0");
            } else if(v.getId() == R.id.btn1) {
                display.append("1");
            } else if(v.getId() == R.id.btn2) {
                display.append("2");
            } else if(v.getId() == R.id.btn3) {
                display.append("3");
            } else if(v.getId() == R.id.btn4) {
                display.append("4");
            } else if(v.getId() == R.id.btn5) {
                display.append("5");
            } else if(v.getId() == R.id.btn6) {
                display.append("6");
            } else if(v.getId() == R.id.btn7) {
                display.append("7");
            } else if(v.getId() == R.id.btn8) {
                display.append("8");
            } else if(v.getId() == R.id.btn9) {
                display.append("9");
            } else if(v.getId() == R.id.btnAdd) {
                display.append("+");
            } else if(v.getId() == R.id.btnSubtract) {
                display.append("-");
            } else if(v.getId() == R.id.btnMultiply) {
                display.append("*");
            } else if(v.getId() == R.id.btnDivide) {
                display.append("/");
            } else if(v.getId() == R.id.btnClear) {
                lastClearClickTime++;
                if(lastClearClickTime>1) {
                    display.setText("");
                    Toast.makeText(MainActivity.this, "History Cleared", Toast.LENGTH_LONG).show();
                    lastClearClickTime = 0;
                }
                Toast.makeText(MainActivity.this, "Click clear again to delete history", Toast.LENGTH_SHORT).show();
            } else if(v.getId() == R.id.btnEquals) {
                mathOperation();
            }
        }
    }
}
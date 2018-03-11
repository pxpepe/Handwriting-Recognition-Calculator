package com.pxpepe.handwritingrecognition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtOp;
    private final static String opValidas = "/*-+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOp = findViewById(R.id.txtOp);

        txtOp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String opInserida = charSequence.toString();
                TextView txtErro = findViewById(R.id.txtErro);
                txtErro.setText("");

                if (opInserida.toUpperCase().indexOf('T') >= 0) {
                    txtOp.setText("+");
                } else if (opInserida.toUpperCase().indexOf('X') >= 0) {
                    txtOp.setText("*");
                } else if (opInserida.trim() != ""
                        && (opValidas.indexOf(opInserida) == -1
                            || opInserida.length()>1)) {
                    txtOp.setText("");
                    txtErro.setText("A operação deve ser válida (/,*,-,+).");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

    }

    public void pulsarBtnOper(View vistaBtn) {

        EditText editEsq = findViewById(R.id.numEsq);
        EditText editOp = findViewById(R.id.txtOp);
        EditText editDir = findViewById(R.id.numDir);
        TextView txtErro = findViewById(R.id.txtErro);

        String txtEsq = editEsq.getText().toString();
        String txtOp = editOp.getText().toString();
        String txtDir = editDir.getText().toString();

        if (txtEsq.trim()!=""
                && txtOp.trim()!=""
                && txtOp.length()==1
                && opValidas.indexOf(txtOp.trim()) >= 0
                && txtDir.trim()!="") {

            Double resultado = 0.0d;
            Double numEsq = Double.valueOf(txtEsq);
            Double numDir = Double.valueOf(txtDir);

            switch (txtOp.charAt(0)) {
                case '/':
                    resultado = numEsq/numDir;
                    break;
                case '*':
                    resultado = numEsq*numDir;
                    break;
                case '-':
                    resultado = numEsq-numDir;
                    break;
                case '+':
                    resultado = numEsq+numDir;
                    break;

            }

            TextView txtVisor = findViewById(R.id.txtVisor);
            txtVisor.setText(String.valueOf(resultado));

            txtErro.setText("");
        } else {
            txtErro.setText("Deve preencher os dois números e a operação, sendo que esta deve ser válida (/,*,-,+).");
        }

    }


}

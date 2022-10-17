package com.example.sqltask14oct22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText idTx,nameTx, brandTx,priceTx,reviewTx ;
    Button addBTN, delBTN, viewTN,updateBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idTx=findViewById(R.id.editTextTextPersonName);
        nameTx=findViewById(R.id.editTextTextPersonName2);
        brandTx=findViewById(R.id.editTextTextPersonName3);
        priceTx=findViewById(R.id.editTextTextPersonName4);
        reviewTx=findViewById(R.id.editTextTextPersonName5);

        addBTN=findViewById(R.id.button);
        updateBTN=findViewById(R.id.button2);
        delBTN=findViewById(R.id.button3);
        viewTN=findViewById(R.id.button4);

        mydb = new DatabaseHelper(this);

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsterted = mydb.addProducts(nameTx.getText().toString(),brandTx.getText().toString()
                        ,priceTx.getText().toString(),reviewTx.getText().toString());

                if(isInsterted ==true){
                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = mydb.updateProducts(idTx.getText().toString(),nameTx.getText().toString(),brandTx.getText().toString()
                        ,priceTx.getText().toString(),reviewTx.getText().toString());

                if(isUpdated ==true){
                    Toast.makeText(MainActivity.this, "Data is updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isdeleted = mydb.deleteProducts(idTx.getText().toString());

                if(isdeleted >0){
                    Toast.makeText(MainActivity.this, "Data has deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data has not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mydb.viewProducts();

                if(res.getCount()==0){
                    ShowMessage("Error", "No products found");
                    return;
                }

                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID " + res.getString(0) + " \n ");
                    buffer.append("Name " + res.getString(1) + " \n ");
                    buffer.append("Brand " + res.getString(2) + " \n ");
                    buffer.append("Price " + res.getString(3) + " \n ");
                    buffer.append("Review " + res.getString(4) + " \n ");
                }
                ShowMessage("Data", buffer.toString());
            }
        });
    }

    public void ShowMessage(String error, String s){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(error);
        builder.setCancelable(true);
        builder.setMessage(s);
        builder.show();
    }
}
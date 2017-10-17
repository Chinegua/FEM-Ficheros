package com.example.chinegua.fem_files;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

        //Añadir menu contextual
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.guardar) {
            EditText textoId = (EditText) findViewById(R.id.et);
            String value = textoId.getText().toString();
            Log.i("MiW",value);
            try {
                FileOutputStream fos = openFileOutput("dialog.txt", Context.MODE_APPEND);
                fos.write(value.getBytes());
                fos.write('\n');
                fos.close();
                Toast.makeText(this,"Guardado", Toast.LENGTH_SHORT).show();

            }
            catch (Exception ex)
            {
                Toast.makeText(this,"Error al guardar", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        if (id == R.id.recuperar) {

            TextView tv = (TextView) findViewById(R.id.tv);

            try {
                BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("dialog.txt")));
                String linea = fin.readLine();

                //Toast.makeText(this,"Done", Toast.LENGTH_SHORT).show();

                while (linea != null) {
                    tv.append(linea+'\n');
                    linea = fin.readLine();


                }

                fin.close();
            }
            catch (Exception e){
                Toast.makeText(this,"Error al recuperar", Toast.LENGTH_SHORT).show();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

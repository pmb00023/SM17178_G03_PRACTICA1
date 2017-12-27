package es.ujaen.git.sm17178_g03_practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    public static final String PARAM_USER = "param_user";
    public static final String PARAM_PASS = "param_pass";
    public static final String PARAM_IP = "param_ip";
    public static final String PARAM_PORT = "param_port";

    Button siguiente;

//Aqui realizamos la bienvenida a la aplicación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        final String user=getIntent().getStringExtra(PARAM_USER);
        String pass=getIntent().getStringExtra(PARAM_PASS);
        String ip = getIntent().getStringExtra(PARAM_IP);
        short port = getIntent().getShortExtra(PARAM_PORT, (short)6000);

//Al pulsar el boton nos lanza a la siguiente actividad,en este caso el MainActivity
        siguiente = (Button)findViewById(R.id.initial_button);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(ServiceActivity.this, MainActivity.class);
                siguiente.putExtra(PARAM_USER,user);
                startActivity(siguiente);
            }
        });


        //Toast.makeText(getApplicationContext()," "+user+"\n Con direccion Ip"+ip,Toast.LENGTH_LONG).show();







        //TextView title = (TextView) findViewById(R.id.textView);
        //Toast.makeText(this,"Hola, esto proviene del service activity"+user, Toast.LENGTH_SHORT).show();
    }




}

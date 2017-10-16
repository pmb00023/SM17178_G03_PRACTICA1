package es.ujaen.git.sm17178_g03_practica1;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param_ip";
    private static final String ARG_PARAM2 = "param_port";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;


    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String ip, int port) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, ip);
        args.putInt(ARG_PARAM2, port);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_login, container, false);

        Button connect = (Button) fragment.findViewById(R.id.button_login);
        final EditText user = (EditText) fragment.findViewById(R.id.editText_login_user);
        final EditText pass = (EditText) fragment.findViewById(R.id.editText_login_password);
        final EditText ip = (EditText) fragment.findViewById(R.id.editText_login_ip);
        final EditText port = (EditText) fragment.findViewById(R.id.editText_login_port);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_user = user.getText().toString();
                String s_pass = pass.getText().toString();
                String s_ip = ip.getText().toString();
                String s_port = port.getText().toString();

                short port2=0;

                try{
                    port2= Short.parseShort(s_port);
                }catch (java.lang.NumberFormatException ex){
                    port2= 6000;
                }

                ConnectionUserData data = new ConnectionUserData(
                        s_user,s_pass,s_ip,port2
                );
                Toast.makeText(getContext(),"Hola "+s_user+" "+s_pass+" "+s_ip+":"+port2,Toast.LENGTH_LONG).show();
                TareaAutentica tarea = new TareaAutentica();
                tarea.execute(data);

                /*Intent nueva = new Intent(getActivity(),ServiceActivity.class); //Prepara la actividad, se puede llamar tambien a otras aplicaciones
                nueva.putExtra(ServiceActivity.PARAM_USER,data.getUser());
                nueva.putExtra("param_pass",data.getPass());
                nueva.putExtra("param_ip",data.getConnectionIP());
                nueva.putExtra("param_port",data.getConnectionPort());
                //Hacerlo mejor con un bundle
                startActivity(nueva);*/
            }
        });

        return fragment;
    }

    public class TareaAutentica extends AsyncTask<ConnectionUserData,Void,String> {
        private ConnectionUserData data;
        public String doInBackground(ConnectionUserData... param){ //Los tres puntos es de java y significa que param puede ser un array
            if(param!=null)
                if(param.length>=1)
                    data=param[0];
            //TODO proceso de autenticación
            return "OK";//OK si la operacion fue correcta y si no otro valor
        }

        public void onPostExecute(String result){

            if(result.compareToIgnoreCase("OK")==0) {
                Intent nueva = new Intent(getActivity(), ServiceActivity.class);
                nueva.putExtra(ServiceActivity.PARAM_USER, data.getUser());
                nueva.putExtra("param_pass", data.getPass());
                nueva.putExtra("param_ip", data.getConnectionIP());
                nueva.putExtra("param_port", data.getConnectionPort());
                startActivity(nueva);
            }else
            {
                Toast.makeText(getContext(),"Error autenticando a "+data.getUser(),Toast.LENGTH_LONG).show();
            }
        }
    }

}

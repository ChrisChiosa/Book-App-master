package bookapp.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by anthony on 3/31/15.
 */
public class Client extends AsyncTask<String, Void, String>
{
    String msg;

    protected String doInBackground(String... msg)
    {
        final int PORT = 2709;
        String host = "129.3.208.119";//"129.3.211.143";//"129.3.171.200";//
        String results = "";

        Socket tcp = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try
        {
            tcp = new Socket(host, PORT);
            out = new PrintWriter(tcp.getOutputStream());
            in = new BufferedReader(new InputStreamReader(tcp.getInputStream()));
            //for(String s : msg)
            //{
                Log.e("SENDING% ", msg[0]);
                out.println(msg[0]);
                out.flush();
            //}
            results = in.readLine();
            Log.e("GOT% ", results);
            out.close();
            in.close();
            tcp.close();

            return results;
        }
        catch(IOException e)
        {

            Log.e("ERROR: ", e.getMessage());
        }
            return "error";
    }


    protected void onPostExecute(String s)
    {
    }
}

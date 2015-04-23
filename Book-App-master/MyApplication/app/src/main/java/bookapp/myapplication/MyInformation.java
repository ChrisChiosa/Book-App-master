package bookapp.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;


public class MyInformation extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);

        Button searchButton = (Button) findViewById(R.id.myInfoButton);
        searchButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                String txt = "GET%" + ((EditText) findViewById(R.id.editEmail)).getText().toString() +
                             "|" + ((EditText) findViewById(R.id.editName)).getText().toString() + "|" +
                             ((EditText) findViewById(R.id.editPhone)).getText().toString();
                MyProperties.getInstance().email = ((EditText) findViewById(R.id.editEmail)).getText().toString();
                Intent nextScreen = new Intent(getApplicationContext(), MyListings.class);

                String newTxt = getMessage(txt);
                nextScreen.putExtra("list", newTxt);
                Log.e("!", MyProperties.getInstance().email);
                Log.e("n", newTxt);

                startActivity(nextScreen);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getMessage(String msg)
    {
        Client c = new Client();
        try
        {
            c.execute(msg);
            return c.get();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        return "error";
    }
}

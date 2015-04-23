package bookapp.myapplication;

/**
 * Created by anthony on 4/16/15.
 */
public class MyProperties
{
    private static MyProperties inst = null;
    public String email = "bob@example.com";

    protected MyProperties(){}

    public static synchronized MyProperties getInstance()
    {
        if(null == inst)
            inst = new MyProperties();
        return inst;
    }
}

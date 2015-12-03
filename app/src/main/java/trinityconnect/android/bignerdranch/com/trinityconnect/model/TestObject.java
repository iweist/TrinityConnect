package trinityconnect.android.bignerdranch.com.trinityconnect.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by owner on 12/1/2015.
 */
@ParseClassName("TestObject")
public class TestObject  extends ParseObject{
    String field1;
    String field2;

    int someint;

    public TestObject(String field1, String field2){
        setField1(field1);
        setField2(field2);

    }

    public TestObject(){


    }

    public int getSomeint() {
        return getInt("someint");
    }

    public void setSomeint(int someint) {
        put("someint", someint);
    }

    public String getField1() {
        return getString("field1");
    }

    public void setField1(String field1) {
        put("field1", field1);
    }

    public String getField2() {
        return getString("field2");
    }

    public void setField2(String field2) {
        put("field2", field2);
    }
}

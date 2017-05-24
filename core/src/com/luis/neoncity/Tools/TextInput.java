package com.luis.neoncity.Tools;

import com.badlogic.gdx.Input;

/**
 * Created by Zach on 5/17/2017.
 */

public class TextInput implements Input.TextInputListener {

    String input = "null";

    @Override
    public void input (String text) {
    input = text;
    }

    @Override
    public void canceled () {
    }

    public String getInput()
    {
        return input;
    }

    public Boolean ifNull()
    {
        if(input.equals("null"))
            return true;
        else
            return false;
    }
}

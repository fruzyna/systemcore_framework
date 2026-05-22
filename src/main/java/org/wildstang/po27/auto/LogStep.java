package org.wildstang.po27.auto;

import org.wildstang.framework.auto.AutoStep;
import org.wildstang.framework.logger.Log;

/**
 * AutoStep which prints out a given message.
 */
public class LogStep extends AutoStep {

    private String mMessage;

    public LogStep(String pMessage) {
        super();

        mMessage = pMessage;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void update() {
        Log.info(mMessage);
        setFinished();
    }

    @Override
    public String getName() {
        return "Log: " + mMessage;
    }
    
}

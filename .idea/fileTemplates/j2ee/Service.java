/*
 *      Created by:     ${USER}
 *      File Name:      ${NAME}
 *      Created on:     ${DATE} ${TIME}
 *      File Name:      ${PACKAGE_NAME}.${NAME}
 *      Additional Note: 
 */

package ${PACKAGE_NAME};

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

#parse("File Header.java")
public class ${NAME} extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

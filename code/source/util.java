

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.passman.PasswordManagerException;
import com.wm.util.security.WmSecureString;
import com.wm.app.b2b.server.OutboundPasswordManager;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void getMethis (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getMethis)>> ---
		// @sigtype java 3.5
		// [i] field:0:required pkgName
		// [i] field:0:required alias
		// [i] field:0:required key
		// [o] field:0:required secret
		String pkgName = ValuesEmulator.getString(pipeline, "pkgName");
		String alias = ValuesEmulator.getString(pipeline, "alias");
		String key = ValuesEmulator.getString(pipeline, "key");
		String inHandle = "com.webmethods.cloudstream." + alias + "." + key;
		System.out.println("inHandle: " + inHandle);
		try {
			WmSecureString retrievePassword = OutboundPasswordManager.retrievePassword(pkgName, inHandle);
			if(retrievePassword == null) {
				System.out.println("Got no result with pacakge as : " + pkgName);
				retrievePassword = OutboundPasswordManager.retrievePassword(null, inHandle);
			}
			if(retrievePassword != null){
				String secret = retrievePassword.toString();
				System.out.println("Got this secret: " + secret);
				ValuesEmulator.put(pipeline, "secret", secret);
			}
		}
		catch (PasswordManagerException e) {
			e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}
}


package DATA.Avm_Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class App {
    public static void main( String[] args ) throws IOException, ParseException, InterruptedException, ExecutionException{
    	
    	FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/avm_rehberi_firebase_admin_keys.json");   // admin keys_json
    	FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://avm-rehberi-default-rtdb.firebaseio.com")
				  .build();
		FirebaseApp.initializeApp(options);
		
		FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/avm_data.json");   // our data_json to firebase
    	JSONParser jsonParser = new JSONParser();
    	JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(data, StandardCharsets.UTF_8));
       	
    	Firestore db = FirestoreClient.getFirestore();
    	ApiFuture<WriteResult> result = db.collection("avm").document("avmler").set(jsonObject);
    	result.get();
    	
    	System.out.println("result     : " + result.isDone());
    }
}